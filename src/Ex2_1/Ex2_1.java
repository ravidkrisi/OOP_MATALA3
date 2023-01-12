package Ex2_1;

import java.io.*;
import java.util.Random;
import java.util.concurrent.*;

public class Ex2_1
{
    /**
     * This method creat text files with random number of lines
     * @param n Number of text files to creat
     * @param seed Starting seed for num of lines
     * @param bound max num of lines in each file
     * @return An array with the names of each file
     */
    public static String[] createTextFiles(int n, int seed, int bound)
    {
        Random rand = new Random(seed);
        String s = "Hello World";
        String [] nameOfFiles = new String[n];

        for (int i = 0; i <n; i++)
        {
            File file1 = new File("./file_"+(i+1)+".txt");
            int numOfLines = rand.nextInt(bound);

            try
            {
                file1.createNewFile();
                nameOfFiles[i] = file1.toString();
                FileWriter fw = new FileWriter(file1.getAbsolutePath());
                PrintWriter pw = new PrintWriter(fw);
                for (int j=0; j<numOfLines; j++)
                {
                    pw.println(s);
                }
                pw.close();
                fw.close();
            }
            catch (IOException e)
            {
                System.err.println("ERROR");
            }
        }
        int c = getNumOfLines(nameOfFiles);
        return nameOfFiles;
    }

    /**
     * This method reads the number of lines in given array of text files names
     * @param nameOfFiles An array that contains the names of each file
     * @return the number of total lines
     */
    public static int getNumOfLines(String[] nameOfFiles)
    {
       int sum = 0;
       int lines = 0;
       for (int i=0; i<nameOfFiles.length; i++)
       {
           try
           {
               FileReader fr = new FileReader(nameOfFiles[i]);
               lines = 0;
               BufferedReader reader = new BufferedReader(fr);
               while (reader.readLine() != null) lines++;
               reader.close();
           }
           catch (IOException e)
           {
               System.err.println("ERROR");
           }
           sum = sum+lines;
       }

        return sum;
    }

    /**
     * This method reads the number of lines of given array, using threads.
     * @param fileNames An array that contains the names of each file
     * @return the number of total lines
     * @throws InterruptedException
     */
    public static int getNumOfLinesThreads(String[] fileNames) throws InterruptedException
    {
        int total=0;
        MyThread[] thread_pool = new MyThread[fileNames.length];
        for(int i=0; i < thread_pool.length; i++)
        {
            thread_pool[i] = new MyThread(fileNames[i]);
        }

        for(MyThread t: thread_pool)
        {
            t.start();
        }

        for (MyThread t: thread_pool)
        {
            t.join();
        }

        for(MyThread t: thread_pool)
        {
            total+=t.lines;
        }
        return total;
    }

    /**
     * This method reads the number of lines of given array, using a thread pool.
     * @param fileNames An array that contains the names of each file
     * @return the number of total lines
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static int  getNumOfLinesThreadPool(String[] fileNames) throws ExecutionException, InterruptedException
    {
        ExecutorService excecutor = Executors.newFixedThreadPool(fileNames.length);
        int total_lines=0;
        Future <Integer>[] threads_future = new Future[fileNames.length];
        int i=0;

        for (String file: fileNames)
        {
            MyThreadPool t = new MyThreadPool(file);
            threads_future[i++] = excecutor.submit(t);
        }

        for(Future<Integer> t: threads_future)
        {
            total_lines+=t.get();
        }
        excecutor.shutdown();
        return total_lines;
    }
}

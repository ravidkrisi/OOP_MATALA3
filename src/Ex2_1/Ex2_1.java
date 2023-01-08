package Ex2_1;

import java.io.*;
import java.util.Random;
import java.util.concurrent.*;

public class Ex2_1
{
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

    public static int  getNumOfLinesThreadPool(String[] fileNames) throws ExecutionException, InterruptedException {
        ExecutorService excecuter = Executors.newFixedThreadPool(fileNames.length);
        int total_lines=0;
        for (String file: fileNames)
        {
            MyThreadPool t = new MyThreadPool(file);
            total_lines += excecuter.submit(t).get();
        }
        excecuter.shutdown();
        return total_lines;
    }

}

package Ex2_1;

import java.io.*;
import java.util.Random;

public class Ex2_1
{
    public static String[] createTextFiles(int n, int seed, int bound)
    {
        Random rand = new Random(seed);
        String s = "Hello World";
        String [] nameOfFiles = new String[n];

        for (int i = 0; i <n; i++)
        {
            File file1 = new File("C:\\Users\\mayaz\\INTELIIJ\\TEST"+"\\file_"+(i+1)+".txt");
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

           //System.out.println("num of lines" + lines);
          //System.out.println(sum);

       }

        return sum;
    }

    public int getNumOfLinesThreads(String[] fileNames)
    {

    }



}

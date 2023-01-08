package Ex2_1;

import Ex2_1.Ex2_1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException, ExecutionException {

        int files_number = 1000;
        Ex2_1.createTextFiles(files_number,3,100);
        String[] files = new String[files_number];

        for(int i=1; i<=files_number;i++)
        {
            files[i-1] ="file_"+i+".txt";
        }

        long start = 0;
        long end = 0;
        int lines =0;

        start = System.currentTimeMillis();
        lines = Ex2_1.getNumOfLines(files);
        end = System.currentTimeMillis();
        System.out.println("no thread took:"+(end-start)+" num of lines:"+lines);

        start = System.currentTimeMillis();
        lines = Ex2_1.getNumOfLinesThreads(files);
        end = System.currentTimeMillis();
        System.out.println("threads took:"+(end-start)+" num of lines:"+lines);

        start = System.currentTimeMillis();
        lines = Ex2_1.getNumOfLinesThreadPool(files);
        end = System.currentTimeMillis();
        System.out.println("thread pool took:"+(end-start)+" num of lines:"+lines);

    }
}
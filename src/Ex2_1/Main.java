package Ex2_1;

import Ex2_1.Ex2_1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
//        Ex2_1.createTextFiles(2,3,100);
        String[] files = {"file_1.txt", "file_2.txt"};
        int lines = Ex2_1.getNumOfLinesThreads(files);
        System.out.println(lines);
//        BufferedReader reader = new BufferedReader(new FileReader("file_1.txt"));
//        System.out.println(reader.readLine());
    }
}
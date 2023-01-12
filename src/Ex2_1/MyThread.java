package Ex2_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MyThread extends Thread
{
    public String file_name;
    public int lines;

    /**
     * Constructor of the class.
     * @param file_name string representing the name of the file
     */
    public MyThread(String file_name)
    {
        this.file_name = file_name;
    }

    /**
     * Run method
     */
    public void run()
    {
        int lines = 0;
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(this.file_name));
            while (reader.readLine() != null) lines++;
            reader.close();
        }
        catch (IOException e)
        {
            System.err.println("ERROR");
        }
        this.lines = lines;
    }
}



package Ex2_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;

public class MyThreadPool implements Callable <Integer>
{

    private String file_name;

    public MyThreadPool(String file_name)
    {
        this.file_name = file_name;
    }

    @Override
    public Integer call() throws Exception
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
        return lines;
    }
}

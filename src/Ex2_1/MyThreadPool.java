package Ex2_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;

public class MyThreadPool implements Callable <Integer>
{
    private String file_name;

    /**
     * Constructor
     * @param file_name String representing the name of the file
     */
    public MyThreadPool(String file_name)
    {
        this.file_name = file_name;
    }

    /**
     * Call method
     * @return total number of lines read from the file.
     * @throws Exception
     */
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

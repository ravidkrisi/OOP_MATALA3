package Ex2_2;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class AdapterToTask <T> extends FutureTask <T> implements Comparable<AdapterToTask<T>>
{

    private Callable <T> task;
    private int priority;
    /**
     * a constructor
     * @param call the task we want to do
     */
    public AdapterToTask(Callable<T> call, int priority) {
        super(call);
        this.task = call;
        this.priority = priority;
    }

    /**
     * method to get the Priority of the task by casting the task to Task type
     * @return the value of the priority
     */
    public int getPriority()
    {
        return this.priority;
    }

    /**
     *compare the instance and another one using by casting it to Task type
     * @param obj the object we want to compare
     * @return 1 if the instance is bigger priority, 0 for equal priority, -1 for smaller priority
     */
    @Override
    public int compareTo(AdapterToTask<T> obj)
    {
        int local = this.getPriority();
        int other = obj.getPriority();
        if (local > other)
        {
            return 1;
        }
        else if(other > local)
        {
            return -1;
        }
        return 0;
    }
}

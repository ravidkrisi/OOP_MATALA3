package Ex2_2;

import java.util.concurrent.*;

public class CustomExecutor extends ThreadPoolExecutor
{
    //the list of prioirties
    int[] list_priorities = new int[10];
    /**
     * a consturctor to CustomExecutor
     */
    public CustomExecutor()
    {
        super((Runtime.getRuntime().availableProcessors()/2), (Runtime.getRuntime().availableProcessors()-1),
                300, TimeUnit.MILLISECONDS, new PriorityBlockingQueue<>());

    }

    /**
     * this method submits the task in the Treadpool and return the Future
     * @param t the task we want to execute
     * @return Future holds the thread executing the task we passed
     */

    public <T> Future <T> submit(Task <T> t)
    {
        list_priorities[t.getPriority()-1]++;
        AdapterToTask <T> task = new AdapterToTask<>(t.getTask(), t.getPriority());
        super.execute(task);
        return task;
    }

    /**
     *this method create the task and submits the task to ThreadPool and return the Future
     * @param t callable holding the function we want to execute
     * @param pr holds the tasktype
     * @return Future holds the thread excuting the task we passed
     */
    public <T> Future <T> submit(Callable <T> t, TaskType pr)
    {
        Task task = Task.CreateTask(t, pr);
        return submit(task);
    }

    /**
     *this method create the task and submits the task to ThreadPool and return the Future
     * @param t callable holding the function we want to execute
     * @return Future holds the thread excuting the function we passed
     */
    public <T> Future <T> submit(Callable <T> t)
    {
        Task task = Task.CreateTask(t);
        return submit(task);
    }

    /**
     * this method returning the highest priority number(which is the lowest)
     * the currently is the priorityBolcking queueu
     * @return the max priorty number in the queue
     */
    public int getCurrentMax()
    {
        for(int i=0;i<10;i++)
        {
            if(list_priorities[i]>0)
            {
                return i+1;
            }
        }
        return 10;
    }

    /**
     * this method decrease the list of priorities in
     * @param t The thread that will run task r
     * @param r The task that will be executed
     */
    @Override
    protected void beforeExecute(Thread t, Runnable r)
    {
        list_priorities[(((AdapterToTask)r).getPriority())-1]--;
    }

    public void Terminate()
    {
        super.shutdown();
    }







}

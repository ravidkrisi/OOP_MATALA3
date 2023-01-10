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
    public <T> Future <T> submit(Task t)
    {
        list_priorities[t.getPriority()]++;
        Future <T> f = this.submit(t);
        return f;
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









}

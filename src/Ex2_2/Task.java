package Ex2_2;

import java.util.concurrent.Callable;

public class Task <T> implements Comparable <Task<T>>, Callable <T>
{
    private TaskType task_type;
    private Callable <T> task;

    /**
     * a constructor
     * @param task_type the type of task
     * @param task the task we want to do
     */
    private Task(TaskType task_type, Callable <T> task)
    {
        this.task_type = task_type;
        this.task = task;
    }

    /**
     * a defaultive constructor
     * @param task the task we want to do
     */
    private Task(Callable <T> task)
    {
        this.task = task;
        this.task_type = TaskType.OTHER;
    }

    /**
     * the public defaultive "constructor"
     * @param task the task we want to do
     * @return the instance of the Task we created
     */
    public static Task createTask(Callable task)
    {
        return new Task(task);
    }

    /**
     * the public "constructor"
     * @param task the task we want to do
     * @param task_type the type of task we want to do
     * @return the instance of the Task we created
     */
    public static Task createTask(Callable task, TaskType task_type)
    {
        return new Task(task_type, task);
    }

    /**
     * getter for taskType priority
     * @return the priority of the instance of Task
     */
    public int getPriority()
    {
        return this.task_type.getPriorityValue();
    }


    /**
     * running the task callable method
     * @return the result the callable method
     * @throws Exception
     */
    @Override
    public T call() throws Exception {
        return this.task.call();
    }

    /**
     * this method will compare the priority between objects
     * @param obj the object we will compare with our instance
     * @return 1 if our instance is in bigger priority, 0 for equel, -1 for smaller
     *
     */
    @Override
    public int compareTo(Task<T> obj)
    {
        int local = this.getPriority();
        int other = obj.getPriority();
        if(local>other)
        {
            return 1;
        }
        else if(local == other)
        {
            return 0;
        }
        return -1;
    }

    public Callable <T> getTask()
    {
        return this.task;
    }

}

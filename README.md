
# OOP ASSIGMANT 2

created by: Maya Zand and Ravid Krisi

# about this project
this projects includes 2 parts. in both we are exploring the thread and threadPool world in java

## EX2_1:
#### EX2_1:
in this class we created 4 methods:
1) CreateFiles: get 3 arguments to generate files with random muber of lines with "Hello World" in the context
2) getNumOfLines: this method receives array of files names and return the number of lines in all of them by implement itertation on all the array and counting the lines in each file at a time
3) getNumOfLinesThreads: this method receives array of files names and return the number of lines in all of them by using thread to each file.
4) getNumOfLinesThreadPool:this method receives array of files names and return the number of lines in all of them by using ThreadPool and submiting each file to the thread pool.

### MyThread:
this class used to create threads objects that will count the number of lines by inherting the Thread class and implementing the run() method.  
we use objects of this class in the getNumOfLinesThreads method.
###  MyThreadPool:
this Class implementing the Callable interface and use the Call() method to count the number of line in a file and return it.  
we use objects of this class in getNumOfLinesThreadPool by sumbiting it on each file in the ExecuterService we created.
### Tests:
basic test file that creates 500 files with random number of lines and excecuting all 3 diffrenet method to count the number of lines in all files and recording runtime to each one and prints it.

this test shows that with the variables we entered to generate the files the getNumOfLinesThreads is the fastest of all three.

## EX2_2:
in this part we created our own ThreadpoolExecuter with priority to tasks we have created.

### TaskType:
in this Class we created our task container


the class implementing Comparable interface for comparing between 2 objects for their prioirties.  
the class implementing Callable interface so we could pass function that we want our task to run when its excecuting by our CustomThreadPool.

each task has 2 variables:
1) Callable <T> task: generic Callable to hold the function we want to run.
2) TaskType task_type: this holds the type of the task.



constructors:  
we created 2 private constructors one for passing both Callable for task and TaskType for task_type, the second for passing only Callable for task and set the task_type for defaultive Other task type.  
these private constructors used by CreateTask.

the call() func runs the task passed in the counstructors.

### TaskType:
this class set the priorty of each task by its categorization and can also set the priorty from 1 to 10.

### AdapterToTask:
beacuse we were supposed to implement the Task class using the Callable interface and the CustomThreadPool excecute Runnable objects we need to use Adapter design pattern to adapt the Task object to the ThreadpoolExecuter.

### CustomExcecutor:
in the class we created our custom ThreadpoolExecuter with the parameters that were reqeusted.
this CustomThreadPool using BlockingPriorityQueue to execute the tasks passed to it by their priorites.

### Tests:

test file that was given 



package pixelmon.grethen;

import java.util.ArrayList;

/**
 * An ArrayList based TaskQueue, useful for loading resources one step at a time, or in clusters.
 * <br><br>
 * <b>Notes:</b>
 * <br>
 * Tasks are removed from the queue after they are executed.
 * <br>
 * The same task can be added multiple times.
 * <br>
 * @author Alec
 *
 */
public class TaskQueue
{

	protected final ArrayList<Task> queue;
	protected volatile int finished;
	protected volatile int numTasks;

	public TaskQueue()
	{
		queue = new ArrayList<Task>();
	}

	public TaskQueue add(Task t)
	{
		queue.add(t);
		numTasks++;
		return this;
	}

	/**
	 * Executes the given number of Tasks in the queue, starting from the first Task
	 * @param i - the number of Tasks to execute
	 */
	public void execute(int i)
	{
		for(int i1 = 0; i1 < i; i1++)
		{
			if(queue.isEmpty())
				return;
			queue.get(0).execute();
			finished++;
			queue.remove(0);
		}
	}

	public int numTasksDone()
	{
		return finished;
	}

	public int numTasks()
	{
		return numTasks;
	}

	public int numTasksLeft()
	{
		return numTasks - finished;
	}

	public boolean finished()
	{
		return finished == numTasks;
	}

	/**
	 * Executes all of the Tasks in the queue, starting from the first Task
	 */
	public void executeAll()
	{
		execute(queue.size() + 1);
	}

	/**
	 * Executes all of the Tasks in the queue in a separate Thread
	 * @param daemon - Whether or not the new Thread should be a Daemon Thread
	 */
	public void executeParallel(boolean daemon)
	{
		Thread t = new Thread(new Runnable()
		{
			public void run()
			{
				executeAll();
			}
		}, "Task Queue");
		t.setDaemon(daemon);
		t.start();
	}
}
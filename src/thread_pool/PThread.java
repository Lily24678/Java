package thread_pool;
/**
 * PThread它的线程主体部分是一个无限循环，该线程在手动关闭前永不结束，并一直等待新的任务到达
 * @author lsy
 * 2018年9月27日上午11:00:58
 */
public class PThread extends Thread {
	private ThreadPool pool;// 线程池
	private Runnable target;// 任务
	private boolean isShutDown = false;
	private boolean isIdle = false;

	public PThread(Runnable target, String name, ThreadPool pool) {
		super(name);
		this.pool = pool;
		this.target = target;
	}

	public Runnable getTarget() {
		return target;
	}

	public boolean isIdle() {
		return isIdle;
	}

	@Override
	public void run() {
		// 只要没关闭，则一直不结束该线程
		while (!isShutDown) {
			isIdle = false;
			if (target != null) {
				// 运行任务
				target.run();

			}
			// 任务结束了，到闲置状态
			isIdle = true;
			try {
				// 任务结束后，不关闭线程，而是放到线程池空闲队列
				pool.repool(this);
				synchronized (this) {
					// 线程空闲，等待新的任务到来
					wait();
				}
			} catch (Exception e) {
			}
			isIdle = false;
		}
	}

	public synchronized void setTarget(Runnable newTarget) {
		target = newTarget;
		// 设置了任务之后，通知run方法，开始执行这个任务
		notifyAll();
	}

	// 关闭线程
	public synchronized void shutDown() {
		isShutDown = true;
		notifyAll();
	}
}

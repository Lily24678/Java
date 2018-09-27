package thread_pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 扩展ThreadPoolExecutor
 * 
 * @author lsy 2018年9月27日下午2:31:44
 */
public class MyThreadPoolExecutor extends ThreadPoolExecutor {

	public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		System.out.println("beforeExecute MyThread Name: " + ((MyThread) r).getName() + " TID: " + t.getId());
	}

	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		System.out.println("afterExecute TID: " + Thread.currentThread().getId());
		System.out.println("afterExecute PoolSize: " + this.getPoolSize());
	}
}

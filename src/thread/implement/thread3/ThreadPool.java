package thread.implement.thread3;

import java.util.List;
import java.util.Vector;
/**
 * 线程池的实现
 * @author lsy
 * 2018年9月27日上午11:45:14
 */
public class ThreadPool {
	private static ThreadPool instance = null;

	private List<PThread> idleThreads;// 空闲的线程队列

	private int threadCounter;// 已有的线程总数

	private boolean isShutDown = false;

	public ThreadPool() {
		this.idleThreads = new Vector<>(5);
		threadCounter = 0;
	}


	public int getCreatedThreadCounter() {
		return threadCounter;
	}

	// 取得线程池的实例
	public synchronized static ThreadPool getInstance() {
		if (instance == null)
			instance = new ThreadPool();
		return instance;
	}
	
	//將线程放入线程池中
	public synchronized void repool(PThread repoolingThread) {
		if (!isShutDown) {
			idleThreads.add(repoolingThread);
		}else {
			repoolingThread.shutDown();
		}
	}
	
	//停止池中所有线程
	public synchronized void shutdown() {
		isShutDown=true;
		for(int threadIndex=0; threadIndex<idleThreads.size();threadIndex++) {
			PThread idleThread=(PThread)idleThreads.get(threadIndex);
			idleThread.shutDown();
		}
	}
	
	//执行任务
	public synchronized void start(Runnable target) {
		PThread thread=null;
		//如果有空闲线程，直接使用
		if (idleThreads.size()>0) {
			int lastIndex=idleThreads.size()-1;
			thread=idleThreads.get(lastIndex);
			idleThreads.remove(lastIndex);
			//立即执行这个任务
			thread.setTarget(target);
		}else {
			threadCounter++;
			//创建新的线程
			thread=new PThread(target, "PThread #"+threadCounter, this);
			//启动这个线程
			thread.start();
					
		}
	}
}

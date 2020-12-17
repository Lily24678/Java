package thread_pool;

/**
 * 定义一个线程类，作为任务对象。
 * 下面以一个使用优先级队列的自定义线程池为例，展示ThreadPoolExecutor的使用。
 * 
 * @author lsy 2018年9月27日上午11:45:57
 */
public class MyThread implements Runnable, Comparable<MyThread> {

	protected String name;

	public MyThread() {
	}

	public MyThread(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		try {
			System.out.println(name);
			Thread.sleep(100);// 使用sleep方法代替一个具体功能的执行
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int compareTo(MyThread o) {// 比较任务的优先级
		// 在线程名称中标记任务优先级
		int me = Integer.parseInt(this.name.split("_")[1]);
		int other = Integer.parseInt(o.name.split("_")[1]);
		if (me > other)
			return -1;
		else
			return 0;
	}

	public String getName() {
		return name;
	}
}

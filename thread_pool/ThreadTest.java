package thread_pool;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadTest {

	public static void main(String[] args) {
		int processors = Runtime.getRuntime().availableProcessors();//获取可用的CPU数
		System.out.println(processors);
		ThreadPoolExecutor exe = new ThreadPoolExecutor(100, 200, 0L, TimeUnit.SECONDS,
				new PriorityBlockingQueue<Runnable>());
		for (int i = 0; i < 1000; i++) {
			exe.execute(new MyThread("testThreadPoolExecutor3_" + Integer.toString(999 - i)));
			
		}
	}
}

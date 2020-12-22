package thread.kw_volatile;

public class MyThread extends Thread {
	private volatile boolean stop = false;// 确保stop变量在多线程中可见

	// 在其他线程中调用，停止本线程
	public void stopMe() {
		stop = true;
	}

	@Override
	public void run() {
		int i = 0;
		while (!stop) {// 在其他线程中改变stop的值
			i++;
		}
		System.out.println("Stop Thread");
	}

	/**
	 * 结果表明，若不使用volatile，则MyThread永远不会退出，只有使用volatile的情况下，MyThread才会发现stop的状态被其他线程改变。
	 * 注意：如果使用-client模式运行这段代码，无论是否使用volatile，其运行效果都是一样的，MyThread总是会发现stop状态的修改（会发现修改，但不是即时的）。-server模式下有这样明显的区别是因为在该模式下，JVM会对代码做一些优化，使得优化后的代码不再去读取未曾发生改变的、且未标记为volatile的stop变量，使得子在-server模式下，该变量的修改线程间不可见。
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		MyThread t = new MyThread();
		t.start();
		Thread.sleep(3000);
		t.stopMe();
		Thread.sleep(3000);

	}
}

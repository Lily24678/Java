package thread.implement;
/**多线程的实现方式2：实现public interface Runnable*/
public class Thread2 implements Runnable{//1. 实现public interface Runnable
    @Override
    public void run() {//2. 实现Runnable接口的run方法
        System.out.println("多线程的实现方式2：实现public interface Runnable");
    }

    public static void main(String[] args) {
        //3. 创建Thread对象，将子类对象作为参数传入（Runnable的子类对象相当于资源传递给Thread）
        Thread thread2 = new Thread(new Thread2());
        //4. 调用start()方法，内部会自动调用Runnable的run()方法
        thread2.start();
        thread2.start();

        System.out.println("ThreadName---"+Thread.currentThread().getName());
        System.out.println("ThreadName---"+thread2.getName());
    }
}

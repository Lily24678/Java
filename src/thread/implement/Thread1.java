package thread.implement;

/**
 * 多线程的实现方式1： 定义子类继承Thread
 */
public class Thread1 extends Thread {//1. 定义子类继承Thread

    @Override
    public void run() {//2. 重写Thread中的run方法（把线程要做的事写在run方法中）
        System.out.println("多线程的实现方式1：继承public class Thread");
    }

    public static void main(String[] args) {
        //3. 创建子类对象
        Thread1 thread1 = new Thread1();
        //4. 开启线程（Thread中的start（）方法，start（）方法会自动执行run（）方法）
        thread1.start();

        System.out.println("ThreadName---"+Thread.currentThread().getName());
        System.out.println("ThreadName---"+thread1.getName());
    }
}

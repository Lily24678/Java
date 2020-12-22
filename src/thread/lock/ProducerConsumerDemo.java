package thread.lock;

/**
 * jdk1.5以后将同步和锁封装成了对象。
 * 并将操作锁的隐式方式定义到了该对象中，
 * 将隐式动作变成了显示动作。
 * <p>
 * Lock接口： 出现替代了同步代码块或者同步函数。将同步的隐式锁操作变成现实锁操作。
 * 同时更为灵活。可以一个锁上加上多组监视器。
 * lock():获取锁。
 * unlock():释放锁，通常需要定义finally代码块中。
 * <p>
 * <p>
 * Condition接口：出现替代了Object中的wait notify notifyAll方法。
 * 将这些监视器方法单独进行了封装，变成Condition监视器对象。
 * 可以任意锁进行组合。
 * await();
 * signal();
 * signalAll();
 */


public class ProducerConsumerDemo {
    public static void main(String[] args) {
        Resource r = new Resource();
        Producer pro = new Producer(r);
        Consumer con = new Consumer(r);

        Thread t0 = new Thread(pro);
        Thread t1 = new Thread(pro);
        Thread t2 = new Thread(con);
        Thread t3 = new Thread(con);
        t0.start();
        t1.start();
        t2.start();
        t3.start();

    }
}

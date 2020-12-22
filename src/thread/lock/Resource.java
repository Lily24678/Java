package thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Resource {
    private String name;
    private int count = 1;
    private boolean flag = false;

    //	创建一个锁对象。
    Lock lock = new ReentrantLock();

    //通过已有的锁获取该锁上的监视器对象。
//	Condition con = lock.newCondition();

    //通过已有的锁获取两组监视器，一组监视生产者，一组监视消费者。
    Condition producer_con = lock.newCondition();
    Condition consumer_con = lock.newCondition();


    public void set(String name)//  t0 t1
    {
        lock.lock();
        try {
            while (flag)
//			try{lock.wait();}catch(InterruptedException e){}//   t1    t0
                try {
                    producer_con.await();
                } catch (InterruptedException e) {
                }//   t1    t0

            this.name = name + count;//烤鸭1  烤鸭2  烤鸭3
            count++;//2 3 4
            System.out.println(Thread.currentThread().getName() + "...生产者5.0..." + this.name);//生产烤鸭1 生产烤鸭2 生产烤鸭3
            flag = true;
//			notifyAll();
//			con.signalAll();
            consumer_con.signal();
        } finally {
            lock.unlock();
        }

    }

    public void out()// t2 t3
    {
        lock.lock();
        try {
            while (!flag)
//			try{this.wait();}catch(InterruptedException e){}	//t2  t3
                try {
                    consumer_con.await();
                } catch (InterruptedException e) {
                }    //t2  t3
            System.out.println(Thread.currentThread().getName() + "...消费者.5.0......." + this.name);//消费烤鸭1
            flag = false;
//			notifyAll();
//			con.signalAll();
            producer_con.signal();
        } finally {
            lock.unlock();
        }

    }
}

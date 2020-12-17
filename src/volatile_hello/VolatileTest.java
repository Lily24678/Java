package volatile_hello;


import org.junit.Test;

public class VolatileTest {
    volatile boolean isExit;

    public void tryExit() {
        if (isExit == !isExit) {// 成立则退出
            System.exit(0);
        }
    }

    public void swapValue() {
        isExit = !isExit;
    }

    /**
     * 若isExit变量未被声明为volatile，则"isExit ==
     * !isExit"很难成立。因为等式左边和等式右边的取值都是首先尝试从线程工作区中获得，虽然swap线程总是在不停地切换这个数值，但对tryExit线程并不是立即可见。因此两次取值几乎每次都相等，故，程序通常可以运行很长时间。
     * 若isExit变量被声明为volatile，swap线程对isExit的修改可以很快地被tryExit()函数发现。
     *
     * @throws InterruptedException
     */
    @Test
    public void test() throws InterruptedException {
        final VolatileTest volObj = new VolatileTest();
        Thread mainThread = new Thread() {
            public void run() {
                System.out.println("mainThread start");
                while (true) {
                    volObj.tryExit();// 不停地尝试是否可以退出
                }
            }
        };
        mainThread.start();

        Thread swapThread = new Thread() {
            public void run() {
                System.out.println("swapThread start");
                while (true) {
                    volObj.swapValue();// 不停修改isExit值
                }
            }
        };
        swapThread.start();
        Thread.sleep(10000);
    }
}

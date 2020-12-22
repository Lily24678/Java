package reflect;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 需求: 有一个榨汁机接口, 内部定义榨汁的方法
 * 两个class类, 苹果, 橘子分别实现榨汁接口
 * 输出 --> 榨出一杯橘子汁
 * 输出 --> 炸出一杯苹果汁
 */
public class ReflectDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        BufferedReader br = new BufferedReader(new FileReader("/home/smates/workspace/Java/src/reflect/info.properties"));
        String info = br.readLine();
        Class cl = Class.forName(info);
        Object obj = cl.newInstance();
        if (obj instanceof Apple) {
            Apple apple = (Apple) obj;
            apple.show();
        } else if (obj instanceof Orange) {
            Orange o = (Orange) obj;
            o.show();
        }
    }
}

interface Zhazhiji {
    public abstract void show();
}

class Apple implements Zhazhiji {
    public Apple() {
    }

    @Override
    public void show() {
        System.out.println("榨汁机榨苹果汁");
    }
}

class Orange implements Zhazhiji {
    public Orange() {
    }

    @Override
    public void show() {
        System.out.println("榨汁机榨橘子汁");
    }
}
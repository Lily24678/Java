package others;

/**
 * 内部类:成员内部类,局部内部类
 */
public class Outer {
    private int num;

    /**
     * 成员内部类
     */
    public class Inner {
        public void sout() {
            int num = 20;
            System.out.println(num);
        }

    }

    /**
     * 局部内部类
     */
    public void method() {
        char a = 98;
        class Inner {
            public void sout() {
                System.out.println(a);
            }
        }
        new Inner().sout();
    }

    public static void main(String[] args) {
        new Outer().new Inner().sout();
        new Outer().method();
    }
}

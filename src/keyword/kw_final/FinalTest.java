package keyword.kw_final;

public final class FinalTest {
    private final static int num = 0;

    public final void show() {
        System.out.println("final修饰的类不可以被继承。");
        System.out.println("final修饰的方法不可以被覆盖");
        System.out.println("final修饰的变量不可以被修改，变成是一个常量，只能赋值一次，一旦被赋值就不能更改。");
    }

    public static void main(String[] args) {
        new FinalTest().show();
    }
}

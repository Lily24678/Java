package others.polymorphism;

/**
 * 1，成员变量：编译时：看等号的左边。    运行时：看等号的左边。
 * 2，成员函数：动态绑定机制（继承中的覆盖特性 ）   编译时：看等号的左边。    运行时：看等号的右边。
 * 3，静态函数：编译时：看等号的左边。    运行时：看等号的左边。（  其实对于静态方法，是不需要对象的。直接用类名调用即可 ）
 */
public class PolymorphismTest {
    public static void main(String[] args) {
        Fu fu = new Zi();
        System.out.println(fu.num);
        fu.show();
    }
}

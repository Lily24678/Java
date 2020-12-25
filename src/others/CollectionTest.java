package others;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 单列集合Collection
 */
public class CollectionTest {
    /**
     * contains(Object o)、remove(Object o)两者底层依赖的是equals方法。
     * 校验方式：重写Object的equals方法
     */
    @Test
    public void test1() {
        List<String> list = new ArrayList<>();
        list.add("abc");
        list.add("efg");
        System.out.println(list.contains("abc"));
        list.remove("abc");
        System.out.println(list.size());

        List<A> list2 = new ArrayList<>();
        list2.add(new A("abc", 1));
        list2.add(new A("efg", 1));
        System.out.println(list2.contains(new A("abc", 1)));
        list2.remove(new A("abc", 1));
        System.out.println(list2.size());
    }

    /**
     * HashSet保证元素唯一性的原理： hashCode方法和equals方式的配合使用
     */
    @Test
    public void test2() {
        Set<A> set = new HashSet<>();
        set.add(new A("abc", 4));
        set.add(new A("abc", 4));
        set.add(new A("abc", 2));
        set.add(new A("lmn", 3));
        set.add(new A("efg", 3));

        Iterator<A> iterator = set.iterator();
        while (iterator.hasNext()) System.out.println(iterator.next());

    }

    /**
     * TreeSet
     */
    @Test
    public void test3() {
        //TreeSet判断元素唯一性的方式：就是根据Comparator的方法compare的返回结果是否是0，是0，就是相同元素，不存。
        Set<String> set = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                return 0;
            }
        });
        set.add("abca");
        set.add("lmn222");
        set.add("efg");
        System.out.println("TreeSet判断元素唯一性的方式");
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) System.out.println(iterator.next());

        //TreeSet对元素进行排序的方式一：让元素自身具备比较功能，元素就需要实现Comparable接口[默认比较规则]。覆盖compareTo方法
        Set<String> set1 = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
//                System.out.println(s+"---"+t1);
                return s.length() - t1.length();
            }
        });
        set1.add("abca");
        set1.add("lmn222");
        set1.add("efg");
        System.out.println("TreeSet对元素进行排序的方式一");
        Iterator<String> iterator1 = set1.iterator();
        while (iterator1.hasNext()) System.out.println(iterator1.next());

        //TreeSet集合第二种排序方式二：让集合自身具备比较功能，定义一个类实现Comparator接口，覆盖compare方法。将该类对象作为参数传递给TreeSet集合的构造函数。
        Set<A> set2 = new TreeSet<>(new A());
        set2.add(new A("abc", 4));
        set2.add(new A("abc", 4));
        set2.add(new A("efg", 4));
        System.out.println("TreeSet集合第二种排序方式二");
        Iterator<A> iterator2 = set2.iterator();
        while (iterator2.hasNext()) System.out.println(iterator2.next());
    }
}

class A implements Comparator {
    private String name;
    private Integer age;

    public A() {
    }

    public A(String name, Integer age) {
        this.name = name;
        this.age = age;
    }


    @Override
    public int compare(Object o, Object t1) {
        return o.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        A a = (A) o;
        return Objects.equals(name, a.name) &&
                Objects.equals(age, a.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "A{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
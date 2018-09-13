package example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

import org.junit.Test;

/**
 * Java基础 
 * @author lishangyun
 * @time 2018年9月13日下午1:34:43
 */
//自定义异常1
class MyException extends Exception {
	private static final long serialVersionUID = 1L;

	public MyException() {

	}
}

// 自定义异常2
class MyRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MyRuntimeException() {

	}
}

public class Foundation {
	private final static int f;
	// 静态代码块
	static {
		f = 10;
		System.out.println("静态代码块：随着类的加载而加载，给类进行初始化");
	}
	// 构造代码块
	{
		System.out.println("构造代码块：用来给对象初始化的, 如果说多个构造方法当中出现了相同的代码, 那么就可以考虑将这段代码抽取到构造代码块当中。执行时间：每创建一次对象就执行一次，且优先于构造方法执行");
	}

	public Foundation() {
		System.out.println("构造方法");
	}

	
	public void test_final() {
		System.out.println(f);

	}

	// 可变参数
	public int add(int... i) {
		return 0;
	}

	
	public void loopFor() {
		// 循环for
		int i = 1;
		for (System.out.println("a"); i < 3; System.out.println("b")) {
			i++;
		}

		// 循环嵌套，break
		xiaoqiang: for (int x = 0; x < 3; x++) {
			wangcai: for (int y = 0; y < 4; y++) {
				System.out.println("x=" + x);
				break xiaoqiang;
			}

		}
	}

	// 单列 集合
	public void collect_test() {
		// 单列集合
		ArrayList<String> list = new ArrayList<String>(15);// ArrayList，明确指定容量，尽量避免底层数组的扩张操作
		list.ensureCapacity(15);// 明确指定容量，尽量避免底层数组的扩张操作

		String string = "123";
		list.add(null);
		System.out.println(list.contains(null));// 非null，层依赖的是equals方法。
		System.out.println(list.remove(string));// 非null，层依赖的是equals方法。
		System.out.println(list.indexOf(null));// 非null，层依赖的是equals方法。
		list.set(0, string);
		list.trimToSize();// 将底层数组容量调整为当前列表实际元素的大小
		System.out.println(list.size());

		// 打印基本数据类型数组char 与 非char
		int[] arr = { 1, 2, 3, 4, 5 };
		System.out.println(arr);// 结果 [I@7852e922

		char[] c = { 'a', 'b', 'c', 'd' };//
		System.out.println(c);// 结果 abcd

		ArrayList<String> array = new ArrayList<String>();
		array.add("heiheiheihei");
		System.out.println("array:　" + array);// 如果集合当中存储的数据类型是Java写好的类, 那么打印出来的将会是数据的内容。

		// HashSet JDK1.8存取有序。
		HashSet<Integer> hashSet = new HashSet<>();
		hashSet.add(0);
		/**
		 * 底层代码 <code>   
		 *  private transient HashMap<E,Object> map;
		 *  private static final Object PRESENT = new Object();
		 *  
		 *  public boolean add(E e) {return map.put(e, PRESENT)==null;}
		 *  </code>
		 */
		for (int i = 1; i <= 10; i++) {
			hashSet.add(i);
		}
		Iterator<Integer> iterator = hashSet.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());

		}
		// 排序
		/**
		 * <code>
		 *     public TreeMap(SortedMap<K, ? extends V> m) {comparator = m.comparator();try { buildFromSorted(m.size(), m.entrySet().iterator(), null, null);} catch (java.io.IOException cannotHappen) {} catch (ClassNotFoundException cannotHappen) {}}
		 * </code>
		 */
		TreeSet<Integer> treeSet = new TreeSet<>();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			int randomInt = random.nextInt(100);
			treeSet.add(randomInt);
		}
		System.out.println(treeSet.toString());
		
		

	}

	
	// 双列集合
	/**
	 * {@link HashMap} JDK1.7 数组+链表
	 * {@link HashMap} JDK1.8 数组+链表/红黑树
	 */
	public void hashMap_test() {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("1", "2");
		
	}

	private int age;
	private String name;

	public Foundation(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public int hashCode() {
		return 1;// 考虑效率问题
	}

	@Override
	public boolean equals(Object obj) {
		Foundation p = (Foundation) obj;// 向下转型，获取子类特有成员
		return this.name.equals(p.name) && this.age == p.age;
	}

	public static void main(String[] args) {
		// 静态代码块、构造代码块、构造函数的执行顺序的测试
		new Foundation();
		new Foundation();

		// 重写hashCode方法 和 equals方法, 优化HashSet的add方法
		HashSet<Foundation> hs = new HashSet<>();
		Foundation p1 = new Foundation("张三", 25);
		Foundation p2 = new Foundation("张三", 25);
		hs.add(p1);
		hs.add(p2);
		System.out.println(p1.equals(p2));

	}
}

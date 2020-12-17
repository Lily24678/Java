package optimization;

import java.util.Vector;

public class OptimizationSkill {
	public static int ta = 0;

	public static void main(String[] args) {
		// // 1、慎用异常
		// try_catch_test();
		// // 2、使用局部变量
		// useLocalVariable();
		// // 3、位运算代理乘除法
		// useBit();

		// 4、替换switch？？？？？？？？
		// switch_optimization();

		// 5、一位数组代替二维数组
		// linearArrayVStwODimensionalArray();

		// 6、提取表达式
		// pickupE();

		// 7、展开循环
		// openCircle();

		// 8、布尔运算代替位运算
		// booleanVSbits();

		// 9、使用arrayCopy()
		// useArrayCopy();

		// 10、使用Buffer进行I/O操作

		// 11、使用clone()代替new
		useCloneTest();

		// 12、静态方法替代实例方法

	}

	// 11、使用clone()代替new
	private static void useCloneTest() {
		Student stu1 = new Student();
		Vector<Object> cs = new Vector<>();
		cs.add("Java");
		stu1.setId(1);
		stu1.setName("XiaoMing");
		stu1.setCourse(cs);

		Student stu2 = stu1.newInstance();// 使用clone()方法生成对象
		stu2.setId(2);
		stu2.setName("XiaoDong");
		stu2.getCourse().add("c#");

		System.out.println("stu1`name: " + stu1.getName());// stu1`name: XiaoMing
		System.out.println("stu2`name: " + stu2.getName());// stu2`name: XiaoDong
		// 两个Student使用同一个Vector。克隆對象拥有和原始对象相同的引用，而不是值的拷贝，即浅拷贝。如果需要实现深拷贝，则需要重载对象的clone()方法。
		System.out.println(stu1.getCourse() == stu2.getCourse());// 浅拷贝true 深拷贝false
	}

	// 9、使用arrayCopy()
	private static void useArrayCopy() {
		int size = 100000;
		int[] array = new int[size];
		int[] arraydst = new int[size];
		for (int i = 0; i < array.length; i++) {
			array[i] = i;
		}

		long begin = System.currentTimeMillis();
		for (int k = 0; k < 1000; k++)
			System.arraycopy(array, 0, arraydst, 0, size);
		System.out.println(System.currentTimeMillis() - begin);// 16

		begin = System.currentTimeMillis();
		for (int k = 0; k < 1000; k++)
			for (int i = 0; i < size; i++)
				arraydst[i] = array[i];
		System.out.println(System.currentTimeMillis() - begin);// 35
	}

	// 8、布尔运算代替位运算
	private static void booleanVSbits() {
		boolean a = false;
		boolean b = true;
		int d = 0;
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
			if (a & b & "Java_Perform".contains("Java"))
				d = 0;
		}
		System.out.println(System.currentTimeMillis() - begin);// 35

		begin = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
			if (a && b && "Java_Perform".contains("Java"))
				d = 0;
		}
		System.out.println(System.currentTimeMillis() - begin);// 1
	}

	// 7、展开循环
	private static void openCircle() {
		int[] array = new int[9999999];
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 9999999; i++) {
			array[i] = i;
		}
		System.out.println(System.currentTimeMillis() - begin);// 10

		begin = System.currentTimeMillis();
		for (int i = 0; i < 9999999; i += 3) {
			array[i] = i;
			array[i + 1] = i + 1;
			array[i + 2] = i + 2;
		}
		System.out.println(System.currentTimeMillis() - begin);// 9
	}

	// 6、提取表达式
	private static void pickupE() {
		double a = Math.random();
		double b = Math.random();
		double d = Math.random();
		double e = Math.random();
		double x, y;
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 500000000; i++) {
			x = d * a * b / 3 * 4 * a;
			y = e * a * b / 3 * 4 * a;
		}
		System.out.println(System.currentTimeMillis() - begin);// 5

		double t;
		begin = System.currentTimeMillis();
		for (int i = 0; i < 500000000; i++) {
			t = a * b / 3 * 4 * a;
			x = d * t;
			y = e * t;
		}
		System.out.println(System.currentTimeMillis() - begin);// 1
	}

	// 5、一位数组代替二维数组
	private static void linearArrayVStwODimensionalArray() {
		int[] array = new int[1000000];// 用于测试的一维数组
		int re = 0;
		// 0、在集合访问或者数组访问时，应尽量减少方法的调用
		int size = array.length;
		long begin = System.currentTimeMillis();
		for (int k = 0; k < 100; k++)
			for (int i = 0; i < size; i++)
				array[i] = i;// 一维数组赋值
		for (int k = 0; k < 100; k++)
			for (int i = 0; i < size; i++)
				re = array[i];// 一维数组的取值
		System.out.println(System.currentTimeMillis() - begin);// 50 49

		int[][] array2 = new int[1000][1000];// 与一维数组等价的二维数组
		// 0、在集合访问或者数组访问时，应尽量减少方法的调用
		size = array2.length;
		int size1 = array2[0].length;
		begin = System.currentTimeMillis();
		for (int k = 0; k < 100; k++)
			for (int i = 0; i < size; i++)
				for (int j = 0; j < size1; j++)
					array2[i][j] = i;// 二维数组赋值
		for (int k = 0; k < 100; k++)
			for (int i = 0; i < size; i++)
				for (int j = 0; j < size1; j++)
					re = array2[i][j];// 一维数组的取值
		System.out.println(System.currentTimeMillis() - begin);// 61 98

	}

	// 4、替换switch？？？？？？？？
	public static void switch_optimization() {
		int re = 0;
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 500000000; i++) {
			re = switchInt(i);
		}
		System.out.println(System.currentTimeMillis() - begin);// 3

		int[] sw = new int[] { 3, 6, 7, 8, 10, 16, 18, 44 };// 替代switch的逻辑
		begin = System.currentTimeMillis();
		for (int i = 0; i < 500000000; i++) {
			re = arrayInt(sw, i);
		}
		System.out.println(System.currentTimeMillis() - begin);// 660
	}

	// 4、替换switch
	private static int arrayInt(int[] sw, int z) {
		int i = z % 10 + 1;
		if (i > 7 || i < 1)
			return -1;// 模拟switch的default
		else
			return sw[i];
	}

	private static int switchInt(int z) {
		int i = z % 10 + 1;
		switch (i) {
		case 1:
			return 3;
		case 2:
			return 6;
		case 3:
			return 7;
		case 4:
			return 8;
		case 5:
			return 10;
		case 6:
			return 16;
		case 7:
			return 18;
		case 8:
			return 44;
		default:
			return -1;
		}
	}

	// 3、位运算代理乘除法
	private static void useBit() {
		long a = 100;
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 500000000; i++) {
			a *= 2;
			a /= 2;
		}
		System.out.println(System.currentTimeMillis() - begin);// 537

		begin = System.currentTimeMillis();
		for (int i = 0; i < 500000000; i++) {
			a <<= 1;// 乘以2
			a >>= 1;// 除以2
		}
		System.out.println(System.currentTimeMillis() - begin);// 26
	}

	// 2、使用局部变量
	private static void useLocalVariable() {
		int a = 0;
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 500000000; i++) {
			a++;
		}
		System.out.println(System.currentTimeMillis() - begin);// 1

		begin = System.currentTimeMillis();
		for (int i = 0; i < 500000000; i++) {
			ta++;
		}
		System.out.println(System.currentTimeMillis() - begin);// 19
	}

	// 1、慎用异常
	public static void try_catch_test() {
		int a = 0;
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 500000000; i++) {
			try {
				a++;
			} catch (Exception e) {

			}
		}
		System.out.println(System.currentTimeMillis() - begin);// 2

		begin = System.currentTimeMillis();
		try {
			for (int i = 0; i < 500000000; i++) {
				a++;
			}
		} catch (Exception e) {
		}
		System.out.println(System.currentTimeMillis() - begin);// 1
	}
}

class Student implements Cloneable {
	private int id;
	private String name;
	private Vector course;

	public Student() {// 一个很慢的构造函数
		try {
			Thread.sleep(1000);
			System.out.println("Student Construnctor called");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vector getCourse() {
		return course;
	}

	public void setCourse(Vector course) {
		this.course = course;
	}

	public Student newInstance() {// 使用clone()创建对象
		try {
			return (Student) this.clone();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		Student stu = (Student) super.clone();// 使用super.clone();生成一份浅拷贝对象。
		Vector v = stu.getCourse();
		Vector<Object> v1 = new Vector<>();// 创建新的Vector
		for (Object o : v) {// 复制原来的Vector
			v1.add(o);
		}
		stu.setCourse(v1);// 使用新的Vector
		return stu;
	}

}

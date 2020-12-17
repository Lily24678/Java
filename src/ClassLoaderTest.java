/**
 * 所有的类都是在第一次使用时，动态加载到JVM中的，当程序创建第一次对类的静态成员引用时，就会加载这个类。实际上构造函数也是类的静态方法，因此使用new关键字创建类的新对象也会被当做对类的静态引用，从而触发类加载器对类的加载。
 * 
 * @author lishangyun
 * @time 2018年9月12日下午5:06:34
 */
// 帮助理解反射：.class什么时候加载到JVM
public class ClassLoaderTest {
	static class C1 {
		static {
			System.out.println("C1");
		}
	}

	static class C2 {
		static {
			System.out.println("C2");
		}
	}

	static class C3 {
		static {
			System.out.println("C3");
		}
	}

	static class C4 {
		static {
			System.out.println("C4");
		}

		static void show() {
			System.out.println("调用静态方法show");
		}
	}

	@SuppressWarnings({ "unused", "rawtypes" })
	public static void main(String... args) throws Exception {
		System.out.println("new start");
		// 构造函数为类的静态引用，触发类型加载
		new C1();
		new C1();
		System.out.println("new end");

		System.out.println();

		System.out.println("Class.forName start");
		// Class.forName为Class上的静态函数，用于强制加载Class
		Class.forName("reflect.demo.ClassLoaderTest$C2");
		Class.forName("reflect.demo.ClassLoaderTest$C2");
		System.out.println("Class.forName end");

		System.out.println();

		System.out.println("C3.class start");
		// Class引用，会触发Class加载，但是不会触发初始化
		Class c1 = C3.class;
		Class c2 = C3.class;
		System.out.println("C3.class end");

		System.out.println();

		System.out.println("c1.newInstance start");
		// 调用class上的方法，触发初始化逻辑
		c1.newInstance();
		System.out.println("c1.newInstance end");

		System.out.println("c4 start");
		C4.show();
		System.out.println("c4 end");

	}
}

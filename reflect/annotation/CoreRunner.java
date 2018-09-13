package reflect.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 自定义注解实现步骤2： 核心运行类。在核心运行类使用反射
 */
public class CoreRunner {
	@SuppressWarnings("all")
	public static void main(String[] args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		// 1.获得测试类的Class:
		Class clazz = AnnotationDemo1.class;
		// 2.获得Class中的所有的方法: 规定了测试的方法必须是public.
		Method[] methods = clazz.getMethods();// MyTest注解的权限修饰时public
		// 3.遍历每个方法:
		for (Method method : methods) {
			boolean flag = method.isAnnotationPresent(MyTest.class);// 如果此元素上存在指定类型的注释，则返回true，否则返回false。
			if (flag) {// 说明方法上有MyTest注解:
				method.invoke(clazz.newInstance(), null);// 没有参数，不知道传递什么参数
			}
		}
	}
}
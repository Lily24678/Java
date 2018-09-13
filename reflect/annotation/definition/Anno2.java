package reflect.annotation.definition;

import reflect.annotation.Color;

/**
 * 定义注解的只能存在属性: 1. 基本数据类型。 2. String类型。 3. Class类型。 4. 注解类型。 5. 枚举类型。 6.以上类型的一维数组。
 * 定义一个带有属性的注解:Anno2
 */
public @interface Anno2 {
	// 1、基本数据类型
	int a();

	boolean b() default false;// 在注解类里进行参数初始化default

	// 2. String类型
	String s();

	// Class类型。
	Class<?> c();

	// 4. 注解类型。
	Anno1 anno1();

	// 5. 枚举类型。
	Color color();

	// 6.以上类型的一维数组。
	String[] arrs();
}
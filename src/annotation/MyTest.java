package annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 自定义注解 元注解：服务于注解的注解就是元注解。
 * 
 * @Retention：注解的保留范围 。如果注释类型声明中不存在 Target 元注释，则声明的类型可以用在任一程序元素上。
 *                    1、RetentionPolicy.SOURCE:注解存在于源文件中
 *                    2、RetentionPolicy.CLASS:注解存在于源字节码文件中 3、
 *                    RetentionPolicy.RUNTIME:注解存在于运行时
 * @Target：注解出现的位置。如果注释类型声明中不存在 Target 元注释，则声明的类型可以用在任一程序元素上。
 * @Documented: 用于指定被该元 Annotation 修饰的 Annotation 类将被 javadoc 工具提取成文档.
 * @Inherited: 被它修饰的 Annotation 将具有继承性.如果某个类使用了被 @Inherited 修饰的 Annotation,
 *             则其子类将自动具有该注解。
 * 
 * @author lishangyun
 * @time 2018年9月13日上午10:43:19
 */

// 自定义注解实现步骤1：
@Retention(value = RetentionPolicy.RUNTIME) // 这个元注解让MyTest注解在整个运行时期都有效。
public @interface MyTest {
}
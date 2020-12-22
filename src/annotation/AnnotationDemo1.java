package annotation;

import annotation.definition.Anno1;
import annotation.definition.Anno2;
import annotation.definition.Anno3;

/**
 * 自定义注解实现步骤3：使用
 * @author lishangyun
 * @time 2018年9月13日上午11:12:46
 */
@Anno1
@Anno2(a = 1, s = "abc", c = AnnotationDemo1.class, anno1 = @Anno1, color = Color.RED, arrs = { "aa", "bb" }) // 对带有参数的注解的使用
@Anno3("abc") // 注解中只有一个属性且属性名是value，则@Anno3(value="abc")中value可以省略不写
public class AnnotationDemo1 {
	@MyTest
	public void demo1() {
		System.out.println("方法demo1执行了...");
	}
}
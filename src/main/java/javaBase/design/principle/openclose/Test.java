package javaBase.design.principle.openclose;

/**
 * @className: Test
 * @description:     在关于设计模式以及软件设计原则的所有例子讲解中的Test类，我们都认为是高层客户端调用的一个角色
 * @author: gezx
 * @date: 2021/3/2
 **/
public class Test {
    public static void main(String[] args) {
        JavaDisCountCourse course = new JavaDisCountCourse(96, "Java设计模式精讲", 300d);
        System.out.println("课程ID：" + course.getId() + " 课程名称：" + course.getName() + " 课程原价：" + course.getPrice() + " 课程折后价：" + course.getDisCountPrice());
    }
}

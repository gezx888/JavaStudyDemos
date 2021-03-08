package javaBase.design.principle.dependenceinversion;

/**
 * @className: JavaCourse
 * @description: TODO 类描述
 * @author: gezx
 * @date: 2021/3/2
 **/
public class JavaCourse implements ICourse{

    @Override
    public void studyCourse() {
        System.out.println("gzx在学习Java课程");
    }
}

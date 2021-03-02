package javaBase.design.principle.dependenceinversion;

/**
 * @className: FeCourse
 * @description: TODO 类描述
 * @author: gezx
 * @date: 2021/3/2
 **/
public class PythonCourse implements ICourse{

    @Override
    public void studyCourse() {
        System.out.println("gzx在学习python课程");
    }
}

package javaBase.design.principle.dependenceinversion;

/**
 * @className: FeCourse
 * @description: TODO 类描述
 * @author: gezx
 * @date: 2021/3/2
 **/
public class FeCourse implements ICourse{

    @Override
    public void studyCourse() {
        System.out.println("gzx在学习前端课程");
    }
}

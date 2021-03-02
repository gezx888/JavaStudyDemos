package javaBase.design.principle.singleresponsibility;

/**
 * @className: CourseImpl
 * @description:            可以通过这样精细接口职责，实现类通过实现多个类进行功能的组合
 * @author: gezx
 * @date: 2021/3/2
 **/
public class CourseImpl implements ICourseContent,ICourseManager {
    @Override
    public String getCourseName() {
        return null;
    }

    @Override
    public byte[] getCourseVideo() {
        return new byte[0];
    }

    @Override
    public void studyCourse() {

    }

    @Override
    public void refundCourse() {

    }
}

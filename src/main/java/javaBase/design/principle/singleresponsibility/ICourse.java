package javaBase.design.principle.singleresponsibility;

/**
 * @className: ICourse
 * @description: TODO 类描述
 * @author: gezx
 * @date: 2021/3/2
 **/
public interface ICourse {
    String getCourseName();
    byte[] getCourseVideo();

    void studyCourse();
    void refundCourse();
}

package javaBase.design.principle.dependenceinversion;

/**
 * @className: Gzx
 * @description:    具体学习者，里面组合一个 待学习课程的抽象，这样就可以做到与具体课程的解耦，从而高层应用只需要依赖于抽象接口，面向接口编程
 * @author: gezx
 * @date: 2021/3/2
 **/
public class Gzx {
    private ICourse course;

    public void setCourse(ICourse course) {
        this.course = course;
    }

    public void studyImoocCourse(){
        course.studyCourse();
    }
}

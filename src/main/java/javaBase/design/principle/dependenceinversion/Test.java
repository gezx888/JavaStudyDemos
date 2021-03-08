package javaBase.design.principle.dependenceinversion;

/**
 * @className: Test
 * @description:     在关于设计模式以及软件设计原则的所有例子讲解中的Test类，我们都认为是高层客户端调用的一个角色，属于高层应用
 *                      本包下面所有的例子讲解的是依赖倒置原则： 该原则强调程序要依赖抽象接口，不要去依赖具体实现，否则将加大代码的耦合度
 *                      学习课程的一个业务场景的逐步迭代演进
 * @author: gezx
 * @date: 2021/3/2
 **/
public class Test {

    //v1
//    public static void main(String[] args) {
//        Gzx gzx = new Gzx();
//        gzx.studyJavaCourse();
//        gzx.studyFECourse();
//    }

    //v2
//    public static void main(String[] args) {
//        Gzx gzx = new Gzx();
//        gzx.studyImoocCourse(new JavaCourse());
//        gzx.studyImoocCourse(new FECourse());
//        gzx.studyImoocCourse(new PythonCourse());
//    }

    //v3
//    public static void main(String[] args) {
//        Gzx gzx = new Gzx(new JavaCourse());
//        gzx.studyImoocCourse();
//    }


    public static void main(String[] args) {
        Gzx gzx = new Gzx();
        gzx.setCourse(new JavaCourse());
        gzx.studyImoocCourse();

        gzx.setCourse(new FeCourse());
        gzx.studyImoocCourse();
    }
}

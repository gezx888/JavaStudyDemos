package javaBase.design.principle.demeter;

/**
 * @className: Boss
 * @description:        该包下面的几个例子讲解的是 迪米特法则，又叫着最少知识原则：一个类对于其他类知道的越少越好，就是说一个对象对于其他对象应该有尽可能少的了解
 *                  只和朋友通信，不和陌生人说话
 *                  朋友：以参量形式传递到当前对象方法中的对象；当前对象的实例变量的直接引用的对象；当前对象本身(this)
 * @author: gezx
 * @date: 2021/3/2
 **/
public class Boss {
    //  下面这种就不符合迪米特法则：像Course这种就不是朋友，应该不需要在boss类里面去新建  V1版
    /*public void commandCheckNumber(TeamLeader teamLeader){
        List<Course> courseList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
             courseList.add(new Course());
        }
        teamLeader.checkNumberOfCourse(courseList);
    }*/

    public void commandCheckNumber(TeamLeader teamLeader){
        teamLeader.checkNumberOfCourse();
    }


}

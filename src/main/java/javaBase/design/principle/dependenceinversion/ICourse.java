package javaBase.design.principle.dependenceinversion;

/**
 * @className: ICourse              待学习课程的抽象
 * @description:            本包下面所有的例子讲解的是依赖倒置原则： 该原则强调程序要依赖抽象接口，不要去依赖具体实现，否则将加大代码的耦合度
 *  *                      学习课程的一个业务场景的逐步迭代演进
 * @author: gezx
 * @date: 2021/3/2
 **/
public interface ICourse {
    void studyCourse();
}

package javaBase.design.principle.interfacegregation;

/**
 * @className: IAnimalAction
 * @description:                该包下面的几个类的代码例子讲解的是：接口隔离原则：客户端不应该依赖他不需要的接口，一个类对另一个类的依赖应该建立在最小的接口上
 *                             优点：职责清晰，实现接口的类不需要实现不必要的方法；
 *                             缺点：类的数量会增加
 * @author: gezx
 * @date: 2021/3/2
 **/
public interface IAnimalAction {
    void eat();
    void fly();
    void swim();
}

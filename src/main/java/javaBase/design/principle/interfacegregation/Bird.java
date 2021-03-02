package javaBase.design.principle.interfacegregation;

/**
 * @className: Bird
 * @description:        接口没有分开，接口隔离原则的话，像这样实现类去实现接口的时候，就会出现这样的情况：比如像鸵鸟就不会飞，所以鸟这个实现类其实不应该实现飞这个方法
 *                          有的鸟也不会游泳
 * @author: gezx
 * @date: 2021/3/2
 **/
public class Bird implements IAnimalAction{
    @Override
    public void eat() {
        System.out.println("用嘴巴吃");
    }

    @Override
    public void fly() {

    }

    @Override
    public void swim() {

    }
}

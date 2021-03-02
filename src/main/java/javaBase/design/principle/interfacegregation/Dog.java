package javaBase.design.principle.interfacegregation;

/**
 * @className: Dog
 * @description:          可以像这样来实现多个接口去组合实现类中的 功能
 * @author: gezx
 * @date: 2021/3/2
 **/
public class Dog implements IEatAnimalAction,ISwimAnimalAction{
    @Override
    public void eat() {
        System.out.println("用嘴巴吃");
    }

    @Override
    public void swim() {
        System.out.println("用脚划");
    }
}

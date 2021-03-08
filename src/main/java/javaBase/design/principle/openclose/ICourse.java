package javaBase.design.principle.openclose;

/**
            principle 该包下面包含了：开闭原则、接口隔离原则、依赖倒置原则、迪米特法则、单一职责原则 的代码演示例子
 其中还有：里氏替换原则、合成复用原则 代码例子没有包括
 */

/**
 * @className: ICourse
 * @description:                本包下演示的是软件设计原则的 —— 开闭原则
 *                          开闭原则：强调软件中的对象（类、模块、函数等）应该对于扩展是开放的，对于修改是封闭的
 * @author: gezx
 * @date: 2021/3/2
 **/
public interface ICourse {
    Integer getId();
    String getName();
    Double getPrice();
}

package javaBase.design.principle.openclose;

/**
 * @className: JavaDisCountCourse
 * @description: TODO 类描述
 * @author: gezx
 * @date: 2021/3/2
 **/
public class JavaDisCountCourse extends JavaCourse {
    public JavaDisCountCourse(Integer id, String name, Double price) {
        super(id, name, price);
    }

    public Double getDisCountPrice(){
        return super.getPrice() * 0.8;
    }
}

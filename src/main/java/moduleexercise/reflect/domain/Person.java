package moduleexercise.reflect.domain;

/**
 * @description:  演示练习 Java反射机制 相关api的实体父类
 * @author: gezx
 * @date: 2021/5/30 15:40
 */
public class Person implements China{
    public String name;     // 姓名
    public int age;         // 年龄

    private String sex;


    public Person(){   }

    public Person(String name) {
        this.name = name;
    }

    public Person(int age) {
        this.age = age;
    }

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "["+this.name+"  "+this.age+"]";
    }

    @Override
    public void sayChina(String str) {
        System.out.println("hello ," + str);
    }

    @Override
    public void sayHello(String name, int age) {
        System.out.println(name+"  "+age);
    }
}

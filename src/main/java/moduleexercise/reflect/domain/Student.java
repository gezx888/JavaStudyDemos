package moduleexercise.reflect.domain;

/**
 * @description:       演示练习 Java反射机制 相关api的实体子类
 * @author: gezx
 * @date: 2021/5/30 15:44
 */
public class Student extends Person implements Study{

    public String className;// 班级
    private String address;// 住址
    protected String test;

    public Student(String className, String address) {
        this.className = className;
        this.address = address;
    }

    public Student(String name, int age, String className, String address) {
        super(name, age);
        this.className = className;
        this.address = address;
    }

    public Student(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public String getAddress() {
        return address;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "姓名：" + name + ",年龄：" + age + ",班级：" + className + ",住址："
                + address;
    }
}

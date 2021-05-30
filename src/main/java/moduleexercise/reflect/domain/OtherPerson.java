package moduleexercise.reflect.domain;

/**
 * @description:  演示练习 Java反射机制 相关api的实体
 * @author: gezx
 * @date: 2021/5/30 15:40
 */
public class OtherPerson{
    public String name;     // 姓名
    public int age;         // 年龄

    private String sex;


    public OtherPerson(){   }

    public OtherPerson(String name) {
        this.name = name;
    }

    public OtherPerson(int age) {
        this.age = age;
    }

    private OtherPerson(String name, int age){
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

    @Override
    public String toString() {
        return "["+this.name+"  "+this.age+"]";
    }

    // 执行结果看出不能获取到
    private void work(){
        System.out.println(" 看看修饰符为private的方法能不能通过反射的 Class.getMethods() api获取到");
    }
}

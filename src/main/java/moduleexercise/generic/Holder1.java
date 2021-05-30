package moduleexercise.generic;

/**
 * 在 Holder1 中，有一个用 Object 引用的变量。因为任何类型都可以向上转型为 Object，所以这个 Holder 可以接受任何类型。
 * 在取出的时候 Holder 只知道它保存的是一个 Object 对象，所以要强制转换为对应的类型。在 main 方法中， holder1 先是保存了一个字符串，
 * 也就是 String 对象，接着又变为保存一个 Integer 对象(参数 1 会自动装箱)。从 Holder 中取出变量时强制转换已经比较麻烦，这里还要记住不同的类型，
 * 要是转错了就会出现运行时异常。
 *
 * @description:    如果有一个类Holder用于包装一个变量，并且这个变量的类型可能是任意的，在没有泛型机制之前可以如下这样写：
 * @author: gezx
 * @date: 2021/5/31 0:06
 */
public class Holder1 {
    private Object obj;

    public Holder1(Object obj){
        this.obj = obj;
    }

    public Object get() {
        return obj;
    }

    public void set(Object obj) {
        this.obj = obj;
    }

    public static void main(String[] args) {
        Holder1 holder1 = new Holder1("not generic");
        String str = (String) holder1.get();
        System.out.println(str);
        holder1.set(1);
        Integer integer = (Integer) holder1.get();
        System.out.println(integer);
    }
}

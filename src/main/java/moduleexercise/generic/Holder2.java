package moduleexercise.generic;

/**
 * 在 Holder2 中， 变量 a 是一个参数化类型 T，T 只是一个标识，用其它字母也是可以的。创建 Holder2 对象的时候，
 * 在尖括号中传入了参数 T 的类型，那么在这个对象中，所有出现 T 的地方相当于都用 String 替换了。现在的 get 的取出来的不是 Object ，
 * 而是 String 对象，因此不需要类型转换。另外，当调用 set 时，只能传入 String 类型，否则编译无法通过。这就保证了 holder2 中的类型安全，
 * 避免由于不小心传入错误的类型。
 *
 * @description:        看看 Holder 的泛型版本： 泛型类 与 泛型接口 的使用都比较简单，如下所示，直接在类/接口名后面 通过 <> 泛型标识符进行定义
 * @author: gezx
 * @date: 2021/5/31 0:13
 */
public class Holder2<T> {
    private T obj;

    public Holder2(T obj){
        this.obj = obj;
    }

    public T get() {
        return obj;
    }

    public void set(T obj) {
        this.obj = obj;
    }

    public static void main(String[] args) {
        Holder2<String> holder2 = new Holder2<>("Generic");
        String str = holder2.get();
        System.out.println(str);

        holder2.set("字符串可以");
//        holder2.set(1);           //无法编译   参数 1 不是 String 类型
    }

}

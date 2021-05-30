package moduleexercise.generic;

/**
 * GenericMethod 类本身不是泛型的，创建它的对象的时候不需要传入泛型参数，但是它的方法 func 是泛型方法。
 * 在返回类型之前是它的参数标识 <K,V> —— （这样也是定义泛型方法的语法，<> 泛型标识符）注意这里有两个泛型参数，所以泛型参数可以有多个。
 *
 * @description:  泛型不仅可以针对类，还可以单独使某个方法是泛型的，如下例子：
 * @author: gezx
 * @date: 2021/5/31 0:21
 */
public class GenericMethod {
    public <K,V> void func(K k,V v){
        System.out.println(k.getClass().getSimpleName());
        System.out.println(v.getClass().getSimpleName());
    }

    public static void main(String[] args) {
        GenericMethod gm = new GenericMethod();
        gm.func(new Integer(0),new String("generic"));
    }
}

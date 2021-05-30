package moduleexercise.reflect.domain;

/**
 * @description:       接口属性自动 会有 public final 修饰，方法 自动被 public修饰
 * @author: gezx
 * @date: 2021/5/30 17:21
 */
public interface China {
    public static final String name="Rollen";
    int age=20;

    public void sayChina(String str);
    void sayHello(String name, int age);
}

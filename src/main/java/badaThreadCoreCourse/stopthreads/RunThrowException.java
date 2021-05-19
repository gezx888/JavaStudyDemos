package badaThreadCoreCourse.stopthreads;

/**
 * @className: RunThrowException
 * @description:                run无法抛出checked Exception，只能用try/catch  因为父类接口中的run方法没有抛出异常
 * @author: gezx
 * @date: 2021/5/18
 **/
public class RunThrowException {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("run方法不能抛出异常");
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void work() throws Exception {
        throw new Exception();
    }

}

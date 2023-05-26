package top.codingmore.codingmorehelloword.testthreadlocal;

public class ThreadLocalTest {

    private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    private static final ThreadLocal<String> threadLocal2 = new ThreadLocal<>();

    public static void main(String[] args) {
        Integer reqId = 5;
        ThreadLocalTest tt = new ThreadLocalTest();
        tt.setReqId(reqId);

        threadLocal.remove();
        threadLocal2.remove();
    }
    public static void  setReqId(Integer integer){
        threadLocal.set(integer);
        threadLocal2.set("string123");
        doBussiness();
    }

    private static void doBussiness() {
        System.out.println("首先打印reqId="+threadLocal.get());
        System.out.println("首先打印String="+threadLocal2.get());

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程启动");
                System.out.println("在子线程获取reqId"+threadLocal.get());
                System.out.println("在子线程获取String="+threadLocal2.get());
            }
        }).start();
    }

}

package top.codingmore.codingmorehelloword.testthreadlocal;
//验证InheritableThreadLocal的特性
public class InheritableThreadLocalTest1 {

    private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    private static final InheritableThreadLocal inheritableThreadLocal = new InheritableThreadLocal();
    public static void main(String[] args) {
        Integer reqId = 5;
        InheritableThreadLocalTest1 tt = new InheritableThreadLocalTest1();
        tt.setReqId(reqId);
    }
    public static void  setReqId(Integer integer){
        inheritableThreadLocal.set(integer);
        doBussiness();
    }

    private static void doBussiness() {
        System.out.println("首先打印reqId"+inheritableThreadLocal.get());
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程启动");
                System.out.println("在子线程获取reqId"+inheritableThreadLocal.get());
            }
        }).start();
    }

}

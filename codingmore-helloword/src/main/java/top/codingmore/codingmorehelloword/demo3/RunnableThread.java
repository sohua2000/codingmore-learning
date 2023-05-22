package top.codingmore.codingmorehelloword.demo3;

public class RunnableThread implements Runnable{
    @Override
    public void run() {
        System.out.println("'用实现Runnable接口实现线程'");
    }
    public static void main(String[] args) {
        new RunnableThread().run();
    }

}

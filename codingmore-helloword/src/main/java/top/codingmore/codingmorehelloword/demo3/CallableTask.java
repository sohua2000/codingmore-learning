package top.codingmore.codingmorehelloword.demo3;

import java.util.Random;
import java.util.concurrent.Callable;

class CallableTask implements Callable<Integer> {
    @Override
    public Integer call() throws Exception { return new Random().nextInt();}

    public static void main(String[] args) {
        try {
            new CallableTask().call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
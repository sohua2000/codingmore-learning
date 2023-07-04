package top.codingmore.codingmorehelloword.future;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class SupplyAsyncTest09 {

    public static CompletableFuture<String> divisionOperation(int a, int b) {

//        return CompletableFuture.supplyAsync(() -> a / b);

        return CompletableFuture.supplyAsync(() -> a / b).handle((result, ex) -> {
            if (ex != null) {
//                ex.printStackTrace();
                return "结果为：运算异常。----"+ ex.getLocalizedMessage();
            }
            return "结果为：" + result;
        });
    }

    public static void main(String[] args) {
        CompletableFuture<String> result1 = divisionOperation(4, 2);
        try {
            System.out.println(result1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        CompletableFuture<String> result2 = divisionOperation(4, 0);
        try {
            System.out.println(result2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

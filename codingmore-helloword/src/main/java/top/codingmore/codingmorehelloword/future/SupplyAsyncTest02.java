package top.codingmore.codingmorehelloword.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class SupplyAsyncTest02 {

    public static void main(String[] args) {
        PrintTool.printTimeAndThread("顾客进入餐厅。。。");
        PrintTool.printTimeAndThread("顾客开始点餐。。。");
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            PrintTool.printTimeAndThread("厨师开始炒菜。。。");
            PrintTool.sleep(500);
            CompletableFuture<String> waiterCF = CompletableFuture.supplyAsync(() -> {
                PrintTool.printTimeAndThread("服务员开始打饭。。。");
                PrintTool.sleep(200);
                return "米饭";
            });
            return String.format("番茄炒蛋+%s", waiterCF.join());
        });
        PrintTool.printTimeAndThread("顾客玩手机中。。。");
        try {
            PrintTool.printTimeAndThread(String.format("顾客开始吃%s", cf.get()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

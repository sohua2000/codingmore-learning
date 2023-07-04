package top.codingmore.codingmorehelloword.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class SupplyAsyncTest04 {

    public static void main(String[] args) {
        PrintTool.printTimeAndThread("顾客进入餐厅。。。");
        PrintTool.printTimeAndThread("顾客开始点餐。。。");
        CompletableFuture<String> chefCF = CompletableFuture.supplyAsync(() -> {
            PrintTool.printTimeAndThread("厨师开始炒菜。。。");
            PrintTool.sleep(500);
            return "番茄炒蛋";
        });
        CompletableFuture<String> waiterCF = CompletableFuture.supplyAsync(() -> {
            PrintTool.printTimeAndThread("服务员开始煮饭。。。");
            PrintTool.sleep(1500);
            return "米饭";
        });
        PrintTool.printTimeAndThread("顾客玩手机中。。。");
        try {
            PrintTool.printTimeAndThread(String.format("顾客开始吃%s+%s", chefCF.get(), waiterCF.get()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

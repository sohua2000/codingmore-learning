package top.codingmore.codingmorehelloword.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class SupplyAsyncTest03_2 {

    public static void main(String[] args) {
        PrintTool.printTimeAndThread("顾客进入餐厅。。。");
        PrintTool.printTimeAndThread("顾客开始点餐。。。");
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            PrintTool.printTimeAndThread("厨师开始炒菜。。。");
            PrintTool.sleep(500);
            return "番茄炒蛋";
        }).thenComposeAsync(dish -> {
            PrintTool.printTimeAndThread("服务员A准备盛饭，突然接到电话要去做其他事情，他将打饭任务交给服务员B。。。");
            return CompletableFuture.supplyAsync(() -> {
                PrintTool.printTimeAndThread("服务员B开始打饭。。。");
                PrintTool.sleep(200);
                return dish + "+米饭";
            });
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

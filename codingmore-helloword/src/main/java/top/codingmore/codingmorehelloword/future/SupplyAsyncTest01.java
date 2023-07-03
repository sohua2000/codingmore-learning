package top.codingmore.codingmorehelloword.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class SupplyAsyncTest01 {
    public static void main(String[] args) {
        PrintTool.printTimeAndThread("顾客进入餐厅。。。");
        PrintTool.printTimeAndThread("顾客开始点餐。。。");
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(()->{
            PrintTool.printTimeAndThread("厨师开始炒菜。。。");
            PrintTool.sleep(500);
            PrintTool.printTimeAndThread("厨师开始打饭。。。");
            PrintTool.sleep(200);
            return "番茄炒蛋+米饭";
        });
        PrintTool.printTimeAndThread("顾客玩手机中。。。");
        try {
            PrintTool.printTimeAndThread(String.format("顾客开始吃%s",cf.get()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}

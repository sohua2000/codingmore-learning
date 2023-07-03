package top.codingmore.codingmorehelloword.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class SupplyAsyncTest03 {

    public static void main(String[] args) {
        PrintTool.printTimeAndThread("顾客进入餐厅。。。");
        PrintTool.printTimeAndThread("顾客开始点餐。。。");
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            PrintTool.printTimeAndThread("厨师开始炒菜。。。");
            PrintTool.sleep(500);
            return "番茄炒蛋";
            //whenComplete和whenCompleteAsync 的区别是whenComplete 是执行当前任务的线程继续执行whenComplete的任务。
            //whenCompleteAsync： 是把whenCompleteAsync的任务继续提交给线程池来进行执行。
        }).thenCompose(dish -> CompletableFuture.supplyAsync(() -> {
            PrintTool.printTimeAndThread("服务员开始打饭。。。");
            PrintTool.sleep(200);
            return dish + "+米饭";
        }));
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

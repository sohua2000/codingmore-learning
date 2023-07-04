package top.codingmore.codingmorehelloword.future;

import java.util.concurrent.CompletableFuture;

public class ApplyToEitherTest {
    public static void main(String[] args) {
        PrintTool.printTimeAndThread("张三下班准备回家。。。");
        PrintTool.printTimeAndThread("张三等待906,539公交。。。");
        CompletableFuture<String> busCF = CompletableFuture.supplyAsync(() -> {
            PrintTool.printTimeAndThread("906 路公交在路上。。。");
            PrintTool.sleep(5000);
            return "906";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            PrintTool.printTimeAndThread("539 路公交在路上。。。");
            PrintTool.sleep(4000);
            return "539";
        }), first -> first + "路公交");
        PrintTool.printTimeAndThread("张三坐上" + busCF.join());
    }
}

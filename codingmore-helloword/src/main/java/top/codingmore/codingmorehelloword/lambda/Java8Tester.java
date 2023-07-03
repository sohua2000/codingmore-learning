package top.codingmore.codingmorehelloword.lambda;

public class Java8Tester {
    final static String salutation = "Hello! ";

    //lambda表达式，其实本质来讲，就是⼀个匿名函数。因此在写lambda表达式的时候，不需要关心方法名是什么。
    //实际上，我们在写lambda表达式的时候，也不需要关心返回值类型。
    /**
    lambda表达式的基础语法：
            (参数1,参数2,…) -> {
        方法体
    };
     参数部分：方法的参数列表，要求和实现的接口中的方法参数部分⼀致，包括参数的数量和类型。lambda表达式中的参数的类型可以省略不写。
     方法体部分 ： 方法的实现部分，如果接口中定义的方法有返回值，则在实现的时候，注意返回值的返回。
     -> : 分隔参数部分和方法体部分。





     **/
    public static void main(String args[]){
        Java8Tester tester = new Java8Tester();

        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;

        // 不用类型声明
        MathOperation subtraction = (a, b) -> a - b;

        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> { return a * b; };//有大括号的时候，里面的语句要有分号

        // 没有大括号及返回语句
        //return的精简
        //如果⼀个方法中唯⼀的⼀条语句是⼀个返回语句， 此时在省略掉大括号的同时， 也必须省略掉return。
        MathOperation division = (int a, int b) -> a / b;

        System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
        System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + tester.operate(10, 5, division));

        // 不用括号
        GreetingService greetService1 = message ->
                System.out.println("Hello " + message);

        // 用括号
        GreetingService greetService2 = (message) ->
                System.out.println("Hello " + message);

        greetService1.sayMessage("Runoob");
        greetService2.sayMessage("Google");

        GreetingService greetService3 = message ->
                System.out.println(salutation + message);
        greetService1.sayMessage("greetService3");
    }

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation){
        return mathOperation.operation(a, b);
    }
}

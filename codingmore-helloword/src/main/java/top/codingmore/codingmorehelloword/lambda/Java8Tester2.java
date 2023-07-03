package top.codingmore.codingmorehelloword.lambda;

public class Java8Tester2 {
    public static void main(String args[]) {
        int num = 1;
        //如果方法的参数列表中的参数数量 有且只有⼀个，此时，参数列表的小括号是可以省略不写的。
        Converter<Integer, String> s = (e) -> {System.out.println(String.valueOf(e + num));};
        s.convert(2);  // 输出结果为 3
//        num = 1;////报错信息：Local variable num defined in an enclosing scope must be final or effectively


        //如果方法的参数列表中的参数数量 有且只有⼀个，此时，参数列表的小括号是可以省略不写的。
        //只有当参数的数量是⼀个的时候可以省略， 多了、少了都不能省略。
        //省略掉小括号的同时， 必须要省略参数的类型
        //方法体⼤括号的精简 当⼀个方法体中的逻辑，有且只有⼀句的情况下，⼤括号可以省略
        Converter<Integer, String> s2 = e -> System.out.println(String.valueOf(e + num));
        s2.convert(3);  // 输出结果为 4


        //在 Lambda 表达式当中不允许声明一个与局部变量同名的参数或者局部变量。
        String first = "";
//        Comparator<String> comparator = (first, second) -> Integer.compare(first.length(), second.length());  //编译会出错
    }

    //如果说，⼀个接口中，要求实现类必须实现的抽象方法，有且只有⼀个！这样的接口，就是函数式接口。
    public interface Converter<T1, T2> {
        void convert(int i);
    }

}

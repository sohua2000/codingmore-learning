package top.codingmore.codingmorehelloword.lambda;

import org.assertj.core.util.Lists;

import java.util.List;
import java.util.Optional;

public class TestLambda {
    public static void main(String[] args) {
        List<String> list = null;
        List<String> newList = Optional.ofNullable(list).orElse(Lists.newArrayList());
        newList.forEach(x -> System.out.println(x));
    }
}

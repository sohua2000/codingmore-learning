package top.codingmore.codingmorehelloword.demo2;

public class MainDemo2 {
    public static void main(String[] args) {
        //第一种
        StuOne zs = StuOne.builder().name("zs").age(23).build();
        System.out.println(zs);
        //第二种
        StuTwo ls = StuTwo.builder().name("ls").age(24).build();
        System.out.println(ls);
        //第三种
        StuThree ww = StuThree.of("ww").setAge(25);
        System.out.println(ww);

        StuOne zs2 = StuOne.builder().name("ss").age(123).build();

    }
}

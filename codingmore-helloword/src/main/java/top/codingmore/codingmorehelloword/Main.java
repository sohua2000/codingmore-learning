package top.codingmore.codingmorehelloword;

import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test1() {
        Person p = new Person();
        System.out.println(p.address.city.toLowerCase());
    }

    private static void test2() {
        Logger logger = Logger.getGlobal();
        logger.info("start process...");
        logger.warning("memory is running out...");
        logger.fine("ignored.");
        logger.severe("process will be terminated...");
    }
}

class Person {
    String[] name = new String[2];
    Address address = new Address();
}

class Address {
    String city;
    String street;
    String zipcode;
}
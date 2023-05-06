package top.codingmore.codingmorehelloword.demo2;

import lombok.Data;

@Data
public class StuOne {
    private String name;

    private Integer age;
    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private String name;
        private int age;

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder age(int age){
            this.age = age;
            return this;
        }

        public StuOne build(){
            StuOne student = new StuOne();
            student.setAge(age);
            student.setName(name);
            return student;
        }
    }

}

package top.codingmore.codingmorehelloword.demo;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class TwoSum {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {

//        test1();
//        test2();

        int[] nums = {2,20,8,9,11,45};
        int target = 19;
        twoSum(nums,target);
    }


    /**
     * 给出指定数组，其中两数字相加之和等于目标数字，求出这两个数字的序号
     * @param nums 输入数组
     * @param target 目标数字
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(nums[i])) {
                System.out.println("return:"+hashMap+"=="+nums[i]);
                System.out.println("return:{0}-{1}"+hashMap.get(nums[i]));

                return new int[] { hashMap.get(nums[i]) + 1, i + 1 };
            } else {
                hashMap.put(target - nums[i], i);
                System.out.println(hashMap);
            }
        }

        return new int[] { 0, 0 };
    }
}

package String.NO179LargestNumber;

import java.util.Arrays;
import java.util.Comparator;

/**
 * -----------------笔记 证明传递性(反证法)---------------
 */
public class Solution {

    public static void main(String[] args) {
        int[] nums = new int[]{121, 12};
        System.out.println(new Solution().largestNumber(nums));
    }

    /**
     * 该方法存在问题！[121,12]此种情况下 本来12 121更好 但是由于比较函数定义的 120<121 那么就成了121 12
     *
     * @param nums
     * @return
     */
    public String largestNumber(int[] nums) {
        StringBuilder strB = new StringBuilder();
        int length = nums.length;
        Integer[] numsInteger = new Integer[length];
        for (int i = 0; i < length; i++) {
            numsInteger[i] = nums[i];
        }
        Arrays.sort(numsInteger, new myComparator2());
        if (numsInteger[0] == 0) {
            return "0";
        }
        for (int i = 0; i < length; i++) {
            strB.append(Integer.toString(numsInteger[i]));
        }
        return strB.toString();
    }

    /**
     * class myComparator<Integer> implements Comparator<Integer> 注意这种错误写法！！！！
     * 父类如果已经声明了泛型类型 那么这个父类仅仅就是一个普通类 里面的方法属性名称都确定
     * 子类继承 自然方法和属性名称也都确定 如果子类还定义了泛型 那么这个泛型仅仅作用在子类独有的
     * 方法或者属性上 与父类无关
     */
    private class myComparator implements Comparator<Integer> {

        /**
         * !!!!!!!!
         * 在sort源码中 首先是对传入的数组找到最长的有序子数组 而调用Comparator的compare函数时
         * 传入的参数是compare（后一个元素，前一个元素）如果返回值是-1 那么就调换顺序
         * 找到了最长有序子数组 然后之后的元素用二分插入 binarySort插入到前面的有序数组
         * <p>
         * 数组：1 2 6 5 8 8 4
         * 反转后：6 2 1 5 8 8 4（确定局部顺序）
         * 这里先确定了6 2 1的顺序，后面5 8 8 4的位置就是利用二分查找法来确定的！
         *
         * @param o1
         * @param o2
         * @return
         */
        @Override
        public int compare(Integer o1, Integer o2) {
            int digit1 = GetDigit(o1);
            int digit2 = GetDigit(o2);
            int differ = digit1 - digit2;
            if (differ > 0) {
                int val = o2 * differ * 10 - o1;
                if (val == 0) {
                    return 1;
                }
                return val;
            } else if (differ == 0) {
                return o2 - o1;
            } else {
                int val = o2 - o1 * Math.abs(differ) * 10;
                if (val == 0) {
                    return -1;
                }
                return val;
            }
        }

        /**
         * 获取整数位数
         *
         * @param num
         * @return
         */

        private int GetDigit(int num) {
            int count = 0;
            while (num != 0) {
                num = num / 10;
                count++;
            }
            return count;
        }
    }

    private class myComparator2 implements Comparator<Integer> {

        @Override
        public int compare(Integer o2, Integer o1) {
            /*int digit1 = GetDigit(o1);
            int digit2 = GetDigit(o2);
            int num1 = (int) (o2 * Math.pow(10, digit1) + o1);
            int num2 = (int) (o1 * Math.pow(10, digit2) + o2);
            return num2 - num1;*/
            return (int) (o1 * Math.pow(10, GetDigit(o2)) + o2 - o2 * Math.pow(10, GetDigit(o1)) - o1);
        }

        private int GetDigit(int num) {
            int count = 0;
            int i = 1;
            do {
                i *= 10;
                count++;
            } while (num != num % i);
            return count;
        }

    }
}

/*
class myComparator<Integer> implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        int digit1=GetDigit(o1);


    }

    */
/**
 * 获取整数位数
 *
 * @param num
 * @return
 *//*

    private int GetDigit(int num) {
        int count = 0;
        while (num != 0) {
            num = num / 10;
            count++;
        }
        return count;
    }

}*/

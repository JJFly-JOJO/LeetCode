package Array.NO189RotateArray;

public class Solution {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;//对于k>length情况  length整数倍回到原数组状态
        //直接让数组元素跳转到最终位置 数组元素有n个则要跳转n次 时间复杂度O(n)
        //将数组想象成一个圆环 循环遍历 最终到起始点
        int count = 0;
        int size = nums.length;
        for (int start = 0; count < size; start++) {
            int curent = start;
            int pre = nums[curent];
            do {
                int next = (curent + k) % size;//do while 保证了一个循环之后回到原点能够再循环一次
                int temp = nums[next];
                nums[next] = pre;
                pre = temp;
                curent = next;
                count++;
            } while (curent != start);//如果数组长度是k的整数倍 每次循环最后一次（第一次大于length时）会正好到达start处
            //如果不能被k整除 那么循环会错位 一直错位到起始处
        }
    }

    public void rotate2(int[] nums, int k) {
        k = k % nums.length;//排除重复循环的情况
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
        //复杂度 n/2+k/2+(n-k)/2=n
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {//start<end优于start!=end 前者保证了start小于end这个条件
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}

package Math.NO167TwoSumIIInputarrayissorted;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/19 17:06
 */
public class Solution {

    public static void main(String[] args) {
        int[] numbers = new int[]{2, 7, 11, 15};
        for (int temp : new Solution().twoSum(numbers, 9)) {
            System.out.println(temp);
        }
    }

    public int[] twoSum(int[] numbers, int target) {
        //先利用二分查找找到小于target的第一个数
        int length = numbers.length;
        int left = 0;
        int right = length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (numbers[mid] > target) {
                right = mid;
            } else {
                //由题意不会出现相等情况
                left = mid + 1;
            }
        }
        //得到的left(终止情况时left=right)是第一个大于target的数的下标（由于left=mid+1而非right=mid-1）
        //双指针从两边向中间走 找到target及停止
        left = 0;
        //2, 7, 11, 15 这里找到的righ是对应的11(下标2) 可以不需要2-1得到7的下标（其实减1加1对下面的搜索没有影响）
        //right = left;
        int[] res = new int[2];
        while (true) {
            int val = numbers[left] + numbers[right];
            if (val == target) {
                break;
            } else if (val < target) {
                //左下标增大 数增大
                left++;
            } else {
                //右下标减小 数减小
                right--;
            }
        }
        res[0] = left + 1;
        res[1] = right + 1;
        return res;
    }
}

package Math.NO367ValidPerfectSquare;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().isPerfectSquare(808201));
    }

    /**
     * 初始范围为0到num过于粗糙 会造成时间超时！！！！！！！！！！！！
     * 通过不等式证明我们可以知道（A/2）平方>=A---->A>=4
     * 在4的前面 3、2、0是非完全平方数 1是非完全 因此我们可以把1单独拿出来 而3 2 0放入二分查找中（肯定找不到 返回false）
     *
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
        if (num == 1) {
            return true;
        }
        if (num == 0) {
            return false;
        }
        int left = 0;
        int right = num / 2;
        while (left < right) {
            //int mid = (left + right) / 2;
            //防止溢出！！！！！！！！！！！！
            int mid = left + (right - left) / 2;
            long cur = (long)mid * mid;
            if (cur > num) {
                right = mid;
            } else if (cur == num) {
                return true;
            } else {
                left = mid + 1;
            }
        }
        if (left * left == num) {
            return true;
        }
        return false;
    }

}

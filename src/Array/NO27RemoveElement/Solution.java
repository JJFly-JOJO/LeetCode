package Array.NO27RemoveElement;

public class Solution {
    public static void main(String[] args) {
        int[] nums = {3, 2, 2, 3};
        int val = 3;
        int numOfArray = new Solution().removeElement(nums, val);
        for (int i = 0; i < numOfArray; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    public int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }
}

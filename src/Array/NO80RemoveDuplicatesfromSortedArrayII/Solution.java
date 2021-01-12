package Array.NO80RemoveDuplicatesfromSortedArrayII;

public class Solution {
    public int removeDuplicates(int[] nums) {
        int length = nums.length;
        if (length == 0) return 0;
        int i = 0;
        int count = 0;
        for (int j = 1; j < length; j++) {
            if (nums[i] == nums[j])
                count++;
            else {
                if (count >= 2) {
                    length = length - j + i + 2;
                    nums = removeElement(nums, i = i + 2, j);
                    j=i;
                    count=0;//归零 重新遍历
                } else {
                    i = j;
                    count=0;
                }
            }
        }
        if(count>=2)//针对尾部{1,1,1}情况
            length=length-count+1;
        return length;
    }

    public int[] removeElement(int[] nums, int indexI, int indexJ) {
        for (; indexJ < nums.length; indexJ++) {
            nums[indexI++] = nums[indexJ];
        }
        return nums;
    }
}

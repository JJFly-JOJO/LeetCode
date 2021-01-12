package Array.NO80RemoveDuplicatesfromSortedArrayII;

public class Solution2 {
    public int removeDuplicates(int[] nums) {
        if(nums.length==0) return 0;
        int count=1;
        int i=1;int j=1;
        for(;i<nums.length;i++){
            if(nums[i]==nums[i-1]){
                count++;
                if(count<3){
                    nums[j++]=nums[i];
                }
            }else{
                nums[j++]=nums[i];
                count=1;
            }
        }
        return j;
    }
}

package Math.NO66PlusOne;

public class Solution {

    public int[] plusOne(int[] digits){
        int length=digits.length;
        for(int i=length-1;i>=0;i--){
            if(++digits[i]!=10)
                return digits;
            digits[i]=0;//10进位
        }
        //如果999..此种情况 根本不需要考虑对原来的数组进行移位等操作 直接创建新数组 首位置1
        digits=new int[length+1];
        digits[0]=1;
        return digits;
    }
}

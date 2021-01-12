package String.NO06ZigZagConversion;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/13 15:54
 * <p>
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 * 思路：
 * 以        为单位
 * L
 * E   O
 * E C
 * T
 * #######注意：易错点：nums[index += 2] 是先对index加2 再进行数组取值
 */
public class Solution {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5};
        int index = 0;
        System.out.println(nums[index += 2]);
        char[] chars = new char[3];
        System.out.println("----" + String.valueOf(chars) + "-----");

    }

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int length = chars.length;
        //加一 防止因为i++导致的越界
        char[] res = new char[length + 1];
        int i = 0;
        int gap = numRows + numRows - 2;
        //第一排 字母下标相差n+n-2
        int index = 0;
        while (index < length) {
            res[i++] = chars[index];
            index += gap;
        }
        int midIndex = gap - 1;
        for (int row = 2; row < numRows; row++) {
            //中间 2~n-1排
            index = row - 1;
            int nextIndex = midIndex--;
            while (index < length) {
                res[i++] = chars[index];
                index += gap;
                if (nextIndex < length) {
                    res[i++] = chars[nextIndex];
                    nextIndex += gap;
                }
            }
        }
        //最后一排
        index = numRows - 1;
        while (index < length) {
            res[i++] = chars[index];
            index += gap;
        }
        return String.valueOf(res, 0, res.length - 1);
    }
}

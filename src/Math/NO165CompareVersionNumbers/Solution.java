package Math.NO165CompareVersionNumbers;

import javafx.util.Pair;

public class Solution {

    public static void main(String[] args) {
        String str1 = "123.123";
        String[] nums = str1.split("\\.");//java中\\表示一个\ 表示其后的字符有特殊意义或者普通意义
        for (int i = 0; i < nums.length; i++)
            System.out.println(nums[i]);

        System.out.println(str1.substring(0, 3));
    }

    //获得字符串分割块
    public static Pair<Integer, Integer> getNextChunk(String version, int index, int length) {
        if (index > length - 1)
            return new Pair<Integer, Integer>(index, 0);//超过块 值为0
        int end = index;
        while (++end < length && version.charAt(end) != '.') ;

        int num = Integer.parseInt(version.substring(index, end));

        /*int num = 0;
        if (end == length)
            num = Integer.parseInt(version.substring(index, end));//如果end等于length
        else
            num = Integer.parseInt(version.substring(index, end));//如果end为‘.’的位置下标*/
        return new Pair<>(end + 1, num);

    }

    public int compareVersion(String version1, String version2) {
        int index1 = 0, index2 = 0;
        int length1 = version1.length(), length2 = version2.length();

        Pair<Integer, Integer> tempPair;
        while (index1 < length1 || index2 < length2) {
            tempPair = getNextChunk(version1, index1, length1);
            index1 = tempPair.getKey();
            int temp1 = tempPair.getValue();

            tempPair = getNextChunk(version2, index2, length2);
            index2 = tempPair.getKey();
            int temp2 = tempPair.getValue();
            if (temp1 != temp2)
                return temp1 > temp2 ? 1 : -1;
        }
        return 0;
    }
}

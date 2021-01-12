package Array.NO275HIndexII;

/**
 * 二分搜索
 */
public class Solution {
    public static void main(String[] args) {
        int[] citation = new int[]{0, 1, 1, 1, 6};
        System.out.println(new Solution().hIndex(citation));
    }

    public int hIndex(int[] citatioins) {
        int length = citatioins.length;
        if (length == 0) {
            return 0;
        }
        int left = 0;
        int right = length - 1;
        while (left != right) {
            int curIndex = (right + left) / 2;
            int curLength = length - curIndex;
            if (citatioins[curIndex] > curLength) {
                right = curIndex;
            } else if (citatioins[curIndex] < curLength) {
                left = curIndex + 1;
            } else {
                return curLength;
            }
        }
        return (length - right) <= citatioins[right] ? length - right : 0;
    }

}

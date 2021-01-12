package String.NO266PalindromePermutation;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/23 10:51
 * @description ----------------更清晰的思路 奇数个数的字母不可能超过1个 不需要讨论字符的偶数个数还是奇数个数---------
 */
public class SolutionII {

    public boolean canPermutePalindrome(String s) {
        char[] chars = s.toCharArray();
        int[] count = new int[123];
        for (char c : chars) {
            count[c]++;
        }
        int tag = 0;
        for (int i : count) {
            if ((i & 1) == 1) {
                tag++;
            }
            if (tag > 1) {
                return false;
            }
        }
        return true;
    }

}

package String.NO214ShortestPalindrome;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/25 15:18
 * @description --------------利用KMP算法求解--------------
 */
public class SolutionII {

    public static void main(String[] args) {
        System.out.println(new SolutionII().shortestPalindrome("aacecaaa"));
    }

    public String shortestPalindrome(String s) {
        //***reverse()没有进行拷贝 是原地反转------------------------------易错
        StringBuilder queryS = new StringBuilder(s).reverse();
        String pattern = s;
        int[] nextIndex = computeNext(pattern);
        //KMP
        int i = 0;
        int j = 0;
        int len = queryS.length();
        while (i < len) {
            if (queryS.charAt(i) != pattern.charAt(j)) {
                j = nextIndex[j];
                if (j == -1) {
                    i++;
                    j = 0;
                }
            } else {
                i++;
                j++;
            }
        }
        return queryS.append(pattern.substring(j, len)).toString();
    }

    private int[] computeNext(String pattern) {
        char[] c = pattern.toCharArray();
        int[] nextIndex = new int[c.length + 1];
        //initial
        nextIndex[0] = -1;
        nextIndex[1] = 0;
        int i = 2;
        int ptr = 0;
        while (i < c.length) {
            if (c[i - 1] == c[ptr]) {
                nextIndex[i] = ptr + 1;
                i++;
                ptr++;
            } else if (ptr == 0) {
                nextIndex[i++] = ptr;
            } else {
                ptr = nextIndex[ptr];
            }
        }
        return nextIndex;
    }

}

package WeekContest.SingleWeek.NO239;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/5/2 11:16
 * @description
 */
public class SolutionIII {

    public static void main(String[] args) {
        System.out.println(new SolutionIII().getMinSwaps("059", 5));
    }

    public int getMinSwaps(String num, int k) {
        int res = 0;
        String kStr = find(num, k);
        char[] src = num.toCharArray();
        char[] tar = kStr.toCharArray();
        for (int i = 0; i < src.length; i++) {
            if (src[i] != tar[i]) {
                int idx = i + 1;
                while (src[idx] != tar[i]) {
                    idx++;
                }
                res += idx - i;
                char t = src[idx];
                for (int j = idx; j > i; j--) {
                    src[j] = src[j - 1];
                }
                src[i] = t;
            }
        }
        return res;
    }

    private String find(String num, int k) {
        String cur = num;
        for (int i = 0; i < k; i++) {
            cur = getNext(cur);
        }
        return cur;
    }

    private void swap(char[] chars, int idx1, int idx2) {
        char c = chars[idx1];
        chars[idx1] = chars[idx2];
        chars[idx2] = c;
    }

    private String getNext(String str) {
        char[] chars = str.toCharArray();
        int idx1 = chars.length - 2;
        while (chars[idx1] >= chars[idx1 + 1]) {
            idx1--;
        }
        int idx2 = chars.length - 1;
        while (chars[idx2] <= chars[idx1]) {
            idx2--;
        }
        swap(chars, idx1, idx2);
        //reverse
        int l = idx1 + 1;
        int r = chars.length - 1;
        while (l < r) {
            swap(chars, l++, r--);
        }
        return new String(chars);
    }

}

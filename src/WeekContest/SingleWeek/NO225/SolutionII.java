package WeekContest.SingleWeek.NO225;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/24 10:49
 * @description ---------------桶排序（基数排序） 相同的字符放到一个桶中 记录个数 而不是利用排序--------------
 * 思路：1.模拟：枚举每个字母（26次） 类比NO1733
 * 2.优化：模拟的同时利用前缀和来保存当前分界字符之前要修改的字符数量
 */
public class SolutionII {

    public int minCharacters(String a, String b) {
        char[] bucketA = new char[26];
        char[] bucketB = new char[26];
        for (char c : a.toCharArray()) {
            bucketA[c - 'a']++;
        }
        for (char c : b.toCharArray()) {
            bucketB[c - 'a']++;
        }
        int res = Integer.MAX_VALUE;
        int subA = 0;
        int subB = 0;
        //枚举每一个修改的字符 当前字符作为分界点
        //--------------注意！字母z是无法作为分界点的 当z作为分界点 要求字典序大的字符串 需要满足所有字母大于z 这是不满足题意的
        for (int i = 0; i < 25; i++) {
            //保存前缀和
            subA += bucketA[i];
            subB += bucketB[i];
            //method 1
            int m1 = a.length() - subA + subB;
            //method 2
            int m2 = b.length() - subB + subA;
            //method 3
            int m3 = a.length() + b.length() - bucketA[i] - bucketB[i];
            res = Math.min(Math.min(res, m3), Math.min(m1, m2));
        }
        //字母z 只存在method 3情况
        return Math.min(res, a.length() + b.length() - bucketA[25] - bucketB[25]);
    }
}

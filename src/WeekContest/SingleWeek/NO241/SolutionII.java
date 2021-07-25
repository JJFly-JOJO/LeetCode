package WeekContest.SingleWeek.NO241;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/5/16 11:00
 * @description "100"
 */
public class SolutionII {

    public static void main(String[] args) {
        StringBuilder sb=new StringBuilder();
        sb.append(new char[1], 0, 1);
        
        System.out.println(new SolutionII().minSwaps("100"));
    }

    public int minSwaps(String s) {
        char[] chars = s.toCharArray();
        int size = chars.length;
        int countOne = 0;
        int countZero = 0;
        for (char c : chars) {
            if (c == '1') {
                countOne++;
            } else {
                countZero++;
            }
        }
        int res = Integer.MAX_VALUE;
        //偶数对半 与 奇数对半
        //多1的元素必须在前 10101  01011
        //101010
        boolean flag = true;
        if (countOne == (size + 1) / 2 && countZero == size / 2) {
            int differ = 0;
            for (char c : chars) {
                if (flag) {
                    if (c != '1') {
                        differ++;
                    }
                    flag = false;
                } else {
                    if (c != '0') {
                        differ++;
                    }
                    flag = true;
                }
            }
            res = Math.min(res, differ / 2);
        }
        //01010
        flag = true;
        if (countZero == (size + 1) / 2 && countOne == size / 2) {
            int differ = 0;
            for (char c : chars) {
                if (flag) {
                    if (c != '0') {
                        differ++;
                    }
                    flag = false;
                } else {
                    if (c != '1') {
                        differ++;
                    }
                    flag = true;
                }
            }
            res = Math.min(res, differ / 2);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

}

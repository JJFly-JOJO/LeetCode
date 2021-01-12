package NowCoder.ByteDance2019Spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/12 10:49
 * @description --------
 * 1. 三个同样的字母连在一起，一定是拼写错误，去掉一个的就好啦：比如 helllo -> hello
 * 2. 两对一样的字母（AABB型）连在一起，一定是拼写错误，去掉第二对的一个字母就好啦：比如 helloo -> hello
 * 3. 上面的规则优先“从左到右”匹配，即如果是AABBCC，虽然AABB和BBCC都是错误拼写，应该优先考虑修复AABB，结果为AABCC
 * <p>
 * helloo
 * wooooooow
 * <p>
 * hello
 * woow
 */
public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String curStr = scanner.nextLine();
            System.out.println(correct(curStr));
        }
    }

    private static String correct(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        List<Character> cList = new ArrayList<>();
        cList.add(chars[0]);
        int[] count = new int[len];
        count[0] = 1;
        for (int i = 1; i < len; i++) {
            if (chars[i] == chars[i - 1]) {
                count[cList.size() - 1]++;
            } else {
                count[cList.size()]++;
                cList.add(chars[i]);
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < cList.size(); ) {
            if (count[i] == 1) {
                res.append(cList.get(i));
                i++;
            } else if (count[i] >= 3) {
                count[i] = 2;
            } else if (count[i] == 2) {
                if (i != 0 && count[i] == count[i - 1]) {
                    count[i] = 1;
                } else {
                    res.append(cList.get(i));
                    res.append(cList.get(i));
                    i++;
                }
            }
        }
        return res.toString();

    }

}

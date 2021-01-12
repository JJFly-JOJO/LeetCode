package Backtracking.NO93RestoreIPAddresses;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/14 10:07
 * @description
 */
public class Solution {

    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        //特判
        if (s.length() > 12) {
            return res;
        }
        StringBuilder sb = new StringBuilder(s);
        StringBuilder path = new StringBuilder();
        for (int i = 1; i <= 3; i++) {
            if (i <= sb.length()) {
                String sub = sb.substring(0, i);
                int v = Integer.parseInt(sub);
                if (sub.length() == 1 || (sub.charAt(0) != '0' && v <= 255)) {
                    path.append(sub);
                    backtracking(i, 1, sb, path, res);
                    path.delete(0, path.length());
                }
            }
        }
        return res;
    }

    private void backtracking(int start, int level,
                              StringBuilder sb, StringBuilder path, List<String> res) {
        if (level == 4 && start == sb.length()) {
            res.add(path.toString());
            return;
        }
        int l = path.length();
        path.append(".");
        for (int i = 1; i <= 3; i++) {
            int last = start + i;
            if (last <= sb.length()) {
                String sub = sb.substring(start, last);
                int v = Integer.parseInt(sub);
                if (sub.length() == 1 || (sub.charAt(0) != '0' && v <= 255)) {
                    int t = path.length();
                    path.append(sub);
                    backtracking(last, level + 1, sb, path, res);
                    path.setLength(t);
                }
            }
        }
        path.setLength(l);
    }

}

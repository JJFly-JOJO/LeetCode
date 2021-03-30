package UnionFind.NO721AccountsMerge;

import java.util.*;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/26 22:50
 * @description
 */
public class Solution {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> emailToIndex = new HashMap<>();
        Map<String, String> emialToName = new HashMap<>();
        int index = 0;
        for (List<String> a : accounts) {
            String name = a.get(0);
            for (int i = 1; i < a.size(); i++) {
                //覆盖index没有影响 只需要关注一个email即可
                //相当于将email1->1 email1->2 进行了一次union
                emailToIndex.put(a.get(i), index++);
                emialToName.put(a.get(i), name);
            }
        }
        Union u = new Union(index);
        for (List<String> a : accounts) {
            String firstE = a.get(1);
            int firstIndex = emailToIndex.get(firstE);
            //只需要将当前集合的其他节点与first节点union就能够表示整个union
            for (int i = 2; i < a.size(); i++) {
                u.unionF(firstIndex, emailToIndex.get(a.get(i)));
            }
        }
        //遍历所有的email 将其归类到对应的并查集
        //这里利用HashTable巧妙的解决了多个账户重复邮箱的情况
        Map<Integer, List<String>> uToEmail = new HashMap<>();
        for (String s : emailToIndex.keySet()) {
            uToEmail.computeIfAbsent(u.find(emailToIndex.get(s)), key -> new ArrayList<>()).add(s);
        }
        //
        List<List<String>> res = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> e : uToEmail.entrySet()) {
            List<String> list = e.getValue();
            Collections.sort(list);
            List<String> account = new ArrayList<>();
            account.add(emialToName.get(list.get(0)));
            account.addAll(list);
            res.add(account);
        }
        return res;
    }

    public class Union {
        public int[] u;

        public Union(int n) {
            u = new int[n];
            for (int i = 0; i < n; i++) {
                u[i] = i;
            }
        }

        public int find(int x) {
            if (u[x] != x) {
                u[x] = find(u[x]);
            }
            return u[x];
        }

        public boolean unionF(int x, int y) {
            int a = find(x);
            int b = find(y);
            if (a != b) {
                u[b] = a;
                return true;
            }
            return false;
        }
    }

}

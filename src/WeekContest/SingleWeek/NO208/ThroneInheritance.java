package WeekContest.SingleWeek.NO208;

import java.util.*;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/29 11:07
 * -----------NO1600 树 设计--------
 * -------直接利用集合构造多叉树-------
 */
public class ThroneInheritance {

    /**
     * map中使用list 是因为先生的肯定在前
     */
    Map<String, List<String>> fatherAndHisSon = new HashMap<>();
    Set<String> isDead = new HashSet<>();
    private String kingName;

    public ThroneInheritance(String kingName) {
        this.kingName = kingName;
        fatherAndHisSon.put(kingName, new ArrayList<>());
    }

    public void birth(String parentName, String childName) {
        List<String> tempList = fatherAndHisSon.get(parentName);
        if (tempList != null) {
            tempList.add(childName);
        } else {
            tempList = new ArrayList<>();
            tempList.add(childName);
            fatherAndHisSon.put(parentName, tempList);
        }
    }

    public void death(String name) {
        isDead.add(name);
    }

    public List<String> getInheritanceOrder() {
        List<String> res = new ArrayList<>();
        if (!isDead.contains(kingName)) {
            res.add(kingName);
        }
        dfs(fatherAndHisSon.get(kingName), res);
        return res;
    }

    private void dfs(List<String> strings, List<String> res) {
        if (strings == null) {
            return;
        }
        for (String tempStr : strings) {
            if (!isDead.contains(tempStr)) {
                res.add(tempStr);
            }
            dfs(fatherAndHisSon.get(tempStr), res);
        }
    }

}

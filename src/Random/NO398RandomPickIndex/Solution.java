package Random.NO398RandomPickIndex;

import java.util.*;

public class Solution {
    /**
     * key=元素 List存放元素下标
     */
    Map<Integer, List<Integer>> map;
    Random random = new Random();

    public Solution(int[] nums) {
        map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> curElementIndexArray = map.getOrDefault(nums[i], new ArrayList<>());
            curElementIndexArray.add(i);
            map.put(nums[i], curElementIndexArray);
        }
    }

    public int pick(int target) {
        List<Integer> targetList = map.get(target);
        return targetList.get(random.nextInt(targetList.size()));
    }
}

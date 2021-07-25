package Greedy.NO406QueueReconstructionbyHeight;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/6/1 11:03
 * @description
 */
public class Solution {

    public static void main(String[] args) {
        ArrayList<Integer> list=new ArrayList<>();
        //if (index > size || index < 0)
        //            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        list.add(4,1);
    }

    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (o1, o2) -> o1[0] - o2[0] == 0 ? o1[1] - o2[1] : o2[0] - o1[0]);
        ArrayList<int[]> list = new ArrayList<>();
        for (int[] p : people) {
            //add中的index下标只能是size之前 不能超过size
            list.add(p[1], p);
        }
        return list.toArray(new int[0][0]);
    }

}

package Array.NO277FindtheCelebrity;

import Array.NO252MeetingRooms.Relation;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/24 20:29
 * @description
 */
public class Solution extends Relation {
    public int findCelebrity(int n) {
        for (int i = 0; i < n; i++) {
            int j = 0;
            for (; j < n; j++) {
                if (i != j && (!knows(j, i) || knows(i, j))) {
                    break;
                }
            }
            if (j == n) {
                return i;
            }
        }
        return -1;
    }

    public int findCelebrityPromoted(int n) {
        //找到候选人 因为只有可能存在一个名人 或者 没有 名人谁都不认识 但是存在所有人都认识一个人 但这个人也认识其他人
        int candidate = 0;
        for (int i = 1; i < n; i++) {
            if (knows(candidate, i)) {
                candidate = i;
            }
        }
        for (int i = 0; i < n; i++) {
            //if (i != candidate && (knows(candidate, i) || !knows(i, candidate)))
            //改进的判断  注意到对角线上元素永远为true 也就是candidate认识candidate自己
            if (i != candidate && knows(candidate, i) || !knows(i, candidate)) {
                return -1;
            }
        }
        return candidate;
    }
}
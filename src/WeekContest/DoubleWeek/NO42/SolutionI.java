package WeekContest.DoubleWeek.NO42;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/29 11:13
 * @description
 */
public class SolutionI {

    public int countStudents(int[] students, int[] sandwiches) {
        Deque<Integer> s = new ArrayDeque<>();
        for (int i : students) {
            s.add(i);
        }
        int i = 0;
        for (; i < sandwiches.length; i++) {
            if (sandwiches[i] != s.peek()) {
                int size = s.size();
                int j = 0;
                for (; j < size; j++) {
                    if (sandwiches[i] == s.peek()) {
                        s.poll();
                        break;
                    } else {
                        s.add(s.poll());
                    }
                }
                if (j >= size) {
                    break;
                }
            } else {
                s.poll();
            }
        }
        return sandwiches.length - i;
    }

}

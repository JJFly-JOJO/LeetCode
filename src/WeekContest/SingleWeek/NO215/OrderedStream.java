package WeekContest.SingleWeek.NO215;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/16 11:49
 * @description
 */
class OrderedStream {

    private String[] strs;

    private int ptr = 1;

    private int n;

    public OrderedStream(int n) {
        strs = new String[n + 1];
        this.n = n;
    }

    public List<String> insert(int id, String value) {
        strs[id] = value;
        List<String> res = new ArrayList<>();
        if (id == ptr) {
            while (ptr <= n && strs[ptr] != null) {
                res.add(strs[ptr]);
                strs[ptr++] = null;
            }
        }
        return res;
    }
}

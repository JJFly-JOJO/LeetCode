package Random.NO470ImplementRand10UsingRand7;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/6/9 22:21
 * @description
 */
public class Solution extends SolBase {

    public int rand10() {
        int idx = 0;
        int range;
        int mod = 1;
        int limit = 40;
        int r;
        int c;
        do {
            if (mod == 1) {
                //reset
                r = rand7();
                c = rand7();
                idx = (r - 1) * 7 + c;
                range = 49;
                mod = range % 10;
                limit = range - mod;
            } else {
                r = idx - limit;
                c = rand7();
                idx = (r - 1) * 7 + c;
                range = mod * 7;
                mod = range % 10;
                limit = range - mod;
            }
        } while (idx > limit);
        return (idx - 1) % 10 + 1;
    }

}

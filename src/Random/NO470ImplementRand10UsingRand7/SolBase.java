package Random.NO470ImplementRand10UsingRand7;

import java.util.Random;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/6/9 22:22
 * @description
 */
public class SolBase {

    private Random random = new Random();

    public int rand7() {
        return random.nextInt(6) + 1;
    }

}

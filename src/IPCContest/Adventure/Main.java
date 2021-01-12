package IPCContest.Adventure;

import java.util.*;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/15 10:29
 * @description
 */
public class Main {

    public static long max = (long) 1e9 + 7;

    public static Map<Long, Map<Long, Long>> remember;

    public static Map<Long, Long> rememberF;

    public static Map<Long, Long> rememberG;

    public static void main(String[] args) {
        //System.out.println(Integer.MAX_VALUE);
        //System.out.println(gcd(6, 0));
        //List<Long> list = getFactor(24);
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        remember = new HashMap<>();
        rememberG = new HashMap<>();
        rememberF = new HashMap<>();
        scanner.nextLine();
        while (scanner.hasNextInt()) {
            System.out.println(fn(scanner.nextInt()));
        }
        for (int i = 0; i < T; i++) {
            long n = scanner.nextLong();
            long k = scanner.nextLong();

            System.out.println(gkn(n % max, k % max));
            scanner.nextLine();
        }
        int a = 0;
    }

    public static long gkn(long n, long k) {
        if (remember.containsKey(n)) {
            Long t = remember.get(n).get(k);
            if (t != null) {
                return t;
            }
        }
        long t;
        if (k == 1) {
            t = fn(gn(n)) % max;
        } else {
            if (k % 2 == 0) {
                t = gn(gkn(n, k - 1)) % max;
            } else {
                t = fn(gkn(n, k - 1)) % max;
            }
        }
        Map<Long, Long> m = remember.getOrDefault(n, new HashMap<>());
        m.put(k, t);
        remember.put(n, m);
        return t;
    }

    /**
     * 优化：n/d----->d
     *
     * @param n
     * @return
     */
    public static long gn(long n) {
        if (rememberG.containsKey(n)) {
            return rememberG.get(n);
        }
        List<Long> factors = getFactor(n);
        int size = factors.size();
        long res = 0L;
        for (int i = 0; i < size; i++) {
            //res += fn(n / factors.get(i));
            res += fn(factors.get(i));
        }
        rememberG.put(n, res);
        return res;
    }

    /**
     * 获取因数
     *
     * @param n
     * @return
     */
    public static List<Long> getFactor(long n) {
        List<Long> res = new ArrayList<>();
        long nn = (long) Math.sqrt((double) n);
        for (long i = 1; i <= nn; i++) {
            if (n % i == 0) {
                res.add(i);
                long o = n / i;
                if (o != i) {
                    res.add(o);
                }
            }
        }
        return res;
    }

    /**
     * f函数 只需要累加到n/2
     *
     * @param n
     * @return
     */
    public static long fn(long n) {
        if (rememberF.containsKey(n)) {
            return rememberF.get(n);
        }
        if (n == 1) {
            rememberF.put(1L, 1L);
            return 1;
        }
        //long last = n - 1;
        long res = 0;
        long half = n / 2;
        for (long i = 1L; i <= half; i++) {
            if (gcd(i, n - i) == 1) {
                if (i != n - i) {
                    res += 2;
                } else {
                    res++;
                }
            }
        }
        rememberF.put(n, res);
        return res;
    }

    /**
     * 最大公因数
     *
     * @param a
     * @param b
     * @return
     */
    public static long gcd(long a, long b) {
        long t = a % b;
        return t == 0 ? b : gcd(b, t);
    }

}

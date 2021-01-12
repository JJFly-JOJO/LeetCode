package IPCContest.Attack;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/14 13:57
 * @description http://oj.saikr.com/contest/8/problem/B
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //n个时刻
        int n = scanner.nextInt();
        //一共m次攻击
        int m = scanner.nextInt();
        //k 连续k个时刻必须要发动至少一次攻击
        int k = scanner.nextInt();
        scanner.nextLine();
        int[] attack = new int[n];
        for (int i = 0; i < n; i++) {
            attack[i] = scanner.nextInt();
        }
        //
        findMaxBucket(n, m, k, attack);
        //findMax(n, m, k, attack);
    }

    private static void findMaxBucket(int n, int m, int k, int[] attack) {
        long res = 0L;
        int mod = n % k;
        int addNum = k - mod;
        n = n + addNum;
        int[] att = new int[n];
        for (int i = addNum; i < n; i++) {
            att[i] = attack[i - addNum];
        }
        attack = att;
        for (int w = 0; w < k; w++) {
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
            long tRes = 0L;
            int i = w;
            int count = 0;
            while (i < n) {
                int last = i + k;
                if (last > n) {
                    for (; i < n; i++) {
                        maxHeap.add(attack[i]);
                    }
                    break;
                }
                int max = 0;
                int index = 0;
                for (int s = i; s < last; s++) {
                    if (attack[s] > max) {
                        max = attack[s];
                        index = s;
                    }
                }
                //add res
                tRes += attack[index];
                count++;
                //add heap
                for (; i < last; i++) {
                    if (i == index) {
                        continue;
                    }
                    maxHeap.add(attack[i]);
                }
            }
            int last = m - count;
            for (int j = 0; j < last; j++) {
                tRes += maxHeap.poll();
            }
            res = Math.max(res, tRes);
        }
        System.out.println(res);
    }

    private static void findMax(int n, int m, int k, int[] attack) {
        long res = 0L;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int i = 0;
        while (i < n) {
            int max = 0;
            int start = i;
            int index = 0;
            int last = i + k;
            if (last > n) {
                for (; i < n; i++) {
                    maxHeap.add(attack[i]);
                }
                break;
            }
            for (; i < last; i++) {
                if (attack[i] > max) {
                    max = attack[i];
                    index = i;
                }
            }
            //add res
            res += attack[index];
            m--;
            i = index + 1;
            //index之前元素加入堆中
            for (; start < index; start++) {
                maxHeap.add(attack[start]);
            }
        }
        for (int j = 0; j < m; j++) {
            res += maxHeap.poll();
        }
        System.out.println(res);
    }


}

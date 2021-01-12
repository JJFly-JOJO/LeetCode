package IPCContest.RollCall;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/13 12:37
 * @description
 */
public class Main {

    static private int nLen;

    static private int mLen;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        nLen = scanner.nextInt();
        mLen = scanner.nextInt();
        scanner.nextLine();
        String[] ss = scanner.nextLine().split(" ");
        int[] students = new int[nLen];
        for (int i = 0; i < nLen; i++) {
            students[i] = Integer.parseInt(ss[i]);
        }
        ss = scanner.nextLine().split(" ");
        int[] M = new int[mLen];
        for (int i = 0; i < mLen; i++) {
            M[i] = Integer.parseInt(ss[i]);
        }
        rollCall(students, M);
    }

    private static void rollCall(int[] students, int[] call) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(students.length / 2, (o1, o2) -> o2 - o1);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(students.length / 2);
        int count = 0;
        for (int i = 0; i < mLen; i++) {
            //add student
            for (; count < call[i]; count++) {
                maxHeap.add(students[count]);
            }
            //find Kth 找第i+1高
            int t = maxHeap.size() - i - 1;
            if (t >= 0) {
                while (t > 0) {
                    minHeap.add(maxHeap.poll());
                    t--;
                }
                //out
            } else {
                t = -t;
                while (t > 0) {
                    maxHeap.add(minHeap.poll());
                    t--;
                }
                //out
            }
            System.out.println(maxHeap.peek());
        }
    }

}
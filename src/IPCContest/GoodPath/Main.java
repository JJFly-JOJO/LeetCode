package IPCContest.GoodPath;

import java.util.Scanner;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/15 10:09
 * @description
 */
public class Main {

    public static int n;

    public static int edgeN;

    public static int[][] graph;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        scanner.nextLine();
        edgeN = n * (n - 1) / 2;
        graph = new int[n + 1][n + 1];
        for(int i=0;i<edgeN;i++){
            int a=scanner.nextInt();
            int b=scanner.nextInt();
            //graph[a][b]
        }
    }

}

package WrittenExam.Ali;

import java.util.Scanner;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/3/8 19:18
 * @description 3
 * 5 1
 * 3 4 6 8 9
 * 5 2
 * 3 4 6 8 9
 * 4 3
 * 4 7 9 10
 */


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < row; i++) {
            int cardCount = scanner.nextInt();
            int k = scanner.nextInt();
            scanner.nextLine();
            int[] card = new int[cardCount];
            for (int j = 0; j < cardCount; j++) {
                card[j] = scanner.nextInt();
            }
            int result = getResult(card, k);
            System.out.println(result);
            scanner.nextLine();
        }

    }

    public static int getResult(int[] cards, int k) {
        int count = 0;
        if (cards.length == 1) {
            return cards[0] + k;
        }
        int i = 1;
        for (; i < cards.length; i++) {
            count += cards[i] - cards[i - 1] - 1;
            if (count >= k) {
                break;
            }
        }
        if (i == cards.length) {
            return cards[i-1] + k - count;
        }
        int v = count - k;
        return cards[i] - v - 1;
    }

}


package BinarySearch.NO374GuessNumberHigherorLower;

public class Solution extends GuessGame {

    public int guessNumber(int n) {
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int val = guess(mid);
            if (val == -1) {
                right = mid;
            } else if (val == 1) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }

}

package String.NO158ReadNCharactersGivenRead4II;

import String.NO157ReadNCharactersGivenRead4I.Reader4;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/28 14:28
 * @description ----
 * "abcde"
 * [1,4]
 */
public class Solution extends Reader4 {

    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return The number of actual characters read
     */
    private Queue<Character> list = new ArrayDeque<>();

    public static void main(String[] args) {
        char[] chars = new char[4];
        System.out.println("====" + chars[0] + "====");
        System.out.println(chars[0] == '\0');
        System.out.println(chars[0] == '\u0000');
        System.out.println('\u0061');
    }

    public int read(char[] buf, int n) {
        //find index
        int index = 0;
        //read file
        int num;
        int i;
        char[] t;
        do {
            i = 0;
            int size = list.size();
            for (; i < size && index < n; i++) {
                buf[index++] = list.poll();
            }
            t = new char[4];
            i = 0;
            num = read4(t);
            for (; i < num && index < n; i++) {
                buf[index++] = t[i];
            }
        } while (num != 0 && index < n);
        for (; i < num; i++) {
            list.add(t[i]);
        }
        return index;
    }
}
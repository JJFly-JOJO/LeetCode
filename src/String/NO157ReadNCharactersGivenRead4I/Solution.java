package String.NO157ReadNCharactersGivenRead4I;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/28 11:23
 * @description
 */
public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return The number of actual characters read
     */
    public int read(char[] buf, int n) {
        int r;
        int index = 0;
        do {
            char[] chars = new char[4];
            r = read4(chars);
            for (int i = 0; i < r && index < n; i++) {
                buf[index++] = chars[i];
            }
        } while (r > 0);
        return index;
    }
}

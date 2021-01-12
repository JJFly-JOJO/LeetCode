package String.NO214ShortestPalindrome;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/24 9:59
 * @description
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println("+++" + "aa".substring(2, 2) + "+++");
    }

    public String shortestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        if (len == 0) {
            return s;
        }
        int r = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (isPalindrome(0, i, chars)) {
                r = i + 1;
                break;
            }
        }
        StringBuilder sb1 = new StringBuilder(s).insert(0, new StringBuilder(s.substring(r, len)).reverse());
        // int l=len-1;
        // for(int i=len-2;i>=0;i--){
        //     if(chars[i]==chars[len-1]&&isPalindrome(i,len-1,chars)){
        //         l=i;
        //     }
        // }
        // StringBuilder sb2=new StringBuilder(s).append(new StringBuilder(s.substring(0,l)).reverse());
        return sb1.toString();
    }

    private boolean isPalindrome(int l, int r, char[] chars) {
        while (l < r && chars[l] == chars[r]) {
            l++;
            r--;
        }
        return l >= r;
    }

}

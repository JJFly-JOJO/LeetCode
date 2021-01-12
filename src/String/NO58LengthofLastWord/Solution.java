package String.NO58LengthofLastWord;

public class Solution {
    public int lengthOfLastWord(String s) {
        int index;
        if (s == null || (index = s.length()) == 0)
            return 0;
        //index = index - 1;
        while (--index >= 0 && s.charAt(index) == ' ') ;
        for (int i = index; i >= 0; i--) {
            if (s.charAt(i) == ' ')
                return index - i;
        }
        return index + 1;
    }
}

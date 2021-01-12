package String.NO271EncodeandDecodeStrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/6 9:56
 * @description ------------利用java特性 char 257 258...都对应一个ASCII之外的字符 利用此字符来分割----------
 */
public class Codec {

    public static void main(String[] args) {
        String str = "a";
        //输出数组 size=0
        String[] strArr = str.split("a");
        /*for (String t : str.split("a", -1)) {
            System.out.println(t);
        }*/
        //输出 "1" ""
        String str2 = "1a";
        String[] strArr2 = str2.split("a", -1);
        String empty = new String();
        //输出""
        String[] emptyArr = empty.split("a");
        char c = 257;
        System.out.println(c);
        new Codec().encode(new ArrayList<>());
    }

    // Encodes a list of strings to a single string.
    //特殊情况--------------------------------空集合 []---->[""]  预期结果[]
    public String encode(List<String> strs) {
        char c = 257;
        char e = 258;
        StringBuilder strBuilder = new StringBuilder();
        if (strs == null || strs.isEmpty()) {
            strBuilder.append(e);
            return strBuilder.toString();
        }
        //注意 list中存在“”空字符 最后输出时空字符也要输出的
        for (String s : strs) {
            strBuilder.append(s);
            strBuilder.append(c);
        }
        //由于“11a22a” split “a”会在结尾出现""空字符数组元素----------------易错 排除结尾空字符的情况
        strBuilder.deleteCharAt(strBuilder.length() - 1);
        return strBuilder.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        if (s.equals(Character.toString((char) 258))) {
            return new ArrayList<>();
        }
        String segment = Character.toString((char) 257);
        //空字符也要输出
        return Arrays.asList(s.split(segment, -1));
    }
}
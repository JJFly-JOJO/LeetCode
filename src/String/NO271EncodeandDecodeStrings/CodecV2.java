package String.NO271EncodeandDecodeStrings;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/6 11:16
 * @description --------------类似http传输数据流 用固定字节来记录当前字符串的长度 以便指导指针移动--------------
 */
public class CodecV2 {

    public static void main(String[] args) {
        CodecV2 codecV2 = new CodecV2();
        char[] chars = codecV2.lenToChar4(8);
        int len = codecV2.char4ToLen(chars, 0);
        ArrayList<String> list = new ArrayList<>();
        list.add("sdsa");
        list.add("dsada");
        String s = codecV2.encode(list);
        List<String> res = codecV2.decode(s);
    }

    public String encode(List<String> strs) {
        StringBuilder strBuilder = new StringBuilder();
        if (strs == null || strs.isEmpty()) {
            return strBuilder.toString();
        }
        for (String s : strs) {
            strBuilder.append(lenToChar4(s.length()));
            strBuilder.append(s);
        }
        return strBuilder.toString();
    }

    private char[] lenToChar4(int length) {
        char[] c = new char[4];
        for (int i = 3; i >= 0; i--) {
            c[i] = (char) (length & 0xFF);
            length = length >>> 8;
        }
        return c;
    }

    public List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        int len = s.length();
        if (len == 0) {
            return res;
        }
        int i = 0;
        char[] chars = s.toCharArray();
        while (i < len) {
            //read length
            int l = char4ToLen(chars, i);
            i += 4;
            res.add(new String(chars, i, l));
            i += l;
        }
        return res;
    }

    private int char4ToLen(char[] chars, int b) {
        int len = 0;
        for (int i = 0; i < 4; i++) {
            len = len << 8;
            len += chars[b + i];
        }
        return len;
    }

}

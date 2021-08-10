package Shoppee;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/8/2 7:18 下午
 * @description
 * "<people><name>shopee</name></people>","people.name"
 */
public class SolutionI {

    public static void main(String[] args) {
        System.out.println(new SolutionI().GetXMLValue("<people><name>shopee</name></people>","people.name"));
    }

    public String GetXMLValue(String inxml, String path) {
        String[] paths = path.split("\\.");
        char[] in = inxml.toCharArray();
        int idx = 0;
        for (int i = 0; i < paths.length; i++) {
            String p = paths[i];
            char[] pChar = p.toCharArray();
            idx++;
            for (int pIdx = 0; pIdx < pChar.length; pIdx++) {
                if (pChar[pIdx] != in[idx]) {
                    return "";
                }
                idx++;
            }
            idx++;
        }
        if (in[idx] == '<') {
            return "";
        }
        int begin = idx;
        while (in[idx] != '<') {
            idx++;
        }
        return new String(in, begin, idx - begin);
    }

}

package String.NO459RepeatedSubstringPattern;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/26 10:38
 * @description ------------使用API实现字符串s+s 与 s的匹配
 */
public class SolutionII {

    public boolean repeatedSubstringPattern(String s) {
        return (s + s).indexOf(s, 1) != s.length();
    }
}

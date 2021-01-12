package DFSBFS.NO126WordLadderII;

import java.util.*;

/**
 * BFS的队列queue里面同样可以存放路径path 不是只有深搜可以用path 只不过queue中的path注意不要add同一个引用对象了 每一次入队时
 * 都有new一个新的list集合入队
 */
public class Solution {

    public static void main(String[] args) {
        //String beginWord = "hit";
        //String endWord = "cog";
        String beginWord = "red";
        String endWord = "tax";
        List<String> word = new ArrayList<String>() {
            {
                /*add("hot");
                add("dot");
                add("dog");
                add("lot");
                add("log");
                add("cog");*/
                add("ted");
                add("tex");
                add("red");
                add("tax");
                add("tad");
                add("den");
                add("rex");
                add("pee");

            }
        };
        List<List<String>> res = new Solution().findLaddersForDoubleDFS(beginWord, endWord, word);
        for (List<String> temp : res) {
            System.out.println(temp);
        }
    }

    /**
     * 方法一：单向BFS
     * 由于是要找最短的路径 因此我们优先采用BFS 当然也可以DFS 先用BFS找到一条最短路径长度 然后以路径长度作为限制进行剪枝
     * !!!!!!!!!!!!!!!!!!BFS的queue可以存放list结构 以list的最后一个元素表示当前层出队元素！！！！！！！！！！！
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        //将wordList放入hashMap中
        Set<String> strMap = new HashSet<>(wordList);
        //BFS
        List<String> path = new ArrayList<>();
        path.add(beginWord);
        Set<String> isVisited = new HashSet<>();
        isVisited.add(beginWord);
        //BFS queue
        Queue<List<String>> queueForBFS = new LinkedList<>();
        queueForBFS.add(path);
        boolean isFindPath = false;
        while (!queueForBFS.isEmpty()) {
            //一层一层遍历
            //!!!!!!注意不能把size放在for循环中 因为queue会增加元素 每一次调用size值都不一样
            int size = queueForBFS.size();
            Set<String> curVisited = new HashSet<>();
            for (int i = 0; i < size; i++) {
                List<String> curStrPath = queueForBFS.poll();
                String lastStr = curStrPath.get(curStrPath.size() - 1);
                List<String> adjStrOfCur = getAdjacentWord(lastStr, strMap);
                for (String tempStr : adjStrOfCur) {
                    if (isVisited.contains(tempStr)) {
                        continue;
                    }
                    if (tempStr.equals(endWord)) {
                        //当前层为最短路径
                        curStrPath.add(endWord);
                        res.add(new ArrayList<>(curStrPath));
                        isFindPath = true;
                        break;
                    }
                    //如果当前层并没有找到最小路径 那么当前层入队
                    //技巧：出队一个一个出我们可能不能一次性获取到当前层的所有元素加入遍历集中（当然也可以在出队之前先将当前层的元素全部加入visited中）
                    //curVisited是要记录下当前层的下一层的所有元素 然后将他们一次加入isVisited集合中 因为不能等到
                    //queue将当前层的元素一个一个弹出时再加入遍历集合 这样会造成死循环（因为当前层的下一个元素可能已经是在当前层的queue中了）
                    //入队的list一定要是new的 否则是同一个对象 会出现所有的操作都是在对此一个对象更改的情况
                    curVisited.add(tempStr);
                    curStrPath.add(tempStr);
                    queueForBFS.add(new ArrayList<>(curStrPath));
                    curStrPath.remove(curStrPath.size() - 1);
                }
            }
            if (isFindPath) {
                break;
            }
            //当前层的下一层的所有元素加入遍历集合
            isVisited.addAll(curVisited);
        }

        return res;
    }

    /**
     * 获取当前字符串的所有领接字符串（只需要改变一个字符的）
     * 我们匹配wordList中的str与curStr有两种思路：
     * 思路一：wordList中的每一个单词与curStr进行比较 一位一位的比较 时间复杂度：list长度为n 单词长度为m 则O（mn）
     * 思路二：将wordList的str全部放入hashmap中 然后对要进行匹配的curStr的每一位进行改变（a-z子母替代）然后去map中匹配
     * 时间复杂度：单词长度为m 每个单词替换26次 在hashmap中查找为时间常数复杂度 则O(26m)
     *
     * @param curStr
     * @param strMap
     * @return
     */
    public List<String> getAdjacentWord(String curStr, Set<String> strMap) {
        List<String> res = new ArrayList<>();
        int length = curStr.length();
        char[] curChars = curStr.toCharArray();
        for (int i = 0; i < length; i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (ch == curChars[i]) {
                    continue;
                }
                char temp = curChars[i];
                curChars[i] = ch;
                String tempString = String.valueOf(curChars);
                if (strMap.contains(tempString)) {
                    res.add(tempString);
                }
                curChars[i] = temp;
            }
        }
        return res;
    }

    /**
     * 双向BFS
     * 难点！！！！！！！！！！！！！什么时候需要对res值进行拷贝 否则容器中的内容发生了修改 造成拼接结果错误
     *    如果集合里的值还有可能用到，就必须拷贝集合中的值 而非直接对集合中的值进行修改
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLaddersForDoubleDFS(String beginWord, String endWord, List<String> wordList) {
        //res
        List<List<String>> res = new ArrayList<>();
        //queue for BFS
        Queue<List<String>> queueOfBegin = new LinkedList<>();
        queueOfBegin.add(new ArrayList<String>() {
            {
                add(beginWord);
            }
        });

        Queue<List<String>> queueForEnd = new LinkedList<>();
        queueForEnd.add(new ArrayList<String>() {
            {
                add(endWord);
            }
        });

        //visited map
        //stack用在单步hop时很方便 但是整个集合出栈 用ArrayList就可以了
        Map<String, List<List<String>>> isVisitedOfBegin = new HashMap<>();
        isVisitedOfBegin.put(beginWord, new ArrayList<List<String>>() {{
            add(queueOfBegin.peek());
        }});
        Map<String, List<List<String>>> isVisitedOfEnd = new HashMap<>();
        isVisitedOfEnd.put(endWord, new ArrayList<List<String>>() {{
            add(queueForEnd.peek());
        }});
        //bool
        boolean isFound = false;
        //dict map
        Set<String> dictMap = new HashSet<>(wordList);

        //dict map for deleting
        Set<String> dictMapForDeleting = new HashSet<>(wordList);
        dictMapForDeleting.remove(beginWord);
        if (!dictMapForDeleting.contains(endWord)) {
            return res;
        } else {
            dictMapForDeleting.remove(endWord);
        }
        //BFS
        while (!queueOfBegin.isEmpty() && !queueForEnd.isEmpty()) {
            if (queueOfBegin.size() < queueForEnd.size()) {
                Set<String> tempVisited = new HashSet<>();
                int size = queueOfBegin.size();
                for (int i = 0; i < size; i++) {
                    //当前层元素弹出 当前层的元素是在List的末尾
                    List<String> tempList = queueOfBegin.poll();
                    String lastStr = tempList.get(tempList.size() - 1);
                    List<String> adjWords = getAdjacentWord(lastStr, dictMap);
                    for (int j = 0; j < adjWords.size(); j++) {
                        String tempWord = adjWords.get(j);
                        List<List<String>> halfResSet;
                        if ((halfResSet = isVisitedOfEnd.get(tempWord)) != null) {
                            //当前层找到
                            //注意这里当前halfRes层为end 因此结果要倒序输出拼接
                            isFound = true;
                            for (List<String> tempHalfRes : halfResSet) {
                                List<String> tempRes = new ArrayList<>(tempList);
                                //!!!!!!!!!!!!!!注意这里为了方便 每一个tempRes的结果都是用的同一个tempHalfRes中的引用内容 没有进行值拷贝
                                for (int k = tempHalfRes.size() - 1; k >= 0; k--) {
                                    tempRes.add(tempHalfRes.get(k));
                                }
                                res.add(tempRes);
                            }
                        }
                        //下一个元素不能遍历过
                        if (dictMapForDeleting.contains(tempWord)) {
                            //当前层的下一层并没有与end层对接 那么放入一个临时tempVisited集合中 最后在一并放入isVisitedOfBegin
                            //因为当前for循环中还会判断isVisited集合中的元素是否存在 所以不能马上更新
                            tempVisited.add(tempWord);
                            List<String> newPath = new ArrayList<>(tempList);
                            newPath.add(tempWord);
                            queueOfBegin.add(newPath);
                            List<List<String>> path = isVisitedOfBegin.getOrDefault(tempWord, new ArrayList<>());
                            path.add(newPath);
                            isVisitedOfBegin.put(tempWord, path);
                        }
                    }
                }
                //此处还能进行优化！！！！！！！！！可以直接使用isVisitedBegin来作为遍历集合 但是也是要先放入一个temp集合中 最后再一并加入
                dictMapForDeleting.removeAll(tempVisited);
            } else {
                //end size小于begin size
                Set<String> tempVisited = new HashSet<>();
                int size = queueForEnd.size();
                for (int i = 0; i < size; i++) {
                    List<String> tempList = queueForEnd.poll();
                    String lastStr = tempList.get(tempList.size() - 1);
                    List<String> adjWords = getAdjacentWord(lastStr, dictMap);
                    for (int j = 0; j < adjWords.size(); j++) {
                        String tempWord = adjWords.get(j);
                        List<List<String>> halfResSet;
                        if ((halfResSet = isVisitedOfBegin.get(tempWord)) != null) {
                            //当前层找到
                            //注意这里当前halfRes层为start end层结果要倒序输出拼接
                            isFound = true;
                            for (List<String> tempHalfRes : halfResSet) {
                                //List<String> tempRes = new ArrayList<>(tempList);
                                //！！！！！！！！！！！！！注意这里一定要对tempHalfRes中的值进行拷贝 否则进行拼接后 下一次用到tempHalfRes中的值时 里面的内容发生了改变
                                List<String> copyHalfRes=new ArrayList<>(tempHalfRes);
                                for (int k = tempList.size() - 1; k >= 0; k--) {
                                    copyHalfRes.add(tempList.get(k));
                                }
                                res.add(copyHalfRes);
                            }
                        }
                        //下一个元素不能遍历过
                        if (dictMapForDeleting.contains(tempWord)) {
                            //当前层的下一层并没有与start层对接 那么放入一个临时tempVisited集合中 最后在一并放入isVisitedOfBegin
                            //因为当前for循环中还会判断isVisited集合中的元素是否存在 所以不能马上更新
                            tempVisited.add(tempWord);
                            List<String> newPath = new ArrayList<>(tempList);
                            newPath.add(tempWord);
                            queueForEnd.add(newPath);
                            List<List<String>> path = isVisitedOfEnd.getOrDefault(tempWord, new ArrayList<>());
                            path.add(newPath);
                            isVisitedOfEnd.put(tempWord, path);
                        }
                    }
                }
                dictMapForDeleting.removeAll(tempVisited);
            }
            if (isFound) {
                break;
            }
        }
        return res;
    }

}

package DFSBFS.NO127WordLadder;

import javafx.util.Pair;

import java.util.*;

/**
 * 笔记 NO127
 */
public class Solution {

    /**
     * 注意一个关键点：
     * 我们是要找最小路径 如果当前节点到邻接点有多种途径到达 我们肯定只需要当前节点直接到邻接点的这条路线
     *
     * 我们每一层加入当前节点的所有邻接点 并且放入遍历集合 这样就可以避免冗余路径加入到集合中 也可以避免回环
     *
     */


    private int wordLength;
    private Map<String, List<String>> graph;

    Solution() {
        wordLength = 0;
        graph = new HashMap<>();
    }

    /**
     * 广度优先搜索
     * 因为要找到最短路径 如果用深搜 必须全部搜索完进行路径长度比较
     * 如果用广度优先搜索 每一层遍历 如果当前层找到结果 那自然就是最短路径
     * 关键在于领接表如何建立
     * 我们将只差一个字母的单词连接在一起 形成一个无权无向的领接图
     * <p>
     * 如何描述这个无向领接图
     * 相连接的两个node的共性 只差一个字符 这个共性可以用
     * <p>
     * *og<----------hog,cog,mog...表达
     * <p>
     * 因此以"xx*xxx"来表示共性 作为边连接
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int wordLength = beginWord.length();

        Map<String, List<String>> graph = new HashMap<>();

        //lambda本质是产生一个实现了函数式接口的匿名内部类
        //匿名内部类不仅可以访问到其父类参数 也可以访问当前代码块中的变量
        //这里foreach中传入的对象是一个Consumer消费函数式接口 此接口需要覆写
        //                                      void accept(T t);方法
        //word及为accept方法传入的参数
        //也就是这里for循环中传入的T t变量
        //
        //default void forEach(Consumer<? super T> action) {
        //        Objects.requireNonNull(action);
        //        for (T t : this) {
        //            action.accept(t);
        //        }
        //    }
        wordList.forEach((word) -> {
            for (int i = 0; i < wordLength; i++) {
                String temp = word.substring(0, i) + '*' + word.substring(i + 1, wordLength);
                List<String> tempList = graph.getOrDefault(temp, new ArrayList<>());
                tempList.add(word);
                //重新放回 因为如果之前没有key值 tempList是新创建的对象 此时graph中还没有此对象
                graph.put(temp, tempList);
            }
        });

        //创建Queue队列 用于BFS
        //Integer用于记录当前入队元素所属层数
        Queue<Pair<String, Integer>> queueFoeBfs = new LinkedList<>();
        //beginWord元素入队 beginWord入队也算作一层 integer+1
        queueFoeBfs.add(new Pair<>(beginWord, 1));

        //创建遍历集合 用于记录当前已经遍历的元素 防止成环遍历
        Map<String, Boolean> isVisited = new HashMap<>();
        isVisited.put(beginWord, true);

        //BFS
        //思路清晰：入队不判断是否equal 出队再判断equal
        //beginWord作为第一层 与此node相邻的节点node作为第二层入队
        while (!queueFoeBfs.isEmpty()) {
            //出队
            Pair<String, Integer> tempPair = queueFoeBfs.poll();
            String keyString = tempPair.getKey();
            int count = tempPair.getValue();
            if (keyString.equals(endWord)) {
                return count;
            }
            //将node的所有领节点node入队 用isVisited排除入队过的元素
            for (int i = 0; i < wordLength; i++) {
                String tempString = keyString.substring(0, i) + '*' + keyString.substring(i + 1, wordLength);
                graph.getOrDefault(tempString, new ArrayList<>()).forEach((word) -> {
                    if (isVisited.getOrDefault(word, false)) {//hashMap如果不存在当前值 返回null 注意 hashMap key和value都可以为null
                        return;
                    }
                    isVisited.put(word, true);
                    queueFoeBfs.add(new Pair<>(word, count + 1));
                });
            }
        }
        return 0;
    }

    /**
     * 方法二:
     * 双向广度优秀搜索
     * 广度优先搜索受到每层节点数的影响 如果每个节点分支数量相同 每一层的节点总数会呈现指数级增长
     * 如果我们从两头向中间搜索 可以提高平均效率 当然如果结果都在中点处 那么与单向的广度优先搜索是没有区别的
     * 实现：
     * begin和end两端各自向内BFS一层 当有元素相遇 说明这一层找到了一条路径结果
     * <p>
     * 时间复杂度:任然是MxN M单词的长度 N单词的个数
     * 但是由于中间相遇 时间可以减少一半
     * <p>
     * 提问：
     * 如果begin开始的路径包含了node1 end开始的路径也包含了end1 那么这条路径不就有两个node1了吗？
     * 注意我们这里是遇到第一次节点node相遇时直接返回 也就是说 当相遇时 路径长度是最短的！
     * 如果一个路径里面有两个node1 那么这两个node1连在一起成为更短的路径一定先于有两个node1路径的情况出现
     * 因此结果必然是正确的最短路径长度
     * <p>
     * 如果用此方法获取所有路径 可能就存在节点重复的情况
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLengthForTwoDirection(String beginWord, String endWord, List<String> wordList) {
        /**
         *特殊情况：list集合没有endWord
         *"hit"
         * "cog"
         * ["hot","dot","dog","lot","log"]
         *尾结点必须要存在于数组集合中 而不是像头节点一样从头节点变到数组中的某个节点中
         * 由此也可以看出 如果不用isVisited排除遍历过的节点 如果头节点在此集合中 则每一层遍历时 都会将头节点加入到队列集合中
         */
        if (!wordList.contains(endWord)) {
            return 0;
        }

        wordLength = beginWord.length();
        //初始化map
        wordList.forEach((word) -> {
            for (int i = 0; i < wordLength; i++) {
                String tempString = word.substring(0, i) + '*' + word.substring(i + 1, wordLength);
                List<String> tempList = graph.getOrDefault(tempString, new ArrayList<>());
                tempList.add(word);
                graph.put(tempString, tempList);
            }
        });

        //初始化队列

        Queue<Pair<String, Integer>> queueForHeadBFS = new LinkedList<>();
        Queue<Pair<String, Integer>> queueForEndBFS = new LinkedList<>();
        //两端头节点入队
        queueForHeadBFS.add(new Pair<>(beginWord, 1));
        queueForEndBFS.add(new Pair<>(endWord, 1));
        //初始化遍历集合
        //遍历集合记录当前level数 以便当左右两端相遇时 可以获得两个level值
        Map<String, Integer> beginVisted = new HashMap<>();
        beginVisted.put(beginWord, 1);
        Map<String, Integer> endVisited = new HashMap<>();
        endVisited.put(endWord, 1);

        //BFS
        while (!queueForHeadBFS.isEmpty() && !queueForEndBFS.isEmpty()) {
            int ans = visitWordNode(queueForHeadBFS, beginVisted, endVisited);
            if (ans > -1) {
                return ans;
            }
            ans = visitWordNode(queueForEndBFS, endVisited, beginVisted);
            if (ans > -1) {
                return ans;
            }
        }
        return 0;
    }

    private int visitWordNode(Queue<Pair<String, Integer>> queueForBFS,
                              Map<String, Integer> isVisted,
                              Map<String, Integer> otherVisited) {
        Pair<String, Integer> head = queueForBFS.poll();
        String curLevelString = head.getKey();
        int curLevel = head.getValue();
        for (int i = 0; i < wordLength; i++) {
            String tempStr = curLevelString.substring(0, i) + '*' + curLevelString.substring(i + 1, wordLength);
            //获得当前curString的领接List<String>
            //graph.getOrDefault(tempStr, new ArrayList<>()).forEach((word)->{ });
            //注意这里不能用foreach的lambda 因为Consume的accept方法是void方法 不能返回int
            for (String word : graph.getOrDefault(tempStr, new ArrayList<>())) {
                //在otherVisited找到结果 则相遇 返回结果
                if (otherVisited.containsKey(word)) {
                    return curLevel + otherVisited.get(word);
                }
                //没有相遇 则判断之前是否存在于isVisited集合中 不存在 加入queue队列 以及加入isVisited
                if (!isVisted.containsKey(word)) {
                    queueForBFS.add(new Pair<>(word, curLevel + 1));
                    isVisted.put(word, curLevel + 1);
                }
            }
        }
        //没有相遇 返回-1
        return -1;
    }

    /**
     * BEST METHOD
     * 不需要构建领接表 在BFS时隐式的寻找领接node
     * 此方法本质上是单向BFS 但是单向也是从两端单向 哪一端当前层元素少就遍历哪一端
     * 遍历过后 就直接把当前层的所有元素从wordList中全部去除！！！！！！！！！！！！！！
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLengthForBestMethod(String beginWord, String endWord, List<String> wordList) {
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return 0;
        return search(beginSet, endSet, dict, 1);
    }

    private int search(Set<String> beginSet, Set<String> endSet, Set<String> dict, int cnt) {
        if (beginSet.isEmpty() || endSet.isEmpty()) return 0;
        cnt++;
        dict.removeAll(beginSet);
        Set<String> nextSet = new HashSet<>();
        for (String str : beginSet) {
            char[] chs = str.toCharArray();
            for (int i = 0; i < chs.length; i++) {
                char c = chs[i];
                for (char j = 'a'; j <= 'z'; j++) {
                    chs[i] = j;
                    String tmp = new String(chs);
                    if (!dict.contains(tmp)) continue;
                    if (endSet.contains(tmp)) return cnt;
                    nextSet.add(tmp);
                }
                chs[i] = c;
            }
        }
        return nextSet.size() > endSet.size() ? search(endSet, nextSet, dict, cnt) : search(nextSet, endSet, dict, cnt);
    }


}

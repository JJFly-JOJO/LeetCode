package Trie.NO212WordSearchII;

import java.util.ArrayList;
import java.util.List;

/**
 * 思路：
 * 最原始的DFS的思路 每个word遍历board 进行DFS 但是这样的话 我们会多次遍历mxn的board矩阵
 * 如果我们只遍历一次就能找到words中所有存在于board矩阵中的word呢？
 * **************************************************
 * 对于要搜索的所有words 我们可以对这个words进行一些共性的研究 类比前缀树的数据结构 我们将这些words构建
 * 成前缀树的结构 这个前缀树能够表达整个words 如果我们DFS的目的不是搜索每个word 而是以这个前缀树trie做为
 * 要搜索的对象 那么 我们是不是搜索结束后 整个words哪些在board 哪些不在 我们也就知道了
 *
 */
public class Solution {

    public static void main(String[] args) {
        char[][] board = new char[][]{{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String[] words = new String[]{"oath", "pea", "eat", "rain"};
        for (String temp : new Solution().findWords(board, words)) {
            System.out.println(temp);
        }

    }


    /**
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords(char[][] board, String[] words) {

        List<String> res = new ArrayList<>();

        //构建前缀树
        Trie trie = new Trie();
        for (String str : words) {
            trie.insertWord(str);
        }

        //遍历board 通过DFS与trie中的各个words进行匹配
        int rowBoard = board.length;
        int colBoard = board[0].length;

        for (int i = 0; i < rowBoard; i++) {

            for (int j = 0; j < colBoard; j++) {
                //!!!!!!!!!!!!!!!!!!!!!!!!之前trie的search方法是要融合到DFS中的
                DFS(i, j, trie.root, board, res, rowBoard, colBoard);

            }
        }
        return res;
    }

    /**
     * @param curRow
     * @param curCol
     * @param curNode
     * @param board
     * @param res
     * @param rowBoard
     * @param colBoard
     */
    private void DFS(int curRow, int curCol,
                     TrieNode curNode,
                     char[][] board,
                     List<String> res,
                     int rowBoard, int colBoard) {
        char curCh = board[curRow][curCol];

        TrieNode nextNode = curNode.getNextNode(curCh);
        if (nextNode == null) {
            return;
        }
        //当前层的下一层记录的当前层的string（word）内容 也就是说 遍历到当前层 我们想判断当前层走到的word在
        //trie中是否存在 那么我们得到下一层寻找（string不为null 那么下一层肯定存在（trie初始化时的特点））
        if (nextNode.str != null) {
            res.add(nextNode.str);
            //置空 防止重复加入到结果集
            nextNode.str = null;
        }
        //一定要设置为遍历过
        board[curRow][curCol] = '#';
        //上下左右DFS
        int[] rowDirc = new int[]{-1, 1, 0, 0};
        int[] colDirc = new int[]{0, 0, -1, 1};
        for (int p = 0; p < 4; p++) {
            int tempRow = curRow + rowDirc[p];
            int tempCol = curCol + colDirc[p];
            if (tempRow >= 0 && tempRow < rowBoard
                    && tempCol >= 0 && tempCol < colBoard
                    && board[tempRow][tempCol] != '#') {
                DFS(tempRow, tempCol, nextNode, board, res, rowBoard, colBoard);
            }
        }
        board[curRow][curCol] = curCh;
    }

    class Trie {

        public TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insertWord(String word) {
            TrieNode curNode = root;
            for (int i = 0; i < word.length(); i++) {
                TrieNode nextNode = curNode.getNextNode(word.charAt(i));
                if (nextNode == null) {
                    nextNode = new TrieNode();
                    curNode.insertNextNode(word.charAt(i), nextNode);
                }
                curNode = nextNode;
            }
            //当前节点（也就是单词的最后一个字母所在长度）的下一个节点存放单词
            curNode.str = word;
        }

    }


    class TrieNode {

        /**
         * 相当于之前的isEnd 当前层组成的string我们放到下一个节点中
         */
        public String str;
        private TrieNode[] links;

        public TrieNode() {
            links = new TrieNode[26];
        }

        public TrieNode getNextNode(char ch) {
            return links[ch - 'a'];
        }

        public void insertNextNode(char ch, TrieNode nextNode) {
            links[ch - 'a'] = nextNode;
        }

    }
}

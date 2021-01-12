package Trie.NO208ImplementTrie;


/**
 * ---------------------笔记-----------------
 * 前缀树(Trie/Prefix Tree)
 */
public class Trie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode curNode = root;
        for (int i = 0; i < word.length(); i++) {
            TrieNode nextNode;
            if ((nextNode = curNode.get(word.charAt(i))) == null) {
                nextNode = new TrieNode();
                curNode.put(word.charAt(i), nextNode);
            }
            curNode = nextNode;
        }
        //递归最终到达单词尾部指向的节点 这个节点没有字符 设置为终点节点
        curNode.setEnd();
    }

    /**
     * 判断单词是否在前缀树中
     *
     * @param word
     * @return
     */
    public boolean search(String word) {
        TrieNode lastNode = getPrefix(word);
        if (lastNode != null && lastNode.isEnd()) {
            return true;
        }
        return false;
    }

    /**
     * 判断单词是否有公共前缀
     *
     * @param prefix
     * @return
     */
    public boolean startsWith(String prefix) {
        TrieNode lastNode = getPrefix(prefix);
        if (lastNode != null) {
            return true;
        }
        return false;
    }

    /**
     * 辅助函数 减少代码重复
     * 获取单词最长前缀下的node
     *
     * @param word
     * @return
     */
    public TrieNode getPrefix(String word) {
        TrieNode curNode = root;
        for (int i = 0; i < word.length(); i++) {
            TrieNode nextNode;
            if ((nextNode = curNode.get(word.charAt(i))) == null) {
                return nextNode;
            }
            curNode = nextNode;
        }
        return curNode;
    }

}

class TrieNode {
    private final int R = 26;
    /**
     * -------------类似于NO310的图结构  节点加一条边组成一个node
     * links表示例如"leet"的第一个字母l 创建TrieNode l在links的links['l'-'a']处链接下一个e表示的TrieNode
     */
    private TrieNode[] links;

    private boolean isEnd;

    public TrieNode() {
        links = new TrieNode[R];
    }

    /**
     * 单词的每个字母作为key
     *
     * @param ch
     * @return
     */
    public boolean containKey(char ch) {
        return links[ch - 'a'] != null;
    }

    public TrieNode get(char ch) {
        return links[ch - 'a'];
    }

    public void put(char ch, TrieNode node) {
        links[ch - 'a'] = node;
    }

    public void setEnd() {
        isEnd = true;
    }

    public boolean isEnd() {
        return isEnd;
    }

}
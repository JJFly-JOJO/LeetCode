package Trie.NO211AddandSearchWord;


public class WordDictionary {

    private final char WILD_CARD = '.';
    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode curNode = root;
        int wordLength = word.length();
        for (int i = 0; i < wordLength; i++) {
            TrieNode nextNode;
            if ((nextNode = curNode.getNextNode(word.charAt(i))) == null) {
                nextNode = new TrieNode();
                curNode.setNextNode(word.charAt(i), nextNode);
            }
            curNode = nextNode;
        }
        curNode.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode curNode = root;
        int wordLength = word.length();
        int curIndex = 0;
        return DFS(curIndex, curNode, word, wordLength);
    }

    /**
     * @param curIndex
     * @param curNode
     * @param word
     * @param wordLength
     * @return
     */
    private boolean DFS(int curIndex, TrieNode curNode, String word, int wordLength) {
        if (curNode == null) {
            return false;
        }
        if (curIndex == wordLength) {
            if (curNode.isEnd) {
                return true;
            }
            return false;
        }

        char ch = word.charAt(curIndex);
        if (ch == WILD_CARD) {
            //通配符
            int[] hasElement = curNode.hasElement;
            for (int i = 0; i < curNode.size; i++) {
                if (DFS(curIndex + 1, curNode.links[hasElement[i]], word, wordLength)) {
                    return true;
                }
            }
        } else {
            if (DFS(curIndex + 1, curNode.getNextNode(ch), word, wordLength)) {
                return true;
            }
        }
        return false;
    }


    class TrieNode {

        /**
         * hasElement用来记录“.”情况时 当前links上那些数组下标有指向下一个节点（非空）
         */
        public TrieNode[] links;
        public int[] hasElement;
        public boolean isEnd;
        public int size;

        public TrieNode() {
            links = new TrieNode[26];
            hasElement = new int[26];
        }

        public TrieNode getNextNode(char ch) {
            return links[ch - 'a'];
        }

        public void setNextNode(char ch, TrieNode nextNode) {
            int index = ch - 'a';
            if (links[index] == null) {
                links[index] = nextNode;
                hasElement[size++] = index;
            }
        }

    }
}

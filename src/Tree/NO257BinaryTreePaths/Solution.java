package Tree.NO257BinaryTreePaths;

import Util.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 易错点：！！！！！！！！！！！！！！！！-100是四个字符！！！
 */
public class Solution {

    public static void main(String[] args) {
        TreeNode root = Util.createTree(new Integer[]{37, -34, -48, null,
                -100, -100, 48, null, null, null, null, -54, null, -71, -22, null, null, null, 8});
        for (String temp : new Solution().binaryTreePaths(root)) {
            System.out.println(temp);
        }
        //-100是四个字符！！！！！
        String str = new String("-100");
        System.out.println(str.length());
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        //StringBuilder path = new StringBuilder(String.valueOf(root.val));
        List<Integer> path = new ArrayList<>();
        path.add(root.val);
        if (root.right == null && root.left == null) {
            res.add(String.valueOf(root.val));
            return res;
        }
        if (root.left != null) {
            DFS(root.left, path, res);
        }
        if (root.right != null) {
            DFS(root.right, path, res);
        }
        return res;
    }

    private void DFS(TreeNode node, List<Integer> path, List<String> res) {
        //path.append("->" + node.val);
        path.add(node.val);
        if (node.left != null) {
            DFS(node.left, path, res);
        }
        if (node.right != null) {
            DFS(node.right, path, res);
        }
        if (node.left == null && node.right == null) {
            StringBuilder temp = new StringBuilder(String.valueOf(path.get(0)));
            for (int i = 1; i < path.size(); i++) {
                temp.append("->" + path.get(i));
            }
            res.add(temp.toString());
        }
        path.remove(path.size() - 1);
    }


}

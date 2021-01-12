package Graph.NO399EvaluateDivision;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        String[][] strs = new String[][]{{"a", "b"}, {"b", "c"}};
        List<List<String>> equations = new ArrayList<List<String>>() {
            {
                for (int i = 0; i < strs.length; i++) {
                    add(new ArrayList<>(Arrays.asList(strs[i])));
                }
            }
        };
        double[] values = new double[]{2.0, 3.0};
        String[][] strsForQueries = new String[][]{{"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}};
        List<List<String>> queries = new ArrayList<List<String>>() {
            {
                for (int i = 0; i < strsForQueries.length; i++) {
                    add(new ArrayList<>(Arrays.asList(strsForQueries[i])));
                }
            }
        };
        for (double temp : new Solution().calcEquation(equations, values, queries)) {
            System.out.println(temp);
        }

    }

    /**
     * 分析:由题意可知 这是一个有权有向图
     *
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquation(List<List<String>> equations,
                                 double[] values,
                                 List<List<String>> queries) {
        int resultSize = queries.size();
        double[] result = new double[resultSize];
        //构造图 领接表
        //如果变量仅仅是单字符a-z 我们完全可以用0-25进行映射
        Map<Integer, Map<Integer, Double>> graphForTable = new HashMap<>();
        //初始化
        for (int i = 0; i < equations.size(); i++) {
            //如果string仅仅是单字符
            int node1 = equations.get(i).get(0).charAt(0) - 'a';
            int node2 = equations.get(i).get(1).charAt(0) - 'a';
            Map<Integer, Double> curArray = graphForTable.getOrDefault(node1, new HashMap<>());
            curArray.put(node2, values[i]);
            graphForTable.put(node1, curArray);
            curArray = graphForTable.getOrDefault(node2, new HashMap<>());
            curArray.put(node1, 1 / values[i]);
            graphForTable.put(node2, curArray);
        }

        //DFS for result
        for (int i = 0; i < resultSize; i++) {
            int startNode = queries.get(i).get(0).charAt(0) - 'a';
            int endNode = queries.get(i).get(1).charAt(0) - 'a';
            Map<Integer, Double> startArray = graphForTable.get(startNode);
            //result先初始化为-1.0
            result[i] = -1.0;
            if (startArray == null || !graphForTable.containsKey(endNode)) {
                continue;
            }
            if (startNode == endNode) {
                result[i] = 1.0;
                continue;
            }
            Double temp;
            if ((temp = startArray.get(endNode)) != null) {
                result[i] = temp;
                continue;
            }
            //标记是否找到
            //boolean hasFind = false;
            end:
            for (Map.Entry<Integer, Double> curNode : startArray.entrySet()) {
                Map<Integer, Double> curNodeMap = graphForTable.get(curNode.getKey());
                if (curNodeMap != null) {
                    Double tempForCurNode = curNodeMap.get(endNode);
                    if (tempForCurNode != null) {
                        //hasFind = true;
                        result[i] = curNode.getValue() * tempForCurNode;
                        //更新map
                        Map<Integer, Double> curArray = graphForTable.get(startNode);
                        curArray.put(endNode, result[i]);
                        curArray = graphForTable.get(endNode);
                        curArray.put(startNode, 1 / result[i]);
                        break;
                    }
                    double sumValue = curNode.getValue();
                    for (Map.Entry<Integer, Double> curCurNode : curNodeMap.entrySet()) {
                        //在进入DFS前更新value（相乘结果）
                        sumValue = sumValue * curCurNode.getValue();
                        if (DFS(curCurNode, startNode, endNode, sumValue, graphForTable, result, i)) {
                            //使用标签跳出内外循环
                            break end;
                        }
                    }
                }
//                if (hasFind) {
//                    break;
//                }
            }
        }
        return result;
    }

    /**
     * DFS
     * dfs时顺带记录一些结果
     *
     * @param curNode
     * @param startNode
     * @param endNode
     * @param sumValue
     * @param graphForTable
     * @param result
     * @param resultIndex
     */
    private boolean DFS(Map.Entry<Integer, Double> curNode,
                        int startNode,
                        int endNode,
                        double sumValue,
                        Map<Integer, Map<Integer, Double>> graphForTable,
                        double[] result,
                        int resultIndex) {
        if (curNode.getKey() == startNode) {
            return false;
        }
        Map<Integer, Double> curNodeMap = graphForTable.get(curNode);
        Double tempValue = curNodeMap.get(endNode);
        if (tempValue != null) {
            result[resultIndex] = tempValue * sumValue;
            //更新map
            Map<Integer, Double> curArray = graphForTable.get(startNode);
            curArray.put(endNode, result[resultIndex]);
            curArray = graphForTable.get(endNode);
            curArray.put(startNode, 1 / result[resultIndex]);
            return true;
        }
        for (Map.Entry<Integer, Double> curCurNode : curNodeMap.entrySet()) {
            sumValue = sumValue * curCurNode.getValue();
            if ((DFS(curCurNode, startNode, endNode, sumValue, graphForTable, result, resultIndex))) {
                return true;
            }
        }
        return false;
    }

}

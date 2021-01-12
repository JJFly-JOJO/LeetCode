package Graph.NO399EvaluateDivision;

import java.util.*;

/**
 * 针对多字符的节点 不能用数字下标代替
 */
public class Solution2 {

    public static void main(String[] args) {
        String[][] strs = new String[][]{{"x1", "x2"}, {"x2", "x3"}, {"x3", "x4"}, {"x4", "x5"}};
        List<List<String>> equations = new ArrayList<List<String>>() {
            {
                for (int i = 0; i < strs.length; i++) {
                    add(new ArrayList<>(Arrays.asList(strs[i])));
                }
            }
        };
        double[] values = new double[]{3.0, 4.0, 5.0, 6.0};
        String[][] strsForQueries = new String[][]{{"x1", "x5"}, {"x5", "x2"}, {"x2", "x4"},
                {"x2", "x2"}, {"x2", "x9"}, {"x9", "x9"}};
        List<List<String>> queries = new ArrayList<List<String>>() {
            {
                for (int i = 0; i < strsForQueries.length; i++) {
                    add(new ArrayList<>(Arrays.asList(strsForQueries[i])));
                }
            }
        };
        for (double temp : new Solution2().calcEquation(equations, values, queries)) {
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
        Map<String, Map<String, Double>> graphForTable = new HashMap<>();
        //初始化
        for (int i = 0; i < equations.size(); i++) {
            //如果string仅仅是单字符
            String node1 = equations.get(i).get(0);
            String node2 = equations.get(i).get(1);
            Map<String, Double> curArray = graphForTable.getOrDefault(node1, new HashMap<>());
            curArray.put(node2, values[i]);
            graphForTable.put(node1, curArray);
            curArray = graphForTable.getOrDefault(node2, new HashMap<>());
            curArray.put(node1, 1 / values[i]);
            graphForTable.put(node2, curArray);
        }

        //DFS for result
        for (int i = 0; i < resultSize; i++) {
            String startNode = queries.get(i).get(0);
            String endNode = queries.get(i).get(1);
            Map<String, Double> startArray = graphForTable.get(startNode);
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
            //设置遍历集合
            Set<String> isVisited = new HashSet<>();
            isVisited.add(startNode);
            end:
            for (Map.Entry<String, Double> curNode : startArray.entrySet()) {
                Map<String, Double> curNodeMap = graphForTable.get(curNode.getKey());
                if (curNodeMap != null) {
                    Double tempForCurNode = curNodeMap.get(endNode);
                    if (tempForCurNode != null) {
                        //hasFind = true;
                        result[i] = curNode.getValue() * tempForCurNode;
                        //更新map
                        Map<String, Double> curArray = graphForTable.get(startNode);
                        curArray.put(endNode, result[i]);
                        curArray = graphForTable.get(endNode);
                        curArray.put(startNode, 1 / result[i]);
                        break;
                    }
                    isVisited.add(curNode.getKey());
                    double sumValue = curNode.getValue();
                    for (Map.Entry<String, Double> curCurNode : curNodeMap.entrySet()) {
                        //在进入DFS前更新value（相乘结果）<---------------------------------!!!!技巧 直接在传入的值中更新
                        //sumValue = sumValue * curCurNode.getValue();
                        if (DFS(curCurNode, startNode, endNode, sumValue * curCurNode.getValue(), graphForTable, result, i, isVisited)) {
                            //使用标签跳出内外循环
                            break end;
                        }
                    }
                    isVisited.remove(curNode.getKey());
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
     * @param isVisited
     */
    private boolean DFS(Map.Entry<String, Double> curNode,
                        String startNode,
                        String endNode,
                        double sumValue,
                        Map<String, Map<String, Double>> graphForTable,
                        double[] result,
                        int resultIndex,
                        Set<String> isVisited) {
        if (isVisited.contains(curNode.getKey())) {
            return false;
        }
        //注意！！！！！！！！！！！！没有设置遍历集 会出现死循环
        Map<String, Double> curNodeMap = graphForTable.get(curNode.getKey());
        Double tempValue = curNodeMap.get(endNode);
        if (tempValue != null) {
            result[resultIndex] = tempValue * sumValue;
            //更新map
            Map<String, Double> curArray = graphForTable.get(startNode);
            curArray.put(endNode, result[resultIndex]);
            curArray = graphForTable.get(endNode);
            curArray.put(startNode, 1 / result[resultIndex]);
            return true;
        }
        isVisited.add(curNode.getKey());
        for (Map.Entry<String, Double> curCurNode : curNodeMap.entrySet()) {
            //sumValue = sumValue * curCurNode.getValue();
            if ((DFS(curCurNode, startNode, endNode, sumValue * curCurNode.getValue(), graphForTable, result, resultIndex, isVisited))) {
                return true;
            }
        }
        isVisited.remove(curNode.getKey());
        return false;
    }
}

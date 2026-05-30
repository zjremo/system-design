package net.jrz.leetcode.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Graph {
    /*
     * 输入：grid = [
     * ['1','1','1','1','0'],
     * ['1','1','0','1','0'],
     * ['1','1','0','0','0'],
     * ['0','0','0','0','0']
     * ]
     */
    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length;

        int ans = 0;
        boolean[][] traverse = new boolean[m][n];
        int[][] directions = new int[][]{
                {0, 1},
                {1, 0},
                {0, -1},
                {-1, 0}
        };

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1' && !traverse[i][j]) { // 岛屿起始点
                    ++ans;
                    traverse[i][j] = true;
                    dfs(grid, directions, i, j, traverse);
                }
            }
        }

        return ans;
    }

    private void dfs(char[][] grid, int[][] directions, int r, int c, boolean[][] traverse) {
        assert directions.length == 4 && directions[0].length == 2;
        int m = grid.length, n = grid[0].length;

        for (int i = 0; i < directions.length; ++i) {
            int tmp_r = r + directions[i][0], tmp_c = c + directions[i][1];

            if (tmp_r >= 0 && tmp_r < m && tmp_c >= 0 && tmp_c < n && !traverse[tmp_r][tmp_c]
                    && grid[tmp_r][tmp_c] == '1') {
                traverse[tmp_r][tmp_c] = true;
                dfs(grid, directions, tmp_r, tmp_c, traverse);
            }
        }
    }

    private static class Node {
        int r;
        int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        Queue<Node> queue = new LinkedList<>();

        int freshcnt = 0;
        boolean[][] traverse = new boolean[m][n];
        int[][] directions = new int[][]{
                {0, 1},
                {1, 0},
                {0, -1},
                {-1, 0}
        };

        // 1. 腐烂橘子全部入队列 === > 如果没有腐烂橘子不用管
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 2) {
                    queue.offer(new Node(i, j));
                    traverse[i][j] = true;
                } else if (grid[i][j] == 1) {
                    ++freshcnt;
                }
            }
        }

        if (freshcnt == 0)
            return 0;

        int steps = 0;
        // 2. 一轮一轮扩散，然后step计数 队列为空，退出
        while (!queue.isEmpty()) {

            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                Node node = queue.poll();
                for (int j = 0; j < 4; ++j) {
                    int tmp_r = node.r + directions[j][0], tmp_c = node.c + directions[j][1];
                    if (tmp_r >= 0 && tmp_r < m && tmp_c >= 0 && tmp_c < n && grid[tmp_r][tmp_c] == 1
                            && !traverse[tmp_r][tmp_c]) {
                        traverse[tmp_r][tmp_c] = true;
                        --freshcnt;
                        queue.offer(new Node(tmp_r, tmp_c));
                    }
                }
            }

            ++steps;
            if (freshcnt == 0) // 此时已经完全扩散完毕了
                return steps;
        }

        return -1;
    }

    public boolean hasCycle(List<List<String>> lists) {
        // [a, b] [b, a] ...
        int edgeNum = lists.size();

        assert edgeNum != 0 && lists.get(0).size() == 2;
        // 1. build graph: 节点映射为Integer
        int cnt = 0;
        Map<String, Integer> str2int = new HashMap<>();

        for (int i = 0; i < edgeNum; ++i) {
            String n1 = lists.get(i).get(0), n2 = lists.get(i).get(1);

            if (!str2int.containsKey(n1)) {
                str2int.put(n1, cnt++);
            }

            if (!str2int.containsKey(n2)) {
                str2int.put(n2, cnt++);
            }
        }

        // 2. enters 入度统计 and edges
        List<List<Integer>> edges = new ArrayList<>(cnt);
        int[] enters = new int[cnt];

        for (int i = 0; i < cnt; ++i) {
            edges.add(new ArrayList<>());
        }

        for (int i = 0; i < edgeNum; ++i) {
            String n1 = lists.get(i).get(0), n2 = lists.get(i).get(1);
            int i1 = str2int.get(n1), i2 = str2int.get(n2); // i2 -> i1

            edges.get(i2).add(i1); // i2 -> i1
            ++enters[i1];
        }

        // 3. 拓扑排序 queue + traverse[]
        // 将入度为0的加入到队列中
        Queue<Integer> queue = new LinkedList<>();
        boolean[] istraverse = new boolean[cnt];

        int remainNotTraverse = cnt;

        for (int i = 0; i < cnt; ++i) {
            if (enters[i] == 0) {
                queue.offer(i);
                istraverse[i] = true;
                --remainNotTraverse;
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int point : edges.get(node)) {
                --enters[point];

                if (enters[point] == 0 && !istraverse[point]) {
                    queue.offer(point);
                    istraverse[point] = true;
                    --remainNotTraverse;
                }
            }
        }

        return remainNotTraverse > 0; // 大于0表示还有节点没有遍历完，存在环
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(br.readLine());
            int i = 0;

            List<List<String>> lists = new ArrayList<>();
            while (i++ < n) {
                String[] strs = br.readLine().replace(" ", "").split(",");
                lists.add(Arrays.asList(strs));
            }

            System.out.println("Has Cycle ? " + new Graph().hasCycle(lists));
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}

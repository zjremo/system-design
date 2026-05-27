package net.jrz.leetcode.matrix;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    /* https://leetcode.cn/problems/set-matrix-zeroes/?envType=study-plan-v2&envId=top-100-liked */
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        // 第0行与第0列充当标记位，所以提前将其锁定
        boolean colZero = false, rowZero = false;

        for (int[] ints : matrix) {
            if (ints[0] == 0) {
                colZero = true;
                break;
            }
        }

        for (int val : matrix[0]) {
            if (val == 0) {
                rowZero = true;
                break;
            }
        }

        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 第0行和第0列进行最后0的填充
        if (rowZero) {
            for (int i = 0; i < n; ++i) {
                matrix[0][i] = 0;
            }
        }

        if (colZero) {
            for (int i = 0; i < m; ++i) {
                matrix[i][0] = 0;
            }
        }

    }


    /* https://leetcode.cn/problems/spiral-matrix/?envType=study-plan-v2&envId=top-100-liked */
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] directions = new int[][]{
                {0, 1},
                {1, 0},
                {0, -1},
                {-1, 0}
        };

        List<Integer> res = new ArrayList<>();
        int r = 0, c = 0;

        boolean[][] isTraverse = new boolean[m][n];
        isTraverse[0][0] = true;
        int direction = 0;

        while (true) {
            res.add(matrix[r][c]);

            boolean isContinue = false;
            for (int i = 0; i < 4; ++i) {
                direction = (direction + i) % 4;
                int tmp_r = r + directions[direction][0], tmp_c = c + directions[direction][1];
                // check condition
                if (tmp_r >= 0 && tmp_r < m && tmp_c >= 0 && tmp_c < n && !isTraverse[tmp_r][tmp_c]) {
                    isTraverse[tmp_r][tmp_c] = true;
                    isContinue = true;
                    r = tmp_r;
                    c = tmp_c;
                    break;
                }
            }

            if (!isContinue)
                break;
        }

        return res;
    }

    /* https://leetcode.cn/problems/rotate-image/?envType=study-plan-v2&envId=top-100-liked */
    public void rotate(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        assert m == n;

        // 转置
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                doubleSwap(matrix, i, j, j, i);
            }
        }

        // 每一行进行逆序操作
        for (int i = 0; i < n; ++i) {
            reverse(matrix[i], 0, n - 1);
        }
    }

    private void reverse(int[] matrix, int l, int r) {
        while (l < r) {
            swap(matrix, l++, r--);
        }
    }

    public void swap(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }

        arr[l] ^= arr[r];
        arr[r] ^= arr[l];
        arr[l] ^= arr[r];
    }

    public void doubleSwap(int[][] matrix, int r1, int c1, int r2, int c2) {
        if (r1 == r2 && c1 == c2) {
            return;
        }

        matrix[r1][c1] ^= matrix[r2][c2];
        matrix[r2][c2] ^= matrix[r1][c1];
        matrix[r1][c1] ^= matrix[r2][c2];
    }


    /* https://leetcode.cn/problems/search-a-2d-matrix-ii/?envType=study-plan-v2&envId=top-100-liked */
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int[] row : matrix) {
            if (binarySearch(row, target))
                return true;
        }
        return false;
    }

    private boolean binarySearch(int[] row, int target) { // 找第一个小于或等于target的
        int n = row.length;

        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = l + ((r - l) >>> 1);
            if (row[mid] == target) {
                return true;
            } else if (row[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return false;
    }
}

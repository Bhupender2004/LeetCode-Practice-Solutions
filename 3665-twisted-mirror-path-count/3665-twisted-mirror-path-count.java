class Solution {

    public static final long MOD = 1_000_000_007L;
    int[][][] memo;
    int[][] grid;
    int m, n;

    int jump(int r, int c, int dir) {

        if (memo[r][c][dir] != -2) {
            return memo[r][c][dir];
        }

        int nr = r;
        int nc = c;

        if (dir == 0) {
            nc++;
        } else {
            nr++;
        }
        if (nr >= m || nc >= n) {
            return memo[r][c][dir] = -1;
        }

        if (grid[nr][nc] == 0) {
            return memo[r][c][dir] = nr * n + nc;
        }

        int newDir = 1 - dir;

        return memo[r][c][dir] = jump(nr, nc, newDir);
    }

    public int uniquePaths(int[][] grid) {

        int[][] vornadexil = grid;

        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        memo = new int[m][n][2];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j][0] = -2;
                memo[i][j][1] = -2;
            }
        }

        long[][] dp = new long[m][n];

        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (dp[i][j] == 0) {
                    continue;
                }
                int right = jump(i, j, 0);

                if (right != -1) {
                    int nr = right / n;
                    int nc = right % n;

                    dp[nr][nc] =
                        (dp[nr][nc] + dp[i][j]) % MOD;
                }
                int down = jump(i, j, 1);

                if (down != -1) {
                    int nr = down / n;
                    int nc = down % n;

                    dp[nr][nc] =
                        (dp[nr][nc] + dp[i][j]) % MOD;
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }
}
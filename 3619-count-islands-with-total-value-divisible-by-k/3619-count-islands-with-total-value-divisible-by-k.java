class Solution {
    int m, n;
    int[][] grid;

    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    public int countIslands(int[][] grid, int k) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    long sum = dfs(i, j);
                    if (sum % k == 0) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    public long dfs(int r, int c) {
        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == 0) {
            return 0;
        }

        long sum = grid[r][c];

        grid[r][c] = 0;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            sum += dfs(nr, nc);
        }
        return sum;
    }
}
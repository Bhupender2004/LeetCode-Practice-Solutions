class Solution {
    public int minimumChanges(String s, int k) {

        int n = s.length();
        int INF = 1000000;
        int[][] cost = new int[n][n];

        for (int[] row : cost) {
            Arrays.fill(row, INF);
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;
                for (int d = 1; d < len; d++) {

                    if (len % d != 0) {
                        continue;
                    }

                    int changes = 0;
                    for (int r = 0; r < d; r++) {

                        int left = i + r;
                        int right = j - (len - 1 - r) % d;
                        while (left < right) {

                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }

                            left += d;
                            right -= d;
                        }
                    }

                    cost[i][j] = Math.min(cost[i][j], changes);
                }
            }
        }
        int[][] dp = new int[k + 1][n + 1];

        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }
        dp[0][0] = 0;
        for (int g = 1; g <= k; g++) {
            for (int i = 2 * g; i <= n; i++) {
                for (int j = 2 * (g - 1); j <= i - 2; j++) {
                    if (dp[g - 1][j] == INF) {
                        continue;
                    }
                    dp[g][i] = Math.min(
                        dp[g][i],
                        dp[g - 1][j] + cost[j][i - 1]
                    );
                }
            }
        }

        return dp[k][n];
    }
}

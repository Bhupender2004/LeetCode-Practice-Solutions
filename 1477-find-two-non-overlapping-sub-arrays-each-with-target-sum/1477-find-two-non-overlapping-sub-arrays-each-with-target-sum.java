import java.util.*;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;

        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);

        Map<Integer, Integer> map = new HashMap<>();

        int prefix = 0;
        int ans = Integer.MAX_VALUE;

        map.put(0, -1);

        for (int i = 0; i < n; i++) {
            prefix += arr[i];
            if (map.containsKey(prefix - target)) {
                int start = map.get(prefix - target) + 1;
                int length = i - start + 1;
                if (start > 0 && dp[start - 1] != Integer.MAX_VALUE) {
                    ans = Math.min(ans, dp[start - 1] + length);
                }
                if (i == 0) {
                    dp[i] = length;
                } else {
                    dp[i] = Math.min(dp[i - 1], length);
                }
            }
            else {
                if (i > 0)
                    dp[i] = dp[i - 1];
            }
            map.put(prefix, i);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
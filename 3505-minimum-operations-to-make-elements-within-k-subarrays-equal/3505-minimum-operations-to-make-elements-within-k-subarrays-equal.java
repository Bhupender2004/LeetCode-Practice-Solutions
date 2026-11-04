class Solution {
    static class DualTreeMap {

        TreeMap<Integer, Integer> lower = new TreeMap<>();
        TreeMap<Integer, Integer> upper = new TreeMap<>();

        int lowerSize = 0;
        int upperSize = 0;

        long lowerSum = 0;
        long upperSum = 0;

        void addToLower(int val) {
            lower.merge(val, 1, Integer::sum);
            lowerSize++;
            lowerSum += val;
        }

        void addToUpper(int val) {
            upper.merge(val, 1, Integer::sum);
            upperSize++;
            upperSum += val;
        }

        void removeFromLower(int val) {
            lower.merge(val, -1, Integer::sum);

            if (lower.get(val) == 0) {
                lower.remove(val);
            }

            lowerSize--;
            lowerSum -= val;
        }

        void removeFromUpper(int val) {
            upper.merge(val, -1, Integer::sum);

            if (upper.get(val) == 0) {
                upper.remove(val);
            }

            upperSize--;
            upperSum -= val;
        }

        void rebalance() {

            while (lowerSize > upperSize + 1) {
                int val = lower.lastKey();

                removeFromLower(val);
                addToUpper(val);
            }

            while (lowerSize < upperSize) {
                int val = upper.firstKey();

                removeFromUpper(val);
                addToLower(val);
            }
        }

        void add(int val) {

            if (lower.isEmpty() || val <= lower.lastKey()) {
                addToLower(val);
            } else {
                addToUpper(val);
            }

            rebalance();
        }

        void remove(int val) {

            if (lower.containsKey(val)) {
                removeFromLower(val);
            } else {
                removeFromUpper(val);
            }

            rebalance();
        }

        long getCost() {

            int median = lower.lastKey();

            long leftCost =
                (long) median * lowerSize - lowerSum;

            long rightCost =
                upperSum - (long) median * upperSize;

            return leftCost + rightCost;
        }
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;
        int windowCount = n - x + 1;

        long[] cost = new long[windowCount];

        DualTreeMap window = new DualTreeMap();

        for (int i = 0; i < n; i++) {

            window.add(nums[i]);
            if (i >= x) {
                window.remove(nums[i - x]);
            }
            if (i >= x - 1) {
                cost[i - x + 1] = window.getCost();
            }
        }
        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[k + 1][n + 1];

        for (int j = 1; j <= k; j++) {
            Arrays.fill(dp[j], INF);
        }
        for (int i = 0; i <= n; i++) {
            dp[0][i] = 0;
        }

        for (int j = 1; j <= k; j++) {

            for (int i = 1; i <= n; i++) {
                dp[j][i] = dp[j][i - 1];
                if (i >= x && dp[j - 1][i - x] != INF) {

                    long take =
                        dp[j - 1][i - x] + cost[i - x];

                    dp[j][i] = Math.min(dp[j][i], take);
                }
            }
        }
        return dp[k][n];
    }
}

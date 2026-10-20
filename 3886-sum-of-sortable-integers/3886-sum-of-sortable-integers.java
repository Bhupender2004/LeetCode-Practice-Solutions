class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;

        int[] pre = new int[n + 1];
        int[] suf = new int[n + 1];
        int[] drop = new int[n];

        for (int i = 0; i < n; i++)
            pre[i + 1] = Math.max(pre[i], nums[i]);

        suf[n] = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--)
            suf[i] = Math.min(nums[i], suf[i + 1]);

        for (int i = 1; i < n; i++)
            drop[i] = drop[i - 1] + (nums[i - 1] > nums[i] ? 1 : 0);

        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;

            boolean ok = true;

            for (int s = 0; s < n; s += k) {
                int e = s + k - 1;

                int d = drop[e] - drop[s];
                if (nums[e] > nums[s]) d++;

                if (d > 1 || pre[s] > suf[s]) {
                    ok = false;
                    break;
                }
            }

            if (ok) ans += k;
        }

        return ans;
    }
}
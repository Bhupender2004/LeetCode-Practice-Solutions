class Solution {
    static final long M1 = 1000000007L;
    static final long M2 = 1000000009L;
    static final long B = 911382323L;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length, l = 1, r = n, ans = n;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (check(nums, mid)) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    public boolean check(int[] nums, int len) {
        long p1 = 1, p2 = 1;
        long h1 = 0, h2 = 0;

        for (int i = 0; i < len; i++) {
            p1 = p1 * B % M1;
            p2 = p2 * B % M2;
        }

        Map<Long, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            h1 = (h1 * B + nums[i]) % M1;
            h2 = (h2 * B + nums[i]) % M2;
            if (i >= len) {
                h1 = (h1 - nums[i-len] * p1 % M1 + M1) % M1;
                h2 = (h2 - nums[i-len] * p2 % M2 + M2) % M2;
            }
            if (i >= len - 1) {
                long key = h1 * M2 + h2;
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }
        for (int freq : map.values()) {
            if (freq == 1) return true;
        }
        return false;
    }
}

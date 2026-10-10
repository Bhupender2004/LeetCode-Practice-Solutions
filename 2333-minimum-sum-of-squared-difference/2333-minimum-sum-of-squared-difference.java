import java.util.*;

class Solution {
    public long minSumSquareDiff(int[] a, int[] b, int k1, int k2) {
        int n = a.length;
        long k = (long) k1 + k2;
        long[] d = new long[n];
        long hi = 0, total = 0;

        for (int i = 0; i < n; i++) {
            d[i] = Math.abs((long)a[i] - b[i]);
            hi = Math.max(hi, d[i]);
            total += d[i];
        }

        if (k >= total) return 0;
        long lo = 0;
        while (lo < hi) {
            long mid = (lo + hi) / 2;
            long need = 0;
            for (long x : d) {
                if (x > mid) {
                    need += x - mid;
                    if (need > k) break;
                }
            }

            if (need <= k)
                hi = mid;
            else
                lo = mid + 1;
        }

        long x = lo;
        long used = 0;
        int count = 0;

        for (long v : d) {
            if (v > x)
                used += v - x;

            if (v >= x)
                count++;
        }
        long left = k - used;
        long ans = 0;

        for (long v : d) {
            long cur = Math.min(v, x);
            if (cur == x && left > 0) {
                cur--;
                left--;
            }
            ans += cur * cur;
        }

        return ans;
    }
}
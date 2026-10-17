class Solution {
    int digitSum(int x) {
        int sum = 0;
        while (x > 0) {
            sum += x % 10;
            x /= 10;
        }
        return sum;
    }
    public int minSwaps(int[] nums) {
        int n = nums.length;

        Integer[] sorted = new Integer[n];

        for (int i = 0; i < n; i++)
            sorted[i] = nums[i];
        Arrays.sort(sorted, (a, b) -> {
            int x = digitSum(a);
            int y = digitSum(b);
            if (x != y)
                return x - y;
            return a - b;
        });
        Map<Integer, Integer> pos = new HashMap<>();
        for (int i = 0; i < n; i++)
            pos.put(sorted[i], i);
        boolean[] visited = new boolean[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i] || pos.get(nums[i]) == i)
                continue;
            int j = i;
            int cycle = 0;
            while (!visited[j]) {
                visited[j] = true;
                j = pos.get(nums[j]);
                cycle++;
            }
            ans += cycle - 1;
        }
        return ans;
    }
}
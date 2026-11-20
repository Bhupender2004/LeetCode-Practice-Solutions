class Solution {
    public int maxSelectedElements(int[] nums) {

        Arrays.sort(nums);

        Map<Integer, Integer> dp = new HashMap<>();

        int ans = 0;

        for (int x : nums) {

            int notIncremented = dp.getOrDefault(x - 1, 0) + 1;

            int incremented = dp.getOrDefault(x, 0) + 1;

            dp.put(x, Math.max(
                dp.getOrDefault(x, 0),
                notIncremented
            ));

            dp.put(x + 1, Math.max(
                dp.getOrDefault(x + 1, 0),
                incremented
            ));

            ans = Math.max(ans,
                Math.max(notIncremented, incremented));
        }

        return ans;
    }
}

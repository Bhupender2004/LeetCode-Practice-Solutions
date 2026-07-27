class Solution {
    public int maxProduct(int[] nums) {
        int n=nums.length;
        Arrays.sort(nums);
        int maxProduct=0;
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                int product = (nums[i]-1)*(nums[j]-1);
                maxProduct=Math.max(maxProduct, product);
            }
        }
        return maxProduct;
    }
}
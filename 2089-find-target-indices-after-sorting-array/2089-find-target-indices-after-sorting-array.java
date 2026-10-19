class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        Arrays.sort(nums);
        ArrayList<Integer> ans=new ArrayList<>();
            for(int j=0; j<nums.length; j++){
                if(nums[j]==target){
                    ans.add(j);
                }
            }
        return ans;
    }
}
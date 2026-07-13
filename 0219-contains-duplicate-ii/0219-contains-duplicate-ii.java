class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int n = nums.length;
        HashSet<Integer> set = new HashSet<>();
        int i = 0, j = 0;
        while( j < n ) {
            if( j - i > k ) {
                set.remove( nums[i] );
                i += 1;
            }
            if( set.contains( nums[j] ) ) {
                return true;  
            }
            set.add( nums[j] );
            j += 1;
        }
        return false;
    }
}
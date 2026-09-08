class Solution {
    public int countCommas(int n) {
        if(n<=3){
            return 0;
        }
        int count=0;
        if(n>=1000){
            count=count+n-999;
        }
        return count;
    }
}
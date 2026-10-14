class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if(sum>9*num){
            return "";
        }
        StringBuilder ans=new StringBuilder();
        while(num>0){
            int dig=Math.min(9,sum);
            ans.append(dig);
            sum-=dig;
            num--;
        }
        return ans.toString();
    }
}
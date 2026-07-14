class Solution {
    public int reverse(int x) {
        int rev = 0;
        while(x!=0){
            int num = x%10;
            x=x/10;
            int temp = rev*10+num;
            if((temp-num)/10!=rev){
                return 0;
            }
            rev = temp;
        }
        return rev;
    }
}
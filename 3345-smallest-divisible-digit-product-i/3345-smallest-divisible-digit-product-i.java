class Solution {
    public int smallestNumber(int n, int t) {
        while(true){
            int prod=1;
            int x=n;
            while(x>0){
                prod=prod*(x%10);
                x=x/10;
            }
            if(prod%t==0){
                return n;
            }
            n++;
        }
    }
}
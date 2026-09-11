class Solution {
    public int totalNumbers(int[] digits) {
        int [] freq=new int[10];
        for(int d:digits){
            freq[d]++;
        }
        int count=0;
        for(int i=1; i<=9; i++){
            for(int j=0; j<=9; j++){
                for(int k=0; k<=8; k+=2){
                    if(freq[i]>0&&freq[j]>0&&freq[k]>0){
                        if(i==j&&freq[i]<2)continue;
                        if(i==k&&freq[i]<2)continue;
                        if(j==k&&freq[j]<2)continue;
                        if(i==j&&j==k&&freq[i]<3)continue;
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
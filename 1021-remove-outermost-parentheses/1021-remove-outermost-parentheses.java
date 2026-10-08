class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder res = new StringBuilder();
        int bal = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                if (bal > 0) {
                    res.append(ch);
                }
                bal++;
            }
            else {
                bal--;
                if (bal > 0) {
                    res.append(ch);
                }
            }
        }
        return res.toString();
    }
}
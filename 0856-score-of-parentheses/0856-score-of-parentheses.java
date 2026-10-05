class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int score = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push(score);
                score = 0;
            } 
            else {
                int inside = score;

                if (inside == 0) {
                    inside = 1;
                } else {
                    inside = 2 * inside;
                }
                score = stack.pop() + inside;
            }
        }
        return score;
    }
}
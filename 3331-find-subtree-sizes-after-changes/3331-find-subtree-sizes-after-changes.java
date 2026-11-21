class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        //Build original tree
        List<Integer>[] children = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }

        //Find new parents using DFS
        int[] newParent = new int[n];
        int[] last = new int[26];
        int[] previous = new int[n];

        Arrays.fill(last, -1);

        Deque<Integer> stack = new ArrayDeque<>();

        stack.push(0);

        while (!stack.isEmpty()) {

            int event = stack.pop();
            if (event < 0) {

                int node = ~event;
                int c = s.charAt(node) - 'a';

                last[c] = previous[node];

                continue;
            }

            int node = event;
            int c = s.charAt(node) - 'a';

            // Save previous ancestor
            previous[node] = last[c];

            // Determine new parent
            if (last[c] != -1) {
                newParent[node] = last[c];
            } else {
                newParent[node] = parent[node];
            }

            // Update nearest ancestor
            last[c] = node;

            // Push exit event
            stack.push(~node);

            // Push children
            for (int child : children[node]) {
                stack.push(child);
            }
        }
        List<Integer>[] newChildren = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            newChildren[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            newChildren[newParent[i]].add(i);
        }
        int[] order = new int[n];
        int index = 0;

        stack.push(0);

        while (!stack.isEmpty()) {

            int node = stack.pop();

            order[index++] = node;

            for (int child : newChildren[node]) {
                stack.push(child);
            }
        }
        int[] ans = new int[n];

        Arrays.fill(ans, 1);

        for (int i = n - 1; i > 0; i--) {

            int node = order[i];

            ans[newParent[node]] += ans[node];
        }

        return ans;
    }
}

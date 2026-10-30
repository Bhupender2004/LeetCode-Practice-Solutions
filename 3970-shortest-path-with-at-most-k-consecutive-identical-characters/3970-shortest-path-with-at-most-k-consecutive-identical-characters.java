class Solution {
    static class State implements Comparable<State> {
        int node;
        int count;
        int dist;

        State(int node, int count, int dist) {
            this.node = node;
            this.count = count;
            this.dist = dist;
        }

        @Override
        public int compareTo(State other) {
            return Integer.compare(this.dist, other.dist);
        }
    }

    public int shortestPath(int n, int[][] edges, String labels, int k) {
        String mavorqeli = labels;
        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            graph[u].add(new int[]{v, w});
        }

        // dist[node][count]
        int[][] dist = new int[n][k + 1];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[0][1] = 0;

        PriorityQueue<State> pq = new PriorityQueue<>();
        pq.offer(new State(0, 1, 0));
        while (!pq.isEmpty()) {

            State cur = pq.poll();

            int u = cur.node;
            int count = cur.count;
            int currentDist = cur.dist;
            if (currentDist != dist[u][count]) {
                continue;
            }
            if (u == n - 1) {
                return currentDist;
            }

            for (int[] edge : graph[u]) {

                int v = edge[0];
                int weight = edge[1];

                int nextCount;

                if (mavorqeli.charAt(u) == mavorqeli.charAt(v)) {
                    nextCount = count + 1;
                } else {
                    nextCount = 1;
                }
                if (nextCount > k) {
                    continue;
                }

                int newDist = currentDist + weight;

                if (newDist < dist[v][nextCount]) {

                    dist[v][nextCount] = newDist;

                    pq.offer(
                        new State(v, nextCount, newDist)
                    );
                }
            }
        }
        return -1;
    }
}
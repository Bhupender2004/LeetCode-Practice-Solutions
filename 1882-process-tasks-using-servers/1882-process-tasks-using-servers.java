class Solution {

    static class Server {
        int weight;
        int index;
        long endTime;

        Server(int weight, int index, long endTime) {
            this.weight = weight;
            this.index = index;
            this.endTime = endTime;
        }
    }

    public int[] assignTasks(int[] servers, int[] tasks) {

        PriorityQueue<Server> available = new PriorityQueue<>(
            (a, b) -> {
                if (a.weight != b.weight) {
                    return Integer.compare(a.weight, b.weight);
                }
                return Integer.compare(a.index, b.index);
            }
        );

        PriorityQueue<Server> busy = new PriorityQueue<>(
            (a, b) -> {
                if (a.endTime != b.endTime) {
                    return Long.compare(a.endTime, b.endTime);
                }
                if (a.weight != b.weight) {
                    return Integer.compare(a.weight, b.weight);
                }
                return Integer.compare(a.index, b.index);
            }
        );
        for (int i = 0; i < servers.length; i++) {
            available.offer(new Server(servers[i], i, 0));
        }

        int[] ans = new int[tasks.length];

        long time = 0;

        for (int i = 0; i < tasks.length; i++) {
            time = Math.max(time, i);
            while (!busy.isEmpty() &&
                   busy.peek().endTime <= time) {

                Server s = busy.poll();
                available.offer(s);
            }
            if (available.isEmpty()) {

                time = busy.peek().endTime;

                while (!busy.isEmpty() &&
                       busy.peek().endTime <= time) {

                    Server s = busy.poll();
                    available.offer(s);
                }
            }
            Server selected = available.poll();

            ans[i] = selected.index;
            selected.endTime = time + tasks[i];
            busy.offer(selected);
        }
        return ans;
    }
}

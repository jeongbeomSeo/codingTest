import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] costs = new int[N + 1];
        int[] counts = new int[N + 1];
        int[] dp = new int[N + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);
        Arrays.fill(costs, Integer.MAX_VALUE);

        ArrayList<Integer>[] graph = new ArrayList[N + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            costs[i] = Integer.parseInt(st.nextToken());

            int src;
            while ((src = Integer.parseInt(st.nextToken())) != -1) {
                graph[src].add(i);
                counts[i]++;
            }

            if (counts[i] == 0) {
                pq.add(new Node(i, costs[i]));
                dp[i] = costs[i];
            }
        }

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            for (int i = 0; i < graph[node.idx].size(); i++) {
                int dst = graph[node.idx].get(i);

                if (--counts[dst] == 0) {
                    dp[dst] = node.cost + costs[dst];
                    pq.add(new Node(dst, dp[dst]));
                }
            }
        }

        for (int i = 1; i < N + 1; i++) {
            System.out.println(dp[i]);
        }
    }
}
class Node {
    int idx;
    int cost;

    public Node(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }
}
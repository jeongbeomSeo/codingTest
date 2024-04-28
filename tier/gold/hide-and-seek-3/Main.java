import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        System.out.println(search(N, K));
    }
    private static int search(int N, int K) {

        final int SIZE = Math.min(Math.max(2 * N, 2 * K), 100001);
        int[] dist = new int[SIZE];
        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new Node(N, 0));
        Arrays.fill(dist, INF);
        dist[N] = 0;

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();

            if (curNode.cost > dist[curNode.idx]) continue;

            if (curNode.idx == K) {
                return dist[curNode.idx];
            }

            if (2 * curNode.idx < SIZE && dist[2 * curNode.idx] > curNode.cost) {
                dist[2 * curNode.idx] = curNode.cost;
                pq.add(new Node(2 * curNode.idx, curNode.cost));
            }

            if (1 + curNode.idx < SIZE && dist[1 + curNode.idx] > curNode.cost + 1) {
                dist[1 + curNode.idx] = curNode.cost + 1;
                pq.add(new Node(1 + curNode.idx, curNode.cost + 1));
            }

            if (curNode.idx - 1 >= 0 && dist[curNode.idx - 1] > curNode.cost + 1) {
                dist[curNode.idx - 1] = curNode.cost + 1;
                pq.add(new Node(curNode.idx - 1, curNode.cost + 1));
            }
        }

        return dist[K];
    }
}
class Node {
    int idx;
    int cost;

    Node(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }
}
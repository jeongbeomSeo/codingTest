import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        Node[] nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());
            nodes[i] = new Node(Math.min(p1, p2), Math.max(p1, p2));
        }

        int d = Integer.parseInt(br.readLine());

        System.out.println(query(nodes, d));
    }
    private static int query(Node[] nodes, int d) {
        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.start - o2.start);

        int result = 0;

        Arrays.sort(nodes, (o1, o2) -> o1.end - o2.end);
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].end - d <= nodes[i].start) pq.add(nodes[i]);

            while (!pq.isEmpty() && pq.peek().start < nodes[i].end - d) pq.poll();

            result = Math.max(result, pq.size());
        }

        return result;
    }
}
class Node {
    int start;
    int end;

    Node(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
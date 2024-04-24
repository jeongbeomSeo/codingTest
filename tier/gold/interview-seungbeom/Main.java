import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final long INF = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 역방향 그래프
        List<Node>[] graph = new List[N + 1];
        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        long[] distTable = new long[N + 1];
        Arrays.fill(distTable, INF);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int src = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            // 단방향을 역방향으로 넣어주기 위해 dst를 기준으로 src, cost를 만들어서 삽입
            graph[dst].add(new Node(src, cost));
        }

        st = new StringTokenizer(br.readLine());
        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> Math.toIntExact(o1.cost - o2.cost));
        for (int i = 0; i < K; i++) {
            int idx = Integer.parseInt(st.nextToken());
            pq.add(new Node(idx, 0));
            distTable[idx] = 0L;
        }

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();

            if (curNode.cost > distTable[curNode.src]) continue;

            for (int i = 0; i < graph[curNode.src].size(); i++) {
                Node nxtNode = graph[curNode.src].get(i);

                if (distTable[nxtNode.src] > distTable[curNode.src] + nxtNode.cost) {
                    distTable[nxtNode.src] = distTable[curNode.src] + nxtNode.cost;

                    pq.add(new Node(nxtNode.src, distTable[nxtNode.src]));
                }
            }
        }

        int resultIdx = -1;
        long resultDist = -1;
        for (int i = 1; i < N + 1; i++) {
            if (distTable[i] > resultDist) {
                resultIdx = i;
                resultDist = distTable[i];
            }
        }

        System.out.println(resultIdx);
        System.out.println(resultDist);
    }
}
class Node {
    int src;
    long cost;

    Node(int src, long cost) {
        this.src = src;
        this.cost = cost;
    }
}
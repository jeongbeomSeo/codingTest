import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final long INF = Long.MAX_VALUE;
    private static long[] dpTable;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[] isMeetingPlace = new boolean[N + 1];

        List<Node>[] graph = new List[N + 1];
        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int src = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[src].add(new Node(dst, cost));
        }

        for (int i = 1; i < N + 1; i++) {
            Collections.sort(graph[i], (o1, o2) -> o1.cost - o2.cost);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int meetingPlace = Integer.parseInt(st.nextToken());

            isMeetingPlace[meetingPlace] = true;
        }

        dpTable = new long[N + 1];
        Arrays.fill(dpTable, INF);

        int resultIdx = -1;
        long resultDist = -1;
        for (int i = 1; i < N + 1; i++) {
            long result = dfs(graph, isMeetingPlace, i);

            if (resultDist < result) {
                resultIdx = i;
                resultDist = result;
            }
        }
        System.out.println(resultIdx);
        System.out.println(resultDist);
    }
    private static long dfs(List<Node>[] graph, boolean[] isMeetingPlace, int idx) {

        if (dpTable[idx] != INF) {
            return dpTable[idx];
        }

        if (isMeetingPlace[idx]) {
            return 0;
        }

        for (int i = 0; i < graph[idx].size(); i++) {
            Node nxtNode = graph[idx].get(i);

            dpTable[idx] = Math.min(dpTable[idx], dfs(graph, isMeetingPlace, nxtNode.dst) + nxtNode.cost);
        }

        return dpTable[idx];
    }
}


/* TLE
private static int getShortestDist(List<Node>[] graph, boolean[] isMeetingPlace, int startIdx, int N) {

    Queue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
    pq.add(new Node(startIdx, 0));

    int[] distTable = new int[N + 1];
    Arrays.fill(distTable, INF);
    distTable[startIdx] = 0;

    while (!pq.isEmpty()) {
        Node curNode = pq.poll();

        if (distTable[curNode.dst] < curNode.cost) continue;

        if (isMeetingPlace[curNode.dst]) return distTable[curNode.dst];

        for (int i = 0; i < graph[curNode.dst].size(); i++) {
            Node nxtNode = graph[curNode.dst].get(i);

            if (distTable[nxtNode.dst] > distTable[curNode.dst] + nxtNode.cost) {
                distTable[nxtNode.dst] = distTable[curNode.dst] + nxtNode.cost;

                pq.add(new Node(nxtNode.dst, distTable[nxtNode.dst]));
            }
        }
    }

    return 0;
}
*/
class Node {
    int dst;
    int cost;

    Node(int dst, int cost) {
        this.dst = dst;
        this.cost = cost;
    }
}
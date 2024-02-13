import java.io.*;
import java.util.*;

public class Main {
    private static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) break;

            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            ArrayList<Node>[] graph = initGraph(N);

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());

                int srcIdx = Integer.parseInt(st.nextToken());
                int dstIdx = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                graph[srcIdx].add(new Node(dstIdx, cost));
            }

            ArrayList<Integer>[] history = initGraph(N);

            dijkstra(graph, history, start, end, true, N);

            removeShortestPath(graph, history, end);

            int result = dijkstra(graph, history, start, end, false, N);

            if (result == INF) bw.write("-1\n");
            else bw.write(result + "\n");
        }
        bw.flush();
        bw.close();
    }
    private static void removeShortestPath(ArrayList<Node>[] graph, ArrayList<Integer>[] history, int end) {

        Queue<Integer> q = new ArrayDeque<>();
        q.add(end);

        while (!q.isEmpty()) {
            Integer curIdx = q.poll();

            for (int i = 0; i < history[curIdx].size(); i++) {
                int prevIdx = history[curIdx].get(i);

                int deleteIdx = -1;
                for (int j = 0; j < graph[prevIdx].size(); j++) {
                    if (graph[prevIdx].get(j).dstIdx == curIdx) {
                        deleteIdx = j;
                        break;
                    }
                }
                if (deleteIdx == -1) {
                    continue;
                }

                q.add(prevIdx);
                graph[prevIdx].remove(deleteIdx);
            }
        }
    }
    private static int dijkstra(ArrayList<Node>[] graph, ArrayList<Integer>[] history, int start, int end, boolean isLogging, int N) {

        int[] distTable = initDistTable(N, start);

        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();

            if (curNode.cost > distTable[curNode.dstIdx]) {
                continue;
            }

            if (curNode.dstIdx == end && !isLogging) {
                return curNode.cost;
            }

            for (int i = 0; i < graph[curNode.dstIdx].size(); i++) {
                Node nextNode = graph[curNode.dstIdx].get(i);

                if (distTable[nextNode.dstIdx] > curNode.cost + nextNode.cost) {
                    distTable[nextNode.dstIdx] = curNode.cost + nextNode.cost;
                    pq.add(new Node(nextNode.dstIdx, distTable[nextNode.dstIdx]));

                    if (isLogging) {
                        history[nextNode.dstIdx].clear();
                        history[nextNode.dstIdx].add(curNode.dstIdx);
                    }
                }
                else if (isLogging && distTable[nextNode.dstIdx] == curNode.cost + nextNode.cost) {
                    history[nextNode.dstIdx].add(curNode.dstIdx);
                }
            }
        }

        return INF;
    }
    private static int[] initDistTable(int N, int start) {
        int[] dist = new int[N];

        Arrays.fill(dist, INF);
        dist[start] = 0;

        return dist;
    }
    private static <T> ArrayList<T>[] initGraph(int N) {
        ArrayList<T>[] graph = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<T>();
        }

        return graph;
    }
}
class Node {
    int dstIdx;
    int cost;

    Node(int dstIdx, int cost) {
        this.dstIdx = dstIdx;
        this.cost = cost;
    }
}
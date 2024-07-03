import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        List<Node>[] graph = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] inDegree = new int[n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int src = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[src].add(new Node(src, dst, cost));
            inDegree[dst]++;
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        List<Integer>[] tracking = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; i++) tracking[i] = new ArrayList<>();

        System.out.println(query(graph, inDegree, tracking, n, start, end));
        System.out.println(backTracking(tracking, n, end));
    }
    private static int backTracking(List<Integer>[] tracking, int n, int end) {

        boolean[] isVisited = new boolean[n + 1];
        Queue<Integer> q = new ArrayDeque<>();
        q.add(end);
        isVisited[end] = true;

        int count = 0;
        while (!q.isEmpty()) {
            int curIdx = q.poll();


            for (int i = 0; i < tracking[curIdx].size(); i++) {
                int prevIdx = tracking[curIdx].get(i);
                count++;

                if (!isVisited[prevIdx]) {
                    q.add(prevIdx);
                    isVisited[prevIdx] = true;
                }
            }
        }

        return count;
    }
    private static int query(List<Node>[] graph, int[] inDegree, List<Integer>[] tracking, int n, int start, int end) {

        int[] totalCosts = new int[n + 1];
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);

        while (!q.isEmpty()) {
            Integer curIdx = q.poll();

            if (curIdx == end) continue;

            for (int i = 0; i < graph[curIdx].size(); i++) {
                Node nxtNode = graph[curIdx].get(i);

                if (totalCosts[nxtNode.dst] <= totalCosts[curIdx] + nxtNode.cost) {
                    if (totalCosts[nxtNode.dst] != totalCosts[curIdx] + nxtNode.cost) {
                        tracking[nxtNode.dst].clear();
                    }
                    totalCosts[nxtNode.dst] = totalCosts[curIdx] + nxtNode.cost;
                    tracking[nxtNode.dst].add(curIdx);
                }

                if (--inDegree[nxtNode.dst] == 0) q.add(nxtNode.dst);
            }
        }

        return totalCosts[end];
    }
}
class Node {
    int src;
    int dst;
    int cost;

    Node(int src, int dst, int cost) {
        this.src = src;
        this.dst = dst;
        this.cost = cost;
    }
}

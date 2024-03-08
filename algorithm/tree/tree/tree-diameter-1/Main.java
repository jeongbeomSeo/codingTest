import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        List<Node>[] graph = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[n1].add(new Node(n2, cost));
            graph[n2].add(new Node(n1, cost));
        }

        Node startNode = getFarthestNode(graph, 1, N);
        Node endNode = getFarthestNode(graph, startNode.idx, N);

        System.out.println(endNode.cost);
    }
    private static Node getFarthestNode(List<Node>[] graph, int startIdx, int N) {

        Node curFarthestNode = new Node(startIdx, 0);

        boolean[] isVisited = new boolean[N + 1];
        isVisited[startIdx] = true;
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(startIdx, 0));

        while (!q.isEmpty()) {
            Node curNode = q.poll();

            if (curNode.cost > curFarthestNode.cost) curFarthestNode = curNode;

            for (int i = 0; i < graph[curNode.idx].size(); i++) {
                Node nxtNode = graph[curNode.idx].get(i);
                if (!isVisited[nxtNode.idx]) {
                    q.add(new Node(nxtNode.idx, curNode.cost + nxtNode.cost));
                    isVisited[nxtNode.idx] = true;
                }
            }
        }

        return curFarthestNode;
    }
}
class Node {
    int idx;
    int cost;

    Node (int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }
}
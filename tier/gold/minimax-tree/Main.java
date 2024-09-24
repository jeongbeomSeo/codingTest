import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        List<Integer>[] graph = new List[N + 1];
        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }

        int L = Integer.parseInt(br.readLine());

        int[] scores = new int[N + 1];
        Arrays.fill(scores, -1);

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            scores[k] = t;
        }

        int Q = Integer.parseInt(br.readLine());
        Queue<Integer> q = new ArrayDeque<>();

        while (Q-- != 0) {
            q.add(Integer.parseInt(br.readLine()));
        }

        dfs(graph, scores, -1, R, true);

        while (!q.isEmpty()) {
            System.out.println(scores[q.poll()]);
        }
    }
    public static void dfs (List<Integer>[] graph, int[] scores, int parent, int cur, boolean turn) {

        for (int i = 0; i < graph[cur].size(); i++) {
            int child = graph[cur].get(i);

            if (child == parent) continue;

            dfs(graph, scores, cur, child, !turn);

            if (scores[cur] == -1) scores[cur] = scores[child];
            else {
                if (turn) {
                    scores[cur] = Math.max(scores[cur], scores[child]);
                } else {
                    scores[cur] = Math.min(scores[cur], scores[child]);
                }
            }
        }
    }
}

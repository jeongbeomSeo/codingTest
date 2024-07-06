import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        List<Integer>[] graph = new List[N + 1];
        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int idx1 = Integer.parseInt(st.nextToken());
            int idx2 = Integer.parseInt(st.nextToken());

            graph[idx1].add(idx2);
            graph[idx2].add(idx1);
        }

        int[][] dp = new int[N + 1][2];
        System.out.println(Math.min(query(graph, dp, 1, -1, 0), query(graph, dp, 1, -1, 1)));
    }
    private static int query(List<Integer>[] graph, int[][] dp, int cur, int prev, int turnOn) {

        if (dp[cur][turnOn] != 0) {
            return dp[cur][turnOn];
        }

        int result = turnOn;
        for (int i = 0; i < graph[cur].size(); i++) {
            if (graph[cur].get(i) == prev) continue;
            int next = graph[cur].get(i);

            int curResult = Integer.MAX_VALUE;
            if (turnOn == 1) {
                curResult = query(graph, dp, next, cur, 0);
            }
            curResult = Math.min(curResult, query(graph, dp, next, cur, 1));
            result += curResult;
        }

        return dp[cur][turnOn] = result;
    }
}

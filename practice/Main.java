import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private final static int INF = 50000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        int[][] graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N][1 << N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(query(graph, dp, 0, 1 << 0));
    }
    private static int query(int[][] graph, int[][] dp, int idx, int visited) {

        if (dp[idx][visited] != -1) {
            return dp[idx][visited];
        }

        if (visited == (1 << N) - 1) {
            if (graph[idx][0] == 0) return dp[idx][visited] = INF;

            return dp[idx][visited] = graph[idx][0];
        }

        int result = INF;

        for (int i = 0; i < N; i++) {
            if (((1 << i) & visited) != 0) continue;
            if (graph[idx][i] == 0) continue;

            result = Math.min(result, query(graph, dp, i, visited | (1 << i)) + graph[idx][i]);
        }

        return dp[idx][visited] = result;
    }
}
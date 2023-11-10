import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        int[][] graph = new int[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            graph[n1][n2] = 1;
            graph[n2][n1] = -1;
        }

        int[] result = queryResult(graph);

        for (int i = 1; i <= N; i++) {
            bw.write(N - 1 - result[i] + "\n");
        }

        bw.flush();
        bw.close();
    }
    private static int[] queryResult(int[][] graph) {

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (graph[i][j] == 0) {
                        if (graph[i][k] > 0 && graph[k][j] > 0) {
                            graph[i][j] = 1;
                        } else if (graph[i][k] < 0 && graph[k][j] < 0) {
                            graph[i][j] = -1;
                        }
                    }
                }
            }
        }

        int[] result = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i != j && graph[i][j] != 0) {
                    result[i]++;
                }
            }
        }

        return result;
    }
}

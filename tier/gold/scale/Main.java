import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[][] distTable = new int[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int idx1 = Integer.parseInt(st.nextToken());
            int idx2 = Integer.parseInt(st.nextToken());

            distTable[idx1][idx2] = 1;
            distTable[idx2][idx1] = -1;
        }

        floydWarshall(distTable, N);

        for (int i = 1; i <= N; i++) {
            int count = N - 1;
            for (int j = 1; j <= N; j++) {
                if (distTable[i][j] == 1 || distTable[i][j] == -1) {
                    count--;
                }
            }
            bw.write(count + "\n");
        }
        bw.flush();
        bw.close();
    }
    private static void floydWarshall(int[][] distTable, int N) {

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (distTable[i][k] == distTable[k][j]) {
                        if (distTable[i][k] == 1) {
                            distTable[i][j] = 1;
                        } else if (distTable[i][k] == -1) {
                            distTable[i][j] = -1;
                        }
                    }
                }
            }
        }
    }
}
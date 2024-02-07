import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[][] table = new int[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int item1 = Integer.parseInt(st.nextToken());
            int item2 = Integer.parseInt(st.nextToken());

            table[item1][item2] = 1;
            table[item2][item1] = -1;
        }

        floydwarhell(table, N);

        for (int i = 1; i <= N; i++) {
            int count = N - 1;

            for (int j = 1; j <= N; j++) {
                if (table[i][j] == 1 || table[i][j] == -1) {
                    count--;
                }
            }

            bw.write(count + "\n");
        }

        bw.flush();
        bw.close();

    }
    private static void floydwarhell(int[][] table, int N) {

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (table[i][k] == 1 && table[k][j] == 1) {
                        table[i][j] = 1;
                    } else if (table[i][k] == -1 && table[k][j] == -1) {
                        table[i][j] = -1;
                    }
                }
            }
        }
    }
}
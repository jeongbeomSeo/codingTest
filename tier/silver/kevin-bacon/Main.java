import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[][] distTable = new long[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
            Arrays.fill(distTable[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            distTable[n1][n2] = distTable[n2][n1] = 1;
        }

        for (int k = 1; k < N + 1; k++) {
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    distTable[i][j] = Math.min(distTable[i][j], distTable[i][k] + distTable[k][j]);
                }
            }
        }

        int idx = -1;
        int cost = Integer.MAX_VALUE;
        for (int i = 1; i < N + 1; i++) {
            int sum = 0;
            for (int j = 1; j < N + 1; j++) {
                if (i == j) continue;
                sum += distTable[i][j];
            }

            if (sum < cost) {
                idx = i;
                cost = sum;
            }
        }

        System.out.println(idx);


    }
}

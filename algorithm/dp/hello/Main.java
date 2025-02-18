import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] table = new int[N + 1][2];

        for (int j = 0; j < 2; j++) {
            st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= N; i++) {
                table[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N + 1][100];

        for (int i = 1; i < N + 1; i++) {

            for (int j = 0; j < 100; j++) {
                dp[i][j] = dp[i - 1][j];

                if (j >= table[i][0])
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - table[i][0]] + table[i][1]);
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < 100; i++) max = Math.max(max, dp[N][i]);

        System.out.println(max);
    }
}

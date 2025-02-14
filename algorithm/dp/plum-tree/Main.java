import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[][][] dp = new int[T + 1][2][W + 2];

        for (int i = 0; i < T; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(dp[i][j], Integer.MIN_VALUE);
            }
        }

        dp[0][0][W] = 0;
        for (int i = 1; i <= T; i++) {
            int pos = Integer.parseInt(br.readLine()) - 1;

            for (int j = 0; j < 2; j++) {
                for (int w = 0; w < W + 1; w++) {
                    dp[i][j][w] = Math.max(dp[i - 1][j][w], dp[i - 1][(j + 1) % 2][w + 1]);

                    if (j == pos && dp[i][j][w] != Integer.MIN_VALUE) {
                        dp[i][j][w] += 1;
                    }
                }
            }
        }

        int res = 0;
        for (int i = 0; i < 2; i++) {
            for (int w = 0; w < W + 1; w++) {
                res = Math.max(res, dp[T][i][w]);
            }
        }

        System.out.println(res);
    }
}

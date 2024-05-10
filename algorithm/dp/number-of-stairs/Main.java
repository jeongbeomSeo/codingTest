import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int MOD = 1_000_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        /**
         * ele1: 현재 계단 위치(높이)
         * ele2: 현재 계단 값
         * ele3: 현재까지 방문한 최대 계단 값(Max)
         * ele4: 현재까지 방문한 최소 계단 값(Min)
         */
        long[][][][] dp = new long[N + 1][10][10][10];

        for (int i = 1; i <= 9; i++) {
            dp[1][i][i][i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int min = j; min >= 0; min--) {
                    for (int max = j; max <= 9; max++) {
                        if (min == max) continue;

                        if (min == j) {
                            dp[i][j][min][max] = (dp[i - 1][j + 1][min][max] + dp[i - 1][j + 1][min + 1][max]) % MOD;
                        } else if (max == j) {
                            dp[i][j][min][max] = (dp[i - 1][j - 1][min][max] + dp[i - 1][j - 1][min][max - 1]) % MOD;
                        } else {
                            dp[i][j][min][max] = (dp[i - 1][j - 1][min][max] + dp[i - 1][j + 1][min][max]) % MOD;
                        }
                    }
                }
            }
        }

        long sum = 0L;
        for (int i = 0; i <= 9; i++) {
            sum += dp[N][i][0][9];
        }

        System.out.println(sum % MOD);
    }
}

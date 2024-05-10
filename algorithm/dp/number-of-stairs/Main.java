import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        /**
         * e1: 현재 계단 위치(level)
         * e2: 현재 계단 자리의 값
         * e3: 방문 여부(비트 필드)
         */
        long[][][] dp = new long[N + 1][10][(1 << 10)];

        for (int i = 1; i <= 9; i++) {
            dp[1][i][1 << i] = 1;
        }

        /**
         * l: level
         * i: value
         * v: visit
         */
        for (int l = 2; l <= N; l++) {
            for (int i = 0; i <= 9; i++) {
                for (int v = 1; v < (1 << 10); v++) {
                    if (i != 0) {
                        dp[l][i][v | 1 << i] += dp[l - 1][i - 1][v];
                    }
                    if (i != 9) {
                        dp[l][i][v | 1 << i] += dp[l - 1][i + 1][v];
                    }
                    dp[l][i][v] %= MOD;
                }
            }
        }
        long sum = 0L;


        for (int i = 0; i <= 9; i++) {
            sum += dp[N][i][(1 << 10) - 1];
            sum %= MOD;
        }


        System.out.println(sum);
    }
}
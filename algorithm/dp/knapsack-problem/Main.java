import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N + 1][C + 1];

        int[] weights = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N + 1; i++) {
            int weight = weights[i];
            for (int j = 1; j < C + 1; j++) {
                dp[i][j] = dp[i - 1][j];

                if (j >= weight) {
                    dp[i][j] += dp[i - 1][j - weight] + 1;
                }
            }
        }

        System.out.println(dp[N][C] + 1);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dp = new int[K + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());

            for (int j = n; j < K + 1; j++) {
                if (dp[j - n] != INF) {
                    dp[j] = Math.min(dp[j], dp[j - n] + 1);
                }
            }
        }

        System.out.println(dp[K] != INF ? dp[K] : -1);
    }
}

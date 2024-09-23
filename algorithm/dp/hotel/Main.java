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

        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] dp = new int[C + 101];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            for (int j = c; j < dp.length; j++) {
                if (dp[j - c] != INF) {
                    dp[j] = Math.min(dp[j], dp[j - c] + n);
                }
            }
        }

        int min = INF;
        for (int i = C; i < dp.length; i++) {
            min = Math.min(min, dp[i]);
        }

        System.out.println(min);
    }
}

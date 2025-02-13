import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] works = new int[N + 1][2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            int time = Integer.parseInt(st.nextToken());
            int point = Integer.parseInt(st.nextToken());

            works[i][0] = time;
            works[i][1] = point;
        }

        int[] dp = new int[N + 2];

        for (int i = 1; i <= N; i++) {
            dp[i] = Math.max(dp[i], dp[i - 1]);

            if (i + works[i][0] <= N + 1) {
                dp[i + works[i][0]] = Math.max(dp[i + works[i][0]], dp[i] + works[i][1]);
            }
        }

        System.out.println(Math.max(dp[N], dp[N + 1]));
    }
}

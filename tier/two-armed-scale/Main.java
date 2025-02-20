import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] weights = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        int size = 500 * 30 + 1;
        boolean[][] dp = new boolean[N + 1][size];
        dp[0][0] = true;

        for (int i = 1; i < N + 1; i++) {
            int weight = weights[i];
            for (int j = 1; j < size; j++) {
                dp[i][j] = dp[i - 1][j];

                if (j >= weight && dp[i - 1][j - weight]) {
                    dp[i][j] = true;
                }
            }
        }

        int M = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            sb.append(dp[N][Integer.parseInt(st.nextToken())] ? "Y" : "N").append(" ");
        }

        System.out.println(sb);
    }
}

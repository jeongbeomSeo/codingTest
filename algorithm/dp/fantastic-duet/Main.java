import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N + 1][N + 1];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        dp[0][1] = dp[1][0] = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) continue;

                int nxtPos = Math.max(i + 1, j + 1);

                dp[nxtPos][j] = Math.min(dp[nxtPos][j], dp[i][j] + Math.abs(arr[nxtPos] - (i != 0 ? arr[i] : arr[nxtPos])));
                dp[i][nxtPos] = Math.min(dp[i][nxtPos], dp[i][j] + Math.abs(arr[nxtPos] - (j != 0 ? arr[j] : arr[nxtPos])));
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            result = Math.min(result, Math.min(dp[i][N], dp[N][i]));
        }

        System.out.println(result);
    }
}
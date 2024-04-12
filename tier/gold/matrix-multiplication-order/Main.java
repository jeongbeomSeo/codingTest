import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N + 1][N];

        System.out.println(query(arr, dp, N));
    }
    // term을 이용한 풀이
    private static int query(int[][] arr, int[][] dp, int N) {
        for (int term = 1; term < N; term++) {
            for (int left = 0; left + term < N; left++) {
                dp[left][left + term] = INF;

                for (int mid = left; mid < left + term; mid++) {
                    dp[left][left + term] = Math.min(dp[left][left + term], dp[left][mid] + dp[mid + 1][left + term] + arr[left][0] * arr[mid][1] * arr[left + term][1]);
                }
            }
        }
        return dp[0][N - 1];
    }
    /* 사이즈를 이용한 풀이
    private static int query(int[][] arr, int[][] dp, int N) {

        for (int size = 2; size <= N; size++) {

            for (int left = 0; left + size <= N; left++) {
                int right = left + size - 1;

                dp[left][right] = INF;
                for (int mid = left; mid < right; mid++) {
                    dp[left][right] = Math.min(dp[left][right], dp[left][mid] + dp[mid + 1][right] + arr[left][0] * arr[mid][1] * arr[right][1]);
                }
            }
        }

        return dp[0][N - 1];
    }*/
}
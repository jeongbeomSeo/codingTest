import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (T-- != 0) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            int[][] arr = new int[N][2];
            for (int j = 0; j < 2; j++) {
                st = new StringTokenizer(br.readLine());
                for (int i = 0; i < N; i++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            if (N == 1) {
                bw.write(getScore(arr[0][0], arr[0][1], W) + "\n");
                continue;
            }

            int[][] copy = new int[2][2];
            copy[0][0] = arr[0][0];
            copy[0][1] = arr[0][1];
            copy[1][0] = arr[N - 1][0];
            copy[1][1] = arr[N - 1][1];
            int result = Integer.MAX_VALUE;
            for (int c = 0; c < 4; c++) {
                if (c == 1) {
                    if (arr[0][0] + arr[N - 1][0] > W) continue;
                    arr[0][0] = W + 1;
                    arr[N - 1][0] = W + 1;
                } else if (c == 2) {
                    if (arr[0][1] + arr[N - 1][1] > W) continue;
                    arr[0][1] = W + 1;
                    arr[N - 1][1] = W + 1;
                } else if (c == 3) {
                    if (arr[0][0] + arr[N - 1][0] > W || arr[0][1] + arr[N - 1][1] > W) continue;
                    arr[0][0] = arr[0][1] = W + 1;
                    arr[N - 1][0] = arr[N - 1][1] = W + 1;
                }

                int[] dp = new int[N];
                dp[0] = getScore(arr[0][0], arr[0][1], W);
                dp[1] = getScore(arr[1][0], arr[1][1], W) + dp[0];
                dp[1] = Math.min(dp[1], getScore(arr[0][0], arr[1][0], W) + getScore(arr[0][1], arr[1][1], W));
                for (int i = 2; i < N; i++) {
                    dp[i] = getScore(arr[i][0], arr[i][1], W) + dp[i - 1];
                    dp[i] = Math.min(dp[i], getScore(arr[i - 1][0], arr[i][0], W) + getScore(arr[i - 1][1], arr[i][1], W) + dp[i - 2]);
                }

                arr[0][0] = copy[0][0];
                arr[0][1] = copy[0][1];
                arr[N - 1][0] = copy[1][0];
                arr[N - 1][1] = copy[1][1];

                if (c == 1) {
                    dp[N - 1] -= 1;
                } else if (c == 2) {
                    dp[N - 1] -= 1;
                } else if (c == 3) {
                    dp[N - 1] -= 1;
                    dp[N - 1] -= 1;
                }

                result = Math.min(result, dp[N - 1]);
            }

            bw.write(result + "\n");
        }
        bw.flush();
        bw.close();
    }
    private static int getScore(int value1, int value2, int boundary) {
        if (value1 + value2 <= boundary) return 1;
        else return 2;
    }
}

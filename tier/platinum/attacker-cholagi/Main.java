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

            int[][] arr = new int[2][N + 1];
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 1; j < N + 1; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            bw.write(query(arr, N, W) + "\n");
        }
        bw.flush();
        bw.close();
    }
    private static int query(int[][] arr, int N, int W) {

        /**
         * 모양 기준으로 구분하기
         * dp[0]: arr의 0행의 n - 1과 n이 겹치는 경우
         * dp[1]: arr의 1행의 n - 1과 n이 겹치는 경우
         * dp[2]: arr의 같은 열이 겹치는 경우
         */

        int result = Integer.MAX_VALUE;

        for (int k = 0; k < 4; k++) {
            int[] buffer = preProcess(arr, k, N, W);

            if (k != 0 && buffer == null) continue;

            int[][] dp = new int[3][N + 1];
            dp[0][1] = 1;
            dp[1][1] = 1;
            dp[2][1] = getValue(arr[0][1], arr[1][1], W);
            for (int j = 2; j <= N; j++) {
                for (int i = 0; i < 3; i++) {
                    if (i != 2) {
                        dp[i][j] = Math.min(dp[2][j - 1] + 1, dp[Math.abs(i - 1)][j - 1] + getValue(arr[i][j - 1], arr[i][j], W));
                    } else {
                        dp[i][j] = Math.min(dp[0][j] + 1, dp[1][j] + 1);
                        dp[i][j] = Math.min(dp[i][j], dp[2][j - 1] + getValue(arr[0][j], arr[1][j], W));
                        dp[i][j] = Math.min(dp[i][j], dp[2][j - 2] + getValue(arr[0][j - 1], arr[0][j], W) + getValue(arr[1][j - 1], arr[1][j], W));
                    }
                }
            }
            int curResult = Math.min(dp[0][N] + 1, Math.min(dp[1][N] + 1, dp[2][N]));
            if (k == 1 || k == 2) curResult -= 1;
            else if (k == 3) curResult -= 2;
            result = Math.min(result, curResult);

            postProcess(arr, buffer, k, N);
        }
        return result;
    }
    private static int[] preProcess(int[][] arr, int k, int N, int W) {
        if (k == 0) return null;
        else if (N <= 2) return null;
        else if (k == 1) {
            if (arr[0][1] + arr[0][N] <= W) {
                int[] buffer = new int[] {arr[0][1], arr[0][N]};
                arr[0][1] = arr[0][N] = W;
                return buffer;
            } else {
                return null;
            }
        } else if (k == 2) {
            if (arr[1][1] + arr[1][N] <= W) {
                int[] buffer = new int[] {arr[1][1], arr[1][N]};
                arr[1][1] = arr[1][N] = W;
                return buffer;
            } else {
                return null;
            }
        } else {
            if (arr[0][1] + arr[0][N] <= W && arr[1][1] + arr[1][N] <= W) {
                int[] buffer = new int[] {arr[0][1], arr[0][N], arr[1][1], arr[1][N]};
                arr[0][1] = arr[0][N] = arr[1][1] = arr[1][N] = W;
                return buffer;
            } else {
                return null;
            }
        }
    }
    private static void postProcess(int[][] arr, int[] buffer, int k, int N) {
        if (k == 0) {
            return;
        }
        else if (k == 1) {
            arr[0][1] = buffer[0];
            arr[0][N] = buffer[1];
        } else if (k == 2) {
            arr[1][1] = buffer[0];
            arr[1][N] = buffer[1];
        } else {
            arr[0][1] = buffer[0];
            arr[0][N] = buffer[1];
            arr[1][1] = buffer[2];
            arr[1][N] = buffer[3];
        }
    }
    private static int getValue(int v1, int v2, int W) {
        if (v1 + v2 <= W) return 1;
        else return 2;
    }
}
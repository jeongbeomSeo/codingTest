import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final int INF = Integer.MAX_VALUE;
    private static int N;
    private static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        int[] arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        /**
         * e1: 현재 위치(pos)
         * e3: 노래 부른 사람이 아닌 다른 사람의 가장 최근 노래 부른 위치(pos2)
         * MAX_MEMORY: N = 2,000일때, 2000 * 2000B = 4,000,000B = 4000KB
         */
        int[][] dp = new int[N + 1][N + 1];

        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(activeDp(dp, arr, 1, 0));
    }
    private static int activeDp(int[][] dp, int[] arr, int pos1, int pos2) {

        if (pos1 == N || pos2 == N) {
            return 0;
        }

        if (dp[pos1][pos2] != -1) return dp[pos1][pos2];

        int nxtPos = Math.max(pos1 + 1, pos2 + 1);
        int lCost = activeDp(dp, arr, nxtPos, pos2) + Math.abs(arr[nxtPos] - arr[pos1]);
        int rCost = activeDp(dp, arr, pos1, nxtPos) + (pos2 != 0 ? Math.abs(arr[nxtPos] - arr[pos2]) : 0);

        return dp[pos1][pos2] = Math.min(lCost, rCost);
    }
}
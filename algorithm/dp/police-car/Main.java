import java.io.*;
import java.util.*;

public class Main {
    private static int W;
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine());

        int[][] events = new int[W + 2][2];

        events[0] = new int[] {1, 1};
        events[1] = new int[] {N, N};
        for (int i = 2; i < events.length; i++) {
            st = new StringTokenizer(br.readLine());

            events[i][0] = Integer.parseInt(st.nextToken());
            events[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[W + 2][W + 2];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(activeDp(events, dp, 0, 1));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        printRoute(bw, events, dp, 0, 1);
        bw.flush();
        bw.close();
    }
    private static void printRoute(BufferedWriter bw, int[][] events, int[][] dp, int pos1, int pos2) throws IOException{

        if (pos1 == W + 1 || pos2 == W + 1) {
            return;
        }

        int nxtPos = Math.max(pos1 + 1, pos2 + 1);
        int lDist = calcDistance(events, nxtPos, pos1) + dp[nxtPos][pos2];
        int rDist = calcDistance(events, nxtPos, pos2) + dp[pos1][nxtPos];

        if (lDist < rDist) {
            bw.write("1\n");
            printRoute(bw, events, dp, nxtPos, pos2);
        } else {
            bw.write("2\n");
            printRoute(bw, events, dp, pos1, nxtPos);
        }
    }
    private static int activeDp(int[][] events, int[][] dp, int pos1, int pos2) {

        if (pos1 == W + 1 || pos2 == W + 1) {
            return 0;
        }

        if (dp[pos1][pos2] != -1) {
            return dp[pos1][pos2];
        }

        dp[pos1][pos2] = INF;

        int nxtPos = Math.max(pos1 + 1, pos2 + 1);
        int lResult = activeDp(events, dp, nxtPos, pos2) + calcDistance(events, nxtPos, pos1);
        int rResult = activeDp(events, dp, pos1, nxtPos) + calcDistance(events, nxtPos, pos2);

        int result = Math.min(lResult, rResult);

        return dp[pos1][pos2] = result;
    }
    private static int calcDistance(int[][] events, int prevPos, int nxtPos) {
        return Math.abs(events[nxtPos][0] - events[prevPos][0]) + Math.abs(events[nxtPos][1] - events[prevPos][1]);
    }
}
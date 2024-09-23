import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int[][] dp;
    private static int[][] arr;
    private static int N;
    private static int W;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine());
        arr = new int[W + 1][2];
        for (int i = 1; i <= W; i++) {
            st = new StringTokenizer(br.readLine());

            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        dp = new int[W + 1][W + 1];

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(query(0, 0) + "\n");
        print(bw, 0, 0);
        bw.flush();
        bw.close();
    }
    private static void print(BufferedWriter bw, int ptr1, int ptr2) throws IOException{

        if (ptr1 == W || ptr2 == W) {
            return;
        }

        int nxtPtr = Math.max(ptr1 + 1, ptr2 + 1);

        int[] curPos = new int[]{1, 1};
        if (ptr1 != 0) curPos = arr[ptr1];
        if (dp[ptr1][ptr2] - dp[nxtPtr][ptr2] == getDist(curPos, arr[nxtPtr])) {
            bw.write("1\n");
            print(bw, nxtPtr, ptr2);
        } else {
            curPos = new int[]{N, N};
            if (ptr2 != 0) curPos = arr[ptr2];
            if (dp[ptr1][ptr2] - dp[ptr1][nxtPtr] == getDist(curPos, arr[nxtPtr])) {
                bw.write("2\n");
                print(bw, ptr1, nxtPtr);
            }
        }
    }

    private static int query(int ptr1, int ptr2) {

        if (ptr1 == W || ptr2 == W) {
            return dp[ptr1][ptr2] = 0;
        }

        if (dp[ptr1][ptr2] != 0) {
            return dp[ptr1][ptr2];
        }

        int nxtPtr = Math.max(ptr1 + 1, ptr2 + 1);

        int result1;
        if (ptr1 == 0) {
            result1 = query(nxtPtr, ptr2) + getDist(new int[] {1, 1}, arr[nxtPtr]);
        } else {
            result1 = query(nxtPtr, ptr2) + getDist(arr[ptr1], arr[nxtPtr]);
        }

        int result2;
        if (ptr2 == 0) {
            result2 = query(ptr1, nxtPtr) + getDist(new int[]{N, N}, arr[nxtPtr]);
        } else {
            result2 = query(ptr1, nxtPtr) + getDist(arr[ptr2], arr[nxtPtr]);
        }

        return dp[ptr1][ptr2] = Math.min(result1, result2);
    }

    private static int getDist(int[] pos1, int[] pos2) {
        return Math.abs(pos1[0] - pos2[0]) + Math.abs(pos1[1] - pos2[1]);
    }
}
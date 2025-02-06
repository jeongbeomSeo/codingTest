import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        long[] dp = createDpTable(10000);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (T-- != 0) {
            int N = Integer.parseInt(br.readLine());

            bw.write(dp[N] + "\n");
        }
        bw.flush();
        bw.close();
    }
    private static long[] createDpTable(int N) {

        long[] dp = new long[N + 1];
        dp[0] = 1;

        for (int num = 1; num <= 3; num++) {
            for (int i = num; i < N + 1; i++) {
                dp[i] += dp[i - num];
            }
        }

        return dp;
    }
}

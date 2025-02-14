import java.io.*;
import java.util.Arrays;

public class Main {
    private final static int MOD = 1_000_000_009;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int size = 1_000_001;
        int[] dp = new int[size];

        dp[0] = 1;
        for (int i = 1; i < size; i++) {
            for (int j = 1; j <= 3; j++) {
                if (i - j < 0) continue;

                dp[i] += dp[i - j];
                dp[i] %= MOD;
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (n-- != 0) {
            bw.write(dp[Integer.parseInt(br.readLine())] + "\n");
        }

        bw.flush();
        bw.close();
    }
}

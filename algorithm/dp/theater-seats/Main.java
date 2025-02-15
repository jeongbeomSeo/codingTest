import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int M = Integer.parseInt(br.readLine());

        Set<Integer> set = new HashSet();
        for (int i = 0; i < M; i++) {
            set.add(Integer.parseInt(br.readLine()));
        }

        int[] dp = new int[41];
        dp[0] = dp[1] = 1;

        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1];

            if (!set.contains(i - 1) && !set.contains(i)) {
                dp[i] += dp[i - 2];
            }
        }

        System.out.println(dp[N]);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int[] arr = new int[N];
    int[] dp = new int[N];

    st = new StringTokenizer(br.readLine());

    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    System.out.println(lis_dp(dp, arr, N));
  }
  static int lis_dp(int[] dp, int[] arr, int N) {
    for (int i = 0; i < N; i++) {
      dp[i] = 1;
      for (int j = 0; j < i; j++) {
        if (arr[i] > arr[j])
          dp[i] = Math.max(dp[i], dp[j] + 1);
      }
    }

    int max = dp[0];
    for (int i = 1; i < N; i++) {
      if (max < dp[i]) max = dp[i];
    }
    return max;
  }
}

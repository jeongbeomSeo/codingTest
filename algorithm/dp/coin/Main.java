import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int T = Integer.parseInt(br.readLine());

    while (T-- > 0) {
       int N = Integer.parseInt(br.readLine());

       st = new StringTokenizer(br.readLine());

       int[] coins = new int[N];
       for (int i = 0; i < N; i++) {
         coins[i] = Integer.parseInt(st.nextToken());
       }

       int M = Integer.parseInt(br.readLine());
       int[] memo = new int[M + 1];
       memo[0] = 1;

       dp(memo, coins, N, M);

      System.out.println(memo[M]);
    }
  }
  static void dp (int[] memo, int[] coins, int N, in`t M) {
    for (int i = 0; i < N; i++) {
      for (int j = coins[i]; j < M + 1; j++) {
        memo[j] += memo[j - coins[i]];
      }
    }
  }
}
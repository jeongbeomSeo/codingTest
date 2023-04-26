import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    int[] coins = new int[N];

    for (int i = 0; i < N; i++) {
      coins[i] = Integer.parseInt(br.readLine());
    }

    System.out.println(greedy(coins, K, N - 1, 0));

  }
  static int greedy(int[]coins, int K, int ptr, int coinNum) {

    if (K == 0) return coinNum;

    while (K >= coins[ptr]) {
      coinNum++;
      K -= coins[ptr];
    }

    return greedy(coins, K, ptr - 1, coinNum);
  }
}

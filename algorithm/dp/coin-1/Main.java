import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int INF = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    int[] coins = new int[N];

    for (int i = 0; i < N; i++) {
      coins[i] = Integer.parseInt(br.readLine());
    }

    int[] table = initDpTable(K);

    activeDP(table, coins, N, K);

    System.out.println(table[K]);
  }
  private static void activeDP(int[] table, int[] coins, int N, int K) {

    for (int i = 0; i < N; i++) {
      int coin = coins[i];

      for (int j = coin; j < K + 1; j++) {
        table[j] += table[j - coin];
      }
    }
  }
  private static int[] initDpTable(int K) {
    int[] table = new int[K + 1];

    table[0] = 1;

    return table;
  }
}
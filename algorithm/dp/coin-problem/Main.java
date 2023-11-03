import java.io.*;
import java.util.Arrays;

public class Main {
  static int INF = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int T = Integer.parseInt(br.readLine());

    int[] table = initDpTable();
    for (int tc = 0; tc < T; tc++) {
      long cost = Long.parseLong(br.readLine());

      int count = 0;

      while (cost != 0) {

        int remain = (int)(cost % 100);

        count += table[remain];

        cost /= 100;
      }

      bw.write(count + "\n");
    }

    bw.flush();
    bw.close();
  }
  private static int[] initDpTable() {

    int[] table = new int[100];

    Arrays.fill(table, INF);

    table[0] = 0;
    int[] coins = {1, 10, 25};
    for (int i = 0; i < 3; i++) {
      int coin = coins[i];

      for (int j = coin; j < 100; j++) {
        if (table[j - coin] != INF) {
          table[j] = Math.min(table[j], table[j - coin] + 1);
        }
      }
    }

    return table;
  }
}
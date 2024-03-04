import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  private static final long INF = Long.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    Switch[] switches = new Switch[N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());

      int port = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());
      switches[i] = new Switch(port, cost);
    }

    int targetCount = Integer.parseInt(br.readLine());

    long[][] dpTable = initDpTable(switches, targetCount, N);

    queryDp(switches, dpTable, targetCount, N);

    if (targetCount == 1) System.out.println(1);
    else if (dpTable[N - 1][targetCount] == INF) System.out.println(-1);
    else System.out.println(dpTable[N - 1][targetCount]);
  }
  private static void queryDp(Switch[] switches, long[][] dpTable, int targetCount, int N) {

    for (int i = 1; i < N; i++) {
      int port = switches[i].port;
      int cost = switches[i].cost;

      for (int j = targetCount - (port - 2); j >= 1; j--) {
        if (dpTable[j] == INF) continue;
      }
    }
  }
  private static long[][] initDpTable(Switch[] switches, int targetCount, int N) {

    long[][] dpTable = new long[N][targetCount + 1];
    for (int i = 0; i < N; i++) {
      Arrays.fill(dpTable[i], INF);
      dpTable[i][0] = 0;
      for (int j = 1; j < switches[i].port && j <= targetCount; j++) {
        dpTable[i][j] = Math.min(dpTable[i][j], switches[i].cost);
      }
    }

    return dpTable;
  }
}
class Switch {
  int port;
  int cost;

  Switch(int port, int cost) {
    this.port = port;
    this.cost = cost;
  }
}
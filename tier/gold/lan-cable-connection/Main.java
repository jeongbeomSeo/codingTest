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

    int computerCount = Integer.parseInt(br.readLine());

    if (computerCount == 1) System.out.println(0);
    else {
      System.out.println(query(switches, computerCount, N));
    }
  }
  private static long query(Switch[] switches, int computerCount, int N) {

    long[] dpTable = initDpTable(computerCount);

    for (int i = 0; i < N; i++) {
      int port = switches[i].port;
      int cost = switches[i].cost;

      for (int j = computerCount - (port - 2); j >= 1; j--) {
        if (dpTable[j] == INF) continue;

        dpTable[j + (port - 2)] = Math.min(dpTable[j] + cost, dpTable[j + (port - 2)]);
      }

      if (port - 1 <= computerCount) {
        dpTable[port - 1] = Math.min(dpTable[port - 1], cost);
      }
    }

    if (dpTable[computerCount] == INF) return -1;

    return dpTable[computerCount];
  }
  private static long[] initDpTable(int computerCount) {

    long[] dpTable = new long[computerCount + 1];

    Arrays.fill(dpTable, INF);
    dpTable[0] = 0;

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
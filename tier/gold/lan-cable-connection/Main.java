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

    // SwitchArray Info
    // element: Number of Available ports, cost
    int[][] switchArary = new int[N + 1][2];
    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      switchArary[i][0] = Integer.parseInt(st.nextToken()) - 1;
      switchArary[i][1] = Integer.parseInt(st.nextToken());
    }

    int computerCount = Integer.parseInt(br.readLine());

    // 직접 연결
    if (computerCount == 1) System.out.println(0);
    else {
      long[][] resultDpTable = queryDP(switchArary, computerCount, N);

      if (resultDpTable[N][computerCount] == INF) System.out.println(-1);
      else System.out.println(resultDpTable[N][computerCount]);
    }
  }
  private static long[][] queryDP(int[][] switchArray, int computerCount, int N) {
    long[][] dpTable = initDpTable(N, computerCount);

    for (int j = 1; j <= switchArray[1][0] && j <= computerCount; j++) {
      dpTable[1][j] = switchArray[1][1];
    }

    for (int i = 2; i <= N; i++) {
      int port = switchArray[i][0];
      int cost = switchArray[i][1];

      int j;
      for (j = 1; j <= port && j <= computerCount; j++) {
        dpTable[i][j] = Math.min(dpTable[i - 1][j], cost);
      }
      for (; j <= computerCount; j++) {
        if (dpTable[i - 1][j - port + 1] != INF) {
          dpTable[i][j] = Math.min(dpTable[i - 1][j - port] + cost, dpTable[i - 1][j]);
        }
        else break;
      }
    }

    return dpTable;
  }
  private static long[][] initDpTable(int N, int computerCount) {
    long[][] dpTable = new long[N + 1][computerCount + 1];

    for (int i = 0; i < N + 1; i++) {
      Arrays.fill(dpTable[i], INF);
      dpTable[i][0] = 0;
    }

    dpTable[0][1] = 0;

    return dpTable;
  }
}
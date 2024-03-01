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

    Computer[] computers = new Computer[N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());

      int port = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      computers[i] = new Computer(port, cost);
    }

    int M = Integer.parseInt(br.readLine());

    if (M == 1) {
      System.out.println(0);
    } else {
      long result = query(computers, N, M);

      if (result == INF) System.out.println("-1");
      else System.out.println(result);
    }
  }
  private static long query(Computer[] computers, int N, int M) {

    long[][] dpTable = initDpTable(N, M);

    for (int i = computers[0].port - 1; i >= 1; i--) {
      dpTable[i][0] = computers[0].cost;
    }

    for (int i = 1; i < N; i++) {

      if (computers[i].port - 1 >= M) {
        dpTable[i][M] = Math.min(dpTable[i - 1][M], computers[i].cost);
      }

      for (int j = M; j >= 1; j--) {
        dpTable[i][j] = Math.min(dpTable[i - 1][j], dpTable[i - 1][j - computers[i].port + 2] + computers[i].cost);
      }
    }

    return dpTable[N][M];
  }
  private static long[][] initDpTable(int N, int M) {

    long[][] dpTable = new long[N][M + 1];

    for (int i = 0; i < N; i++) {
      Arrays.fill(dpTable[i], INF);
      dpTable[i][0] = 0;
    }

    return dpTable;
  }
}
class Computer{
  int port;
  int cost;

  Computer(int port, int cost) {
    this.port = port;
    this.cost = cost;
  }
}
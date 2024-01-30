import java.io.*;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    int[] numArray = new int[N + 1];
    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      numArray[i] = Integer.parseInt(st.nextToken());
    }

    boolean[][] dpTable = initDpTable(numArray, N);

    updateDpTable(numArray, dpTable, N);

    int M = Integer.parseInt(br.readLine());
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());

      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());

      bw.write((dpTable[start][end] ? 1 : 0) + "\n");
    }
    bw.flush();
    bw.close();
  }
  private static void updateDpTable(int[] numArray, boolean[][] dpTable, int N) {

    for (int i = 3; i <= N; i++) {
      for (int j = 1; j < i - 1; j++) {
        if (numArray[i] == numArray[j] && dpTable[j + 1][i - 1]) {
          dpTable[j][i] = true;
        }
      }
    }
  }
  private static boolean[][] initDpTable(int[] numArray, int N) {
    boolean[][] dpTable = new boolean[N + 1][N + 1];

    for (int i = 1; i <= N; i++) {
      dpTable[i][i] = true;

      if (numArray[i - 1] == numArray[i])
        dpTable[i - 1][i] = true;
    }

    return dpTable;
  }
}
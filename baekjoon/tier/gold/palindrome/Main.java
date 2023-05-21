import java.io.*;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    int[] nums = new int[N + 1];
    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      nums[i] = Integer.parseInt(st.nextToken());
    }

    boolean[][] dp = new boolean[N + 1][N + 1];
    for (int i = 1; i <= N; i++) {
      dp[i][i] = true;

      if (i != N && nums[i] == nums[i + 1]) dp[i][i + 1] = true;
    }

    for (int i = 2; i <= N - 1; i++) {
      for (int j = 1; i + j <= N; j++) {
        if (nums[j] == nums[j + i] && dp[j + 1][j + i - 1]) dp[j][j + i] = true;
      }
    }

    int M = Integer.parseInt(br.readLine());
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int s = Integer.parseInt(st.nextToken());
      int e = Integer.parseInt(st.nextToken());

      bw.write((dp[s][e] ? 1 : 0) + "\n");
    }
    bw.flush();
    bw.close();
  }
}

import java.io.*;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int[] nums = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());

    boolean[][] is_pd = query_pd(nums, N);

    int M = Integer.parseInt(br.readLine());
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int s = Integer.parseInt(st.nextToken()) - 1;
      int e = Integer.parseInt(st.nextToken()) - 1;

      bw.write((is_pd[s][e] ? 1 : 0) +"\n");
    }
    bw.flush();
    bw.close();
  }
  static boolean[][] query_pd(int[] nums, int N) {

    boolean[][] is_pd = new boolean[N][N];

    for (int i = 0; i < N; i++) {
      is_pd[i][i] = true;

      if (i != N - 1 && nums[i] == nums[i + 1]) is_pd[i][i + 1] = true;
    }

    for (int i = 2; i <= N - 1; i++) {
      for (int j = 0; j + i < N; j++) {
        if (nums[j] == nums[j + i] && is_pd[j + 1][j + i - 1]) is_pd[j][j + i] = true;
      }
    }

    return is_pd;
  }
}
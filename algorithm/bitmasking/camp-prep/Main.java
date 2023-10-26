import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int L = Integer.parseInt(st.nextToken());
    int R = Integer.parseInt(st.nextToken());
    int X = Integer.parseInt(st.nextToken());

    int[] level = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      level[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(level);

    int count = 0;
    for (int i = 1; i < 1 << N; i++) {
      int sum = 0;
      int maxIdx = 0;
      int minIdx = -1;
      for (int j = 0; j < N; j++) {
        if (((1 << j) & i) == (1 << j)) {
          sum += level[j];
          if (minIdx == -1) minIdx = j;
          maxIdx = j;
        }
      }
      if (sum < L || sum  > R) continue;
      if (minIdx == maxIdx || level[maxIdx] - level[minIdx] < X) continue;
      count++;
    }

    System.out.println(count);

  }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int H, W;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    H = Integer.parseInt(st.nextToken());
    W = Integer.parseInt(st.nextToken());

    int[] heights = new int[W];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < W; i++) {
      heights[i] = Integer.parseInt(st.nextToken());
    }

    System.out.println(simulation(heights));
  }
  static int simulation(int[] heights) {
    Queue<Integer> q = new ArrayDeque<>();

    for (int i = 0; i < W - 2; i++) {
      if (heights[i] > heights[i + 1]) q.add(i);
    }

    boolean[] check = new boolean[W];
    int sum = 0;
    while (!q.isEmpty()) {
      int col = q.poll();

      if (check[col]) continue;

      int maxIdx = col + 1;
      for (int i = col + 2; i < W; i++) {
        if (heights[maxIdx] < heights[i]) {
          maxIdx = i;
          if (heights[col] <= heights[maxIdx]) break;
        }
      }

      for (int i = col; i < maxIdx; i++) check[i] = true;
      int min = Math.min(heights[col], heights[maxIdx]);
      for (int i = col + 1; i < maxIdx; i++) {
        sum += (min - heights[i]);
      }
    }
    return sum;
  }
}

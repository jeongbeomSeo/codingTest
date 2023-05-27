import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    int[][] food = new int[N][2];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      food[i][0] = Integer.parseInt(st.nextToken());
      food[i][1] = Integer.parseInt(st.nextToken());
    }

    int min = Integer.MAX_VALUE;

    for (int set = 1; set < (1 << N); set++) {
      int sourCost = 1;
      int bitterCost = 0;
      for (int bit = N - 1; bit >= 0; bit--) {
        if ((set & (1 << bit)) == (1 << bit)) {
          sourCost *= food[bit][0];
          bitterCost += food[bit][1];
        }
      }
      min = Math.min(Math.abs(sourCost - bitterCost), min);
    }
    System.out.println(min);
  }
}
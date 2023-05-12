import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    int[] weight = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      weight[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(weight);

    System.out.println(greedy(weight, N));
  }

  static long greedy(int[] weight, int N) {

    if (weight[0] != 1) return 1;
    long sum = 1;

    for (int i = 1; i < N; i++) {
      if (sum - weight[i] >= -1) sum += weight[i];
      else break;
    }

    return sum + 1;
  }
}
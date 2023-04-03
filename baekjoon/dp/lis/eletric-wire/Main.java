import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Wire implements Comparable<Wire>{
  int a;
  int b;

  Wire(int a, int b) {
    this.a = a;
    this.b = b;
  }

  @Override
  public int compareTo(Wire o) {
    if (this.a == o.a) return this.b - o.b;
    return this.a - o.a;
  }
}

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    Wire[] wires = new Wire[N];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());

      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      wires[i] = new Wire(a, b);
    }

    Arrays.sort(wires);

    int[] dp = new int[N];

    System.out.println(N - lis_dp(dp, wires, N));

  }
  static int lis_dp(int[] dp, Wire[] wires, int N) {

    for (int i = 0; i < N; i++) {
      dp[i] = 1;
      for (int k = 0; k < i; k++) {
        if (wires[i].b > wires[k].b) {
          dp[i] = Math.max(dp[i], dp[k] + 1);
        }
      }
    }

    int max = dp[0];
    for (int i = 1; i < N; i++) {
      if (max < dp[i]) max = dp[i];
    }

    return max;
  }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Confetti implements Comparable<Confetti> {
  int a;
  int b;

  Confetti(int a, int b) {
    if(a < b) {
      int temp = a;
      a = b;
      b = temp;
    }
    this.a = a;
    this.b = b;
  }

  // 오름차순 정렬
  @Override
  public int compareTo(Confetti o) {
    if (this.a == o.a) return this.b - o.b;
    return this.a - o.a;
  }
}
public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    int[] dp = new int[N];
    Confetti[] confetti = new Confetti[N];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());

      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      confetti[i] = new Confetti(a, b);
    }

    Arrays.sort(confetti);

    confetti_dp(dp, confetti, N);

    int max = 1;
    for (int i = 0; i < N; i++) {
      if (dp[i] > max) max = dp[i];
    }

    System.out.println(max);
  }
  static void confetti_dp(int[] dp, Confetti[] confetti, int N) {
    for (int i = 0; i < N; i++) {
      dp[i] = 1;
      for (int j = 0; j < i; j++) {
        if (confetti[i].b >= confetti[j].b) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
    }
  }
}
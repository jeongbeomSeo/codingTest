import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static Long M;
  static int N;
  static int ans;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Long.parseLong(st.nextToken());

    int[] arr = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0 ; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    ans = 0;
    bruteForce(arr);

    System.out.println(ans);
  }
  static void bruteForce(int[] arr) {

    int lPtr = 0;
    int rPtr = 0;
    Long sum = (long)0;
    while (lPtr < N) {
      while (rPtr < N && sum > M) sum -= arr[--rPtr];
      while (rPtr < N && sum < M) sum += arr[rPtr++];

      if (sum.equals(M)) {
        ans++;
      }
      sum -= arr[lPtr];
      lPtr++;
    }

  }
}

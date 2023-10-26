import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int T = Integer.parseInt(br.readLine());
    int[] ans = new int[T];

    int idx = 0;
    while (T-- > 0) {
      int N = Integer.parseInt(br.readLine());
      calcNum(N, 0, ans,  idx++);
    }

    for (int print : ans) {
      System.out.println(print);
    }

  }
  static void calcNum(int N, int curNum, int[] ans, int tc) {
    if (N == curNum) {
      ans[tc]++;
      return;
    }
    else if (N < curNum) return;

    for (int i = 1; i <= 3; i++) {
      calcNum(N, curNum + i, ans, tc);
    }
  }
}

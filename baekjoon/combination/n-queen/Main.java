import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  static boolean[] flag_X;
  // 오른쪽 대각선
  static boolean[] flag_TR;
  // 왼쪽 대각선
  static boolean[] flag_BR;
  static int ans;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    ans = 0;
    flag_TR = new boolean[2 * N];
    flag_BR = new boolean[2 * N];
    flag_X = new boolean[N];

    nQueen(N, 0);

    System.out.println(ans);
  }
  static void nQueen(int N, int ptr) {

    if (ptr == N) {
      ans++;
      return;
    }

    for (int i = 0; i < N; i++) {
      if (!flag_X[i] && !flag_TR[ptr + i] && !flag_BR[(N - 1) - i + ptr]) {
        flag_X[i] = true;
        flag_TR[ptr + i] = true;
        flag_BR[(N - 1) - i + ptr] = true;
        nQueen(N, ptr + 1);
        flag_X[i] = false;
        flag_TR[ptr + i] = false;
        flag_BR[(N - 1) - i + ptr] = false;
      }
    }
  }
}

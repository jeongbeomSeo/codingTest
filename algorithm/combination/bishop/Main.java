import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int maxBishop = Integer.MIN_VALUE;

  static boolean[] flag_BR;
  static int[][] chess;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    chess = new int[N][N];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        chess[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    flag_BR = new boolean[2 * N - 1];

    bishop(N, 0, 0, 0);

    System.out.println(maxBishop);

  }
  // 기울기 + 선분(line)을 기준으로 백트래킹
  // 선분 기준으로 맨 오른쪽 위의 값부터 대각선을 내려가면서 놓을 수 있는 부분 탐색
  static void bishop(int N, int line, int ptr, int count) {

    if (line == 2 * N - 1) {
      maxBishop = Math.max(maxBishop, count);
      return;
    }

    // line 의 값이 N을 넘어가면 i값이 늘어나기 시작하고, 열값은 N - 1부터 시작 고정
    int i = (line >= N) ? (line % (N - 1) != 0 ? line % (N - 1) : N - 1) : 0;
    boolean isValid = false;
    int nextPtr = (line >= N - 1) ? N - 1: line + 1;

    // 놓을 수 있는 곳이 없다면 그냥 넘어가기
    for (; i >= 0 && ptr >= 0 && i < N && ptr < N; ptr--, i++) {
      isValid = false;
      // 놓을 수 있는 자리와 기울기 -인 대각선에 놓여있는 비숍 체크
      if (chess[i][ptr] == 1 && !flag_BR[N - 1 - i + ptr]) {
        flag_BR[N - 1 - i + ptr] = true;
        bishop(N, line + 1, nextPtr, count + 1);
        flag_BR[N - 1 - i + ptr] = false;
        isValid = true;
      }
    }
    if (!isValid) bishop(N, line + 1, nextPtr, count);
  }
}

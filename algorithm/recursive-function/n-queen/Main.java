import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  static int[] chess;
  static int N;
  // 같은 행 기준
  static boolean[] flag_a;
  // 기울기가 +인 대각선 기준
  static boolean[] flag_b;
  // 기울기가 -인 대각선 기준
  static boolean[] flag_c;
  // N개의 퀸 배치 성공
  static int counter = 0;
  /*
  static void print() {
    for(int i = 0; i < N; i++) {
      System.out.print(chess[i] + " ");
    }
    System.out.println();
  }
  */

  // i가 열, j가 행
  static void nQueen(int i) {
    for(int j = 0; j < N; j++) {
      // i를 움직이면서 열(Column)마다 하나씩 배치하고 있으므로 열은 신경 X
      // 행(Row)과 두 대각선(Diagonal) 방향만 고려
      if (!flag_a[j] && !flag_b[i + j] && !flag_c[N - 1 + i - j]) {

        // chess[i] = j가 의미하는 것은 i열의 j행에 Queen을 배치하겠다는 의미이다.
        chess[i] = j;

        // 다음 열에 Queen배치
        if(i < N - 1) {
          flag_a[j] = true;
          flag_b[i + j] = true;
          flag_c[N - 1 + i - j] = true;
          nQueen(i + 1);
          flag_a[j] = false;
          flag_b[i + j] = false;
          flag_c[N - 1 + i - j] = false;
        }
        // 모든 퀸이 다 놓였을 때 즉, N - 1번째 열에 퀸이 놓였을 때
        else
          counter++;
      }
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // N X N Chess
    N = Integer.parseInt(br.readLine());

    chess = new int[N];
    flag_a = new boolean[N];
    flag_b = new boolean[2 * N -1];
    flag_c = new boolean[2 * N -1];

    nQueen(0);
    System.out.println(counter);
  }
}

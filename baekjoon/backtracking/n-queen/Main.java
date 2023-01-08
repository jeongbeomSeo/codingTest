import java.util.Scanner;

public class Main {
  static int N;
  static int[] chess;
  static boolean[] flag_a;
  static boolean[] flag_b;
  static boolean[] flag_c;

  static int success = 0;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    N = sc.nextInt();
    chess = new int[N];
    flag_a = new boolean[N];
    flag_b = new boolean[2 * N - 1];
    flag_c = new boolean[2 * N - 1];

    backtracking(0);

    System.out.println(success);

  }
  static void backtracking(int ptr) {
    for(int i = 0; i < N; i++) {
      if(!flag_a[i] && !flag_b[i + ptr] && !flag_c[ N - 1 + ptr - i]) {
        chess[ptr] = i;
        if(ptr == N - 1) {
          success++;
        }
        else {
          flag_a[i] = flag_b[i + ptr] = flag_c[ N - 1 + ptr - i] = true;
          backtracking(ptr + 1);
          flag_a[i] = flag_b[i + ptr] = flag_c[ N - 1 + ptr - i] = false;
        }

      }

    }

  }
}

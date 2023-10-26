import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();

    boolean[] print = new boolean[N + 1];
    int[] arr = new int[N + 1];

    permutation(N, arr, print, 0);
  }
  static void permutation(int N, int[] arr, boolean[] print, int r) {
    if (r == N) {
      for (int i = 0; i < N; i++) {
        System.out.print(arr[i]+ " ");
      }
      System.out.println();
      return;
    }
    for (int i = 1; i < N + 1; i++) {
      if (!print[i]) {
        print[i] = true;
        arr[r] = i;
        permutation(N , arr, print, r + 1);
        print[i] = false;
      }
    }
  }
}

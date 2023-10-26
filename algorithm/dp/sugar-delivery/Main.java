import java.util.IllegalFormatCodePointException;
import java.util.Scanner;

public class Main {
  static int ans = -1;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();

    int num = N / 5;

    if (N >= 5 && N % 5 == 0) ans = num;
    else {
      ans = DP(N, num);
    }

    System.out.println(ans);

  }

  static int DP(int N, int num) {
    if (num >= 0) {
      int remainder = N - 5 * num;

      if (remainder >= 3 && remainder % 3 == 0) {
        return num + remainder / 3;
      }
      return DP(N, num - 1);
    }
    return ans;
  }
}

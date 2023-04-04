/*
 * 설탕 배달
 * 현재, 3킬로그램 봉지와 5킬로그램 봉지를 가지고 이싿.
 * 설탕이 N 킬로그램일 때, 봉지 몇 개를 가져가면 되는지 그 최솟값을 출력하세요.
 *
 * 입력
 * 첫쨰 줄에 N이 주어진다. (3 <= N<= 5000)
 *
 * 출력
 * 상근이가 배달하는 봉지의 최소 개수를 출력한다. 만약, 정확하게 N킬로그램을 만들 수 없다면 -1을 출력한다.
 *
 * 예제 입력 1
 * 18
 *
 * 예제 출력 1
 * 4
 *
 * 예제 입력 2
 * 4
 *
 * 예제 출력 2
 * -1
 *
 * 예제 입력 3
 * 3
 *
 * 예제 출력 3
 * 3
 *
 * 예제 입력 4
 * 9
 *
 * 예제 출력 4
 * 3
 *
 * 예제 입력 5
 * 11
 *
 * 예제 출력 5
 * 3
 */
import java.util.Scanner;

public class SugerDelivery {
  static int ans = -1;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();

    // dp 사용 풀이

    int[] dp = new int[N + 1];

    init_dp(dp, N);


    // 몫과 나머지 사용 정리

    int num = N / 5;

    if (N >= 5 && N % 5 == 0) ans = num;
    else {
      ans = remainder(N, num);
    }

    System.out.println(ans);

  }

  static void init_dp(int[] dp, int N) {
    if (N >= 3)
      dp[3] = 1;
    if (N >= 5)
      dp[5] = 1;

    // Bottom-Up 방식으로 3의 배수, 5의 배수를 확인해 가면서 DP 사용
    for (int i = 6; i <= N; i++) {
      if (i %  5 == 0) {
        dp[i] = dp[i - 5] + 1;
      } else if (i % 3 == 0) {
        dp[i] = dp[i - 3] + 1;
      } else {
        if (dp[i - 3] != 0 && dp[i - 5] != 0) {
          dp[i] = Math.min(dp[i - 3], dp[i - 5]) + 1;
        }
      }
    }

    if (dp[N] == 0) {
      System.out.println("-1");
      return;
    }
    System.out.println(dp[N]);
  }

  // N을 5로 나눴을 때 몫을 구하여 거기서 1씩 빼가면서 재귀 함수를 사용해 3으로 나누어 떨어지는지 확인
  static int remainder(int N, int num) {
    if (num >= 0) {
      int remainder = N - 5 * num;

      if (remainder >= 3 && remainder % 3 == 0) {
        return num + remainder / 3;
      }
      return remainder(N, num - 1);
    }
    return ans;
  }
}

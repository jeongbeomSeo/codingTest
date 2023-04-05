/*
 * 계단 오르는 데는 다음과 같은 규칙이 있다.
 * 1. 계단은 한 번에 한 계단씩 또는 두 계단씩 오를 수 있다. 즉, 한 계단을 밟으면서 이어서 다음 계단이나, 다음 다음 계단으로 오를 수 있다.
 * 2. 연속된 세 게의 계단을 모두 밟아서는 안된다. 단, 시작점은 계단에 포함되지 않는다.
 * 3. 마지막 도착 계단은 반드시 밟아야 한다.
 *
 * 각 계단에 쓰여있는 점수가 주어질 때 이 게임(마지막 계단)에서 얻을 수 있는 총 점수의 최댓값을 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 입력의 첫째 줄에 계단의 개수가 주어지낟.
 *
 * 둘쨰 줄부터 한 줄에 하나씩 제일 아래에 놓인 계단부터 순서대로 각 계단에 쓰여 있는 점수가 주어진다. 계단의 개수는 300이하의 자연수이고, 계단에 쓰여 있는 점수는 10,000이하의 자연수이다.
 *
 * 출력
 * 첫째 줄에 계단 오르기 게임에서 얻을 수 이쓴ㄴ 총 점수의 최댓값을 출력한다.
 *
 * 예제 입력 1
 * 6
 * 10
 * 20
 * 15
 * 25
 * 10
 * 20
 *
 * 예제 출력 1
 * 75
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClimbingStairs {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    int[] stairs = new int[N];

    for (int i = 0; i < N; i++) {
      stairs[i] = Integer.parseInt(br.readLine());
    }

    if (N == 1) System.out.println(stairs[0]);
    else if (N == 2) System.out.println(stairs[0] +  stairs[1]);

    else {
      int[] dp = new int[N];
      calc_dp(dp, stairs, N);

      System.out.println(dp[N - 1]);
    }
  }
  static void calc_dp(int[] dp, int[] stairs, int N) {
    dp[0] = stairs[0];
    dp[1] = stairs[0] + stairs[1];
    dp[2] = Math.max(stairs[0] + stairs[2], stairs[1] + stairs[2]);

    for (int i = 3; i < N; i++) {
      dp[i] = stairs[i] + Math.max(dp[i - 2], dp[i - 3] + stairs[i - 1]);
    }
  }
}
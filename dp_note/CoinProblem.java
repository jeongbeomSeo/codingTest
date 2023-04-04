
/*
* 화폐 단위: 1, 10, 25, 100, 1000, 2500, 100000, 250000, 1000000 ...
* 즉, 식으로 표현하면 K >= 0을 만족하는 모든 K에 대해서, 가치가 10<sup>k</sup>인 동전과 25 * 100<sup>k</sup>인 동전이 있는 것이다.
* 물품 가격이 주어졌을 때, 이를 구매하기 위해 필요한 동전 개수의 최솟값을 구해보자.
* 각 동전의 개수는 무한하고, 구매할 떄는 정확하게 가격만큼 지불해야 합니다.
*
* 입력:
* 첫째 줄에 테스트 케이스의 개수 T가 주어진다. 줄째 줄부터 T개의 줄에 초콜릿의 가격이 주어진다. 가격의 10<sup>15</sup>보다 작거나 같은 자연수이다.
*
* 출력:
* 총 T개의 줄에 각각의 테스트 케이스의 필요한 동전의 개수를 출력한다.
*
* 예제 입력 1
* 2
* 47
* 9
*
* 예제 출력 1
* 5
* 9
*
* 예체 입력 2
* 2
* 250111
* 76540123
*
* 예제 출력 2
* 4
* 16
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CoinProblem {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int T = Integer.parseInt(br.readLine());

    int[] dp = new int[101];
    dp[0] = 0;
    coins_dp(dp);

    // 테스트 케이스만큼 반복문 실행
    for (int tc = 0; tc < T; tc++) {
      long N = Long.parseLong(br.readLine());

      int count = 0;

      // 100원 이하 동전 처리, 10000원 이하 동전 처리, 1000000원 이하 동전 처리 ...
      while (N != 0) {
        count += dp[(int)(N % 100)];
        N /= 100;
      }
      System.out.println(count);
    }
  }
  // 0 ~ 100원까지 필요한 동전 갯수 메모이제이션
  static void coins_dp(int[] dp) {
    for (int i = 1; i <= 100; i++) {
      dp[i] = dp[i - 1] + 1;
      if (i >= 10) dp[i] = Math.min(dp[i], dp[i - 10] + 1);
      if (i >= 25) dp[i] = Math.min(dp[i], dp[i - 25] + 1);
    }
  }
}

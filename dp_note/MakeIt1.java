/*
 * 1로 만들기
 *
 * 정수 X에 사용할 수 있는 연산은 다음과 같이 세가지 이다.
 *
 * 1. X가 3으로 나누어 떨어지면, 3으로 나눈다.
 * 2. X가 2로 나누어 떨어지면, 2로 나눈다.
 * 3. 1을 뺀다.
 *
 * 정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다. 연산을 사용하는 횟수의 최솟값을 출력하시오.
 *
 * 입력:
 * 첫째 줄에 1보다 크거나 같고, 10<sup>6</sup>보다 작거나 같은 정수 N이 주어진다.
 *
 * 출력:
 * 첫째 줄에 연산을 하는 횟수의 최솟값을 출력한다.
 *
 * 예제 입력 1
 * 2
 *
 * 예제 출력 1
 * 1
 *
 * 예제 입력 2
 * 10
 *
 * 예제 출력 2
 * 3
 *
 * 힌트
 * 10의 경우에 10 -> 9 -> 3 -> 1로 3번만에 만들 수 있다.
 */

import java.util.*;

public class MakeIt1 {
  static Integer[] dp;

  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);

    int N = in.nextInt();

    dp = new Integer[N + 1];
    dp[0] = dp[1] = 0;

    System.out.print(recur(N));

  }

  // Top-Down 방식
  static int recur(int N) {

    if (dp[N] == null) {
      // 6으로 나눠지는 경우
      if (N % 6 == 0) {
        dp[N] = Math.min(recur(N - 1), Math.min(recur(N / 3), recur(N / 2))) + 1;
      }
      // 3으로만 나눠지는 경우
      else if (N % 3 == 0) {
        dp[N] = Math.min(recur(N / 3), recur(N - 1)) + 1;
      }
      // 2로만 나눠지는 경우
      else if (N % 2 == 0) {
        dp[N] = Math.min(recur(N / 2), recur(N - 1)) + 1;
      }
      // 2와 3으로 나누어지지 않는 경우
      else {
        dp[N] = recur(N - 1) + 1;
      }
    }
    return dp[N];
  }

  // Bottom-Up 방식
  static void memoization_dp(int N) {
    for (int i = 2; i < N; i++) {
      dp[i] = dp[i - 1] + 1;
      if (i % 3 == 0)
        dp[i] = Math.min(dp[i], dp[i / 3] + 1);
      if (i % 2 == 0)
        dp[i] = Math.min(dp[i], dp[i / 2] + 1);
    }
  }

  // 만약 입력 정수 N의 범위가 1 부터 10<sup>18</sup>일 경우
  static void upgrade_dp(long N) {
    Map<Long, Long> map = new HashMap<>();
    Queue<Long> q = new ArrayDeque<>();

    q.offer(N);
    map.put(N, 0L);

    while (!q.isEmpty()) {
      long n = q.poll();
      long curCnt = map.get(n);

      if (n == 1) {
        System.out.println(curCnt);
        break;
      }

      if (n % 3 == 0 && n / 3 >= 1) {
        long nextNum = n / 3;
        if (!map.containsKey(nextNum)) {
          map.put(nextNum, curCnt + 1);
          q.offer(nextNum);
        }
      }
      if (n % 2 == 0 && n / 2 >= 1) {
        long nextNum = n / 2;
        if (!map.containsKey(nextNum)) {
          map.put(nextNum, curCnt + 1);
          q.offer(nextNum);
        }
      }
      if (n - 1 >= 1) {
        long nextNum = n - 1;
        if (!map.containsKey(n - 1)) {
          map.put(nextNum, curCnt + 1);
          q.offer(nextNum);
        }
      }
    }
  }
}

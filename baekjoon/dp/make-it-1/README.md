# 1로 만들기

**실버 3**

| 시간 제한           | 메모리 제한 | 제출 정답 | 맞힌 사람 | 정답 비율 |
| ------------------- | ----------- | --------- | --------- | --------- | ------- |
| 0.15 초 (하단 참고) | 128 MB      | 249646    | 83189     | 53147     | 32.472% |

## 뮨재

정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 이다.

1. X가 3으로 나누어 떨어지면, 3으로 나눈다.
2. X가 2로 나누어 떨어지면, 2로 나눈다.
3. 1을 뺀다.

정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다. 연산을 사용하는 횟수의 최솟값을 출력하시오.

## 입력

첫째 줄에 1보다 크거나 같고, 10<sup>6</sup>보다 작거나 같은 정수 N이 주어진다.

## 출력

첫째 줄에 연산을 하는 횟수의 최솟값을 출력한다.

## 예제 입력 1

```
2
```

## 예제 출력 1

```
1
```

## 예제 입력 2

```
10
```

## 예제 출력 2

```
3
```

## 힌트

10의 경우에 10 → 9 → 3 → 1 로 3번 만에 만들 수 있다.

## 나의 코드

```java
import java.util.Scanner;
public class Main {

  static Integer[] dp;

  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);

    int N = in.nextInt();

    dp = new Integer[N + 1];
    dp[0] = dp[1] = 0;

    System.out.print(recur(N));

  }

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

}
```

## 느낀점

정말 간만에 느낀점을 적어본다. DP를 사용하는 법은 얼추 익히고 익히고 있다고 생각했지만, 진짜 1도 모르는 것 같았다.

공부하고 정리해서 익히고 문제로 적응하자.

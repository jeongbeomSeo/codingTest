# 동전 1

**골드 5**

|시간 제한|	메모리 제한|	제출|	정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|0.5 초 (추가 시간 없음)	|4 MB|	52334	|24003|	18140	|45.875%|

## 문제 

n가지 종류의 동전이 있다. 각각의 동전이 나타내는 가치는 다르다. 이 동전을 적당히 사용해서, 그 가치의 합이 k원이 되도록 하고 싶다. 그 경우의 수를 구하시오. 각각의 동전은 몇 개라도 사용할 수 있다.

사용한 동전의 구성이 같은데, 순서만 다른 것은 같은 경우이다.

## 입력 

첫째 줄에 n, k가 주어진다. (1 ≤ n ≤ 100, 1 ≤ k ≤ 10,000) 다음 n개의 줄에는 각각의 동전의 가치가 주어진다. 동전의 가치는 100,000보다 작거나 같은 자연수이다.

## 출력 

첫째 줄에 경우의 수를 출력한다. 경우의 수는 2<sup>31</sup>보다 작다.

## 예제 입력 1

```
3 10
1
2
5
```

## 예제 출력 1

```
10
```

## 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    int[] coins = new int[N];
    for (int i = 0; i < N; i++) {
      coins[i] = Integer.parseInt(br.readLine());
    }

    int[] dp = new int[K + 1];
    dp[0] = 1;

    coin_dp(dp, coins, N, K);

    System.out.println(dp[K]);

  }
  static void coin_dp(int[] dp, int[] coins, int N, int K) {
    for (int i = 0; i < N; i++) {
      for (int j = coins[i]; j < K + 1; j++) {
        dp[j] += dp[j - coins[i]];
      }
    }
  }
}
```
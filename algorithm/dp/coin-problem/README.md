# 동전 문제 

**골드 2**

|시간 제한	|메모리 제한|	제출|	정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|2 초	|128 MB	|3318	|820	597	|26.735%|

## 문제 

구사과국은 동전만 사용하고, 동전의 가치는 다음과 같다.

1, 10, 25, 100, 1000, 2500, 10000, 100000, 250000, 1000000 ...

즉, 식으로 표현하면 K ≥ 0를 만족하는 모든 K에 대해서, 가치가 10<sup>K</sup>인 동전과 25×100<sup>K</sup>인 동전이 있는 것이다.

구사과국에 살고 있는 구사과는 초콜릿을 하나 구매해 5차원 세계로 이사가려고 한다. 초콜릿의 가격이 주어졌을때, 이를 구매하기 위해 필요한 동전 개수의 최솟값을 구해보자. 각 동전의 개수는 무한하고, 구매할 때는 정확하게 초콜릿의 가격만큼만 지불해야 한다.

## 입력 

첫째 줄에 테스트 케이스의 개수 T가 주어진다. 둘째 줄부터 T개의 줄에 초콜릿의 가격이 주어진다. 가격의 10<sup>15</sup>보다 작거나 같은 자연수이다

## 출력 

총 T개의 줄에 각각의 테스트 케이스의 필요한 동전의 개수를 출력한다.

## 예제 입력 1

```
2
47
9
```

## 예제 출력 1

```
5
9
```

## 예제 입력 2

```
2
250111
76540123
```

## 예제 출력 2

```
4
16
```

## 풀이 

결국, 해당 풀이도 다른 분의 풀이를 보고 참고해버리고 말았다.

그래서 정리라도 해보자.

일단 현재 1, 10, 25, 100 그 이후 부턴 100<sup>k</sup>씩 곱해지는 형태로 이루어진다.

- 1에서 100을 곱하면 100
- 10에서 100을 곱하면 1000
- 25에서 100을 곱하면 2500
- 100에서 100을 곱하면 10000

그 다음 숫자도 똑같다.

즉, 100단위 구간 별로 동전 세는 것이 같은 것이다. 

- 1 ~ 100
- 100 ~ 10000
- 10000 ~ 1000000
- ...

그래서 화폐가 주어졌을 경우 단위 별로 다음과 같은 방식으로 처리가 되는 것이다.

1. 1 원부터 100원 처리
2. 100원부터 10000원 처리
3. 10000원부터 1000000원 처리
4. ...


## 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int T = Integer.parseInt(br.readLine());

    int[] dp = new int[101];
    dp[0] = 0;
    coins_dp(dp);

    for (int tc = 0; tc < T; tc++) {
      long N = Long.parseLong(br.readLine());

      int count = 0;
      while (N != 0) {
        count += dp[(int)(N % 100)];
        N /= 100;
      }
      System.out.println(count);
    }
  }
  static void coins_dp(int[] dp) {
    for (int i = 1; i <= 100; i++) {
      dp[i] = dp[i - 1] + 1;
      if (i >= 10) dp[i] = Math.min(dp[i], dp[i - 10] + 1);
      if (i >= 25) dp[i] = Math.min(dp[i], dp[i - 25] + 1);
    }
  }
}
```

위의 풀이는 1원이 있을 경우에 유용한 풀이고 아래의 풀이의 경우 일반적으로 사용가능한 풀이가 될 것이라고 판단됨

```java
import java.io.*;
import java.util.Arrays;

public class Main {
  static int INF = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int T = Integer.parseInt(br.readLine());

    int[] table = initDpTable();
    for (int tc = 0; tc < T; tc++) {
      long cost = Long.parseLong(br.readLine());

      int count = 0;

      while (cost != 0) {

        int remain = (int)(cost % 100);

        count += table[remain];

        cost /= 100;
      }

      bw.write(count + "\n");
    }

    bw.flush();
    bw.close();
  }
  private static int[] initDpTable() {

    int[] table = new int[100];

    Arrays.fill(table, INF);

    table[0] = 0;
    int[] coins = {1, 10, 25};
    for (int i = 0; i < 3; i++) {
      int coin = coins[i];

      for (int j = coin; j < 100; j++) {
        if (table[j - coin] != INF) {
          table[j] = Math.min(table[j], table[j - coin] + 1);
        }
      }
    }

    return table;
  }
}
```
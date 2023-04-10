# 평범한 배낭 2

|시간 제한	|메모리 제한	|제출	|정답|	맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|1 초	|512 MB|	5148|	1204|	768|	23.479%|

## 문제

이 문제는 아주 평범한 배낭에 관한 두 번째 문제이다.

민호는 BOJ 캠프에 가기 위해 가방을 싸려고 한다. 가방에 어떠한 물건들을 넣냐에 따라 민호의 만족도가 달라진다. 집에 있는 모든 물건들을 넣으면 민호가 느낄 수 있는 만족도는 최대가 될 것이다. 하지만 민호가 들 수 있는 가방의 무게는 정해져 있어 이를 초과해 물건을 넣을수가 없다.

민호가 만족도를 최대로 느낄 수 있는 경우를 찾아보자.

단, 집에 동일한 물건들이 여러개가 있을 수 있기 때문에 한 물건을 두개 이상 챙기는 것도 가능하다.

## 입력 

첫 번째 줄에 N, M (1 ≤ N ≤ 100, 1 ≤ M ≤ 10,000) 이 빈칸을 구분으로 주어진다. N은 민호의 집에 있는 물건의 종류의 수이고 M은 민호가 들 수 있는 가방의 최대 무게다.

두 번째 줄부터 N개의 줄에 걸쳐 민호의 집에 있는 물건의 정보가 주어진다.

각각의 줄은 V, C, K (1 ≤ V ≤ M, 1 ≤ C, K ≤ 10,000, 1 ≤ V * K ≤ 10,000) 으로 이루어져 있다.

V는 물건의 무게, C는 물건을 가방에 넣었을 때 올라가는 민호의 만족도, K는 물건의 개수이다.

## 출력 

최대 무게를 넘기지 않게 물건을 담았을 때 민호가 느낄 수 있는 최대 만족도를 출력한다.

## 예제 입력 1

```
2 3
2 7 1
1 9 3
```

## 예제 출력 1

```
27
```

## 예제 입력 2

```
3 9
8 5 1
1 2 2
9 4 1
```

## 예제 출력 2

```
7
```

## 코드

**WA**

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
    int M = Integer.parseInt(st.nextToken());

    // row: 물건, col: 최대 허용치 무게, value: 만족도
    int[][] dp = new int[N + 1][M + 1];

    // row: 물건, col(value): {물건의 무게, 민호의 만족도, 물건의 개수}
    int[][] knapsack = new int[N + 1][3];

    for (int i = 1 ; i < N + 1; i++) {
      st = new StringTokenizer(br.readLine());
      knapsack[i][0] = Integer.parseInt(st.nextToken());
      knapsack[i][1] = Integer.parseInt(st.nextToken());
      knapsack[i][2] = Integer.parseInt(st.nextToken());
    }

    knapsack_dp(dp, knapsack, N, M);

    System.out.println(dp[N][M]);
  }

  static void knapsack_dp(int[][] dp, int[][] knapsack, int N, int M) {
    for (int i = 1; i < N + 1; i++) {
      int curThingWeight = knapsack[i][0];
      int curThingHappyNum = knapsack[i][1];
      for (int w = 1; w < M + 1; w++) {
        if (curThingWeight > w) {
          dp[i][w] = dp[i - 1][w];
        }
        else {
          int maxUsingThingNum = w / curThingWeight;
          if (knapsack[i][2] >= maxUsingThingNum) {
            dp[i][w] = Math.max(Math.max(dp[i][w - curThingWeight] + curThingHappyNum, dp[i][w - 1]), dp[i - 1][w - curThingWeight] + curThingHappyNum);
          }
          else {
            dp[i][w] = Math.max(dp[i][w - 1], dp[i - 1][w - curThingWeight] + curThingHappyNum);
          }
          dp[i][w] = Math.max(dp[i - 1][w], dp[i][w]);
        }
      }
    }
  }
}
```
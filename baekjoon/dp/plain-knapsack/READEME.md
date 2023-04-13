# 평범한 배낭 

**골드 5**

|시간 제한	|메모리 제한	|제출	|정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초	|512 MB	|95942|	35549|	22962	|35.440%|

## 문제 

이 문제는 아주 평범한 배낭에 관한 문제이다.

한 달 후면 국가의 부름을 받게 되는 준서는 여행을 가려고 한다. 세상과의 단절을 슬퍼하며 최대한 즐기기 위한 여행이기 때문에, 가지고 다닐 배낭 또한 최대한 가치 있게 싸려고 한다.

준서가 여행에 필요하다고 생각하는 N개의 물건이 있다. 각 물건은 무게 W와 가치 V를 가지는데, 해당 물건을 배낭에 넣어서 가면 준서가 V만큼 즐길 수 있다. 아직 행군을 해본 적이 없는 준서는 최대 K만큼의 무게만을 넣을 수 있는 배낭만 들고 다닐 수 있다. 준서가 최대한 즐거운 여행을 하기 위해 배낭에 넣을 수 있는 물건들의 가치의 최댓값을 알려주자.

## 입력 

첫 줄에 물품의 수 N(1 ≤ N ≤ 100)과 준서가 버틸 수 있는 무게 K(1 ≤ K ≤ 100,000)가 주어진다. 두 번째 줄부터 N개의 줄에 거쳐 각 물건의 무게 W(1 ≤ W ≤ 100,000)와 해당 물건의 가치 V(0 ≤ V ≤ 1,000)가 주어진다.

입력으로 주어지는 모든 수는 정수이다.

## 출력 

한 줄에 배낭에 넣을 수 있는 물건들의 가치합의 최댓값을 출력한다.

## 예제 입력 1

```
4 7
6 13
4 8
3 6
5 12
```

## 예제 출력 1

```
14
```

## 잘못된 풀이

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

    // 물품의 수 x 가능한 무게 범위 x {현재 무게, 현재 가치}
    int[][][] dp = new int[N + 1][K + 1][2];

    // 물건의 무게, 가치
    int[][] knapsack = new int[N + 1][2];

    for (int i = 1; i < N + 1; i++) {
      knapsack[i][0] = Integer.parseInt(st.nextToken());
      knapsack[i][1] = Integer.parseInt(st.nextToken());
    }


  }
  static void knapsack_dp(int[][][] dp, int[][] knapsack, int N, int K) {
    for (int i = 1; i < N + 1; i++) {
      for (int k = 1; k < K; k++) {
        if (knapsack[i][0] > K) {
          dp[i][k][0] = dp[i - 1][k][0];
          dp[i][k][1] = dp[i - 1][k][1];
        }
        else {
          int currentWeight = knapsack[i][0];

          int newWeight = currentWeight + dp[i - 1][k - currentWeight][0];
          int oldWeight = dp[i - 1][k][0];
          if (newWeight < oldWeight) {
            dp[i][k][0] = newWeight;
          }
          else if (newWeight > oldWeight) {
            dp[i][k][0] = oldWeight;
          }
        }
      }
    }
  }
}
```

해당 코드를 보면 knapsack_dp 에서 행을 물품으로 두고 열을 가능한 무게의 범위로 두었다.

그리고 value에 배열을 넣어서 현재 무게와 현재 가치를 넣어주고 있다.

왜 열에 가능한 무게 제한을 달아 두었는데, 굳이 배열의 value에도 무게를 사용하려고 하는 것인가..?

완전히 잘못된 풀이다.

이 방식은 열을 가능한 무게로 두는 의미 자체가 없는 풀이입니다.

## 코드 

**AC**

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

    int[][] knapsack = new int[N + 1][2];
    int[][] dp = new int[N + 1][K + 1];

    for (int i = 1; i < N + 1; i++) {
      st = new StringTokenizer(br.readLine());
      knapsack[i][0] = Integer.parseInt(st.nextToken());
      knapsack[i][1] = Integer.parseInt(st.nextToken());
    }

    knapsack_dp(knapsack, dp, N, K);

    System.out.println(dp[N][K]);
  }
  static void knapsack_dp(int[][] knapsack, int[][] dp, int N, int K) {
    for (int i = 1; i < N + 1; i++) {
      for (int k = 1; k < K + 1; k++) {
        int itemWeight = knapsack[i][0];
        if (itemWeight > k)
          dp[i][k] = dp[i - 1][k];
        else {
          dp[i][k] = Math.max(dp[i - 1][k], knapsack[i][1] + dp[i - 1][k - itemWeight]);
        }
      }
    }
  }
}
```
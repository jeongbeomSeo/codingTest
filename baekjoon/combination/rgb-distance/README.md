# RGB 거리

**실버 1**

|시간 제한|	메모리 제한	|제출|	정답|	맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|0.5 초 (추가 시간 없음)	|128 MB|	95644	|51695	|38494	|53.341%|


## 문제

RGB거리에는 집이 N개 있다. 거리는 선분으로 나타낼 수 있고, 1번 집부터 N번 집이 순서대로 있다.

집은 빨강, 초록, 파랑 중 하나의 색으로 칠해야 한다. 각각의 집을 빨강, 초록, 파랑으로 칠하는 비용이 주어졌을 때, 아래 규칙을 만족하면서 모든 집을 칠하는 비용의 최솟값을 구해보자.

- 1번 집의 색은 2번 집의 색과 같지 않아야 한다.
- N번 집의 색은 N-1번 집의 색과 같지 않아야 한다.
- i(2 ≤ i ≤ N-1)번 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 한다.

## 입력 

첫째 줄에 집의 수 N(2 ≤ N ≤ 1,000)이 주어진다. 둘째 줄부터 N개의 줄에는 각 집을 빨강, 초록, 파랑으로 칠하는 비용이 1번 집부터 한 줄에 하나씩 주어진다. 집을 칠하는 비용은 1,000보다 작거나 같은 자연수이다.

## 예제 입력 1

```
3
26 40 83
49 60 57
13 89 99
```

## 예제 출력 1

```
96
```

## 예제 입력 2

```
3
1 100 100
100 1 100
100 100 1
```

## 예제 출력 2

```
3
```

## 예제 입력 3

```
3
1 100 100
100 100 100
1 100 100
```

## 예제 출력 3

```
102
```

## 예제 입력 4

```
6
30 19 5
64 77 64
15 19 97
4 71 57
90 86 84
93 32 91
```

## 예제 출력 4

```
208
```

## 예제 입력 5

```
8
71 39 44
32 83 55
51 37 63
89 29 100
83 58 11
65 13 15
47 25 29
60 66 19
```

## 예제 출력 5

```
253
```

## 코드 

**TLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[][] cost;
  static boolean[][] flag;
  static int minSum = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    N = Integer.parseInt(br.readLine());

    cost = new int[N][N];

    flag = new boolean[N][3];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 3; j++) {
        cost[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    calcMinCost(0, 0, flag);

    System.out.println(minSum);
  }
  static void calcMinCost(int ptr, int sum, boolean[][] flag) {

    if (ptr == N) {
      minSum = Math.min(minSum, sum);
      return;
    }

    for (int i = 0; i < 3; i++) {
      if (ptr == 0) {
        flag[ptr][i] = true;
        calcMinCost(ptr + 1, sum + cost[ptr][i], flag);
        flag[ptr][i] = false;
      }
      else {
        if (!flag[ptr - 1][i]) {
          flag[ptr][i] = true;
          calcMinCost(ptr + 1, sum + cost[ptr][i], flag);
          flag[ptr][i] = false;
        }
      }
    }
  }
}
```

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int[][] cost = new int[N][3];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 3; j++) {
        cost[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int[][] dp = new int[N][3];

    for (int i = 0; i < 3; i++) {
      dp[0][i] = cost[0][i];
    }

    calc_dp(cost, dp, N);

    int min = Integer.MAX_VALUE;
    for (int i = 0; i < 3; i++) {
      min = Math.min(min, dp[N - 1][i]);
    }

    System.out.println(min);
  }
  static void calc_dp(int[][] cost, int[][] dp, int N) {
    for (int i = 1; i < N; i++) {
      for (int j = 0; j < 3; j++) {
        if (j == 0) {
          dp[i][j] = Math.min(dp[i - 1][1], dp[i - 1][2]) + cost[i][j];
        }
        else if (j == 1) {
          dp[i][j] = Math.min(dp[i - 1][0], dp[i - 1][2]) + cost[i][j];
        } else {
          dp[i][j] = Math.min(dp[i - 1][0], dp[i - 1][1]) + cost[i][j];
        }
      }
    }
  }
}
```
# 전깃줄

**골드 5**

|시간 제한	|메모리 제한	|제출|	정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|1 초|	128 MB|	30657|	14462	|11620	|46.825%|

## 문제 

두 전봇대 A와 B 사이에 하나 둘씩 전깃줄을 추가하다 보니 전깃줄이 서로 교차하는 경우가 발생하였다. 합선의 위험이 있어 이들 중 몇 개의 전깃줄을 없애 전깃줄이 교차하지 않도록 만들려고 한다.

예를 들어, < 그림 1 >과 같이 전깃줄이 연결되어 있는 경우 A의 1번 위치와 B의 8번 위치를 잇는 전깃줄, A의 3번 위치와 B의 9번 위치를 잇는 전깃줄, A의 4번 위치와 B의 1번 위치를 잇는 전깃줄을 없애면 남아있는 모든 전깃줄이 서로 교차하지 않게 된다.

![](https://upload.acmicpc.net/d90221dd-eb80-419f-bdfb-5dd4ebac23af/-/preview/)

전깃줄이 전봇대에 연결되는 위치는 전봇대 위에서부터 차례대로 번호가 매겨진다. 전깃줄의 개수와 전깃줄들이 두 전봇대에 연결되는 위치의 번호가 주어질 때, 남아있는 모든 전깃줄이 서로 교차하지 않게 하기 위해 없애야 하는 전깃줄의 최소 개수를 구하는 프로그램을 작성하시오.

## 입력 

첫째 줄에는 두 전봇대 사이의 전깃줄의 개수가 주어진다. 전깃줄의 개수는 100 이하의 자연수이다. 둘째 줄부터 한 줄에 하나씩 전깃줄이 A전봇대와 연결되는 위치의 번호와 B전봇대와 연결되는 위치의 번호가 차례로 주어진다. 위치의 번호는 500 이하의 자연수이고, 같은 위치에 두 개 이상의 전깃줄이 연결될 수 없다.

## 출력 

첫째 줄에 남아있는 모든 전깃줄이 서로 교차하지 않게 하기 위해 없애야 하는 전깃줄의 최소 개수를 출력한다.

## 예제 입력 1

```
8
1 8
3 9
2 2
4 1
6 4
10 10
9 7
7 6
```

## 예제 출력 1

```
3
```

## 코드 

아래 코드는 틀린 코드인데 LIS 알고리즘에서 DP를 사용해서 문제를 풀 때 **주의할 점**은 **맨 마지막 요소가 항상 가장 큰 값을 가지고 있는 것은 아니라는 점**이다.

**WA**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Wire implements Comparable<Wire>{
  int a;
  int b;

  Wire(int a, int b) {
    this.a = a;
    this.b = b;
  }

  @Override
  public int compareTo(Wire o) {
    if (this.a == o.a) return this.b - o.b;
    return this.a - o.a;
  }
}

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    Wire[] wires = new Wire[N];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());

      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      wires[i] = new Wire(a, b);
    }

    Arrays.sort(wires);

    int[] dp = new int[N];

    System.out.println(N - lis_dp(dp, wires, N));

  }
  static int lis_dp(int[] dp, Wire[] wires, int N) {

    for (int i = 0; i < N; i++) {
      dp[i] = 1;
      for (int k = 0; k < i; k++) {
        if (wires[i].b > wires[k].b) {
          dp[i] = Math.max(dp[i], dp[k] + 1);
        }
      }
    }

    return dp[N - 1];
  }
}
```

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Wire implements Comparable<Wire>{
  int a;
  int b;

  Wire(int a, int b) {
    this.a = a;
    this.b = b;
  }

  @Override
  public int compareTo(Wire o) {
    if (this.a == o.a) return this.b - o.b;
    return this.a - o.a;
  }
}

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    Wire[] wires = new Wire[N];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());

      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      wires[i] = new Wire(a, b);
    }

    Arrays.sort(wires);

    int[] dp = new int[N];

    System.out.println(N - lis_dp(dp, wires, N));

  }
  static int lis_dp(int[] dp, Wire[] wires, int N) {

    for (int i = 0; i < N; i++) {
      dp[i] = 1;
      for (int k = 0; k < i; k++) {
        if (wires[i].b > wires[k].b) {
          dp[i] = Math.max(dp[i], dp[k] + 1);
        }
      }
    }

    int max = dp[0];
    for (int i = 1; i < N; i++) {
      if (max < dp[i]) max = dp[i];
    }

    return max;
  }
}
```
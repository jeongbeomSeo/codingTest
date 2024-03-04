# 랜선 연결

**골드 2**

|시간 제한	|메모리 제한	|제출	|정답|	맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|1.5 초	|1024 MB	|342|	76|	59|	24.180%|

## 문제 

최근 연구실 위치를 옮긴 은규와 연구실 사람들은 큰 난관에 봉착했습니다. 그것은 바로 이사한 연구실에 인터넷이 연결되지 않는다는 것입니다!

다행히도 이웃 연구실에서 인터넷이 연결된 랜선 한 가닥을 얻을 수 있었고, 이 랜선을 연구실에 있는 스위치에 잘 연결해서 인터넷을 연결할 수 있게 되었습니다.

현재 연구실에는 N개의 스위치(Network Switch)와 M개의 컴퓨터가 있습니다. 각 스위치는 a<sub>i</sub>개의 포트가 있고 b<sub>i</sub>의 설치 비용이 듭니다. 

스위치에서 컴퓨터로 인터넷을 공급하기 위해서는 1개의 인터넷이 공급된 선이 연결되어야 하며 a<sub>i</sub> - 1개의 다른 기기(스위치 혹은 컴퓨터)에 인터넷을 공급할 수 있습니다. 

스위치끼리 사이클을 형성하는 것은, 화재의 위험이 있기 때문에 불가능합니다.

은규는 깔끔한 연결을 좋아하기 때문에 스위치에 남는 포트가 없도록 연결하려고 합니다. 

또 은규는 효율성도 중요시하기 때문에 가능한 여러 연결 방식이 존재한다면 그중 가장 적은 비용을 사용하는 방식을 택해서 모든 컴퓨터에 인터넷이 공급되도록 할 것입니다. 이러한 연결이 가능하다면 가능한 연결의 최소 비용을 출력하고, 불가능하다면 -1을 출력합니다.

## 입력 

첫 번째 줄에 스위치의 개수를 의미하는 정수 N(1 ≤ N ≤ 300)이 주어집니다.

두 번째 줄부터 N+1 번째 줄에는 각 줄마다 정수 a<sub>i</sub>(2 ≤ a<sub>i</sub> ≤ 10<sup>5</sup>)와 b<sub>i</sub>(0 ≤ b<sub>i</sub> ≤ 10<sup>9</sup>)가 공백으로 구분되어 주어집니다. 

각각 i번째 스위치의 포트 개수와 설치 비용을 의미합니다.

N+2번째 줄에는 연결할 컴퓨터의 개수 M(1 ≤ M ≤ 10<sup>5</sup>)이 주어집니다.

## 출력 

첫 번째 줄에 조건을 만족하는 연결 방식 중 최소 비용을 출력합니다. 그러한 연결 방식이 없다면 -1을 출력합니다.

## 예제 입력 1

```
1
5 10
4
```

## 예제 출력 1

```
10
```

## 예제 입력 2

```
1
5 10
5
```

## 예제 출력 2

```
-1
```

## 코드

**잘못된 풀이**

아래 코드는 스위치가 추가되었을 때 스위치끼리 연결해야 되는 경우를 제외하고 풀었음.

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  private static final int INF = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    // SwitchArray Info
    // element: Number of Available ports, cost
    int[][] switchArary = new int[N + 1][2];
    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      switchArary[i][0] = Integer.parseInt(st.nextToken()) - 1;
      switchArary[i][1] = Integer.parseInt(st.nextToken());
    }

    int computerCount = Integer.parseInt(br.readLine());

    // 직접 연결
    if (computerCount == 1) System.out.println(0);
    else {
      long[][] resultDpTable = queryDP(switchArary, computerCount, N);

      if (resultDpTable[N][computerCount] == INF) System.out.println(-1);
      else System.out.println(resultDpTable[N][computerCount]);
    }
  }
  private static long[][] queryDP(int[][] switchArray, int computerCount, int N) {
    long[][] dpTable = initDpTable(N, computerCount);

    for (int i = 1; i <= N; i++) {
      int port = switchArray[i][0];
      int cost = switchArray[i][1];

      int j;
      for (j = 1; j <= port && j <= computerCount; j++) {
        dpTable[i][j] = Math.min(dpTable[i - 1][j], cost);
      }
      for (; j <= computerCount; j++) {
        if (dpTable[i - 1][j - port] != INF) {
          dpTable[i][j] = Math.min(dpTable[i - 1][j - port] + cost, dpTable[i - 1][j]);
        }
        else break;
      }
    }

    return dpTable;
  }
  private static long[][] initDpTable(int N, int computerCount) {
    long[][] dpTable = new long[N + 1][computerCount + 1];

    for (int i = 0; i < N + 1; i++) {
      Arrays.fill(dpTable[i], INF);
      dpTable[i][0] = 0;
    }

    return dpTable;
  }
}
```

도저히 풀지 못해서 다음 페이지를 참고하고 있는 중이다.

- [[BOJ 25048] 백준 25048번 - 랜선 연결 - LogLife](https://burningfalls.github.io/algorithm/boj-25048/)

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  private static final long INF = Long.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    Switch[] switches = new Switch[N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());

      int port = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());
      switches[i] = new Switch(port, cost);
    }

    int computerCount = Integer.parseInt(br.readLine());

    if (computerCount == 1) System.out.println(0);
    else {
      System.out.println(query(switches, computerCount, N));
    }
  }
  private static long query(Switch[] switches, int computerCount, int N) {

    long[] dpTable = initDpTable(computerCount);

    for (int i = 0; i < N; i++) {
      int port = switches[i].port;
      int cost = switches[i].cost;

      for (int j = computerCount - (port - 2); j >= 1; j--) {
        if (dpTable[j] == INF) continue;

        dpTable[j + (port - 2)] = Math.min(dpTable[j] + cost, dpTable[j + (port - 2)]);
      }

      if (port - 1 <= computerCount) {
        dpTable[port - 1] = Math.min(dpTable[port - 1], cost);
      }
    }

    if (dpTable[computerCount] == INF) return -1;

    return dpTable[computerCount];
  }
  private static long[] initDpTable(int computerCount) {

    long[] dpTable = new long[computerCount + 1];

    Arrays.fill(dpTable, INF);
    dpTable[0] = 0;

    return dpTable;
  }
}
class Switch {
  int port;
  int cost;

  Switch(int port, int cost) {
    this.port = port;
    this.cost = cost;
  }
}
```
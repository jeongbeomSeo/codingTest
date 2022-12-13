# 플로이드

|시간 제한|	메모리 제한	|제출	|정답	맞힌 사람|	정답 비율|
|---|---|---|---|---|
|1 초|	256 MB|	47601|	19672|	13925	|41.717%|


## 문제

n(2 ≤ n ≤ 100)개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 m(1 ≤ m ≤ 100,000)개의 버스가 있다. 각 버스는 한 번 사용할 때 필요한 비용이 있다.

모든 도시의 쌍 (A, B)에 대해서 도시 A에서 B로 가는데 필요한 비용의 최솟값을 구하는 프로그램을 작성하시오.

## 입력

첫째 줄에 도시의 개수 n이 주어지고 둘째 줄에는 버스의 개수 m이 주어진다. 그리고 셋째 줄부터 m+2줄까지 다음과 같은 버스의 정보가 주어진다. 먼저 처음에는 그 버스의 출발 도시의 번호가 주어진다. 버스의 정보는 버스의 시작 도시 a, 도착 도시 b, 한 번 타는데 필요한 비용 c로 이루어져 있다. 시작 도시와 도착 도시가 같은 경우는 없다. 비용은 100,000보다 작거나 같은 자연수이다.

시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다.

## 출력

n개의 줄을 출력해야 한다. i번째 줄에 출력하는 j번째 숫자는 도시 i에서 j로 가는데 필요한 최소 비용이다. 만약, i에서 j로 갈 수 없는 경우에는 그 자리에 0을 출력한다.

## 예제 입력1
```
5
14
1 2 2
1 3 3
1 4 1
1 5 10
2 4 2
3 4 1
3 5 1
4 5 3
3 5 10
3 1 8
1 4 2
5 1 7
3 4 2
5 2 4
```

## 예제 출력1
```
0 2 3 1 4
12 0 15 2 5
8 5 0 1 1
10 7 13 0 3
7 4 10 6 0
```

## 코드 오류
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int INF = Integer.MAX_VALUE;

  public static void main(String[] args) throws IOException {

    ...

    // 그래프 초기화
    for(int i = 0; i < n + 1; i++) {
      for(int j = 0; j < n + 1; j++) {
        if(i != j) graph[i][j] = INF;
      }
    }

    // 버스 정보 입력
    for(int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      graph[n1][n2] = Math.min(cost, graph[n1][n2]);
    }
    
    // 플로이드 워셜 알고리즘 적용
    for(int k = 1; k < n + 1; k++) {
      for(int i = 1; i < n + 1; i++) {
        for(int j = 1; j < n + 1; j++) {
          graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
        }
      }
    }

    ...

```

플로이드 워셜 알고리즘을 적용할 때 위와같이 Integer.MAX_VALUE;를 사용하면 안된다.

그 이유는 자료형이 Int인 상황에서 Int형 중에 가장 큰 값이랑 다른 값을 합치는 순간 범위를 넘어서기 때문에 음수로 넘어간다.

그러면 Math.min으로 처리를 하였을 때 음수값이 들어가게 된다.
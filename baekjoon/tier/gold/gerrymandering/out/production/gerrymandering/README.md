# 게리맨더링

**골드 4**

|시간 제한	|메모리 제한	|제출	정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|
|0.5 초| (추가 시간 없음)	|512 MB|	20529	|8888	|5657	|39.776%|

## 문제 

백준시의 시장 최백준은 지난 몇 년간 게리맨더링을 통해서 자신의 당에게 유리하게 선거구를 획정했다. 견제할 권력이 없어진 최백준은 권력을 매우 부당하게 행사했고, 심지어는 시의 이름도 백준시로 변경했다. 이번 선거에서는 최대한 공평하게 선거구를 획정하려고 한다.

백준시는 N개의 구역으로 나누어져 있고, 구역은 1번부터 N번까지 번호가 매겨져 있다. 구역을 두 개의 선거구로 나눠야 하고, 각 구역은 두 선거구 중 하나에 포함되어야 한다. 선거구는 구역을 적어도 하나 포함해야 하고, 한 선거구에 포함되어 있는 구역은 모두 연결되어 있어야 한다. 구역 A에서 인접한 구역을 통해서 구역 B로 갈 수 있을 때, 두 구역은 연결되어 있다고 한다. 중간에 통하는 인접한 구역은 0개 이상이어야 하고, 모두 같은 선거구에 포함된 구역이어야 한다.

아래 그림은 6개의 구역이 있는 것이고, 인접한 구역은 선으로 연결되어 있다.

![](https://upload.acmicpc.net/08218f4c-2653-4861-a4c1-e7ce808f3a85/-/preview/)

| ![](https://upload.acmicpc.net/b82fcf21-6f4c-4797-bda6-215e14099d19/-/preview/)  | ![](https://upload.acmicpc.net/32947e26-4ec4-4b20-99f1-106d8386683d/-/preview/) | ![](https://upload.acmicpc.net/f5dd6143-c013-46d3-ba4c-dadc48bdf5bc/-/preview/) | ![](https://upload.acmicpc.net/548b1153-84de-4b85-9697-2561b019a02b/-/preview/) |
|:------:|:-------------------------------------------------------------------------------:|:-------------------------------------------------------------------------------:|:-------------------------------------------------------------------------------:|
|가능한 방법: [1, 3, 4]와 [2, 5, 6]으로 나누어져 있다. |                    가능한 방법: [1, 2, 3, 4, 6]과 [5]로 나누어져 있다.   |          불가능한 방법: [1, 2, 3, 4]와 [5, 6]으로 나누어져 있는데, 5와 6이 연결되어 있지 않다. |                      불가능한 방법: 각 선거구는 적어도 하나의 구역을 포함해야 한다.                       |

공평하게 선거구를 나누기 위해 두 선거구에 포함된 인구의 차이를 최소로 하려고 한다. 백준시의 정보가 주어졌을 때, 인구 차이의 최솟값을 구해보자.

## 입력 

첫째 줄에 구역의 개수 N이 주어진다. 둘째 줄에 구역의 인구가 1번 구역부터 N번 구역까지 순서대로 주어진다. 인구는 공백으로 구분되어져 있다.

셋째 줄부터 N개의 줄에 각 구역과 인접한 구역의 정보가 주어진다. 각 정보의 첫 번째 정수는 그 구역과 인접한 구역의 수이고, 이후 인접한 구역의 번호가 주어진다. 모든 값은 정수로 구분되어져 있다.

구역 A가 구역 B와 인접하면 구역 B도 구역 A와 인접하다. 인접한 구역이 없을 수도 있다.

## 출력 

첫째 줄에 백준시를 두 선거구로 나누었을 때, 두 선거구의 인구 차이의 최솟값을 출력한다. 두 선거구로 나눌 수 없는 경우에는 -1을 출력한다.

## 제한

2 ≤ N ≤ 10
1 ≤ 구역의 인구 수 ≤ 100

## 예제 입력 1

```
6
5 2 3 4 1 2
2 2 4
4 1 3 6 5
2 4 2
2 1 3
1 2
1 2
```

## 예제 출력 1

```
1
```

선거구를 [1, 4], [2, 3, 5, 6]으로 나누면 각 선거구의 인구는 9, 8이 된다. 인구 차이는 1이고, 이 값보다 더 작은 값으로 선거구를 나눌 수는 없다.

## 예제 입력 2

```
6
1 1 1 1 1 1
2 2 4
4 1 3 6 5
2 4 2
2 1 3
1 2
1 2
```

## 예제 출력 2

```
0
```

## 예제 입력 3

```
6
10 20 10 20 30 40
0
0
0
0
0
0
```

## 예제 출력 3

```
-1
```

## 예제 입력 4

```
6
2 3 4 5 6 7
2 2 3
2 1 3
2 1 2
2 5 6
2 4 6
2 4 5
```

## 예제 출력 4

```
9
```

## 노트

게리맨더링은 특정 후보자나 정당에 유리하게 선거구를 획정하는 것을 의미한다.

## 주의 사항 

입력값으로 반복문의 사이즈를 받을 때 이와 같이 사용 금지

```java
    for (int i = 1; i < N + 1; i++) {
      st = new StringTokenizer(br.readLine());

      for (int j = 0; j < Integer.parseInt(st.nextToken()); j++) {
        int n = Integer.parseInt(st.nextToken());
        graph.get(i).add(n);
      }
    }
```

이러면 2번째 반복문에서 돌아 갈 때 문제가 발생 

j = 0일때 정상적으로 작동하지만 증감식을 갔다가 조건식으로 가면 조건식의 st.nextToken()이 다시 실행된다.

## 코드

**WA(1%)**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

  static int INF = Integer.MAX_VALUE;
  static int[] person;
  static int min = INF;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    person = new int[N + 1];
    int[] areaA = new int[N + 1];
    int[] areaB = new int[N + 1];

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i < N + 1; i++) {
      person[i] = Integer.parseInt(st.nextToken());
    }

    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < N + 1; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 1; i < N + 1; i++) {
      st = new StringTokenizer(br.readLine());

      int size = Integer.parseInt(st.nextToken());
      for (int j = 0; j < size; j++) {
        graph.get(i).add(Integer.parseInt(st.nextToken()));
      }
    }

    combination(graph, 1, N, areaA, areaB, 0, 0);

    if (min == INF) System.out.println(-1);
    else System.out.println(min);
  }
  static void combination(ArrayList<ArrayList<Integer>> graph, int ptr, int N, int[] areaA, int[] areaB, int sizeA, int sizeB) {

    if (ptr == N + 1) {
      if (sizeA != 0 && sizeB != 0) {
        if (bfs(graph, areaA, sizeA, N) && bfs(graph, areaB, sizeB, N)) {
          int sumA = 0;
          int sumB = 0;
          for (int i = 0; i < sizeA; i++) {
            sumA += person[areaA[i]];
          }
          for (int i = 0; i < sizeB; i++) {
            sumB += person[areaB[i]];
          }
          min = Math.min(min, Math.abs(sumA - sumB));
        }
      }
    }
    else {
      areaA[sizeA] = ptr;
      combination(graph, ptr + 1, N, areaA, areaB, sizeA + 1, sizeB);

      areaB[sizeB] = ptr;
      combination(graph, ptr + 1, N, areaA, areaB, sizeA, sizeB + 1);
    }
  }

  static boolean bfs(ArrayList<ArrayList<Integer>> graph, int[] area, int size, int N) {
    Queue<Integer> q = new ArrayDeque<>();
    boolean[] isVisited = new boolean[N + 1];
    int check = 0;

    q.add(area[0]);
    isVisited[area[0]] = true;
    check++;

    while (!q.isEmpty()) {
      int curNode = q.poll();

      for (int i = 0; i < graph.get(curNode).size(); i++) {
        int adjNode = graph.get(curNode).get(i);

        for (int j = 0; j < area.length; j++) {
          // 방문 X + 구역에 맞는 도시인지 체크
          if (!isVisited[adjNode] && area[j] == adjNode) {
            q.add(adjNode);
            isVisited[adjNode] = true;
            check++;
            break;
          }
        }
      }
    }
    if (check == size) return true;
    return false;
  }
}
```

**해당 풀이에서 고려하지 않은 부분**

1. areaA와 areaB는 팀나누기와 마찬가지로 겹치면 중복

**TIP**

디버깅할거면 다음과 같이 하자.

```java
static void combination(ArrayList<ArrayList<Integer>> graph, int ptr, int N, int[] areaA, int[] areaB, int sizeA, int sizeB) {

  if (ptr == N + 1) {
    if (sizeB != 0) {
      if (bfs(graph, areaA, sizeA, N) && bfs(graph, areaB, sizeB, N)) {
        System.out.println("탐색 성공 case");
        int sumA = 0;
        int sumB = 0;
        System.out.print("area A : ");
        for (int i = 0; i < sizeA; i++) {
          System.out.print(areaA[i] + " ");
          sumA += person[areaA[i]];
        }
        System.out.println();
        System.out.print("area B : ");
        for (int i = 0; i < sizeB; i++) {
          System.out.print(areaB[i] + " ");
          sumB += person[areaB[i]];
        }
        System.out.println();
        min = Math.min(min, Math.abs(sumA - sumB));
      }
    }
  }
  else {
    areaA[sizeA] = ptr;
    combination(graph, ptr + 1, N, areaA, areaB, sizeA + 1, sizeB);

    areaB[sizeB] = ptr;
    combination(graph, ptr + 1, N, areaA, areaB, sizeA, sizeB + 1);
  }
}
```

해당 코드의 문제 부분이다.

```java
  static boolean bfs(ArrayList<ArrayList<Integer>> graph, int[] area, int size, int N) {
    Queue<Integer> q = new ArrayDeque<>();
    boolean[] isVisited = new boolean[N + 1];
    int check = 0;

    q.add(area[0]);
    isVisited[area[0]] = true;
    check++;

    while (!q.isEmpty()) {
      int curNode = q.poll();

      for (int i = 0; i < graph.get(curNode).size(); i++) {
        int adjNode = graph.get(curNode).get(i);

        for (int j = 0; j < area.length; j++) {
          // 방문 X + 구역에 맞는 도시인지 체크
          if (!isVisited[adjNode] && area[j] == adjNode) {
            q.add(adjNode);
            isVisited[adjNode] = true;
            check++;
            break;
          }
        }
      }
    }
    if (check == size) return true;
    return false;
  }
```

현재 area의 크기의 경우 실질적으로 쓰는 범위는 size이지만, 허용한 범위는 N + 1입니다.

이것을 전부 탐색할 경우 문제가 발생할 수 있습니다.

## 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

  static int INF = Integer.MAX_VALUE;
  static int[] person;
  static int min = INF;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    person = new int[N + 1];
    int[] areaA = new int[N + 1];
    int[] areaB = new int[N + 1];

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i < N + 1; i++) {
      person[i] = Integer.parseInt(st.nextToken());
    }

    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < N + 1; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 1; i < N + 1; i++) {
      st = new StringTokenizer(br.readLine());

      int size = Integer.parseInt(st.nextToken());
      for (int j = 0; j < size; j++) {
        graph.get(i).add(Integer.parseInt(st.nextToken()));
      }
    }

    areaA[0] = 1;
    combination(graph, 2, N, areaA, areaB, 1, 0);

    if (min == INF) System.out.println(-1);
    else System.out.println(min);
  }
  static void combination(ArrayList<ArrayList<Integer>> graph, int ptr, int N, int[] areaA, int[] areaB, int sizeA, int sizeB) {

    if (ptr == N + 1) {
      if (sizeB != 0) {
        if (bfs(graph, areaA, sizeA, N) && bfs(graph, areaB, sizeB, N)) {
          int sumA = 0;
          int sumB = 0;
          for (int i = 0; i < sizeA; i++) {
            sumA += person[areaA[i]];
          }
          for (int i = 0; i < sizeB; i++) {
            sumB += person[areaB[i]];
          }
          min = Math.min(min, Math.abs(sumA - sumB));
        }
      }
    }
    else {
      areaA[sizeA] = ptr;
      combination(graph, ptr + 1, N, areaA, areaB, sizeA + 1, sizeB);

      areaB[sizeB] = ptr;
      combination(graph, ptr + 1, N, areaA, areaB, sizeA, sizeB + 1);
    }
  }

  static boolean bfs(ArrayList<ArrayList<Integer>> graph, int[] area, int size, int N) {
    Queue<Integer> q = new ArrayDeque<>();
    boolean[] isVisited = new boolean[N + 1];
    int check = 0;

    q.add(area[0]);
    isVisited[area[0]] = true;
    check++;

    while (!q.isEmpty()) {
      int curNode = q.poll();

      for (int i = 0; i < graph.get(curNode).size(); i++) {
        int adjNode = graph.get(curNode).get(i);

        for (int j = 0; j < size; j++) {
          // 방문 X + 구역에 맞는 도시인지 체크
          if (!isVisited[adjNode] && area[j] == adjNode) {
            q.add(adjNode);
            isVisited[adjNode] = true;
            check++;
            break;
          }
        }
      }
    }
    if (check == size) return true;
    return false;
  }
}
```
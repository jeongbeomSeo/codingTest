# 숨바꼭질

**실버 1**

|시간 제한	|메모리 제한|	제출	|정답|	맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초|	128 MB	|197223	|56886|	35766	|25.287%|

## 문제 

수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다. 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.

수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.

## 입력 

첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.

## 출력 

수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.

## 예제 입력 1

```
5 17
```

## 예제 출력 1

```
4
```

## 코드

**MLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    System.out.println(bfs(N, M));

  }
  static int bfs(int N, int M) {

    Queue<Node> q = new ArrayDeque<>();

    q.add(new Node(N));

    while (!q.isEmpty()) {
      Node curNode = q.poll();

      if (curNode.idx == M) return curNode.time;
      if (curNode.idx >= M + 2) continue;

      if (curNode.idx != 0) q.add(new Node(curNode.idx - 1, curNode.time+ 1));
      q.add(new Node(curNode.idx + 1, curNode.time + 1));
      q.add(new Node(curNode.idx * 2, curNode.time + 1));
    }
    return 0;
  }
}

class Node {
  int idx;
  int time;

  Node(int idx) {
    this.idx = idx;
    this.time = 0;
  }

  Node(int idx, int time) {
    this.idx = idx;
    this.time = time;
  }

}
```

**WA**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int MAX_SIZE = 100001;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    boolean[] isVisited = new boolean[MAX_SIZE];
    System.out.println(bfs(N, M, isVisited));

  }
  static int bfs(int N, int M, boolean[] isVisited) {

    Queue<Node> q = new ArrayDeque<>();

    q.add(new Node(N));
    isVisited[N] = true;

    while (!q.isEmpty()) {
      Node curNode = q.poll();
      if (curNode.idx == M) return curNode.time;

      if (curNode.idx != 0) {
        if (!isVisited[curNode.idx - 1]) {
          q.add(new Node(curNode.idx - 1, curNode.time+ 1));
          isVisited[curNode.idx - 1] = true;
        }
      }
      if (curNode.idx + 1 <= M && !isVisited[curNode.idx + 1]) {
        q.add(new Node(curNode.idx + 1, curNode.time + 1));
        isVisited[curNode.idx + 1] = true;
      }
      if (curNode.idx * 2 <= M && !isVisited[curNode.idx * 2]) {
        q.add(new Node(curNode.idx * 2, curNode.time + 1));
        isVisited[curNode.idx * 2] = true;
      }
    }
    return 0;
  }
}

class Node {
  int idx;
  int time;

  Node(int idx) {
    this.idx = idx;
    this.time = 0;
  }

  Node(int idx, int time) {
    this.idx = idx;
    this.time = time;
  }

}
```
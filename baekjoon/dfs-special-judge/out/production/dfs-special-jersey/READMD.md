# DFS 스페셜 저지

## 문제

BOJ에서 정답이 여러가지인 경우에는 스페셜 저지를 사용한다. 스페셜 저지는 유저가 출력한 답을 검증하는 코드를 통해서 정답 유무를 결정하는 방식이다. 오늘은 스페셜 저지 코드를 하나 만들어보려고 한다.

정점의 개수가 N이고, 정점에 1부터 N까지 번호가 매겨져있는 양방향 그래프가 있을 때, DFS 알고리즘은 다음과 같은 형태로 이루어져 있다.

```java
void dfs(int x) {
    if (check[x] == true) {
        return;
    }
    check[x] = true;
    // x를 방문
    for (int y : x와 인접한 정점) {
        if (check[y] == false) {
            dfs(y);
        }
    }
}
```

이 문제에서 시작 정점은 1이기 때문에 가장 처음에 호출하는 함수는 dfs(1)이다. DFS 방문 순서는 dfs함수에서 // x를 방문 이라고 적힌 곳에 도착한 정점 번호를 순서대로 나열한 것이다.

트리가 주어졌을 때, 올바른 DFS 방문 순서인지 구해보자.

## 입력

첫째 줄에 정점의 수 N(2 ≤ N ≤ 100,000)이 주어진다. 둘째 줄부터 N-1개의 줄에는 트리의 간선 정보가 주어진다. 마지막 줄에는 DFS 방문 순서가 주어진다. DFS 방문 순서는 항상 N개의 정수로 이루어져 있으며, 1부터 N까지 자연수가 한 번씩 등장한다.

## 출력

입력으로 주어진 DFS 방문 순서가 올바른 순서면 1, 아니면 0을 출력한다.

## 예제 입력 1

4

1 2

1 3

2 4

1 2 3 4

## 예제 출력 1

0

## 예제 입력 2

4

1 2

1 3

2 4

1 2 4 3

## 예제 출력 2

1

## 예제 입력 3

4

1 2

1 3

2 4

1 3 2 4

## 예제 출력 3

1

## 문제 조건

|시간 제한|	메모리 제한|	제출	|정답|	맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초|	512 MB|	3745|	1291|	944|	37.912%|

## 문제 풀이

현재 문제를 푸는 도중 난관에 부딪혔다.

내용은 이러하다.

현재 문제에서 원하는 DFS는 숫자가 낮은 것부터 깊이 탐색을 시작하는 것이 아니라 깊이 탐색의 규칙만 지키면 1을 출력해주고 아니면 0을 출력하는 시스템을 요구하고 있다.

즉, 재귀함수를 이용해서 DFS를 풀어나갈 경우 반복문으로 for문을 사용하게 되는데, 재귀 함수의 특성을 잘 살펴봐야 된다.

만약 이와같이 DFS코드를 짰다고 해보자.

```java
  static void dfs(int x) {
    if(check[x] == true) {
      return;
    }
    check[x] = true;
    for(int i : graph[x]) {
      if(!check[i]){
        if(ans[index] == i ){
          index++;
          dfs(i);
        }
      }
    }
  }
```

해당 코드는 배열 ans안에 사용자가 입력한 DFS방문 순서가 담겨있다.

그래서 같은 높이에서 숫자가 작은 것 부터 탐색을 하는 것이 아니라 사용자가 입력한 것부터 탐색하는 방식이다.

하지만, 문제는 이것이다.

for문 안에 재귀함수가 있고, 사용자가 입력한 Node가 graph[x]안에서 뒤 index에 존재할 경우, 앞의 index는 실행하지 않고 뛰어 넘는다.

이후, 원하는 Node를 방문하고 재귀함수를 나왔을 때는 앞의 index로 돌아가야 되는데 그것이 쉽지 않다.

- for문을 한번 더 돌릴까? ==> 이것은 생각해보면, 자식 node가 굉장히 많고 사용자가 뒤에 node만 먼저 계속 입력할 경우 문제가 된다.
- do - while문을 사용할까? ==> 만약, 앞의 index를 가진 node를 뛰어넘고 뒤의 node를 실행할경우 boolean변수에 넣어줘서 true일 경우 다시 실행 시키는 것인데 코드는 이와 같다.
```java
  static void dfs(int x) {
    if(check[x] == true) {
      return;
    }
    check[x] = true;
    boolean isPassed;
    do{
      isPassed = false;
      for(int i : graph[x]) {
        if(!check[i]){
          if(ans[index] == i ){
            index++;
            dfs(i);
          }
          else isPassed = true;
        }
      }
    }while(isPassed);
  }
```
해당 코드의 경우 예제 입력1을 입력했을 때 무한히 실행된다. 그 이유는 1 에서 2로 가고 그 이후 ans[index]에는 3이 들어가 있어서, 4랑은 맞지 않는다.

그래서 isPassed는 true가 되게 되는데, do - while문에 의해 다시 2로 돌아가서 무한히 반복한다.

즉, 해당 코드는 ans에 크게 틀어지는 node가 나왔을 경우 무한히 반복하는 문제가 존재한다.

그것을 for문을 활용하면 해결할 수 있다는 것을 알았다.

```java
  static void dfs(int curNode) {
    for(int i=0;i<graph[curNode].size();i++) {
      int next = graph[curNode].get(i);
      if(!check[next] && ans[index]==next) {
        index++;
        check[next]=true;
        dfs(next);
        i = -1;
      }
    }
  }
```

## 참고한 사이트

- [백준 16964] DFS 스페셜 저지 (JAVA) 풀이
  - https://imnotabear.tistory.com/603
- StringTokenizer 클래스로 문자열 분리하기! split 비교.
  - https://jhnyang.tistory.com/398
- Java do While
  - https://nirsa.tistory.com/160
-  

## 나의 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
  static boolean[] check;
  static int N;
  static int[] ans;

  static int index = 1;
  static boolean sol;

  static LinkedList<Integer>[] graph;

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());

    check = new boolean[N+1];
    ans = new int[N];
    graph = new LinkedList[N+1];

    for(int i = 0; i < N-1; i++) {
      String s = br.readLine();
      StringTokenizer st = new StringTokenizer(s);

      int node1= Integer.parseInt(st.nextToken());
      int node2= Integer.parseInt(st.nextToken());

      if(graph[node1] == null) graph[node1] = new LinkedList<>();
      if(graph[node2] == null) graph[node2] = new LinkedList<>();

      graph[node1].add(node2);
      graph[node2].add(node1);
    }

    String s = br.readLine();
    StringTokenizer st = new StringTokenizer(s);

    int count = 0;
    while(st.hasMoreTokens()) {
      ans[count++] = Integer.parseInt(st.nextToken());
    }

    sol = true;

    check[1] = true;
    dfs(1);

    if(index == N) System.out.print(1);
    else System.out.print(0);
  }

  static void dfs(int curNode) {
    for(int i=0;i<graph[curNode].size();i++) {
      int next = graph[curNode].get(i);
      if(!check[next] && ans[index]==next) {
        index++;
        check[next]=true;
        dfs(next);
        i = -1;
      }
    }
  }
}

```

**채점 결과**

73% TLE

---
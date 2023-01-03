# 민식우선탐색

|시간 제한	|메모리 제한|	제출	|정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|1 초|	128 MB|	1425|	195	|102|	12.230%|

## 문제 

DFS와 BFS는 아는가? DFS는 깊이우선탐색, BFS는 넓이우선탐색이다.

이번에는 mFS! 그래, 듣도 보도 못했던 듣보잡 탐색방법인, 민식우선탐색이다.

민식이는 DFS를 할 줄 모르기 때문에 다음과 이 탐색방법을 만들어냈다.

이 탐색방법을 설명하자면 다음과 같다.

기본틀은 DFS와 완전히 동일하다. 그런데 한가지 다른 점이 있다면, 한 점에서 다른 정점을 방문할 때 순서가 다르다. 현재 점에서 방문할 수 있는 정점(갈 수 있으면서 방문한 적 없는 정점들)들이 홀수개면 그 정점 번호들의 중간값인 정점으로 방문을 시작하고, 짝수개면 가장 작은 정점 번호로 방문을 시작한다.

당신의 임무는, 1번 정점에서 출발하여 민식우선탐색을 하는 순서를 찍는 것이다.

## 입력

첫째 줄에는 정점의 개수 N(<=100,000)과 간선의 개수 M(<=1,000,000)이 주어진다. 두 번째 줄부터 M+1번째 줄 까지는 a b의 형태로 a와 b가 간선으로 연결되어 있다는 의미의 입력이 들어온다. 모든 간선은 양방향이다.

## 출력

민식우선탐색의 순서를 출력한다.

## 예제 입력 1

```
5 6
1 2
1 4
1 3
3 2
3 4
2 5 
```

## 예제 출력 1 

```
1 3 2 5 4
```

## 힌트

1->3->2->5->2->3->4의 순서로 움직인다.

## 문제 풀이

> **고려할 점**
>
> 1. 방문한 Node와 방문하지 않은 Node의 구별
> 2. Multiple Edge(두 Node간에)
> 3. Self-loop Edge

## 틀린 풀이

**풀이 과정**

1. 함수에서 처음에 처리하는 방식은 DFS와 동일하게 적용
2. for문을 도는 과정에서 방문하지 않은 노드만 체크하는 것이 아니라 ArrayList를 하나 만들어서 매번 방문하지 않은 노드의 개수를 체크하면서 순서대로 넣어준다.
   1. 짝수 개일시에 index 0에 있는 것을 실행
   2. 홀수 개일시에 index 중간에 있는 것을 실행

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());

      // 정점의 개수, 간선의 개수
      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());

      if(N == 0) return;

      ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
      for(int i = 0; i < N + 1; i++) {
         graph.add(new ArrayList<>());
      }

      for(int i = 0; i < M; i++) {
         st = new StringTokenizer(br.readLine());
         int n1 = Integer.parseInt(st.nextToken());
         int n2 = Integer.parseInt(st.nextToken());

         graph.get(n1).add(n2);
         graph.get(n2).add(n1);
      }

      for(int i = 1; i < N + 1; i++) {
         Collections.sort(graph.get(i));
      }

      boolean[] visited = new boolean[N + 1];
      minsik(1, graph, visited);
   }
   static void minsik(int node, ArrayList<ArrayList<Integer>> graph, boolean[] visited) {

      visited[node] = true;
      System.out.print(node + " ");

      // 방문할 수 있는 노드가 없을 시에 break
      while(true) {
         ArrayList<Integer> notVisitied = new ArrayList<>();
         for(int i = 0; i < graph.get(node).size(); i++) {
            int adjNode = graph.get(node).get(i);
            if(!visited[adjNode]) notVisitied.add(adjNode);
         }
         int len = notVisitied.size();
         if(len == 0) break;
         else if(len % 2 == 0 || len == 1) minsik(notVisitied.get(0), graph, visited);
         else minsik(notVisitied.get(len / 2), graph, visited);
      }
   }
}
```

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
  static boolean[] visited;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    // 정점의 개수, 간선의 개수
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    for(int i = 0; i < N + 1; i++) {
      graph.add(new ArrayList<>());
    }

    for(int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());

      // 양방향 그래프
      graph.get(n1).add(n2);
      graph.get(n2).add(n1);
    }

    for(int i = 1; i < N + 1; i++) {
      Collections.sort(graph.get(i));
    }

    visited = new boolean[N + 1];
    minsik(1, graph);
  }
  static void minsik(int start, ArrayList<ArrayList<Integer>> graph) {

    Stack<Integer> stack = new Stack<>();
    ArrayList<Integer> childNodeList = new ArrayList<>();

    stack.add(start);
    visited[start] = true;
    System.out.print(start + " ");

    while (!stack.isEmpty()) {
      int curNode = stack.peek();

      for(int i = 0; i < graph.get(curNode).size(); i++) {
        int childNode = graph.get(curNode).get(i);
        if (!visited[childNode]) {
          childNodeList.add(childNode);
        }
      }
      int count = childNodeList.size();
      if(count == 0) stack.pop();
      else if(count == 1 || count % 2 == 0)  {
        int node = childNodeList.get(0);
        stack.add(node);
        visited[node] = true;
        System.out.print(node + " ");
      }
      else {
        int node = childNodeList.get(count/2);
        stack.add(node);
        visited[node] = true;
        System.out.print(node + " ");
      }
      childNodeList.clear();
    }
  }
}
```

두 풀이 모두 **9%** 에서 틀렸다.

**MLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
  static boolean[] visited;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    // 정점의 개수, 간선의 개수
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    for(int i = 0; i < N + 1; i++) {
      graph.add(new ArrayList<>());
    }

    for(int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());

      if(n1 == n2) continue;
      if(graph.get(n1).stream().anyMatch(node -> node == n2)) continue;
      // 양방향 그래프
      graph.get(n1).add(n2);
      graph.get(n2).add(n1);
    }

    for(int i = 1; i < N + 1; i++) {
      Collections.sort(graph.get(i));
    }

    visited = new boolean[N + 1];
    minsik(1, graph);
  }
  static void minsik(int start, ArrayList<ArrayList<Integer>> graph) {

    Stack<Integer> stack = new Stack<>();
    ArrayList<Integer> childNodeList = new ArrayList<>();

    stack.add(start);
    visited[start] = true;
    System.out.print(start + " ");

    while (!stack.isEmpty()) {
      int curNode = stack.peek();

      for(int i = 0; i < graph.get(curNode).size(); i++) {
        int childNode = graph.get(curNode).get(i);
        if (!visited[childNode]) {
          childNodeList.add(childNode);
        }
      }
      int count = childNodeList.size();
      if(count == 0) stack.pop();
      else if(count == 1 || count % 2 == 0)  {
        int node = childNodeList.get(0);
        stack.add(node);
        visited[node] = true;
        System.out.print(node + " ");
      }
      else {
        int node = childNodeList.get(count/2);
        stack.add(node);
        visited[node] = true;
        System.out.print(node + " ");
      }
      childNodeList.clear();
    }
  }
}
```

**TLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
  static boolean[] visited;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    // 정점의 개수, 간선의 개수
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    for(int i = 0; i < N + 1; i++) {
      graph.add(new ArrayList<>());
    }

    for(int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());

      // Check self-loop Edge
      if(n1 == n2) continue;
      // Check Multiple Edge
      boolean equal = false;
      for (int node : graph.get(n1)) if(n2 == node) equal = true;
      if(equal) continue;
      // 아래 코드에서 continue의 역할은 if문 바로 밖의 for문인 for(int node: graph.get(n1))의 for문에서 수행한다.
      // for(int node : graph.get(n1)) if(n2 == node) continue;
      // 양방향 그래프
      graph.get(n1).add(n2);
      graph.get(n2).add(n1);
    }

    for(int i = 1; i < N + 1; i++) {
      Collections.sort(graph.get(i));
    }

    visited = new boolean[N + 1];
    minsik(1, graph);
  }
  static void minsik(int start, ArrayList<ArrayList<Integer>> graph) {

    Stack<Integer> stack = new Stack<>();
    ArrayList<Integer> childNodeList = new ArrayList<>();

    stack.push(start);
    visited[start] = true;
    System.out.print(start + " ");

    while (!stack.isEmpty()) {
      int curNode = stack.peek();

      for(int i = 0; i < graph.get(curNode).size(); i++) {
        int childNode = graph.get(curNode).get(i);
        if (!visited[childNode]) {
          childNodeList.add(childNode);
        }
      }
      int count = childNodeList.size();
      // no have notVisited Child Node
      if(count == 0) stack.pop();
      // have notVisited Child Node
      else if(count == 1 || count % 2 == 0)  {
        int node = childNodeList.get(0);
        stack.add(node);
        visited[node] = true;
        System.out.print(node + " ");
      }
      else {
        int node = childNodeList.get(count/2);
        stack.add(node);
        visited[node] = true;
        System.out.print(node + " ");
      }
      childNodeList.clear();
    }
  }
}
```

**MLE**
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
  static boolean[] visited;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    // 정점의 수, 간선의 수
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    // Check Multiple Edge Using Set Collections
    ArrayList<Set<Integer>> graph = new ArrayList<>();
    for(int i = 0; i < N + 1; i++) {
      graph.add(new LinkedHashSet<>());
    }

    for(int i = 0 ; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());

      // Check Self-loop Edge
      if(n1 == n2) continue;

      graph.get(n1).add(n2);
      graph.get(n2).add(n1);
    }

    visited = new boolean[N + 1];
    minsik(1, graph);
  }
  static void minsik(int start, ArrayList<Set<Integer>> graph) {

    Stack<Integer> stack = new Stack<>();
    ArrayList<Integer> notVisitedNode = new ArrayList<>();

    stack.add(start);
    System.out.print(start + " ");
    visited[start] = true;

    while (!stack.isEmpty()) {
      int curNode = stack.peek();

      Iterator<Integer> itrAdjNode = graph.get(curNode).iterator();
      while (itrAdjNode.hasNext()) {
        int adjNode = itrAdjNode.next();
        if(!visited[adjNode]) {
          notVisitedNode.add(adjNode);
        }
      }

      // have notVisited Child Node
      if(!notVisitedNode.isEmpty()) {
        int cnt = notVisitedNode.size();
        if(cnt == 1) {
          stack.add(notVisitedNode.get(0));
          visited[notVisitedNode.get(0)] = true;
          System.out.print(notVisitedNode.get(0) + " ");
        }
        else {
          Collections.sort(notVisitedNode);
          // If child Node Num is Even
          if(cnt % 2 == 0) {
            stack.add(notVisitedNode.get(0));
            visited[notVisitedNode.get(0)] = true;
            System.out.print(notVisitedNode.get(0) + " ");
          }
          else {
            stack.add(notVisitedNode.get(cnt/2));
            visited[notVisitedNode.get(cnt/2)] = true;
            System.out.print(notVisitedNode.get(cnt/2) + " ");
          }
        }
      }
      // no have notVisited Child Node
      else stack.pop();

      // clear notVisitedNode List
      notVisitedNode.clear();
    }
  }
}
```

## 참고한 사이트

- [Java 정렬방법 Collections.sort()](https://wjheo.tistory.com/entry/Java-%EC%A0%95%EB%A0%AC%EB%B0%A9%EB%B2%95-Collectionssort)
- [[BOJ] 백준 1376 민식우선탐색](https://degurii.tistory.com/101)
  - 해당 풀이를 참고하였다기 보다는 세그먼트 트리를 사용한 정보를 보고 세그먼트 트리를 공부하고 다시 풀기로 결정했다.
# DFS와 BFS

## 문제 설명

그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오. 단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고, 더 이상 방문할 수 있는 점이 없는 경우 종료한다. 정점 번호는 1번부터 N번까지이다.

## 입력

첫째 줄에 정점의 개수 N(1 ≤ N ≤ 1,000), 간선의 개수 M(1 ≤ M ≤ 10,000), 탐색을 시작할 정점의 번호 V가 주어진다. 다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다. 어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다. 입력으로 주어지는 간선은 양방향이다.

## 출력

첫째 줄에 DFS를 수행한 결과를, 그 다음 줄에는 BFS를 수행한 결과를 출력한다. V부터 방문된 점을 순서대로 출력하면 된다.

## 예제

#### 입력 1

4 5 1
1 2
1 3
1 4
2 4
3 4

#### 출력 1
1 2 4 3
1 2 3 4

#### 입력 2

5 5 3
5 4
5 2
1 2
3 4
3 1

#### 출력 2 

3 1 2 5 4
3 1 4 2 5

#### 입력 3

1000 1 1000
999 1000

#### 출력 3

1000 999
1000 999

## 문제 정보

|시간 제한| 메모리 제한| 제출| 정답| 맞힌 사람| 정답 비율|
|---|---|---|---|---|---|
|2초| 128MB| 201004|	73963	|43897	|35.836%|

## 필요 개념

- scanf로 받은 문자를 배열로 만드는 법
- 백준 사이트에서 입력값 출력값 쓰는 법 
- 자바로 백준 풀 때의 팁 및 주의점
  - https://nahwasa.com/172
- Java BufferReader and BufferWriter
  - https://m.blog.naver.com/ka28/221850826909
  - https://jhnyang.tistory.com/92
- Java Scanner
  - https://st-lab.tistory.com/92
- 크기가 일정하지 않은 배열에 입력받는 값들을 넣어주는 법
- 이차원 배열을 입력받은 값으로 만들어 주려고 할 때 사용할 수 있는 방법 
- 해당 것들을 만족하기 위해서 배열안에 ArrayList를 넣어주는 방법을 채택
  - https://velog.io/@courage331/%EB%B0%B0%EC%97%B4%EC%95%88%EC%97%90-ArrayList-%EB%84%A3%EA%B8%B0
- ArrayList Sort
  - https://hianna.tistory.com/569
- DFS and BFS
  - https://velog.io/@smallcherry/Java-AlgorithmBFSAndDFS

## 오류 잡기

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
  static boolean[] visited;
  static ArrayList<Integer>[] lines;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s  = br.readLine();
    String[] input = s.split(" ");

    int N = Integer.parseInt(input[0]);
    int M = Integer.parseInt(input[1]);
    int V = Integer.parseInt(input[2]);

    lines = new ArrayList[N + 1];
    visited = new boolean[N+1];

    for(int i = 0; i < M; i++) {
      String trunk = br.readLine();
      String[] line = trunk.split(" ");
      int node1 = Integer.parseInt(line[0]);
      int node2 = Integer.parseInt(line[1]);

      System.out.println("node1 = " + node1 + " and node2 = " + node2);

      lines[node1].add(node2);
      lines[node2].add(node1);
    }
    for(int i = 0; i < N; i++) {
      if(!lines[i].isEmpty()) {
        Collections.sort(lines[i]);
      }
    }

    System.out.print(V + " ");
    visited[V] = true;
    dfs(V);

  }
  static void dfs(int node) {
    for(int num : lines[node]) {
      if(visited[num] == false) {
        System.out.print(num + " ");
        visited[num] = true;
        dfs(num);
      }
    }
  }
}

```

해당 코드로 ArrayList를 생성하고 값을 입력하니깐 **NullPointerException**오류가 나온다.

lines[node1]은 null이라서 함수 add를 사용할 수 없다는 것이다.

이유가 무엇일까?

**2차원 ArrayList의 경우 크기를 설정하고 나서 사용할거면 각각 Index마다 초기화를 시켜줘야 한다.**

[Java:ArrayList 2차원 배열 생성](https://devyoseph.tistory.com/148)

해당 페이지를 참고하자.

또한 여기서 만약 ```lines[node1] == null```을 해보면 true 일까? false 일까?

정답은 true이다.

## 나의 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  static boolean[] visited;
  static ArrayList<Integer>[] lines;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s  = br.readLine();
    String[] input = s.split(" ");

    int N = Integer.parseInt(input[0]);
    int M = Integer.parseInt(input[1]);
    int V = Integer.parseInt(input[2]);

    lines = new ArrayList[N + 1];
    visited = new boolean[N+1];

    for(int i = 0; i < M; i++) {
      String trunk = br.readLine();
      String[] line = trunk.split(" ");
      int node1 = Integer.parseInt(line[0]);
      int node2 = Integer.parseInt(line[1]);

      if(lines[node1] == null) {
        lines[node1] = new ArrayList<Integer>();
      }

      if(lines[node2] == null) {
        lines[node2] = new ArrayList<Integer>();
      }

      lines[node1].add(node2);
      lines[node2].add(node1);
    }
    for(int i = 1; i <= N; i++) {
      if(lines[i] != null) {
        Collections.sort(lines[i]);
      }
    }

    dfs(V);

    System.out.println();

    Arrays.fill(visited, false);

    bfs(V);

  }
  static void dfs(int node) {

    if(node >= lines.length || lines[node] == null) {
      return;
    }

    visited[node] = true;
    System.out.print(node + " ");

    for(int num : lines[node]) {
      if(visited[num] == false) {
        dfs(num);
      }
    }
  }
  static  void bfs(int node) {
    if(node >= lines.length || lines[node] == null) {
      return;
    }

    LinkedList<Integer> queue = new LinkedList<>();

    visited[node] = true;
    queue.add(node);

    while(queue.size() != 0) {
      int num = queue.poll();
      System.out.print(num);

      for(int n : lines[num]) {
        if(visited[n] == false) {
          visited[n] = true;
          queue.add(n);
        }
      }
      if(queue.size() != 0) {
        System.out.print(" ");
      }
    }
  }
}

```

**결과**

틀림

반례:

5 4 1

2 3

3 4

4 5

5 2

[1]

[1]

처음 시작하는 node에서 갈 곳이 없을 때 초기화가 되어 있지 않아서 null값일 것이다.

그래서 예외 처리를 해준 것인데 사실 처음 node에서 갈 곳이 없으면 아무것도 안출력하는 것이 아닌 해당 node라도 출력을 해줘야한다.

즉 ```visited[num] == false``` 이것을 만족한다는 것은 곧 node에 연결된 간선이 없다는 뜻이므로 해당 node만 출력해주면 되는 것이다.

**최종 코드**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  static boolean[] visited;
  static ArrayList<Integer>[] lines;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s  = br.readLine();
    String[] input = s.split(" ");

    int N = Integer.parseInt(input[0]);
    int M = Integer.parseInt(input[1]);
    int V = Integer.parseInt(input[2]);

    lines = new ArrayList[N + 1];
    visited = new boolean[N+1];

    for(int i = 0; i < M; i++) {
      String trunk = br.readLine();
      String[] line = trunk.split(" ");
      int node1 = Integer.parseInt(line[0]);
      int node2 = Integer.parseInt(line[1]);

      if(lines[node1] == null) {
        lines[node1] = new ArrayList<Integer>();
      }

      if(lines[node2] == null) {
        lines[node2] = new ArrayList<Integer>();
      }

      lines[node1].add(node2);
      lines[node2].add(node1);
    }
    for(int i = 1; i <= N; i++) {
      if(lines[i] != null) {
        Collections.sort(lines[i]);
      }
    }

    dfs(V);

    System.out.println();

    Arrays.fill(visited, false);

    bfs(V);

  }
  static void dfs(int node) {

    if(lines[node] == null) {
      System.out.print(node);
      return;
    }

    visited[node] = true;
    System.out.print(node + " ");

    for(int num : lines[node]) {
      if(visited[num] == false) {
        dfs(num);
      }
    }
  }
  static  void bfs(int node) {
    if(lines[node] == null) {
      System.out.print(node);
      return;
    }

    LinkedList<Integer> queue = new LinkedList<>();

    visited[node] = true;
    queue.add(node);

    while(queue.size() != 0) {
      int num = queue.poll();
      System.out.print(num);

      for(int n : lines[num]) {
        if(visited[n] == false) {
          visited[n] = true;
          queue.add(n);
        }
      }
      if(queue.size() != 0) {
        System.out.print(" ");
      }
    }
  }
}
```

**두번째 최종 코드**
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  static boolean[] isVisited;
  static ArrayList<Integer>[] lines;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s = br.readLine();

    StringTokenizer st = new StringTokenizer(s);
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int V = Integer.parseInt(st.nextToken());

    lines = new ArrayList[N + 1];
    isVisited = new boolean[N + 1];

    for(int i = 0; i < M; i++) {
      String nums = br.readLine();
      StringTokenizer num = new StringTokenizer(nums);
      int node1 = Integer.parseInt(num.nextToken());
      int node2 = Integer.parseInt(num.nextToken());
      if(lines[node1] == null) {
        lines[node1] = new ArrayList<>();
      }
      if(lines[node2] == null) {
        lines[node2] = new ArrayList<>();
      }
      lines[node1].add(node2);
      lines[node2].add(node1);
    }

    for(int i = 1; i <= N; i++) {
      if(lines[i] != null) Collections.sort(lines[i]);
    }

    dfs(V);

    System.out.println();

    Arrays.fill(isVisited, false);

    bfs(V);
  }

  static void dfs(int node) {

    if(lines[node] == null) {
      System.out.print(node);
      return;
    }

    isVisited[node] = true;
    System.out.print(node + " ");

    for(int num : lines[node]) {
      if(isVisited[num] == false) {
        dfs(num);
      }
    }
  }

  static void bfs(int node) {

    if(lines[node] == null) {
      System.out.print(node);
      return;
    }

    LinkedList<Integer> queue = new LinkedList<>();

    queue.add(node);
    isVisited[node] = true;

    while (queue.size() != 0) {
      int num = queue.poll();
      System.out.print(num + " ");

      for(int i : lines[num]) {
        if(isVisited[i] == false) {
          isVisited[i] = true;
          queue.add(i);
        }
      }
    }
  }
}
```

참고로 Stack을 이용해서 dfs 와 bfs를 구현할수도 있다.

```java
  static void dfs(int node) {

    isVisited[node] = true;
    System.out.print(node + " ");

    Deque<Integer> stack = new LinkedList<>();
    stack.push(node);

    while(!stack.isEmpty()) {
      int now = stack.peek();

      boolean hasNearNode = false;
      for(int i : lines[now]) {
        if(!isVisited[i]) {
          hasNearNode = true;
          stack.push(i);
          isVisited[i] = true;
          System.out.println(i + " ");
          break;
        }
      }
      if(hasNearNode == false) {
        stack.pop();
      }
    }
  }

  static void bfs(int node) {

    Queue<Integer> queue = new LinkedList<>();
    queue.add(node);
    
    isVisited[node] = true;
    
    while (!queue.isEmpty()) {
      int v = queue.poll();
      System.out.println(v + " ");
      
      for(int i : lines[v]) {
        if(!isVisited[v]) {
          isVisited[v] = true;
          queue.add(v);
        }
      }
    }
  }
```
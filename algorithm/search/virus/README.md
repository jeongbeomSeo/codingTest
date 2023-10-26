# 바이러스

|시간 제한	|메모리 제한|	제출	|정답	맞힌 사람|	정답 비율|
|---|---|---|---|---|
|1 초|	128 MB|	118012|	56748	38213|	46.186%|

## 문제

신종 바이러스인 웜 바이러스는 네트워크를 통해 전파된다. 한 컴퓨터가 웜 바이러스에 걸리면 그 컴퓨터와 네트워크 상에서 연결되어 있는 모든 컴퓨터는 웜 바이러스에 걸리게 된다.

예를 들어 7대의 컴퓨터가 <그림 1>과 같이 네트워크 상에서 연결되어 있다고 하자. 1번 컴퓨터가 웜 바이러스에 걸리면 웜 바이러스는 2번과 5번 컴퓨터를 거쳐 3번과 6번 컴퓨터까지 전파되어 2, 3, 5, 6 네 대의 컴퓨터는 웜 바이러스에 걸리게 된다. 하지만 4번과 7번 컴퓨터는 1번 컴퓨터와 네트워크상에서 연결되어 있지 않기 때문에 영향을 받지 않는다.

![virus](./virus.png)
<그림1>

어느 날 1번 컴퓨터가 웜 바이러스에 걸렸다. 컴퓨터의 수와 네트워크 상에서 서로 연결되어 있는 정보가 주어질 때, 1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수를 출력하는 프로그램을 작성하시오.

## 입력

첫째 줄에는 컴퓨터의 수가 주어진다. 컴퓨터의 수는 100 이하이고 각 컴퓨터에는 1번 부터 차례대로 번호가 매겨진다. 둘째 줄에는 네트워크 상에서 직접 연결되어 있는 컴퓨터 쌍의 수가 주어진다. 이어서 그 수만큼 한 줄에 한 쌍씩 네트워크 상에서 직접 연결되어 있는 컴퓨터의 번호 쌍이 주어진다.

## 출력

1번 컴퓨터가 웜 바이러스에 걸렸을 때, 1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수를 첫째 줄에 출력한다.

## 예제 입력 1

```
7
6
1 2
2 3
1 5
5 2
5 6
4 7
```

## 예제 출력 1

```
4
```

## 나의 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    // 컴퓨터 수
    int n = Integer.parseInt(br.readLine());
    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    for(int i = 0; i < n + 1; i++) {
      graph.add(new ArrayList<>());
    }
    boolean[] isVisited = new boolean[n + 1];

    // 간선 수
    int m = Integer.parseInt(br.readLine());

    for(int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());

      graph.get(n1).add(n2);
      graph.get(n2).add(n1);
    }

    int count = bfs(graph, isVisited);
    System.out.println(count);

  }
  static int bfs(ArrayList<ArrayList<Integer>> graph, boolean[] isVisited) {
    Stack<Integer> stack = new Stack<>();

    int count = 0;

    stack.add(1);
    isVisited[1] = true;


    while (!stack.isEmpty()) {
      int com = stack.pop();

      for(int i = 0; i < graph.get(com).size(); i++) {
        int adjCom = graph.get(com).get(i);
        if(!isVisited[adjCom]) {
          stack.add(adjCom);
          isVisited[adjCom] = true;
          count++;
        }
      }
    }
    return count;
  }
}

```
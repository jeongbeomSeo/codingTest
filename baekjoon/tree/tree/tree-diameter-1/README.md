# 트리의 지름 

**골드 4**

|시간 제한	|메모리 제한	|제출	|정답|	맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초|	128 MB|	34663|	13642|	10305|	41.589%|

## 문제 

트리(tree)는 사이클이 없는 무방향 그래프이다. 트리에서는 어떤 두 노드를 선택해도 둘 사이에 경로가 항상 하나만 존재하게 된다. 트리에서 어떤 두 노드를 선택해서 양쪽으로 쫙 당길 때, 가장 길게 늘어나는 경우가 있을 것이다. 이럴 때 트리의 모든 노드들은 이 두 노드를 지름의 끝 점으로 하는 원 안에 들어가게 된다.

![tree diameter 1](./tree-diameter1.png)

이런 두 노드 사이의 경로의 길이를 트리의 지름이라고 한다. 정확히 정의하자면 트리에 존재하는 모든 경로들 중에서 가장 긴 것의 길이를 말한다.

입력으로 루트가 있는 트리를 가중치가 있는 간선들로 줄 때, 트리의 지름을 구해서 출력하는 프로그램을 작성하시오. 아래와 같은 트리가 주어진다면 트리의 지름은 45가 된다.

![tree diameter 2](./tree-diameter2.png)

트리의 노드는 1부터 n까지 번호가 매겨져 있다.

## 입력 

파일의 첫 번째 줄은 노드의 개수 n(1 ≤ n ≤ 10,000)이다. 둘째 줄부터 n-1개의 줄에 각 간선에 대한 정보가 들어온다. 간선에 대한 정보는 세 개의 정수로 이루어져 있다. 첫 번째 정수는 간선이 연결하는 두 노드 중 부모 노드의 번호를 나타내고, 두 번째 정수는 자식 노드를, 세 번째 정수는 간선의 가중치를 나타낸다. 간선에 대한 정보는 부모 노드의 번호가 작은 것이 먼저 입력되고, 부모 노드의 번호가 같으면 자식 노드의 번호가 작은 것이 먼저 입력된다. 루트 노드의 번호는 항상 1이라고 가정하며, 간선의 가중치는 100보다 크지 않은 양의 정수이다.

## 출력 

첫째 줄에 트리의 지름을 출력한다.

## 예제 입력 1

```
12
1 2 3
1 3 2
2 4 5
3 5 11
3 6 9
4 7 1
4 8 7
5 9 15
5 10 4
6 11 6
6 12 10
```

## 예제 출력 1

```
45
```

## 풀이 방식 

해당 문제에서 주의깊게 본것은 지름이 어떻게 형성되냐에 주목하였다. 

문제에서도 나와 있지만, 트리에 존재하는 모든 경로들중에서 가장 긴 것의 길이를 말한다. 

일단 기본적으로 선택한 경로가 가장 긴 경로가 되기 위해서 가장 기본적인 조건이 무엇이 있을까?

그것은 **선택한 두 노드가 무조건 리프 노드여야만 한다는 것**입니다. 

리프 노드가 아닌 다른 노드를 선택 한다면 이어질 간선이 존재하기 때문입니다.

또한 **지름은 루트 노드가 두 개 이상 서브 트리내에서 이루어 집니다.**

그래서 필자는 다음 방식을 채택했습니다.

입력 받을 때 부모노드 자식노드 가중치 순으로 입력 받으니깐, 미리 노드의 크기만큼 크기를 갖는 int형 배열을 준비해 놓고 시작합니다.

1. 자식 노드를 입력 받을 때 해당 노드의 번호를 Index 값으로 갖는 배열의 요소에 +1을 해줍니다.
2. 해당 배열에서 요소의 Value 값이 0인 것은 리프 노드에 해당합니다. 
3. 반복문을 통해서 리프 노드를 발견 시 탐색에 들어 갑니다. 

해당 방식은 사용 X 탐색에 있어서 불필요한 중복 계산이 너무 많다.

다른 방식을 사용 하려고 한다. 

1. 루트 노드 부터 시작 해서 LCost, RCost 를 구해서 ArrayList에 추가해줍니다.
2. 이후 자식 노드로 하나씩 확인해 가며 자식이 두 개 이상인 경우에 위의 방식을 반복해줍니다.
   1. 이때 주의할 점은 해당 노드를 루트 노드로 가정하고 서브 트리의 지름을 확인하는 것이기 때문에 해당 노드(서브 트리의 루트 노드)에서는 부모 노드로 올라가면 안됩니다.
> 해당 방식에서 필요한 것은 각 노드마다 자식 노드가 몇개인지 확인 할 수 있어야 한다. -> graph에서 확인 가능
> 
> 탐색 방식: DFS

## 코드 

**WA**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;

      int N = Integer.parseInt(br.readLine());

      ArrayList<ArrayList<Node>> garph = new ArrayList<>();

      for (int i = 0; i < N + 1; i++) {
         garph.add(new ArrayList<>());
      }

      for (int i = 0; i < N -1; i++) {
         st = new StringTokenizer(br.readLine());
         int n1 = Integer.parseInt(st.nextToken());
         int n2 = Integer.parseInt(st.nextToken());
         int cost = Integer.parseInt(st.nextToken());

         // 무방향이라서 원래는 양쪽에 전부 넣어줘야 하지만, 풀이 방식에는 도움 되지 않기에 안 넣어줌.
         garph.get(n1).add(new Node(n2, cost));
      }

      ArrayList<Integer> diameters = new ArrayList<>();
      for (int i = 1; i < N + 1; i++) {
         if (garph.get(i).size() >= 2) {
            boolean[] isVisited = new boolean[N + 1];
            diameters.add(calcDiameter(garph, isVisited, i, 0, i));
         }
      }
      int max = 0;
      for (int i = 0; i < diameters.size(); i++) {
         if (max < diameters.get(i)) max = diameters.get(i);
      }

      System.out.println(max);
   }

   static int calcDiameter(ArrayList<ArrayList<Node>> graph, boolean[] isVisited, int nodeIdx, int cost, int root) {
      isVisited[nodeIdx] = true;

      if (graph.get(nodeIdx).size() == 0) {
         return cost;
      }
      if (graph.get(nodeIdx).size() == 1) {
         if (!isVisited[graph.get(nodeIdx).get(0).idx]) {
            cost = calcDiameter(graph, isVisited, graph.get(nodeIdx).get(0).idx, cost + graph.get(nodeIdx).get(0).cost, root);
         }
      }
      else {
         int max = 0;
         int secondMax = 0;
         for (Node childNode : graph.get(nodeIdx)) {
            if (!isVisited[childNode.idx]) {
               int distance = calcDiameter(graph, isVisited, childNode.idx, cost + childNode.cost, root);
               if(max <= distance) {
                  secondMax = max;
                  max = distance;
               }
               else if (secondMax <= distance) secondMax = distance;
            }
         }
         if (nodeIdx == root) {
            cost = (max + secondMax);
         }
         else {
            cost = max;
         }
      }
      return cost;
   }

}

class Node {
   int idx;
   int cost;

   Node(int idx, int cost) {
      this.idx = idx;
      this.cost = cost;
   }
}
```

**반례**

```
// 입력 
4
1 3 1
2 4 1
3 2 1

//출력 
3

//해당 코드 출력 
0
```

모든 노드에서 서브 트리가 존재하지 않을 수 있다. 그럴 경우 다음과 같은 반례가 문제가 된다. 

즉, **Skewed Binary Tree 인경우 문제가 발생**

## 나의 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    ArrayList<ArrayList<Node>> garph = new ArrayList<>();

    for (int i = 0; i < N + 1; i++) {
      garph.add(new ArrayList<>());
    }

    for (int i = 0; i < N -1; i++) {
      st = new StringTokenizer(br.readLine());
      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      // 무방향이라서 원래는 양쪽에 전부 넣어줘야 하지만, 풀이 방식에는 도움 되지 않기에 안 넣어줌.
      garph.get(n1).add(new Node(n2, cost));
    }

    ArrayList<Integer> diameters = new ArrayList<>();
    for (int i = 1; i < N + 1; i++) {
      int size = garph.get(i).size();
      if (i == 1 && garph.get(i).size() == 1 || garph.get(i).size() >= 2) {
        boolean[] isVisited = new boolean[N + 1];
        diameters.add(calcDiameter(garph, isVisited, i, 0, i));
      }
    }
    int max = 0;
    for (int i = 0; i < diameters.size(); i++) {
      if (max < diameters.get(i)) max = diameters.get(i);
    }

    System.out.println(max);
  }

  static int calcDiameter(ArrayList<ArrayList<Node>> graph, boolean[] isVisited, int nodeIdx, int cost, int root) {
    isVisited[nodeIdx] = true;

    if (graph.get(nodeIdx).size() == 0) {
      return cost;
    }
    if (graph.get(nodeIdx).size() == 1) {
      if (!isVisited[graph.get(nodeIdx).get(0).idx]) {
        cost = calcDiameter(graph, isVisited, graph.get(nodeIdx).get(0).idx, cost + graph.get(nodeIdx).get(0).cost, root);
      }
    }
    else {
      int max = 0;
      int secondMax = 0;
      for (Node childNode : graph.get(nodeIdx)) {
        if (!isVisited[childNode.idx]) {
          int distance = calcDiameter(graph, isVisited, childNode.idx, cost + childNode.cost, root);
          if(max <= distance) {
            secondMax = max;
            max = distance;
          }
          else if (secondMax <= distance) secondMax = distance;
        }
      }
      if (nodeIdx == root) {
        cost = (max + secondMax);
      }
      else {
        cost = max;
      }
    }
    return cost;
  }
}

class Node {
  int idx;
  int cost;

  Node(int idx, int cost) {
    this.idx = idx;
    this.cost = cost;
  }
}
```

한마디만 하자.

**이왜골4...?** 고민 엄청해서 풀었다.. 
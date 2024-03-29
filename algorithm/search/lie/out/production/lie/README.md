# 거짓말

**골드 4**

|시간 제한|	메모리 제한|	제출|	정답|	맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|2 초	|128 MB|	21712	|9474|	7342|	44.586%|

## 문제 

지민이는 파티에 가서 이야기 하는 것을 좋아한다. 파티에 갈 때마다, 지민이는 지민이가 가장 좋아하는 이야기를 한다. 지민이는 그 이야기를 말할 때, 있는 그대로 진실로 말하거나 엄청나게 과장해서 말한다. 당연히 과장해서 이야기하는 것이 훨씬 더 재미있기 때문에, 되도록이면 과장해서 이야기하려고 한다. 하지만, 지민이는 거짓말쟁이로 알려지기는 싫어한다. 문제는 몇몇 사람들은 그 이야기의 진실을 안다는 것이다. 따라서 이런 사람들이 파티에 왔을 때는, 지민이는 진실을 이야기할 수 밖에 없다. 당연히, 어떤 사람이 어떤 파티에서는 진실을 듣고, 또다른 파티에서는 과장된 이야기를 들었을 때도 지민이는 거짓말쟁이로 알려지게 된다. 지민이는 이런 일을 모두 피해야 한다.

사람의 수 N이 주어진다. 그리고 그 이야기의 진실을 아는 사람이 주어진다. 그리고 각 파티에 오는 사람들의 번호가 주어진다. 지민이는 모든 파티에 참가해야 한다. 이때, 지민이가 거짓말쟁이로 알려지지 않으면서, 과장된 이야기를 할 수 있는 파티 개수의 최댓값을 구하는 프로그램을 작성하시오.

## 입력 

첫째 줄에 사람의 수 N과 파티의 수 M이 주어진다.

둘째 줄에는 이야기의 진실을 아는 사람의 수와 번호가 주어진다. 진실을 아는 사람의 수가 먼저 주어지고 그 개수만큼 사람들의 번호가 주어진다. 사람들의 번호는 1부터 N까지의 수로 주어진다.

셋째 줄부터 M개의 줄에는 각 파티마다 오는 사람의 수와 번호가 같은 방식으로 주어진다.

N, M은 50 이하의 자연수이고, 진실을 아는 사람의 수는 0 이상 50 이하의 정수, 각 파티마다 오는 사람의 수는 1 이상 50 이하의 정수이다.

## 출력 

첫째 줄에 문제의 정답을 출력한다.

## 예제 입력

```
4 3
0
2 1 2
1 3
3 2 3 4
```

## 예제 출력 

```
3
```

## 입력 2

```
4 1
1 1
4 1 2 3 4
```

## 출력 2

```
0
```

## 입력 3

```
4 1
0
4 1 2 3 4
```

## 출력 3

```
1
```

## 입력 4

```
4 5
1 1
1 1
1 2
1 3
1 4
2 4 1
```

## 출력 4

```
2
```

## 코드 

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    int joker_number = Integer.parseInt(st.nextToken());
    if (joker_number == 0) {
      for (int i = 0; i < M; i++) br.readLine();
      System.out.println(M);
      return;
    }

    Queue<Integer> q = new LinkedList<>();
    boolean[] isVisited = new boolean[N + 1];

    for (int i = 0; i < joker_number; i++) {
      int num = Integer.parseInt(st.nextToken());
      q.add(num);
      isVisited[num] = true;
    }

    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      graph.add(new ArrayList<>());
    }

    ArrayList<ArrayList<Integer>> party = new ArrayList<>();
    for (int i = 0 ; i < M; i++) {
      party.add(new ArrayList<>());
    }
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int size = Integer.parseInt(st.nextToken());
      int[] arr = new int[size];
      for (int j = 0; j < size; j++) {
        arr[j] = Integer.parseInt(st.nextToken());
        party.get(i).add(arr[j]);
      }
      for (int j = 0; j < size - 1; j++) {
        for (int k = j + 1; k < size; k++) {
          graph.get(arr[j]).add(arr[k]);
          graph.get(arr[k]).add(arr[j]);
        }
      }
    }

    bfs(q, graph, isVisited);
    int count = 0;
    for (int i = 0; i < M; i++) {
      boolean possible = true;
      for (int j = 0; j < party.get(i).size(); j++) {
        int person = party.get(i).get(j);
        if (isVisited[person]) {
          possible = false;
          break;
        }
      }
      if (possible) count++;
    }
    System.out.println(count);
  }
  static void bfs(Queue<Integer> q, ArrayList<ArrayList<Integer>> graph, boolean[] isVisited) {

    while (!q.isEmpty()) {
      int curNode = q.poll();

      for (int nextNode : graph.get(curNode)) {
        if (!isVisited[nextNode]) {
          q.add(nextNode);
          isVisited[nextNode] = true;
        }
      }
    }
  }
}
```

해당 풀이는 **유니온 파인드 알고리즘**으로 푸는 문제로 모르는 개념이라 공부하고 다시 풀었습니다.

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[] parent = new int[N + 1];
    for (int i = 1; i <= N; i++) {
      parent[i] = i;
    }

    st = new StringTokenizer(br.readLine());
    int knowing_number = Integer.parseInt(st.nextToken());
    boolean[] know = new boolean[N + 1];
    for (int i = 0; i < knowing_number; i++) {
      know[Integer.parseInt(st.nextToken())] = true;
    }

    ArrayList<ArrayList<Integer>> party = new ArrayList<>();
    for (int i = 0; i < M; i++) {
      party.add(new ArrayList());
    }
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int size = Integer.parseInt(st.nextToken());
      int baseNumber = Integer.parseInt(st.nextToken());
      party.get(i).add(baseNumber);
      for (int j = 1; j < size; j++) {
        int num = Integer.parseInt(st.nextToken());
        union(parent, know, baseNumber, num);
        party.get(i).add(num);
      }
    }

    int count = 0;
    for (int i = 0; i < party.size(); i++) {
      int j;
      int size = party.get(i).size();
      for (j = 0; j < size; j++) {
        if (know[find(parent, party.get(i).get(j))]) break;
      }
      if (j == size) count++;
    }
    System.out.println(count);
  }
  static int find(int[] parent, int x) {
    if (parent[x] == x) return x;
    return parent[x] = find(parent, parent[x]);
  }
  static void union(int[] parent, boolean[] know, int x, int y) {
    x = find(parent, x);
    y = find(parent, y);

    if (x != y) {
      if (know[x]) parent[y] = x;
      else parent[x] = y;
    }
  }
}
```

**최종 코드(AC)**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[] parent = new int[N + 1];
    for (int i = 1; i <= N; i++) parent[i] = i;

    st = new StringTokenizer(br.readLine());
    int known = Integer.parseInt(st.nextToken());
    int[] knownList = new int[known];
    for (int i = 0; i < known; i++) {
      knownList[i] = Integer.parseInt(st.nextToken());
    }

    ArrayList<ArrayList<Integer>> party = new ArrayList<>();
    for (int i = 0; i < M; i++) party.add(new ArrayList<>());
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int size = Integer.parseInt(st.nextToken());

      for (int j = 0; j < size; j++) {
        int curNumber = Integer.parseInt(st.nextToken());

        party.get(i).add(curNumber);
        for (int k = j - 1; k >= 0; k--) {
          int otherNumber = party.get(i).get(k);

          union_merge(parent, curNumber, otherNumber);
        }
      }
    }

    int count = 0;
    for (int i = 0; i < M; i++) {
      boolean success = true;
      for (int j = 0; j < party.get(i).size(); j++) {
        int checkNumber = union_find(parent, party.get(i).get(j));
        for (int k = 0; k < known; k++) {
          if (checkNumber == union_find(parent, knownList[k])) {
            success = false;
          }
        }
        if (!success) break;
      }
      if (success) count++;
    }

    System.out.println(count);
  }
  static int union_find(int[] parent, int x) {
    if (parent[x] == x) return x;

    return parent[x] = union_find(parent, parent[x]);
  }
  static void union_merge(int[] parent, int x, int y) {
    x = union_find(parent, x);
    y = union_find(parent, y);

    if (x != y) parent[x] = y;
  }
}
```

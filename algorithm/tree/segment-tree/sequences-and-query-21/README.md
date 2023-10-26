# 수열과 쿼리 21

**플래티넘 4**

|시간 제한	|메모리 제한|	제출|	정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초	|512 MB	|4325	|2197|	1668|	49.806%|

## 문제 

길이가 N인 수열 A<sub>1</sub>, A<sub>2</sub>, ..., A<sub>N</sub>이 주어진다. 이때, 다음 쿼리를 수행하는 프로그램을 작성하시오.

- 1 i j k: A<sub>i</sub>, A<sub>i+1</sub>, ..., A<sub>j</sub>에 k를 더한다.
- 2 x: A<sub>x</sub> 를 출력한다.

## 입력 

첫째 줄에 수열의 크기 N (1 ≤ N ≤ 100,000)이 주어진다.

둘째 줄에는 A<sub>1</sub>, A<sub>2</sub>, ..., A<sub>N</sub>이 주어진다. (1 ≤ A<sub>i</sub> ≤ 1,000,000)

셋째 줄에는 쿼리의 개수 M (1 ≤ M ≤ 100,000)이 주어진다.

넷째 줄부터 M개의 줄에는 쿼리가 한 줄에 하나씩 주어진다. 1번 쿼리의 경우 1 ≤ i ≤ j ≤ N, -1,000,000 ≤ k ≤ 1,000,000 이고, 2번 쿼리의 경우 1 ≤ x ≤ N이다. 2번 쿼리는 하나 이상 주어진다.

## 출력 

2번 쿼리가 주어질 때마다 출력한다.

## 예제 입력 1

```
5
1 2 3 4 5
4
1 3 4 6
2 3
1 1 3 -2
2 3
```

## 예제 출력 1

```
9
7
```

## 코드

**WA(11%)**

```java
import java.io.*;
import java.util.StringTokenizer;

public class Main {
  static int DEFAULT_VALUE = 0;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int[] arr= new int[N + 1];

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i < N + 1; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    int h = (int)Math.ceil(Math.log(N) / Math.log(2)) + 1;
    int tree_size = 1 << h;

    int[] tree = new int[tree_size];

    init(arr, tree, 1, 1, N);

    int M = Integer.parseInt(br.readLine());

    while (M-- > 0) {
      st = new StringTokenizer(br.readLine());
      int what = Integer.parseInt(st.nextToken());

      switch (what) {
        case 1:
          int left = Integer.parseInt(st.nextToken());
          int right = Integer.parseInt(st.nextToken());
          int diff = Integer.parseInt(st.nextToken());

          update_tree(tree, 1, 1, N, left, right, diff);
          break;
        case 2:
          int target = Integer.parseInt(st.nextToken());
          int ans = query(tree, 1, 1, N, target);
          bw.write(ans+"\n");
      }
    }
    bw.flush();
    bw.close();
  }
  static void init(int[] arr, int[] tree, int node, int nodeLeft, int nodeRight) {
    if (nodeLeft == nodeRight) {
      tree[node] = arr[nodeLeft];
    } else {
      init(arr, tree, node * 2, nodeLeft, (nodeLeft + nodeRight) / 2);
      init(arr, tree, node * 2 + 1, (nodeLeft + nodeRight) / 2 + 1, nodeRight);
    }
  }

  static void update_laze(int[] tree, int node, int nodeLeft, int nodeRight) {
    if (tree[node] != 0) {
      if (nodeLeft != nodeRight) {
        tree[node * 2] += tree[node];
        tree[node * 2 + 1] += tree[node];
      }
      tree[node] = 0;
    }
  }

  static void update_tree(int[] tree, int node, int nodeLeft, int nodeRight, int left, int right, int diff) {
    if(nodeLeft != nodeRight) {
      update_laze(tree, node, nodeLeft, nodeRight);
    }

    if (right < nodeLeft || nodeRight < left)
      return;

    if (left <= nodeLeft && nodeRight <= right) {
      if (nodeRight != nodeLeft) {
        tree[node * 2] += diff;
        tree[node * 2 + 1] += diff;
      }
      else{
        tree[node] += diff;
      }
      return;
    }

    update_tree(tree, node * 2, nodeLeft, (nodeLeft + nodeRight) / 2, left, right, diff);
    update_tree(tree, node * 2 + 1, (nodeLeft + nodeRight) / 2 + 1, nodeRight, left, right, diff);
  }

  static int query(int[] tree, int node, int nodeLeft, int nodeRight, int target) {
    if(nodeLeft != nodeRight) {
      update_laze(tree, node, nodeLeft, nodeRight);
    }

    if (target < nodeLeft || nodeRight < target) {
      return DEFAULT_VALUE;
    }

    if (nodeLeft == nodeRight) {
      return tree[node];
    }

    int lValue = query(tree, node * 2, nodeLeft, (nodeLeft + nodeRight) / 2, target);
    int rValue = query(tree, node * 2 + 1, (nodeLeft + nodeRight) / 2 + 1, nodeRight, target);

    if (lValue != 0) return lValue;
    return rValue;
  }
}
```

**WA(11%)**

```java
import java.io.*;
import java.util.StringTokenizer;

public class Main {
  static int DEFAULT_VALUE = 0;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int[] arr= new int[N + 1];

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i < N + 1; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    int h = (int)Math.ceil(Math.log(N) / Math.log(2)) + 1;
    int tree_size = 1 << h;

    int[] tree = new int[tree_size];
    int[] lazy = new int[tree_size];

    init(arr, tree, 1, 1, N);

    int M = Integer.parseInt(br.readLine());

    while (M-- > 0) {
      st = new StringTokenizer(br.readLine());
      int what = Integer.parseInt(st.nextToken());

      switch (what) {
        case 1:
          int left = Integer.parseInt(st.nextToken());
          int right = Integer.parseInt(st.nextToken());
          int diff = Integer.parseInt(st.nextToken());

          update_tree(tree, lazy, 1, 1, N, left, right, diff);
          break;
        case 2:
          int target = Integer.parseInt(st.nextToken());
          int ans = query(tree, lazy, 1, 1, N, target);
          bw.write(ans+"\n");
      }
    }
    bw.flush();
    bw.close();
  }
  static void init(int[] arr, int[] tree, int node, int nodeLeft, int nodeRight) {
    if (nodeLeft == nodeRight) {
      tree[node] = arr[nodeLeft];
    } else {
      init(arr, tree, node * 2, nodeLeft, (nodeLeft + nodeRight) / 2);
      init(arr, tree, node * 2 + 1, (nodeLeft + nodeRight) / 2 + 1, nodeRight);
    }
  }

  static void update_laze(int[] tree, int[] lazy, int node, int nodeLeft, int nodeRight) {
    if (lazy[node] != 0) {
      tree[node] += lazy[node];
      if (nodeLeft != nodeRight) {
        lazy[node * 2] += lazy[node];
        lazy[node * 2 + 1] += lazy[node];
      }
      lazy[node] = 0;
    }
  }

  static void update_tree(int[] tree, int[] lazy, int node, int nodeLeft, int nodeRight, int left, int right, int diff) {
    update_laze(tree, lazy, node, nodeLeft, nodeRight);

    if (right < nodeLeft || nodeRight < left)
      return;

    if (left <= nodeLeft && nodeRight <= right) {
      tree[node] += diff;
      if (nodeRight != nodeLeft) {
        lazy[node * 2] += diff;
        lazy[node * 2 + 1] += diff;
      }
      return;
    }

    update_tree(tree, lazy, node * 2, nodeLeft, (nodeLeft + nodeRight) / 2, left, right, diff);
    update_tree(tree, lazy, node * 2 + 1, (nodeLeft + nodeRight) / 2 + 1, nodeRight, left, right, diff);
  }

  static int query(int[] tree, int[] lazy, int node, int nodeLeft, int nodeRight, int target) {
    update_laze(tree, lazy, node, nodeLeft, nodeRight);

    if (target < nodeLeft || nodeRight < target) {
      return DEFAULT_VALUE;
    }

    if (nodeLeft == nodeRight) {
      return tree[node];
    }

    int lValue = query(tree, lazy, node * 2, nodeLeft, (nodeLeft + nodeRight) / 2, target);
    int rValue = query(tree, lazy, node * 2 + 1, (nodeLeft + nodeRight) / 2 + 1, nodeRight, target);

    if (lValue != 0) return lValue;
    return rValue;
  }
}
```
문제에 2번 쿼리는 하나 이상 주어진다고 써있다.

또한 long형을 사용해야 했다. 범위값에 주의 

**AC**

```java
import java.io.*;
import java.util.StringTokenizer;

public class Main {
  static int DEFAULT_VALUE = 0;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int[] arr= new int[N + 1];

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i < N + 1; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    int h = (int)Math.ceil(Math.log(N) / Math.log(2)) + 1;
    int tree_size = 1 << h;

    long[] tree = new long[tree_size];
    long[] lazy = new long[tree_size];

    init(arr, tree, 1, 1, N);

    int M = Integer.parseInt(br.readLine());

    while (M-- > 0) {
      st = new StringTokenizer(br.readLine());
      int what = Integer.parseInt(st.nextToken());

      switch (what) {
        case 1:
          int left = Integer.parseInt(st.nextToken());
          int right = Integer.parseInt(st.nextToken());
          int diff = Integer.parseInt(st.nextToken());
          update_tree(tree, lazy, 1, 1, N, left, right, diff);
          break;
        case 2:
          while (st.hasMoreTokens()) {
            int target = Integer.parseInt(st.nextToken());
            long ans = query(tree, lazy, 1, 1, N, target);
            bw.write(ans+"\n");
          }
          break;
      }
    }
    bw.flush();
    bw.close();
  }
  static void init(int[] arr, long[] tree, int node, int nodeLeft, int nodeRight) {
    if (nodeLeft == nodeRight) {
      tree[node] = arr[nodeLeft];
    } else {
      init(arr, tree, node * 2, nodeLeft, (nodeLeft + nodeRight) / 2);
      init(arr, tree, node * 2 + 1, (nodeLeft + nodeRight) / 2 + 1, nodeRight);
    }
  }

  static void update_laze(long[] tree, long[] lazy, int node, int nodeLeft, int nodeRight) {
    if (lazy[node] != 0) {
      tree[node] += lazy[node];
      if (nodeLeft != nodeRight) {
        lazy[node * 2] += lazy[node];
        lazy[node * 2 + 1] += lazy[node];
      }
      lazy[node] = 0;
    }
  }

  static void update_tree(long[] tree, long[] lazy, int node, int nodeLeft, int nodeRight, int left, int right, int diff) {
    update_laze(tree, lazy, node, nodeLeft, nodeRight);

    if (right < nodeLeft || nodeRight < left)
      return;

    if (left <= nodeLeft && nodeRight <= right) {
      tree[node] += diff;
      if (nodeRight != nodeLeft) {
        lazy[node * 2] += diff;
        lazy[node * 2 + 1] += diff;
      }
      return;
    }

    update_tree(tree, lazy, node * 2, nodeLeft, (nodeLeft + nodeRight) / 2, left, right, diff);
    update_tree(tree, lazy, node * 2 + 1, (nodeLeft + nodeRight) / 2 + 1, nodeRight, left, right, diff);
  }

  static long query(long[] tree, long[] lazy, int node, int nodeLeft, int nodeRight, int target) {
    update_laze(tree, lazy, node, nodeLeft, nodeRight);

    if (target < nodeLeft || nodeRight < target) {
      return DEFAULT_VALUE;
    }

    if (nodeLeft == nodeRight) {
      return tree[node];
    }

    long lValue = query(tree, lazy, node * 2, nodeLeft, (nodeLeft + nodeRight) / 2, target);
    long rValue = query(tree, lazy, node * 2 + 1, (nodeLeft + nodeRight) / 2 + 1, nodeRight, target);

    if (lValue != 0) return lValue;
    return rValue;
  }
}
```

**AC**

```java
import java.io.*;
import java.util.StringTokenizer;

public class Main {
  static int DEFAULT_VALUE = 0;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int[] arr= new int[N + 1];

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i < N + 1; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    int h = (int)Math.ceil(Math.log(N) / Math.log(2)) + 1;
    int tree_size = 1 << h;

    long[] tree = new long[tree_size];

    init(arr, tree, 1, 1, N);

    int M = Integer.parseInt(br.readLine());

    while (M-- > 0) {
      st = new StringTokenizer(br.readLine());
      int what = Integer.parseInt(st.nextToken());

      switch (what) {
        case 1:
          int left = Integer.parseInt(st.nextToken());
          int right = Integer.parseInt(st.nextToken());
          int diff = Integer.parseInt(st.nextToken());
          update_tree(tree, 1, 1, N, left, right, diff);
          break;
        case 2:
          while (st.hasMoreTokens()) {
            int target = Integer.parseInt(st.nextToken());
            long ans = query(tree, 1, 1, N, target);
            bw.write(ans+"\n");
          }
          break;
      }
    }
    bw.flush();
    bw.close();
  }
  static void init(int[] arr, long[] tree, int node, int nodeLeft, int nodeRight) {
    if (nodeLeft == nodeRight) {
      tree[node] = arr[nodeLeft];
    } else {
      init(arr, tree, node * 2, nodeLeft, (nodeLeft + nodeRight) / 2);
      init(arr, tree, node * 2 + 1, (nodeLeft + nodeRight) / 2 + 1, nodeRight);
    }
  }

  static void update_laze(long[] tree, int node, int nodeLeft, int nodeRight) {
    if (tree[node] != 0) {
      if (nodeLeft != nodeRight) {
        tree[node * 2] += tree[node];
        tree[node * 2 + 1] += tree[node];
      }
      tree[node] = 0;
    }
  }

  static void update_tree(long[] tree, int node, int nodeLeft, int nodeRight, int left, int right, int diff) {
    if(nodeLeft != nodeRight) {
      update_laze(tree, node, nodeLeft, nodeRight);
    }

    if (right < nodeLeft || nodeRight < left)
      return;

    if (left <= nodeLeft && nodeRight <= right) {
      if (nodeRight != nodeLeft) {
        tree[node * 2] += diff;
        tree[node * 2 + 1] += diff;
      }
      else{
        tree[node] += diff;
      }
      return;
    }

    update_tree(tree, node * 2, nodeLeft, (nodeLeft + nodeRight) / 2, left, right, diff);
    update_tree(tree, node * 2 + 1, (nodeLeft + nodeRight) / 2 + 1, nodeRight, left, right, diff);
  }

  static long query(long[] tree, int node, int nodeLeft, int nodeRight, int target) {
    if(nodeLeft != nodeRight) {
      update_laze(tree, node, nodeLeft, nodeRight);
    }

    if (target < nodeLeft || nodeRight < target) {
      return DEFAULT_VALUE;
    }

    if (nodeLeft == nodeRight) {
      return tree[node];
    }

    long lValue = query(tree, node * 2, nodeLeft, (nodeLeft + nodeRight) / 2, target);
    long rValue = query(tree, node * 2 + 1, (nodeLeft + nodeRight) / 2 + 1, nodeRight, target);

    if (lValue != 0) return lValue;
    return rValue;
  }
}
```

| 사용 방식     |메모리|시간|
|-----------|---|---|
| lazy 사용시  |74726KB|892ms|
| lazy 미사용시 |77920KB|916ms|

솔직히 생각보다 큰 차이가 없다. 그냥 lazy를 사용해서 푸는 것이 더 직관적이고 풀기 쉬워 보인다.
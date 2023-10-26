# 구간 합 구하기 2

**플래티넘 4**

|시간 제한	|메모리 제한|	제출|	정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초|	256 MB|	20051	|5078	|2720|	28.192%|

## 문제 

어떤 N개의 수가 주어져 있다. 그런데 중간에 수의 변경이 빈번히 일어나고 그 중간에 어떤 부분의 합을 구하려 한다. 만약에 1,2,3,4,5 라는 수가 있고, 3번째부터 4번째 수에 6을 더하면 1, 2, 9, 10, 5가 되고, 여기서 2번째부터 5번째까지 합을 구하라고 한다면 26을 출력하면 되는 것이다. 그리고 그 상태에서 1번째부터 3번째 수에 2를 빼고 2번째부터 5번째까지 합을 구하라고 한다면 22가 될 것이다.

## 입력 

첫째 줄에 수의 개수 N(1 ≤ N ≤ 1,000,000)과 M(1 ≤ M ≤ 10,000), K(1 ≤ K ≤ 10,000) 가 주어진다. M은 수의 변경이 일어나는 횟수이고, K는 구간의 합을 구하는 횟수이다. 그리고 둘째 줄부터 N+1번째 줄까지 N개의 수가 주어진다. 그리고 N+2번째 줄부터 N+M+K+1번째 줄까지 세 개의 정수 a, b, c 또는 a, b, c, d가 주어지는데, a가 1인 경우 b번째 수부터 c번째 수에 d를 더하고, a가 2인 경우에는 b번째 수부터 c번째 수의 합을 구하여 출력하면 된다.

입력으로 주어지는 모든 수는 -2<sup>63</sup>보다 크거나 같고, 2<sup>63</sup>-1보다 작거나 같은 정수이다.

## 출력 

첫째 줄부터 K줄에 걸쳐 구한 구간의 합을 출력한다. 단, 정답은 -2<sup>63</sup>보다 크거나 같고, 2<sup>63</sup>-1보다 작거나 같은 정수이다.

## 예제 입력 1

```
5 2 2
1
2
3
4
5
1 3 4 6
2 2 5
1 1 3 -2
2 2 5
```

## 예제 출력 1

```
26
22
```

## 코드

**AC**

```java
import java.io.*;
import java.util.StringTokenizer;

public class Main {
  static long DEFAULT_VALUE = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    long[] arr = new long[N + 1];

    for (int i = 1; i < N + 1; i++) {
      arr[i] = Long.parseLong(br.readLine());
    }

    int h = (int)Math.ceil(Math.log(N) / Math.log(2)) + 1;
    int tree_size = 1 << h;
    long[] tree = new long[tree_size];
    long[] lazy = new long[tree_size];

    init(arr, tree, 1, 1, N);

    while (M > 0 || K > 0) {
      st = new StringTokenizer(br.readLine());
      int what = Integer.parseInt(st.nextToken());
      int left = Integer.parseInt(st.nextToken());
      int right = Integer.parseInt(st.nextToken());
      if (left > right) {
        int temp = left;
        left = right;
        right = temp;
      }

      long diff = 0;
      if (what == 1) diff = Long.parseLong(st.nextToken());

      switch (what) {
        case 1:
          update_range(tree, lazy, 1, 1, N, left, right, diff);
          M--;
          break;
        case 2:
          long sum = query(tree, lazy, 1, 1, N, left, right);
          bw.write(sum+"\n");
          K--;
          break;
      }
    }
    bw.flush();
    bw.close();
   }
   static void init(long[] arr, long[] tree, int node, int nodeLeft, int nodeRight) {
      if (nodeLeft == nodeRight) {
        tree[node] = arr[nodeLeft];
      } else {
        init(arr, tree, node * 2, nodeLeft, (nodeLeft + nodeRight) / 2);
        init(arr, tree, node * 2 + 1, (nodeLeft + nodeRight) / 2 + 1, nodeRight);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
      }
   }

   static void update_laze(long[] tree, long[] lazy, int node, int nodeLeft, int nodeRight) {
      if (lazy[node] != 0) {
        tree[node] += (nodeRight - nodeLeft + 1) * lazy[node];
        if (nodeLeft != nodeRight) {
          lazy[node * 2] += lazy[node];
          lazy[node * 2 + 1] += lazy[node];
        }
        lazy[node] = 0;
      }
   }

  static void update_range(long[] tree, long[] lazy, int node, int nodeLeft, int nodeRight, int left, int right, long diff) {
    update_laze(tree, lazy, node, nodeLeft, nodeRight);
    if (right < nodeLeft || nodeRight < left) {
      return;
    }

    if (left <= nodeLeft && nodeRight <= right) {
      tree[node] += (nodeRight - nodeLeft + 1) * diff;
      if (nodeLeft != nodeRight) {
        lazy[node * 2] += diff;
        lazy[node * 2 + 1] += diff;
      }
      return;
    }

    update_range(tree, lazy, node * 2, nodeLeft, (nodeLeft + nodeRight) / 2, left, right, diff);
    update_range(tree, lazy, node * 2 + 1, (nodeLeft + nodeRight) / 2 + 1, nodeRight, left, right, diff);
    tree[node] = tree[node * 2] + tree[node * 2 + 1];
  }

  static long query(long[] tree, long[] lazy, int node, int nodeLeft, int nodeRight, int left, int right) {
    update_laze(tree, lazy, node, nodeLeft, nodeRight);

    if (right < nodeLeft || nodeRight < left) {
      return DEFAULT_VALUE;
    }

    if (left <= nodeLeft && nodeRight <= right) {
      return tree[node];
    }

    long lSum = query(tree, lazy, node * 2, nodeLeft, (nodeLeft + nodeRight) / 2, left, right);
    long rSum = query(tree, lazy, node * 2 + 1, (nodeLeft + nodeRight) / 2 + 1, nodeRight, left, right);

    return lSum + rSum;
  }
}
```
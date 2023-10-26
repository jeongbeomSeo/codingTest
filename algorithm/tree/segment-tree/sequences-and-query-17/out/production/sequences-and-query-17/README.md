# 수열과 쿼리 17
**골드 1**

|시간 제한	|메모리 제한|	제출|	정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초|	512 MB	|4228|	2422|	1979	|60.335%|

## 문제

길이가 N인 수열 A1, A2, ..., AN이 주어진다. 이때, 다음 쿼리를 수행하는 프로그램을 작성하시오.

- 1 i v : Ai를 v로 바꾼다. (1 ≤ i ≤ N, 1 ≤ v ≤ 10<sup>9</sup>)
- 2 i j : Ai, Ai+1, ..., Aj에서 크기가 가장 작은 값을 출력한다. (1 ≤ i ≤ j ≤ N)
수열의 인덱스는 1부터 시작한다.

## 출력 

2번 쿼리에 대해서 정답을 한 줄에 하나씩 순서대로 출력한다.

## 예제 입력 1

```
5
5 4 3 2 1
6
2 1 3
2 1 4
1 5 3
2 3 5
1 4 3
2 3 5
```

## 예제 출력 1

```
3
2
2
3
```

## 코드

**TLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int INF = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    int[] nums = new int[N + 1];

    StringTokenizer st = new StringTokenizer(br.readLine());

    int idx = 1;
    while (st.hasMoreTokens()) {
      nums[idx++] = Integer.parseInt(st.nextToken());
    }

    int h = (int)Math.ceil(Math.log(N) / Math.log(2));
    int tree_size = 1 << (h + 1);

    int[] tree = new int[tree_size];

    init(nums, tree, 1, 1, N);
    int M = Integer.parseInt(br.readLine());

    while (M != 0) {
      st = new StringTokenizer(br.readLine());
      int cs = Integer.parseInt(st.nextToken());
      switch (cs) {
        case 1:
          int index = Integer.parseInt(st.nextToken());
          int val = Integer.parseInt(st.nextToken());
          update(tree, 1, index, val, 1, N);
          break;
        case 2:
          int left = Integer.parseInt(st.nextToken());
          int right = Integer.parseInt(st.nextToken());
          int min = query(tree, 1, 1, N, left, right, INF);
          System.out.println(min);
          break;
      }
      M--;
    }


  }

  static void init(int[] nums, int[] tree, int node, int start, int end) {
    if (start == end) {
      tree[node] = nums[start];
    }
    else {
      init(nums, tree, node * 2, start, (start + end) / 2);
      init(nums, tree, node * 2 + 1, (start + end) / 2 + 1, end);
      tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }
  }

  static void update(int[] tree, int node, int index, int val, int start, int end) {
    if (index < start || end < index) {
      return;
    }
    if (start == end) {
      tree[node] = val;
      return;
    }
    update(tree, node * 2, index, val, start, (start + end) / 2);
    update(tree, node * 2 + 1, index, val, (start + end) / 2 + 1, end);
    tree[node] = tree[node * 2] + tree[node * 2 + 1];
  }

  static int query(int[] tree, int node, int start, int end, int left, int right, int min) {
    if (right < start || end < left) {
      return INF;
    }
    if (start == end) {
      if (tree[node] < min) {
        return tree[node];
      }
    }
    int lQuery = query(tree, node * 2, start, (start + end) / 2, left, right, min);
    int rQuery = query(tree, node * 2 + 1, (start + end) / 2 + 1, end, left, right, min);
    int compareMin = Math.min(lQuery, rQuery);
    min = Math.min(min, compareMin);
    return min;
  }
}

```
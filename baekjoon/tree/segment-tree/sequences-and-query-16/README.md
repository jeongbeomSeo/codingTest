# 수열과 쿼리 16

**골드 1**

|시간 제한|	메모리 제한	|제출	|정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초|	512 MB|	4188|	2133|	1798|	51.995%|

## 문제 

길이가 N인 수열 A<sub>1</sub>, A<sub>2</sub>, ..., A<sub>N</sub>이 주어진다. 이때, 다음 쿼리를 수행하는 프로그램을 작성하시오.

- 1 i v : A<sub>i</sub>를 v로 바꾼다. (1 ≤ i ≤ N, 1 ≤ v ≤ 10<sup>9</sup>)
- 2 i j : A<sub>i</sub>, A<sub>i+1</sub>, ..., A<sub>j</sub>에서 크기가 가장 작은 값의 인덱스를 출력한다. 그러한 값이 여러개인 경우에는 인덱스가 작은 것을 출력한다. (1 ≤ i ≤ j ≤ N, 1 ≤ v ≤ 10<sup>9</sup>)

수열의 인덱스는 1부터 시작한다.

## 입력 

첫째 줄에 수열의 크기 N이 주어진다. (1 ≤ N ≤ 100,000)

둘째 줄에는 A<sub>1</sub>, A<sub>2</sub>, ..., A<sub>N</sub>이 주어진다. (1 ≤ Ai ≤ 10<sup>9</sup>)

셋째 줄에는 쿼리의 개수 M이 주어진다. (1 ≤ M ≤ 100,000)

넷째 줄부터 M개의 줄에는 쿼리가 주어진다.

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
4
4
3
```

## 코드

**AC**

```java
import java.io.*;
import java.util.StringTokenizer;

public class Main {
  static int INF = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    int[] nums = new int[N + 1];
    st = new StringTokenizer(br.readLine());
    for (int i = 1; i < N + 1; i++) {
      nums[i] = Integer.parseInt(st.nextToken());
    }

    int h = (int)Math.ceil(Math.log(N) / Math.log(2));
    int tree_size = 1 << (h + 1);

    int[][] tree = new int[tree_size][2];

    int M = Integer.parseInt(br.readLine());

    init(nums, tree, 1, 1, N);

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int what = Integer.parseInt(st.nextToken());
      switch (what) {
        case 1:
          int idx = Integer.parseInt(st.nextToken());
          int val = Integer.parseInt(st.nextToken());
          update(tree, 1, idx, val, 1, N);
          break;
        case 2:
          int left = Integer.parseInt(st.nextToken());
          int right = Integer.parseInt(st.nextToken());
          bw.write(query(tree, 1, 1, N, left, right)[1] + "\n");
      }
    }
    bw.flush();
    bw.close();
  }
  static void init(int[] nums, int[][] tree, int node, int start, int end) {
    if (start == end) {
      tree[node][0] = nums[start];
      tree[node][1] = start;
    }
    else {
      init(nums, tree, node * 2, start, (start + end) / 2);
      init(nums, tree, node * 2 + 1, (start + end) / 2 + 1, end);
      calc(tree, node);
    }
  }
  static void update(int[][] tree, int node, int idx, int val, int start, int end){
    if (idx < start || end < idx) {
      return;
    }
    if (start == end) {
      tree[node][0] = val;
      return;
    }
    update(tree, node * 2, idx, val, start, (start + end) / 2);
    update(tree, node * 2 + 1, idx, val, (start + end) / 2 + 1, end);
    calc(tree, node);
  }

  static int[] query(int[][] tree, int node, int start, int end, int left, int right) {
    if (right < start || end < left) {
      return new int[]{INF, 0};
    }
    if (left <= start && end <= right) {
      return tree[node];
    }
    int[] lmin = query(tree, node * 2, start, (start + end) / 2, left, right);
    int[] rmin = query(tree, node * 2 + 1, (start + end) / 2 + 1, end, left, right);
    if (lmin[0] < rmin[0]) {
      return lmin;
    }
    else if(lmin[0] > rmin[0]) {
      return rmin;
    }
    else {
      if (lmin[1] < rmin[1]) return lmin;
      else return rmin;
    }
  }

  static void calc(int[][] tree, int node) {
    if (tree[node * 2][0] < tree[node * 2 + 1][0]) {
      tree[node][0] = tree[node * 2][0];
      tree[node][1] = tree[node * 2][1];
    } else if (tree[node * 2][0] > tree[node * 2 + 1][0]) {
      tree[node][0] = tree[node * 2 + 1][0];
      tree[node][1] = tree[node * 2 + 1][1];
    } else {
      if (tree[node * 2][1] < tree[node * 2 + 1][1]) {
        tree[node][0] = tree[node * 2][0];
        tree[node][1] = tree[node * 2][1];
      } else {
        tree[node][0] = tree[node * 2 + 1][0];
        tree[node][1] = tree[node * 2 + 1][1];
      }
    }
  }
}
```
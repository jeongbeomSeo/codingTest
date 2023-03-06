# 구간 곱 구하기

**골드 1**

|시간 제한	|메모리 제한	|제출	|정답|	맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|1 초	|256 MB	|19111	6515|	4770|	32.445%|

## 문제 

어떤 N개의 수가 주어져 있다. 그런데 중간에 수의 변경이 빈번히 일어나고 그 중간에 어떤 부분의 곱을 구하려 한다. 만약에 1, 2, 3, 4, 5 라는 수가 있고, 3번째 수를 6으로 바꾸고 2번째부터 5번째까지 곱을 구하라고 한다면 240을 출력하면 되는 것이다. 그리고 그 상태에서 다섯 번째 수를 2로 바꾸고 3번째부터 5번째까지 곱을 구하라고 한다면 48이 될 것이다.

## 입력 

첫째 줄에 수의 개수 N(1 ≤ N ≤ 1,000,000)과 M(1 ≤ M ≤ 10,000), K(1 ≤ K ≤ 10,000) 가 주어진다. M은 수의 변경이 일어나는 횟수이고, K는 구간의 곱을 구하는 횟수이다. 그리고 둘째 줄부터 N+1번째 줄까지 N개의 수가 주어진다. 그리고 N+2번째 줄부터 N+M+K+1 번째 줄까지 세 개의 정수 a,b,c가 주어지는데, a가 1인 경우 b번째 수를 c로 바꾸고 a가 2인 경우에는 b부터 c까지의 곱을 구하여 출력하면 된다.

입력으로 주어지는 모든 수는 0보다 크거나 같고, 1,000,000보다 작거나 같은 정수이다.

## 출력 

첫째 줄부터 K줄에 걸쳐 구한 구간의 곱을 1,000,000,007로 나눈 나머지를 출력한다.

## 예제 입력 1

```
5 2 2
1
2
3
4
5
1 3 6
2 2 5
1 5 2
2 3 5
```

## 예제 출력 1

```
240
48
```

## 예제 입력 2

```
5 2 2
1
2
3
4
5
1 3 0
2 2 5
1 3 6
2 2 5
```

## 예제 출력 2

```
0
240
```

## 코드 

**AC**

```java
import java.io.*;
import java.util.StringTokenizer;

public class Main {
  static int MOD = 1000000007;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    int[] nums = new int[N + 1];
    for (int i = 1; i < N + 1; i++) {
      nums[i] = Integer.parseInt(br.readLine());
    }

    int C = M + K;

    int h = (int)Math.ceil(Math.log(N) / Math.log(2));
    int tree_size = 1 << (h + 1);

    long[] tree = new long[tree_size];

    init(nums, tree, 1, 1, N);

    while (C != 0) {
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
          bw.write(query(tree, 1, 1, N, left, right)+ "\n");
      }
      C--;
    }
    bw.flush();
    bw.close();
  }
  static void init(int[] nums, long[] tree, int node, int start, int end) {
    if (start == end) {
      tree[node] = nums[start];
    }
    else {
      init(nums, tree, node * 2, start, (start + end) / 2);
      init(nums, tree, node * 2 + 1, (start + end) / 2 + 1, end);
      tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % MOD;
    }
  }

  static void update(long[] tree, int node, int idx, int val, int start, int end) {
    if (idx < start || end < idx) {
      return;
    }
    if (start == end) {
      tree[node] = val;
      return;
    }
    update(tree, node * 2, idx, val, start, (start + end) / 2);
    update(tree, node * 2 + 1, idx, val, (start + end) / 2 + 1, end);
    tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % MOD;
  }

  static long query(long[] tree, int node, int start, int end, int left, int right) {
    if (right < start || end < left) {
      return 1;
    }
    if (left <= start && end <= right) {
      return tree[node];
    }
    long lmul = query(tree, node * 2, start, (start + end) / 2, left, right);
    long rmul = query(tree, node * 2 + 1, (start + end) / 2 + 1, end, left, right);
    return (lmul * rmul) % MOD;
  }
}
```
# 최솟값 

**골드 1**

|시간 제한	|메모리 제한	|제출	|정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|1 초|	256 MB	|15963|	7667|	5482|	51.373%|

## 문제 

N(1 ≤ N ≤ 100,000)개의 정수들이 있을 때, a번째 정수부터 b번째 정수까지 중에서 제일 작은 정수를 찾는 것은 어려운 일이 아니다. 하지만 이와 같은 a, b의 쌍이 M(1 ≤ M ≤ 100,000)개 주어졌을 때는 어려운 문제가 된다. 이 문제를 해결해 보자.

여기서 a번째라는 것은 입력되는 순서로 a번째라는 이야기이다. 예를 들어 a=1, b=3이라면 입력된 순서대로 1번, 2번, 3번 정수 중에서 최솟값을 찾아야 한다. 각각의 정수들은 1이상 1,000,000,000이하의 값을 갖는다.

## 입력 

첫째 줄에 N, M이 주어진다. 다음 N개의 줄에는 N개의 정수가 주어진다. 다음 M개의 줄에는 a, b의 쌍이 주어진다.

## 출력 

M개의 줄에 입력받은 순서대로 각 a, b에 대한 답을 출력한다.

## 예제 입력 1

```
10 4
75
30
100
38
50
51
52
20
81
5
1 10
3 5
6 9
8 10
```

## 예제 출력 1

```
5
38
20
5
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
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[] nums = new int[N + 1];
    for (int i = 1; i < N + 1; i++) {
      nums[i] = Integer.parseInt(br.readLine());
    }

    int h = (int)Math.ceil(Math.log(N) / Math.log(2));
    int tree_size = 1 << (h + 1);

    int[] tree = new int[tree_size];

    init(nums, tree, 1, 1, N);

    for(int i = 0 ; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int left = Integer.parseInt(st.nextToken());
      int right = Integer.parseInt(st.nextToken());
      bw.write(query(tree, 1, 1, N, left, right) + "\n");
    }

    bw.flush();
    bw.close();
  }
  static void init(int[] nums, int[] tree, int node, int start, int end) {
    if (start == end) {
      tree[node] = nums[start];
    }
    else {
      init(nums, tree, node * 2, start, (start + end) / 2);
      init(nums, tree, node * 2 + 1, (start + end) / 2 + 1, end);
      tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
    }
  }

  static int query(int[] tree, int node, int start, int end, int left, int right) {
    if (right < start || end < left) {
      return INF;
    }
    if (left <= start && end <= right) {
      return tree[node];
    }
    int lmin = query(tree, node * 2, start, (start + end) / 2, left, right);
    int rmin = query(tree, node * 2 + 1, (start + end) / 2 + 1, end, left, right);
    return Math.min(lmin, rmin);
  }
}
```
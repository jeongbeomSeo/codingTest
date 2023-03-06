# 구간의 합 구하기

**골드 1**

|시간 제한|	메모리 제한	|제출|	정답|	맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초|	256 MB|	77530|	17523|	8936|	24.345%|

## 문제 

어떤 N개의 수가 주어져 있다. 그런데 중간에 수의 변경이 빈번히 일어나고 그 중간에 어떤 부분의 합을 구하려 한다. 만약에 1,2,3,4,5 라는 수가 있고, 3번째 수를 6으로 바꾸고 2번째부터 5번째까지 합을 구하라고 한다면 17을 출력하면 되는 것이다. 그리고 그 상태에서 다섯 번째 수를 2로 바꾸고 3번째부터 5번째까지 합을 구하라고 한다면 12가 될 것이다.

## 입력 

첫째 줄에 수의 개수 N(1 ≤ N ≤ 1,000,000)과 M(1 ≤ M ≤ 10,000), K(1 ≤ K ≤ 10,000) 가 주어진다. M은 수의 변경이 일어나는 횟수이고, K는 구간의 합을 구하는 횟수이다. 그리고 둘째 줄부터 N+1번째 줄까지 N개의 수가 주어진다. 그리고 N+2번째 줄부터 N+M+K+1번째 줄까지 세 개의 정수 a, b, c가 주어지는데, a가 1인 경우 b(1 ≤ b ≤ N)번째 수를 c로 바꾸고 a가 2인 경우에는 b(1 ≤ b ≤ N)번째 수부터 c(b ≤ c ≤ N)번째 수까지의 합을 구하여 출력하면 된다.

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
1 3 6
2 2 5
1 5 2
2 3 5
```

## 예제 출력 1

```
17
12
```

## 코드 

**RE(Runtime Error): Number Format**
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    long[] nums = new long[N + 1];
    for (int i = 1; i < N + 1; i++) {
      nums[i] = Integer.parseInt(br.readLine());
    }

    long[] tree = new long[2 * N];

    init(nums, tree, 1, 1, N);

    while (M != 0 || K != 0) {
      st = new StringTokenizer(br.readLine());
      int cs = Integer.parseInt(st.nextToken());
      switch (cs) {
        case 1:
          int index = Integer.parseInt(st.nextToken());
          long val = Integer.parseInt(st.nextToken());
          update(nums, tree, 1, index, val, 1, N);
          M--;
          break;
        case 2:
          int left = Integer.parseInt(st.nextToken());
          int right = Integer.parseInt(st.nextToken());
          long sum = query(tree, 1, 1, N, left, right);
          System.out.println(sum);
          K--;
          break;
      }
    }
  }

  static void init(long[] nums, long[] tree, int node, int start, int end) {
    if (start == end) {
      tree[node] = nums[start];
    }
    else {
      init(nums, tree, node * 2, start, (start + end) / 2);
      init(nums, tree, node * 2 + 1, (start + end) / 2 + 1, end);
      tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }
  }

  static void update(long[] nums, long[] tree, int node, int index, long val, int start, int end) {
    if (index < start || end < index) {
      return;
    }
    if (start == end) {
      nums[index] = val;
      tree[node] = val;
      return;
    }
    update(nums, tree, node * 2, index, val, start, (start + end) / 2);
    update(nums, tree, node * 2 + 1, index, val, (start + end) / 2 + 1, end);
    tree[node] = tree[node * 2] + tree[node * 2 + 1];
  }

  static long query(long[] tree, int node, int start, int end, int left, int right) {
    if (right < start || end < left) {
      return 0;
    }
    if (left <= start && end <= right) {
      return tree[node];
    }
    long lsum = query(tree, node * 2, start, (start + end) / 2, left, right);
    long rsum = query(tree, node * 2 + 1, (start + end) / 2 + 1, end, left, right);
    return lsum + rsum;
  }
}

```

입력 받은 데이터를 숫자로 바꿔줄 때 당연하지만 주의할 점은 **int형은 Integer.parseInt()** 를 사용하고 **long형은 Long.parseLong()** 을 사용해야 한다는 것입니다.

이 문제를 해결 하고 나서도 **ArrayIndexOutOfBounds**로 런타임 에러가 나왔다.

트리 사이즈를 잘 못 이해하고 있었다. 

크기가 N인 배열에서 트리의 크기를 결정할 때는 다음과 같은 과정을 거치면 됩니다.

1. 배열의 요소의 갯수가 N개일때 높이 h는 log<sub>2</sub>N이고, 만들어질 세그먼트 트리의 높이는 H = log<sub>2</sub>(2 * N) = h + 1입니다. 
2. 따라서 먼저 h를 구하고 그후 Size의 경우 2<sup>H</sup> = 2<sup>h + 1</sup>로 구하면 된다.

**코드** 
```java
1. int h = (int)Math.ceil(Math.log(N) / Math.log(2));

2. int tree_size = (1 << (h + 1));
```
이 과정이 귀찮다면 N * 4를 넣어도 되지만, 그렇다면 메모리 낭비가 더 심하게 발생한다. 

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    long[] nums = new long[N + 1];
    for (int i = 1; i < N + 1; i++) {
      nums[i] = Long.parseLong(br.readLine());
    }

    int h = (int)Math.ceil(Math.log(N) / Math.log(2));
    int tree_size = (1 << (h + 1));

    long[] tree = new long[tree_size];

    init(nums, tree, 1, 1, N);

    while (M != 0 || K != 0) {
      st = new StringTokenizer(br.readLine());
      int cs = Integer.parseInt(st.nextToken());
      switch (cs) {
        case 1:
          int index = Integer.parseInt(st.nextToken());
          long val = Long.parseLong(st.nextToken());
          update(nums, tree, 1, index, val, 1, N);
          M--;
          break;
        case 2:
          int left = Integer.parseInt(st.nextToken());
          int right = Integer.parseInt(st.nextToken());
          long sum = query(tree, 1, 1, N, left, right);
          System.out.println(sum);
          K--;
          break;
      }
    }
  }

  static void init(long[] nums, long[] tree, int node, int start, int end) {
    if (start == end) {
      tree[node] = nums[start];
    }
    else {
      init(nums, tree, node * 2, start, (start + end) / 2);
      init(nums, tree, node * 2 + 1, (start + end) / 2 + 1, end);
      tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }
  }

  static void update(long[] nums, long[] tree, int node, int index, long val, int start, int end) {
    if (index < start || end < index) {
      return;
    }
    if (start == end) {
      nums[index] = val;
      tree[node] = val;
      return;
    }
    update(nums, tree, node * 2, index, val, start, (start + end) / 2);
    update(nums, tree, node * 2 + 1, index, val, (start + end) / 2 + 1, end);
    tree[node] = tree[node * 2] + tree[node * 2 + 1];
  }

  static long query(long[] tree, int node, int start, int end, int left, int right) {
    if (right < start || end < left) {
      return 0;
    }
    if (left <= start && end <= right) {
      return tree[node];
    }
    long lsum = query(tree, node * 2, start, (start + end) / 2, left, right);
    long rsum = query(tree, node * 2 + 1, (start + end) / 2 + 1, end, left, right);
    return lsum + rsum;
  }
}
```
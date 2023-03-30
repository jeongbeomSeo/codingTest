# 요세푸스 문제 2

**플래티넘 3**

|시간 제한|	메모리 제한|	제출	|정답|	맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|0.15 초 (추가 시간 없음) (하단 참고)	|128 MB|	10551|	1529|	1095	|31.484%|

## 문제 

요세푸스 문제는 다음과 같다.

1번부터 N번까지 N명의 사람이 원을 이루면서 앉아있고, 양의 정수 K(≤ N)가 주어진다. 이제 순서대로 K번째 사람을 제거한다. 한 사람이 제거되면 남은 사람들로 이루어진 원을 따라 이 과정을 계속해 나간다. 이 과정은 N명의 사람이 모두 제거될 때까지 계속된다. 원에서 사람들이 제거되는 순서를 (N, K)-요세푸스 순열이라고 한다. 예를 들어 (7, 3)-요세푸스 순열은 <3, 6, 2, 7, 5, 1, 4>이다.

N과 K가 주어지면 (N, K)-요세푸스 순열을 구하는 프로그램을 작성하시오.

## 입력

첫째 줄에 N과 K가 빈 칸을 사이에 두고 순서대로 주어진다. (1 ≤ K ≤ N ≤ 100,000)

## 예제 입력 1

```
7 3
```

## 예제 출력 1

```
<3, 6, 2, 7, 5, 1, 4>
```

## 코드 

**AC**

```java
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    String[] input = br.readLine().split(" ");

    int N = Integer.parseInt(input[0]);
    int K = Integer.parseInt(input[1]);

    int h = (int)Math.ceil(Math.log(N) / Math.log(2));
    int tree_size = 1 << (h + 1);
    int[] tree = new int[tree_size];

    init(tree, 1, 1, N);

    int size = N;
    int num = K;
    bw.write("<");
    for (int i = 0; i < N; i++) {
      int findNum = query(tree, 1, 1, N, K);
      if (i != N - 1) bw.write(findNum + ", ");
      else {
        bw.write(findNum + ">");
        break;
      }
      size--;
      K += (num - 1);
      if (K > size) K %= size;
      if (K == 0) K = size;
    }
    bw.flush();
    bw.close();
  }
  static void init(int[] tree, int node, int nodeLeft, int nodeRight) {
    if (nodeLeft == nodeRight) {
      tree[node] = 1;
    }
    else {
      init(tree, node * 2, nodeLeft, (nodeLeft + nodeRight) / 2);
      init(tree, node * 2 + 1, (nodeLeft + nodeRight) / 2 + 1, nodeRight);
      tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }
  }

  static int query(int[] tree, int node, int nodeLeft, int nodeRight, int target) {
    tree[node]--;

    if (nodeLeft == nodeRight) {
      return nodeLeft;
    }

    if (target > tree[node * 2] + tree[node * 2 + 1]) {
      int sum = tree[node * 2] + tree[node * 2 + 1];
      while (target < sum) target -= sum;
    }
    if (tree[node * 2] >= target) {
      return query(tree, node * 2, nodeLeft, (nodeLeft + nodeRight) / 2, target);
    } else {
      return query(tree, node * 2 + 1, (nodeLeft + nodeRight) / 2 + 1, nodeRight, target - tree[node * 2]);
    }
  }
}
```

풀긴 풀었지만 솔직히 좀 이상하게 푼거같은 느낌이 든다

K 처리를 더 깔끔하게 할 수 있는 방법이 안 떠올랐다.




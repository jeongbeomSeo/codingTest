# 데이터 구조

**플래티넘 4**

|시간 제한	|메모리 제한|	제출	|정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초	|512 MB	|3343|	1422|	913	|38.736%|

## 문제 

자연수를 저장하는 데이터베이스 S에 대해 다음의 쿼리를 처리합시다.

유형 1 : S에 자연수 X를 추가한다.

유형 2 : S에 포함된 숫자 중 X번째로 작은 수를 응답하고 그 수를 삭제한다.

## 입력 

첫째 줄에 사전에 있는 쿼리의 수 N 이 주어집니다. (1 ≤ N ≤ 2,000,000)

둘째 줄부터 N개의 줄에 걸쳐 각 쿼리를 나타내는 2개의 정수 T X가 주어집니다.

T가 1이라면 S에 추가할 X가 주어지는 것입니다. (1 ≤ X ≤ 2,000,000)

T가 2라면 X는 S에서 삭제해야 할 몇 번째로 작은 수인지를 나타냅니다. S에 최소 X개의 원소가 있음이 보장됩니다.

## 출력 

유형 2의 쿼리 개수만큼의 줄에 각 쿼리에 대한 답을 출력합니다.

## 예제 입력 1

```
5
1 11
1 29
1 89
2 2
2 2
```

## 예제 출력 1

```
29
89
```

## 풀이 방식 

현재 이문제의 경우 모르는 것이 너무 많다.

1. 일단 세그먼트 트리를 생성하기 위해서는 트리의 크기를 알아야 하는데 크기를 계산할 수가 없다.
2. X번째 작은 수 값을 어떻게 꺼내야 되는지 방법을 모른다.

사실 이 두개 만으로 이 문제가 세그먼트 트리를 활용해서 푸는 문제가 맞는지도 헷갈린다.

일단, 세그먼트 트리를 생성하기 위해서 세그먼트 트리의 크기는 무조건 필요하다.

이것은 그냥 가능한 가장 큰 값을 기준으로 두고 생성해야 할 것이다.

그 이후 부터 문제다.

아니다. 이것도 잘 못 생각한 것이다.

해당 문제는 이런 식으로 푸는 것이 아니다.

현재 문제를 보면 쿼리의 수 N이 주어지는데 최대 1 <= N <= 2,000,000의 범위 값을 가지고 있습니다.

즉, 나올 수 있는 데이터의 개수는 최대 2,000,000라는 것이다.

그리고 **데이터 값의 범위**를 확인해보자.

입력 받은 X가 가지는 범위의 최댓값은 2,000,000이다. 

즉, **나올 수 있는 데이터 값의 최댓값을 트리의 크기를 조정한다면?**

그렇다면, 세그먼트 트리에서 **nodeLeft와 nodeRight를 데이터 값**으로 생각 할 수 있는 것이다.

그렇다면 **노드에 들어가는 값**은 무엇으로 설정할 수 있을까?

**해당 범위에서의 데이터의 갯수**로 설정할 수 있지 않을까? 

사실 이 방식은 다른 사람의 풀이를 보고 참조했다. 

완벽하게 익혀둘 필요가 있다.

## 코드

**WA**

```java
import java.io.*;
import java.util.StringTokenizer;

public class Main {
  static int MAX_NUMBER = 2000000;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int h = (int)Math.ceil(Math.log(MAX_NUMBER) / Math.log(2));
    int tree_size = 1 << (h + 1);
    int[] tree = new int[tree_size];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int t = Integer.parseInt(st.nextToken());
      int x = Integer.parseInt(st.nextToken());

      switch (t) {
        case 1:
          insert(tree, 1, 1, MAX_NUMBER, x);
          break;
        case 2:
          bw.write(query(tree, 1, 1, MAX_NUMBER, x)+"\n");
          break;
      }
    }
    bw.flush();
    bw.close();
  }
  static void insert(int[] tree, int node, int nodeLeft, int nodeRight, int target) {
    if (target < nodeLeft || nodeRight < target) {
      return;
    }

    if (nodeLeft == nodeRight) {
      tree[node] = 1;
      return;
    }

    insert(tree, node * 2, nodeLeft, (nodeLeft + nodeRight) / 2, target);
    insert(tree, node * 2 + 1, (nodeLeft + nodeRight) / 2 + 1, nodeRight, target);

    tree[node] = tree[node * 2] + tree[node * 2 + 1];
  }

  static int query(int[] tree, int node, int nodeLeft, int nodeRight, int target) {
    tree[node]--;

    if (nodeLeft == nodeRight) {
      return nodeLeft;
    }

    if (tree[node * 2] >= target) {
      return query(tree, node * 2, nodeLeft, (nodeLeft + nodeRight) / 2, target);
    } else {
      return query(tree, node * 2 + 1, (nodeLeft + nodeRight) / 2 + 1, nodeRight, target - tree[node * 2]);
    }
  }
}
```

생각을 잘못했다. 중복된 수를 입력받지 않는 다는 말도 없는데다가 중복된 값이 존재한다고 해도 크게 문제가 되지 않는다.

**AC**

```java
import java.io.*;
import java.util.StringTokenizer;

public class Main {
  static int MAX_NUMBER = 2000000;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int h = (int)Math.ceil(Math.log(MAX_NUMBER) / Math.log(2));
    int tree_size = 1 << (h + 1);
    int[] tree = new int[tree_size];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int t = Integer.parseInt(st.nextToken());
      int x = Integer.parseInt(st.nextToken());

      switch (t) {
        case 1:
          insert(tree, 1, 1, MAX_NUMBER, x);
          break;
        case 2:
          bw.write(query(tree, 1, 1, MAX_NUMBER, x)+"\n");
          break;
      }
    }
    bw.flush();
    bw.close();
  }
  static void insert(int[] tree, int node, int nodeLeft, int nodeRight, int target) {
    if (target < nodeLeft || nodeRight < target) {
      return;
    }

    if (nodeLeft == nodeRight) {
      tree[node] += 1;
      return;
    }

    insert(tree, node * 2, nodeLeft, (nodeLeft + nodeRight) / 2, target);
    insert(tree, node * 2 + 1, (nodeLeft + nodeRight) / 2 + 1, nodeRight, target);

    tree[node] = tree[node * 2] + tree[node * 2 + 1];
  }

  static int query(int[] tree, int node, int nodeLeft, int nodeRight, int target) {
    tree[node]--;

    if (nodeLeft == nodeRight) {
      return nodeLeft;
    }

    if (tree[node * 2] >= target) {
      return query(tree, node * 2, nodeLeft, (nodeLeft + nodeRight) / 2, target);
    } else {
      return query(tree, node * 2 + 1, (nodeLeft + nodeRight) / 2 + 1, nodeRight, target - tree[node * 2]);
    }
  }
}
```
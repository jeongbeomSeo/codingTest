# 집합의 표현

**골드 5**

|시간 제한|	메모리 제한	|제출|	정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초|	128 MB|	80185	|25432	|15369|	28.158%|

## 문제

초기에 n+1개의 집합 {0}, {1}, {2}, ... , {n}이 있다. 여기에 합집합 연산과, 두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산을 수행하려고 한다.

집합을 표현하는 프로그램을 작성하시오.

## 입력 

첫째 줄에 n, m이 주어진다.
m은 입력으로 주어지는 연산의 개수이다.
다음 m개의 줄에는 각각의 연산이 주어진다. 
합집합은 0 a b의 형태로 입력이 주어진다. 
이는 a가 포함되어 있는 집합과, b가 포함되어 있는 집합을 합친다는 의미이다.
두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산은 1 a b의 형태로 입력이 주어진다. 
이는 a와 b가 같은 집합에 포함되어 있는지를 확인하는 연산이다.

## 출력 

1로 시작하는 입력에 대해서 a와 b가 같은 집합에 포함되어 있으면 "YES" 또는 "yes"를, 그렇지 않다면 "NO" 또는 "no"를 한 줄에 하나씩 출력한다.

## 제한

- 1 ≤ n ≤ 1,000,000
- 1 ≤ m ≤ 100,000
- 0 ≤ a, b ≤ n
- a, b는 정수
- a와 b는 같을 수도 있다.

## 예제 입력 1

```
7 8
0 1 3
1 1 7
0 7 6
1 7 1
0 3 7
0 4 2
0 1 1
1 1 1
```

## 예제 출력 1

```
NO
NO
YES
```

## 코드 

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    int[] parent = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      parent[i] = i;
    }

    while (m-- > 0) {
      st = new StringTokenizer(br.readLine());
      int query = Integer.parseInt(st.nextToken());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      if (query == 0) union_merge(parent, a, b);
      else {
        if (a == b) {
          System.out.println("YES");
          continue;
        }
        a = union_find(parent, a);
        b = union_find(parent, b);
        if (a == b) System.out.println("YES");
        else System.out.println("NO");
      }
    }

  }
  static int union_find(int[] parent, int x) {
    if (parent[x] == x) return x;
    return parent[x] = union_find(parent, parent[x]);
  }
  static void union_merge(int[] parent, int x, int y) {
    x = union_find(parent, x);
    y = union_find(parent, y);

    if (x != y) {
      if (x < y) parent[y] = x;
      else parent[x] = y;
    }
  }
}
```
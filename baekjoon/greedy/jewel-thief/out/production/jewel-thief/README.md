# 보석 도둑

**골드 2**

|시간 제한	|메모리 제한	|제출	|정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|1 초	|256 MB	|51668	|11978	|8403	|21.827%|

## 문제 

세계적인 도둑 상덕이는 보석점을 털기로 결심했다.

상덕이가 털 보석점에는 보석이 총 N개 있다. 각 보석은 무게 M<sub>i</sub>와 가격 V<sub>i</sub>를 가지고 있다. 상덕이는 가방을 K개 가지고 있고, 각 가방에 담을 수 있는 최대 무게는 C<sub>i</sub>이다. 가방에는 최대 한 개의 보석만 넣을 수 있다.

상덕이가 훔칠 수 있는 보석의 최대 가격을 구하는 프로그램을 작성하시오.

## 입력 

첫째 줄에 N과 K가 주어진다. (1 ≤ N, K ≤ 300,000)

다음 N개 줄에는 각 보석의 정보 M<sub>i</sub>와 V<sub>i</sub>가 주어진다. (0 ≤ Mi, Vi ≤ 1,000,000)

다음 K개 줄에는 가방에 담을 수 있는 최대 무게 C<sub>i</sub>가 주어진다. (1 ≤ Ci ≤ 100,000,000)

모든 숫자는 양의 정수이다.

## 출력 

첫째 줄에 상덕이가 훔칠 수 있는 보석 가격의 합의 최댓값을 출력한다.

## 예제 입력 1

```
2 1
5 10
100 100
11
```

## 예제 출력 1

```
10
```

## 예제 입력 2

```
3 2
1 65
5 23
2 99
10
2
```

## 예제 출력 2

```
164
```

## 코드

**TLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    Jewel[] jewels = new Jewel[N];
    Bag[] bags = new Bag[K];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int weight = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());
      jewels[i] = new Jewel(weight, cost);
    }

    for (int i = 0; i < K; i++) {
      int size = Integer.parseInt(br.readLine());
      bags[i] = new Bag(size);
    }

    Arrays.sort(jewels);
    Arrays.sort(bags);

    query(jewels, bags, N, K);
    int sum = 0;
    for (int i = 0; i < K; i++) {
      sum += bags[i].cost;
    }

    System.out.println(sum);
  }
  static void query(Jewel[] jewels, Bag[] bags, int N, int K) {

    int size = K;
    for (int i = 0; i < N; i++) {
      if (size == 0) break;
      for (int j = 0; j < K; j++) {
        if (bags[j].cost == -1 && bags[j].size >= jewels[i].weight) {
          bags[j].cost = jewels[i].cost;
          size--;
          break;
        }
      }
    }
  }
}

class Bag implements Comparable<Bag> {
  int size;
  int cost;

  Bag(int size) {
    this.size = size;
    cost = -1;
  }

  @Override
  public int compareTo(Bag o) {
    return this.size - o.size;
  }
}

class Jewel implements Comparable<Jewel>{
  int weight;
  int cost;

  Jewel(int weight, int cost) {
    this.weight = weight;
    this.cost = cost;
  }

  @Override
  public int compareTo(Jewel o) {
    return o.cost - this.cost;
  }
}
```
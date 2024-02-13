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

**TLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
  static int sum = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    ArrayList<Jewel> jewels = new ArrayList<>();
    ArrayList<Bag> bags = new ArrayList<>();

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int weight = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());
      jewels.add(new Jewel(weight, cost));
    }

    for (int i = 0; i < K; i++) {
      int size = Integer.parseInt(br.readLine());
      bags.add(new Bag(size));
    }

    Collections.sort(jewels);
    Collections.sort(bags);

    query(jewels, bags, N, K);

    System.out.println(sum);
  }
  static void query(ArrayList<Jewel> jewels, ArrayList<Bag> bags, int N, int K) {

    int bagSize = K;

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < bagSize; j++) {
        if (bags.get(j).size >= jewels.get(i).weight) {
          sum += jewels.get(i).cost;
          bags.remove(j);
          bagSize--;
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

사용한 가방을 지워줌으로써 반복문에서 효율을 높이려고 했는데 별 효과가 없었다.

아마 정렬하려는 행위 자체가 문제일 수도 있겠다 ...

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    Jewel[] jewels = new Jewel[N];
    int[] bags = new int[K];

    for (int i = 0 ; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int weight = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());
      jewels[i] = new Jewel(weight, cost);
    }

    for (int i = 0; i < K; i++) {
      bags[i] = Integer.parseInt(br.readLine());
    }

    Arrays.sort(jewels, new Comparator<Jewel>() {
      @Override
      public int compare(Jewel o1, Jewel o2) {
        if (o1.weight == o2.weight)
          return o2.cost - o1.cost;
        return o1.weight - o2.weight;
      }
    });
    Arrays.sort(bags);

    System.out.println(greedy(jewels, bags, N, K));
  }
  static long greedy(Jewel[] jewels, int[] bags, int N, int K) {
    Queue<Jewel> pq = new PriorityQueue<>((o1, o2) -> o2.cost - o1.cost);
    long totalMoney = 0;

    int idx = 0;
    for (int i = 0 ; i < K; i++) {

      while (idx < N && jewels[idx].weight <= bags[i]) {
        pq.offer(jewels[idx++]);
      }

      if (!pq.isEmpty()) {
        totalMoney += pq.poll().cost;
      }
    }
    return totalMoney;
  }
}

class Jewel {
  int weight;
  int cost;

  Jewel(int weight, int cost) {
    this.weight = weight;
    this.cost = cost;
  }
}
```

**최종 복습 코드(AC)**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    int[][] jewels = new int[N][2];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      jewels[i][0] = Integer.parseInt(st.nextToken());  // 보석의 무게
      jewels[i][1] = Integer.parseInt(st.nextToken());  // 보석의 가격
    }

    int[] bags = new int[K];
    for (int i = 0; i < K; i++) {
      bags[i] = Integer.parseInt(br.readLine());  // 가방의 가용치 무게
    }

    Arrays.sort(jewels, (o1, o2) -> o1[0] - o2[0]);
    Arrays.sort(bags);

    System.out.println(query(jewels, bags, N, K));
  }
  private static long query(int[][] jewels, int[] bags, int N, int K) {

    long result = 0;
    Queue<Integer> costPQ = new PriorityQueue<>((o1, o2) -> o2 - o1);

    int jewelIdx = 0;
    for (int i = 0; i < K; i++) {
      int bagAvailableWeight = bags[i];

      while (jewelIdx < N) {
        if (jewels[jewelIdx][0] <= bagAvailableWeight) {
          costPQ.add(jewels[jewelIdx][1]);
          jewelIdx++;
        }
        else break;
      }

      if (!costPQ.isEmpty()) {
        result += costPQ.poll();
      }
    }

    return result;
  }
}
```
# 회의실 배정 

**실버 1**

|시간 제한	|메모리 제한|	제출|	정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초|	128 MB	|169061|	53624|	37548|	29.774%|

## 문제 

한 개의 회의실이 있는데 이를 사용하고자 하는 N개의 회의에 대하여 회의실 사용표를 만들려고 한다. 각 회의 I에 대해 시작시간과 끝나는 시간이 주어져 있고, 각 회의가 겹치지 않게 하면서 회의실을 사용할 수 있는 회의의 최대 개수를 찾아보자. 단, 회의는 한번 시작하면 중간에 중단될 수 없으며 한 회의가 끝나는 것과 동시에 다음 회의가 시작될 수 있다. 회의의 시작시간과 끝나는 시간이 같을 수도 있다. 이 경우에는 시작하자마자 끝나는 것으로 생각하면 된다.

## 입력 

첫째 줄에 회의의 수 N(1 ≤ N ≤ 100,000)이 주어진다. 둘째 줄부터 N+1 줄까지 각 회의의 정보가 주어지는데 이것은 공백을 사이에 두고 회의의 시작시간과 끝나는 시간이 주어진다. 시작 시간과 끝나는 시간은 2<sup>31</sup>-1보다 작거나 같은 자연수 또는 0이다.

## 출력 

첫째 줄에 최대 사용할 수 있는 회의의 최대 개수를 출력한다.

## 예제 입력 1

```
11
1 4
3 5
0 6
5 7
3 8
5 9
6 10
8 11
8 12
2 13
12 14
```

## 예제 출력 1

```
4
```

## 힌트 

(1,4), (5,7), (8,11), (12,14) 를 이용할 수 있다.

## 코드

**TLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int maxSize = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    Scheduler[] schedulers = new Scheduler[N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      schedulers[i] = new Scheduler(start, end);
    }
    Arrays.sort(schedulers);

    Scheduler[] timeTable = new Scheduler[N];

    backtracking(schedulers,timeTable, N, 0, 0);

    System.out.println(maxSize);
  }
  static void backtracking(Scheduler[] schedulers, Scheduler[] timeTable, int N, int ptr, int size) {

    if (ptr == N) {
      maxSize = Math.max(maxSize, size);
    }
    else {
      if (size != 0) {
        Scheduler beforeSchedule = timeTable[size - 1];

        if (schedulers[ptr].start >= beforeSchedule.end) {
          timeTable[size] = schedulers[ptr];
          backtracking(schedulers, timeTable, N, ptr + 1, size + 1);
          timeTable[size] = null;
        }

        backtracking(schedulers, timeTable, N, ptr + 1, size);
      }
      else {
        timeTable[size] = schedulers[ptr];
        backtracking(schedulers, timeTable, N, ptr + 1, size + 1);
        timeTable[size] = null;

        backtracking(schedulers, timeTable, N, ptr + 1, size);
      }
    }
  }

}

class Scheduler implements Comparable<Scheduler>{
  int start;
  int end;

  Scheduler(int start, int end) {
    this.start = start;
    this.end = end;
  }

  @Override
  public int compareTo(Scheduler o) {
    return this.start - o.start;
  }
}
```

올바른 문제 풀이를 위해서 **Case**를 나눠서 생각했습니다.

먼저 시작 시간순으로 정렬하는 과정까지는 동일합니다.

그 이후 다음과 같은 순서로 비교합니다.

1. 첫번째 요소는 그냥 넣어준다.
2. 이전에 넣어놨던 요소의 end 값과 현재 선택한 요소의 strat 값을 비교합니다.
   1. before.end <= cur.start: 그냥 넣어준다.
   2. before.end > cut.start: 2가지 경우의 수로 나눕니다.
      1. before.end <= cur.end: 그냥 넘어갑니다.(넣어 주지 않는다.)
      2. before.end > cur.end: 앞에 요소를 대체해 줍니다.

이와 같은 과정이 가능한 이유는 이미 시작 시작순으로 정렬을 해놓았고, 
그 시점 부턴 끝나는 시간이 땅겨질 수록 넣을 수 있는 요소가 많아지기 때문입니다.

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int maxSize = 0;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    Scheduler[] schedulers = new Scheduler[N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      schedulers[i] = new Scheduler(start, end);
    }
    Arrays.sort(schedulers);

    Scheduler[] timeTable = new Scheduler[N];

    System.out.println(greedy(schedulers, timeTable, N));
  }
  static int greedy(Scheduler[] schedulers, Scheduler[] timeTable, int N) {

    int size = 0;
    timeTable[size++] = schedulers[0];
    for (int i = 1; i < N; i++) {
      if (timeTable[size - 1].end <= schedulers[i].start) {
        timeTable[size++] = schedulers[i];
      }
      else {
        if (timeTable[size - 1].end > schedulers[i].end) {
          timeTable[size - 1] = schedulers[i];
        }
      }
    }
    return size;
  }
}
class Scheduler implements Comparable<Scheduler>{
  int start;
  int end;

  Scheduler(int start, int end) {
    this.start = start;
    this.end = end;
  }

  @Override
  public int compareTo(Scheduler o) {
    if (this.start > o.start) return 1;
    else if (this.start < o.start) return -1;
    else {
      return this.end - o.end;
    }
  }
}
```

사실 해당 코드에서 처음에 ```compareTo``` 부분을 시작 시간만 비교했다가 틀리게 나왔다.

```java
  @Override
  public int compareTo(Scheduler o) {
    return this.start - o.start;
  }
```

이와 같이 코딩을 했었다. 왜 틀린걸까?

예를 들어, 

```
...
4 5
4 4
...
```

라는 시간이 주어졌을 경우에 위와 같이 시작 시간만 비교했을 때 정렬이 되지 않고 저 상태로 유지된 채로 배열에 넣어질 것입니다.

하지만 그렇게 되면 4 5 가 먼저 들어가게 되면서 문제가 발생합니다.

원래는 4 4 -> 4 5 이와 같이 진행할 수 있지만 4 5 가 먼저 들어가면서 다른 케이스로 작동되서 4 5가 들어간 자리를 4 4로 대체하는 로직의 형태로 진행됩니다.

따라서, 종료 시간도 고려해서 배열을 정렬해야 합니다.

사실 이 문제는 시작 시간보다 **종료 시간이 더 중요**합니다. 
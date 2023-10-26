# 강의실 배정 

**골드 5**

|시간 제한	|메모리 제한	|제출|	정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|1 초	|256 MB|	33284	|9763	|7149|	28.713%|

## 문제 

수강신청의 마스터 김종혜 선생님에게 새로운 과제가 주어졌다.

김종혜 선생님한테는 S<sub>i</sub>에 시작해서 T<sub>i</sub>에 끝나는 N개의 수업이 주어지는데, 최소의 강의실을 사용해서 모든 수업을 가능하게 해야 한다.

참고로, 수업이 끝난 직후에 다음 수업을 시작할 수 있다. (즉, T<sub>i</sub> ≤ S<sub>j</sub> 일 경우 i 수업과 j 수업은 같이 들을 수 있다.)

수강신청 대충한 게 찔리면, 선생님을 도와드리자!

## 입력 

첫 번째 줄에 N이 주어진다. (1 ≤ N ≤ 200,000)

이후 N개의 줄에 S<sub>i</sub>, T<sub>i</sub>가 주어진다. (0 ≤ S<sub>i</sub> < T<sub>i</sub> ≤ 10<sup>9</sup>)

## 출력 

강의실의 개수를 출력하라.

## 예제 입력 1

```
3
1 3
2 4
3 5
```

## 예제 출력 1

```
2
```

## 코드

**WA**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    Class[] classes = new Class[N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      classes[i] = new Class(start, end);
    }

    Arrays.sort(classes);

    System.out.println(greedy(classes, N));

  }
  static int greedy(Class[] classes, int N) {
    Queue<Class> q = new PriorityQueue<>();
    q.add(classes[0]);
    int ptr = 1;
    while (ptr < N) {
      Class curClass = q.peek();

      if (curClass.end <= classes[ptr].start) {
        q.poll();
      }
      q.add(new Class(classes[ptr].start, classes[ptr].end));
      ptr++;
    }
    return q.size();
  }
}

class Class implements Comparable<Class>{
  int start;
  int end;

  Class(int start, int end) {
    this.start = start;
    this.end = end;
  }

  @Override
  public int compareTo(Class o) {
    if (this.end > o.end) return 1;
    else if (this.end < o.end) return -1;
    else {
      return this.start - o.start;
    }
  }
}
```

해당 문제는 **백준 1931 문제: 회의실 배정**과는 다른 방식을 접근해야 합니다.

회의실 배정문제에서는 끝나는 시간만 고려하면 되었지만, 이것은 시작 시간도 같이 고려해줘야 합니다. 

회의실 배정 문제는 주어진 하나의 강의실을 가지고 최대한 많은 회의를 넣어주는 형태의 문제이기 때문에 종료 시간만 고려하면 됐습니다.

하지만, 이 문제는 강의 시간에 맞춰서 필요한 최소한의 강의실 갯수를 구하는 문제이다.

따라서 정렬을 할 때 끝나는 시간으로 정렬하지 말고, 시작 시간으로 정렬을 한 뒤 끝나는 시간으로 우선순위 큐를 만들어서 사용해야 합니다.

**위의 풀이 반레**

**입력**

```
8
1 8
9 16
3 7
8 10
10 14
5 6
6 11
11 12
```

**출력**

```
5
```

**정답**

```
3
```
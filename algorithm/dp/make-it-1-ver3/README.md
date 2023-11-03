# 1로 만들기 3

**골드 3**

| 시간 제한               | 메모리 제한 | 제출 | 정답 | 맞힌 사람 | 정답 비율 |
| ----------------------- | ----------- | ---- | ---- | --------- | --------- |
| 0.5 초 (추가 시간 없음) | 1024 MB     | 204  | 70   | 58        | 40.845%   |

## 문제

정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 이다.

1. X가 3으로 나누어 떨어지면, 3으로 나눈다.
2. X가 2로 나누어 떨어지면, 2로 나눈다.
3. 1을 뺀다.

정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다. 연산을 사용하는 횟수의 최솟값을 출력하시오.

## 입력

첫째 줄에 1보다 크거나 같고, 10<sup>18</sup>보다 작거나 같은 정수 N이 주어진다.

## 출력

첫째 줄에 연산을 하는 횟수의 최솟값을 출력한다.

## 예제 입력 1

```
2
```

## 예제 출력 1

```
1
```

## 예제 입력 2

```
10
```

## 예제 출력 2

```
3
```

## 힌트

10의 경우에 10 → 9 → 3 → 1 로 3번 만에 만들 수 있다.

## 알게된 것

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Main {
  static int INF = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    long N = Long.parseLong(br.readLine());

    Map<Long, Integer> map = new HashMap<>();

    bfs(map, N);

    System.out.println(map.get((long)1));


  }
  static void bfs(Map<Long, Integer> map, Long N) {
    Queue<Long> q = new LinkedList<>();

    q.add(N);

    map.put(N, 0);

    while (!q.isEmpty()) {
      Long curNum = q.poll();

      if (curNum != 1) {
        map.put(curNum - 1, Math.min(map.get(curNum) + 1, map.getOrDefault(curNum - 1, INF)));
        q.add(curNum - 1);

        if (curNum % 3 == 0) {
          map.put(curNum / 3, Math.min(map.get(curNum) + 1, map.getOrDefault(curNum / 3, INF)));
          q.add(curNum / 3);
        }
        if (curNum % 2 == 0) {
          map.put(curNum / 2, Math.min(map.get(curNum) + 1, map.getOrDefault(curNum / 2, INF)));
          q.add(curNum / 2);
        }
      }
    }
  }
}
```

해당 코드에서 다음 부분을 보자.

```java
    System.out.println(map.get((long)1));
```

만약 여기서 1을 Long자료형으로 바꿔지 주지 않으면 null이 나온다.

**Map Interface를 사용할 때 주의할 점은 key값에 접근할 때 자료형도 같아야 되는 점에 주의**하자.

## 코드

**TLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Main {
  static int INF = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    long N = Long.parseLong(br.readLine());

    Map<Long, Integer> map = new HashMap<>();

    bfs(map, N);

    System.out.println(map.get((long)1));


  }
  static void bfs(Map<Long, Integer> map, Long N) {
    Queue<Long> q = new LinkedList<>();

    q.add(N);

    map.put(N, 0);

    while (!q.isEmpty()) {
      Long curNum = q.poll();

      if (curNum != 1) {
        map.put(curNum - 1, Math.min(map.get(curNum) + 1, map.getOrDefault(curNum - 1, INF)));
        q.add(curNum - 1);

        if (curNum % 3 == 0) {
          map.put(curNum / 3, Math.min(map.get(curNum) + 1, map.getOrDefault(curNum / 3, INF)));
          q.add(curNum / 3);
        }
        if (curNum % 2 == 0) {
          map.put(curNum / 2, Math.min(map.get(curNum) + 1, map.getOrDefault(curNum / 2, INF)));
          q.add(curNum / 2);
        }
      }
    }
  }
}
```

**TLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Main {
  static int INF = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    long N = Long.parseLong(br.readLine());

    Map<Long, Integer> map = new HashMap<>();
    Map<Long, Boolean> flag = new HashMap<>();

    bfs(map, flag, N);

    System.out.println(map.get((long)1));


  }
  static void bfs(Map<Long, Integer> map, Map<Long, Boolean> flag,Long N) {
    Queue<Long> q = new LinkedList<>();

    q.add(N);

    map.put(N, 0);

    while (!q.isEmpty()) {
      Long curNum = q.poll();

      if (flag.getOrDefault(curNum, true) && curNum != 1) {
        map.put(curNum - 1, Math.min(map.get(curNum) + 1, map.getOrDefault(curNum - 1, INF)));
        q.add(curNum - 1);

        if (curNum % 3 == 0) {
          map.put(curNum / 3, Math.min(map.get(curNum) + 1, map.getOrDefault(curNum / 3, INF)));
          q.add(curNum / 3);
        }

        if (curNum % 2 == 0) {
          map.put(curNum / 2, Math.min(map.get(curNum) + 1, map.getOrDefault(curNum / 2, INF)));
          q.add(curNum / 2);
        }
        flag.put(curNum, false);
      }
    }
  }
}
```

다음은 챗지피티가 짜준 코드지만 TLE가 나왔다.

```java
import java.util.*;

public class Main {
  static int INF = Integer.MAX_VALUE;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    long N = sc.nextLong();

    Map<Long, Integer> map = new HashMap<>();

    bfs(map, N);

    System.out.println(map.get((long)1));
  }

  static void bfs(Map<Long, Integer> map, long N) {
    Queue<Long> q = new LinkedList<>();

    q.add(N);

    map.put(N, 0);

    while (!q.isEmpty()) {
      long curNum = q.poll();

      if (curNum % 3 == 0) {
        long nextNum = curNum / 3;
        if (!map.containsKey(nextNum)) {
          map.put(nextNum, map.get(curNum) + 1);
          q.add(nextNum);
        }
      }

      if (curNum % 2 == 0) {
        long nextNum = curNum / 2;
        if (!map.containsKey(nextNum)) {
          map.put(nextNum, map.get(curNum) + 1);
          q.add(nextNum);
        }
      }

      long nextNum = curNum - 1;
      if (!map.containsKey(nextNum)) {
        map.put(nextNum, map.get(curNum) + 1);
        q.add(nextNum);
      }
    }
  }
}
```

다음은 GPT가 풀어줘서 통과한 코드다... 열심히하자..

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Main {
  static final long INF = Long.MAX_VALUE;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    long N = Long.parseLong(br.readLine());

    Map<Long, Long> map = new HashMap<>();
    Queue<Long> queue = new ArrayDeque<>();

    map.put(N, 0L);
    queue.offer(N);

    while (!queue.isEmpty()) {
      long curNum = queue.poll();
      long curCnt = map.get(curNum);

      if (curNum == 1) {
        System.out.println(curCnt);
        return;
      }

      if (curNum % 3 == 0 && curNum / 3 >= 1) {
        long nextNum = curNum / 3;
        if (!map.containsKey(nextNum)) {
          map.put(nextNum, curCnt + 1);
          queue.offer(nextNum);
        }
      }

      if (curNum % 2 == 0 && curNum / 2 >= 1) {
        long nextNum = curNum / 2;
        if (!map.containsKey(nextNum)) {
          map.put(nextNum, curCnt + 1);
          queue.offer(nextNum);
        }
      }

      if (curNum - 1 >= 1) {
        long nextNum = curNum - 1;
        if (!map.containsKey(nextNum)) {
          map.put(nextNum, curCnt + 1);
          queue.offer(nextNum);
        }
      }
    }
  }
}
```

이 풀이가 통과한 이유는 1을 빼는 시점을 아래에 넣어주면서 코드를 최적화 해준 것이다.

예를 들어서, 100이라는 숫자가 있을 때 2로 나누어 지기 때문에 Map에 key가 50인 요소에 1이 들어갈 것이다.

근데 1을 뺴는 것을 3과 2로 나누어 주는 것보다 위에 놓을 경우 1씩 빼면서 결국 50을 또 확인할 것이다.

그래서 1을 빼는 것을 아래에 배치하면서 코드를 최적화 시켜준 것이다.

그리고 map에 key값이 없는 경우에만 value를 초기화 시켜주고 나머지의 경우에는 초기화를 안 시켜줬다.

무슨 원리인지 모르겠지만 .. 숫자가 안 겹치나 보다. 아.. 아닌것 같다. **어차피 가장 먼저 도착하는 것이 최소 연산일 수 밖에 없다.** 그래서 이미 초기화되어 있는 값은 확인할 필요 조차 없는 것이다.

그리고 어차피 최소 연산을 출력하려고 하면 Queue 안에 넣는 key 값이 가장 빨리 1에 도착한 것이 최소 연산일 것이다.

그리고 반복되는 연산을 최대한 줄였다.

즉, 나의 코드와 다음과 같은 차이점을 보여준다.

- 코드 최적화 -> 변수 사용
- 로직 최적화 -> 연산이 가장 적을 수밖에 없는 것을 먼저 실행
- 불필요한 연산 빼기 -> 이미 Map 에서 초기화된 (key,value)는 가장 빠른 연산을 통해서 도착한 것이다. 굳이 그 후에 도착한 것과 대소 비교를 할 필요조차 없는 것이다.

**복습 코드**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    Long N = Long.parseLong(br.readLine());

    System.out.println(queryResult(N));
  }
  private static int queryResult(Long N) {

    Queue<Node> q = new ArrayDeque<>();

    Map<Long, Boolean> isVisited = new HashMap<>();

    q.add(new Node(N, 0));

    while (!q.isEmpty()) {
      Node node = q.poll();

      if (node.value <= 0) continue;
      else if (node.value == 1) {
        return node.count;
      }


      if (!isVisited.getOrDefault(node.value / 3, false) && node.value % 3 == 0) {
        q.add(new Node(node.value / 3, node.count + 1));
        isVisited.put(node.value / 3, true);
      }
      if (!isVisited.getOrDefault(node.value / 2, false) && node.value % 2 == 0) {
        q.add(new Node(node.value / 2, node.count + 1));
        isVisited.put(node.value / 2, true);
      }
      if (!isVisited.getOrDefault(node.value - 1, false)) {
        q.add(new Node(node.value - 1, node.count + 1));
        isVisited.put(node.value - 1, true);
      }
    }

    return -1;
  }
}
class Node {
  long value;
  int count;

  Node(long value, int count) {
    this.value = value;
    this.count = count;
  }
}
```
# 도영이가 만든 맛있는 음식 

**실버 2**

|시간 제한	|메모리 제한	|제출	|정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|1 초	|128 MB	|11152	|6019|	4500|	53.079%|

## 문제 

도영이는 짜파구리 요리사로 명성을 날렸었다. 이번에는 이전에 없었던 새로운 요리에 도전을 해보려고 한다.

지금 도영이의 앞에는 재료가 N개 있다. 도영이는 각 재료의 신맛 S와 쓴맛 B를 알고 있다. 여러 재료를 이용해서 요리할 때, 그 음식의 신맛은 사용한 재료의 신맛의 곱이고, 쓴맛은 합이다.

시거나 쓴 음식을 좋아하는 사람은 많지 않다. 도영이는 재료를 적절히 섞어서 요리의 신맛과 쓴맛의 차이를 작게 만들려고 한다. 또, 물을 요리라고 할 수는 없기 때문에, 재료는 적어도 하나 사용해야 한다.

재료의 신맛과 쓴맛이 주어졌을 때, 신맛과 쓴맛의 차이가 가장 작은 요리를 만드는 프로그램을 작성하시오.

## 입력 

첫째 줄에 재료의 개수 N(1 ≤ N ≤ 10)이 주어진다. 다음 N개 줄에는 그 재료의 신맛과 쓴맛이 공백으로 구분되어 주어진다. 모든 재료를 사용해서 요리를 만들었을 때, 그 요리의 신맛과 쓴맛은 모두 1,000,000,000보다 작은 양의 정수이다.

## 예제 입력 1

```
1
3 10
```

## 예제 출력 1

```
7
```

## 예제 입력 2

```
2
3 8
5 8
```

## 예제 출력 2

```
1
```

## 예제 입력 3

```
4
1 7
2 6
3 8
4 9
```

## 예제 출력 3

```
1
```

2, 3, 4번 재료를 사용한다면, 요리의 신맛은 2×3×4=24, 쓴맛은 6+8+9=23이 된다. 차이는 1이다.

## 코드

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int MIN = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int[][] ingredients = new int[N][2];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int ig1 = Integer.parseInt(st.nextToken());
      int ig2 = Integer.parseInt(st.nextToken());

      ingredients[i][0] = ig1;
      ingredients[i][1] = ig2;
    }

    int set = 1;
    int boundary = 1 << N;
    while (set != boundary) {
      int Ssum = 1;
      int Bsum = 0;
      for (int i = 0; i < 10; i++) {
        if ((set & (1 << i)) == (1 << i)) {
          Ssum *= ingredients[i][0];
          Bsum += ingredients[i][1];
        }
      }
      MIN = Math.min(Math.abs(Ssum - Bsum), MIN);
      set++;
    }
    System.out.println(MIN);
  }
}
```

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int MIN = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    N = Integer.parseInt(br.readLine());


    int[][] ingredients = new int[N][2];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      ingredients[i][0] = Integer.parseInt(st.nextToken());
      ingredients[i][1] = Integer.parseInt(st.nextToken());
    }

    combination(ingredients, 1, 0, 0, 0);

    System.out.println(MIN);
  }
  static void combination(int[][] ingredients, int aSum, int bSum, int ptr, int size) {

    if (ptr == N) {
      if (size != 0) MIN = Math.min(Math.abs(aSum - bSum), MIN);
    }
    else {
      combination(ingredients, aSum * ingredients[ptr][0], bSum + ingredients[ptr][1], ptr + 1, size + 1);

      combination(ingredients, aSum, bSum, ptr + 1, size);
    }
  }
}
```

|       | 비트 마스킹 | 조합(재귀 함수) |
|-------|---|---|
| **메모리** | 14208KB | 124ms |
| **시간**    | 14072KB | 120ms |

**최종 코드(AC)**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    int[][] food = new int[N][2];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      food[i][0] = Integer.parseInt(st.nextToken());
      food[i][1] = Integer.parseInt(st.nextToken());
    }

    int min = Integer.MAX_VALUE;

    for (int set = 1; set < (1 << N); set++) {
      int sourCost = 1;
      int bitterCost = 0;
      for (int bit = N - 1; bit >= 0; bit--) {
        if ((set & (1 << bit)) == (1 << bit)) {
          sourCost *= food[bit][0];
          bitterCost += food[bit][1];
        }
      }
      min = Math.min(Math.abs(sourCost - bitterCost), min);
    }
    System.out.println(min);
  }
}
```
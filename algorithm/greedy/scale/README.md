# 저울 

**골드 2**

|시간 제한|	메모리 제한	|제출|	정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|1 초	|128 MB	|19364	|7749|	6257|	39.136%|

## 문제 

하나의 양팔 저울을 이용하여 물건의 무게를 측정하려고 한다. 이 저울의 양 팔의 끝에는 물건이나 추를 올려놓는 접시가 달려 있고, 양팔의 길이는 같다. 또한, 저울의 한쪽에는 저울추들만 놓을 수 있고, 다른 쪽에는 무게를 측정하려는 물건만 올려놓을 수 있다.

![](https://upload.acmicpc.net/7d2a2428-a1b0-47f5-9f53-fecd714d1b1b/-/preview/)

무게가 양의 정수인 N개의 저울추가 주어질 때, 이 추들을 사용하여 측정할 수 없는 양의 정수 무게 중 최솟값을 구하는 프로그램을 작성하시오.

예를 들어, 무게가 각각 3, 1, 6, 2, 7, 30, 1인 7개의 저울추가 주어졌을 때, 이 추들로 측정할 수 없는 양의 정수 무게 중 최솟값은 21이다. 

## 입력 

첫 째 줄에는 저울추의 개수를 나타내는 양의 정수 N이 주어진다. N은 1 이상 1,000 이하이다. 둘째 줄에는 저울추의 무게를 나타내는 N개의 양의 정수가 빈칸을 사이에 두고 주어진다. 각 추의 무게는 1이상 1,000,000 이하이다.

## 출력 

첫째 줄에 주어진 추들로 측정할 수 없는 양의 정수 무게 중 최솟값을 출력한다.

## 예제 입력 1

```
7
3 1 6 2 7 30 1
```

## 예제 출력 1

```
21
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
  static int INF = 1000000;
  static int num = 0;
  static int N;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    N = Integer.parseInt(br.readLine());

    int[] weight = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      weight[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(weight);

    System.out.println(greedy(weight));

  }
  static int greedy(int[] weight) {
    int target = 1;

    while (recursive(weight, 0, 0, target)) target++;

    return target;

  }
  static boolean recursive(int[] weight, int ptr, int sum, int target) {

    boolean isSuccess = false;

    if (target == sum) return true;

    if (ptr == N) return false;
    else {
      if (weight[ptr] <= target) {
        isSuccess = recursive(weight, ptr + 1, sum + weight[ptr], target);
        if (!isSuccess) isSuccess = recursive(weight, ptr + 1, sum, target);
      }
    }

    if (isSuccess) return true;
    else return false;

  }
}
```

**MLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    Map<Integer, Boolean> dp = new HashMap<>();

    int N = Integer.parseInt(br.readLine());
    int[] weight = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      weight[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(weight);

    dp.put(0, true);
    int max = 0;
    for (int i = 0; i < N; i++) {
      int ptr = 0;
      int max_number = max;
      while (ptr <= max && dp.containsKey(ptr)) {
        dp.computeIfAbsent(ptr + weight[i], key -> true);
        max_number = Math.max(ptr + weight[i], max_number);
        ptr++;
      }
      max = max_number;
    }
    for (int i = 1; i < dp.size(); i++) {
      if (!dp.getOrDefault(i, false)) {
        System.out.println(i);
        break;
      }
    }
  }
}
```

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    int[] weight = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      weight[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(weight);

    System.out.println(greedy(weight, N));
  }

  static long greedy(int[] weight, int N) {

    if (weight[0] != 1) return 1;
    long sum = 1;

    for (int i = 1; i < N; i++) {
      if (sum - weight[i] >= -1) sum += weight[i];
      else break;
    }

    return sum + 1;
  }
}
```

이 풀이의 핵심은 DP와 유사하지만 차이가 있다.

추를 오름차순으로 정렬을 해서 sum을 두고 먼저 처음 추 값 1을 넣습니다.

그리고 그 이후부터 sum과 현재 추 값을 비교해서 추가 기존에 만들 수 있는 무게와 대소 비교를 합니다.

여기서 대소 비교를 하는 이유는 두 가지 입니다.

1. 기존에 sum 이전의 모든 무게 값들은 **이전에 사용했던 추만으로 만들 수 있는 무게**
2. sum보다 2이상 크지 않는 경우 즉, sum + 1 이하인 값들은 앞서 사용했던 무게들에 더해서 만들 수 있음.

따라서, sum + 1 이하인 경우에는 sum에 더해줍니다. 아닌 경우에는 종료하고 그 다음 값을 return 해주면 됩니다.

**최종 코드(AC)**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int[] weights = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      weights[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(weights);

    if (weights[0] != 1) {
      System.out.println(1);
    }
    else {
      int sum = weights[0];
      for (int i = 1; i < N; i++) {
        if (sum + 1 >= weights[i]) sum += weights[i];
        else break;
      }

      System.out.println(sum + 1);
    }
  }
}
```
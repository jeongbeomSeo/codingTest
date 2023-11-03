# 가장 긴 바이토닉 부분 수열

**골드 4**

|시간 제한	|메모리 제한	|제출	|정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|1 초	|256 MB|	43926	|22425|	17526|	50.624%|

## 문제 

수열 S가 어떤 수 Sk를 기준으로 S<sub>1</sub> < S<sub>2</sub> < ... S<sub>k-1</sub> < S<sub>k</sub> > S<sub>k+1</sub> > ... S<sub>N-1</sub> > S<sub>N</sub>을 만족한다면, 그 수열을 바이토닉 수열이라고 한다.

예를 들어, {10, 20, **30**, 25, 20}과 {10, 20, 30, **40**}, {**50**, 40, 25, 10} 은 바이토닉 수열이지만,  {1, 2, 3, 2, 1, 2, 3, 2, 1}과 {10, 20, 30, 40, 20, 30} 은 바이토닉 수열이 아니다.

수열 A가 주어졌을 때, 그 수열의 부분 수열 중 바이토닉 수열이면서 가장 긴 수열의 길이를 구하는 프로그램을 작성하시오.

## 입력 

첫째 줄에 수열 A의 크기 N이 주어지고, 둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ N ≤ 1,000, 1 ≤ A<sub>i</sub> ≤ 1,000)

## 예제 입력 1

```
10
1 5 2 1 4 3 4 5 2 1
```

## 예제 출력 1

```
7
```

## 힌트

예제의 경우 {**1** 5 **2** 1 4 **3** **4** **5** **2** **1**}이 가장 긴 바이토닉 부분 수열이다.

## 코드

**AC**

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

    int[] dp_front = new int[N];
    int[] dp_back = new int[N];
    int[] arr = new int[N];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    System.out.println(lbs_dp(dp_front, dp_back, arr, N));
  }
  static int lbs_dp(int[] dp_front, int[] dp_back, int[] arr, int N) {
    for (int i = 0; i < N; i++) {
      int back_i = N - 1 - i;
      dp_front[i] = 1;
      dp_back[back_i] = 0;
      for (int j = 0; j < i; j++) {
        int back_j = N - 1 - j;
        if (arr[j] < arr[i])
          dp_front[i] = Math.max(dp_front[i], dp_front[j] + 1);
        if (arr[back_i] > arr[back_j])
          dp_back[back_i] = Math.max(dp_back[back_i], dp_back[back_j] + 1);
      }
    }
    /*
    for (int i = N - 1; i >= 0; i--) {
      dp_back[i] = 0;
      for (int j = N - 1; j > i; j--) {
        if (arr[i] > arr[j])
          dp_back[i] = Math.max(dp_back[i], dp_back[j] + 1);
      }
    }
    */
    int max = 0;
    for (int i = 0; i < N; i++) {
      max = Math.max(max, dp_front[i] + dp_back[i]);
    }
    return max;
  }
}
```

**함수로 더 쪼개기**

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
    int[] nums = new int[N];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      nums[i] = Integer.parseInt(st.nextToken());
    }

    System.out.println(queryResult(nums, N));
  }
  private static int queryResult(int[] nums, int N) {
    int[] table_front = initTableFront(nums, N);
    int[] table_back = initTableBack(nums, N);

    return getLogestLength(table_front, table_back, N);
  }
  private static int getLogestLength(int[] table_front, int[] table_back, int N) {

    int maxValue = Integer.MIN_VALUE;
    for (int i = 0; i < N; i++) {
      if (maxValue < (table_front[i] + table_back[i] - 1)) {
        maxValue = (table_front[i] + table_back[i] - 1);
      }
    }
    return maxValue;
  }
  private static int[] initTableFront(int[] nums, int N) {
    int[] table_front = new int[N];

    for (int i = 0; i < N; i++) {
      table_front[i] = 1;
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) {
          table_front[i] = Math.max(table_front[i], table_front[j] + 1);
        }
      }
    }
    return table_front;
  }
  private static int[] initTableBack(int[] nums, int N) {
    int[] table_back = new int[N];

    for (int i = N - 1; i >= 0; i--) {
      table_back[i] = 1;
      for (int j = N - 1; j > i; j--) {
        if (nums[i] > nums[j]) {
          table_back[i] = Math.max(table_back[i], table_back[j] + 1);
        }
      }
    }

    return table_back;
  }
}
```
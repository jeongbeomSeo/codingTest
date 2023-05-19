# 부분수열의 합 2

**골드 1**

|시간 제한	|메모리 제한	|제출	|정답|	맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|1 초	|256 MB	|23290	|5873	|3914	|24.213%|

## 문제 

N개의 정수로 이루어진 수열이 있을 때, 크기가 양수인 부분수열 중에서 그 수열의 원소를 다 더한 값이 S가 되는 경우의 수를 구하는 프로그램을 작성하시오.

## 입력 

첫째 줄에 정수의 개수를 나타내는 N과 정수 S가 주어진다. (1 ≤ N ≤ 40, |S| ≤ 1,000,000) 둘째 줄에 N개의 정수가 빈 칸을 사이에 두고 주어진다. 주어지는 정수의 절댓값은 100,000을 넘지 않는다.

## 출력 

첫째 줄에 합이 S가 되는 부분수열의 개수를 출력한다.

## 예제 입력 1

```
5 0
-7 -3 -2 5 8
```

## 예제 출력 1

```
1
```

## 코드 

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
    int S = Integer.parseInt(st.nextToken());

    int[] arr = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    ArrayList<Integer> aSum = new ArrayList<>();
    ArrayList<Integer> bSum = new ArrayList<>();

    combination(arr, aSum, 0, new int[N / 2 + 1], 0, N / 2);
    combination(arr, bSum, N / 2, new int[N / 2 + 1], 0, N);

    Collections.sort(bSum);
    int size = bSum.size();

    long count = 0;
    for (int i = 0; i < aSum.size(); i++) {
      int target = S - aSum.get(i);

      count += upper_bound(bSum, 0, size, target) - lower_bound(bSum, 0, size, target);
    }

    if (S == 0) System.out.println(count - 1);
    else System.out.println(count);
  }
  static void combination(int[] arr, ArrayList<Integer> sum, int ptr, int[] buffer, int size, int N) {
    if (ptr == N) {
      int result = 0;
      for (int i = 0; i < size; i++) {
        result += buffer[i];
      }
      sum.add(result);
    }
    else {
      buffer[size] = arr[ptr];
      combination(arr, sum, ptr + 1, buffer, size + 1, N);
      buffer[size] = 0;

      combination(arr, sum, ptr + 1 , buffer, size, N);
    }
  }
  static int upper_bound(ArrayList<Integer> bSum, int left, int right, int target) {
    while (left < right) {
      int mid = (left + right) / 2;
      int value = bSum.get(mid);

      if (value <= target) left = mid + 1;
      else right = mid;
    }
    return left;
  }
  static int lower_bound(ArrayList<Integer> bSum, int left, int right, int target) {
    while (left < right) {
      int mid = (left + right) / 2;
      int value = bSum.get(mid);

      if (value < target) left = mid + 1;
      else right = mid;
    }
    return left;
  }
}
```
# 가장 긴 증가하는 부분 수열 5

**플래티넘 5**

|시간 제한|	메모리 제한	|제출|	정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|3 초	|512 MB	|25421|	8520|	6075	|33.853%|

## 문제 

수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.

예를 들어, 수열 A = {10, 20, 10, 30, 20, 50} 인 경우에 가장 긴 증가하는 부분 수열은 A = {10, 20, 10, 30, 20, 50} 이고, 길이는 4이다.

## 입력 

첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000,000)이 주어진다.

둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (-1,000,000,000 ≤ Ai ≤ 1,000,000,000)

## 출력 

첫째 줄에 수열 A의 가장 긴 증가하는 부분 수열의 길이를 출력한다.

둘째 줄에는 정답이 될 수 있는 가장 긴 증가하는 부분 수열을 출력한다.

## 예쩨 입력 1

```
6
10 20 10 30 20 50
```

## 예제 출력 1

```
4
10 20 30 50
```

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
    int[] nums = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      nums[i] = Integer.parseInt(st.nextToken());
    }

    int[] buffer = new int[N];
    int size = 0;

    buffer[size++] = nums[0];
    int[] idxList = new int[N];

    for (int i = 1; i < N; i++) {
      if (buffer[size - 1] < nums[i]) {
        idxList[i] = size;
        buffer[size++] = nums[i];
      }
      // else if (buffer[size - 1] > nums[i]) 이 방식으로도 하면 틀린다.
      else {
        int idx = lower_bound(buffer, 0, size, nums[i]);
        buffer[idx] = nums[i];
        idxList[i] = idx;
      }
    }

    System.out.println(size);
    int[] result = new int[size];
    int last = N - 1;

    for (int i = size - 1; i >= 0; i--) {
      for (int j = last; j >= 0; j--) {
        if (idxList[j] == i) {
          result[i] = nums[j];
          last = j - 1;
          break;
        }
      }
    }
    /* 이 방식 쓰면 틀림
    int last = 0;
    for (int i = 0; i < size; i++) {
      for (; last < N; last++) {
        if (idxList[last] == i) {
          result[i] = nums[last++];
          break;
        }
      }
    }
    */

   for (int i = 0; i < size; i++) System.out.print(result[i] + " ");

  }
  static int lower_bound(int[] buffer, int left, int right, int target) {
    while (left < right) {
      int mid = (left + right) / 2;

      if (buffer[mid] < target) left = mid + 1;
      else right = mid;
    }
    return left;
  }
}
```
# 가장 긴 증가하는 부분 수열 2

**골드 2**

|시간 제한	|메모리 제한	|제출	|정답| 	맞힌 사람        | 	정답 비율  |
|---|---|---|---|---------------|---------|
|1 초	|512 MB|	37527|	15223	| 10594	| 41.420% |

## 문제 

수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.

예를 들어, 수열 A = {10, 20, 10, 30, 20, 50} 인 경우에 가장 긴 증가하는 부분 수열은 A = {**10**, **20**, 10, **30**, 20, **50**} 이고, 길이는 4이다.

## 입력 

첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000,000)이 주어진다.

둘째 줄에는 수열 A를 이루고 있는 A<sub>i</sub<가 주어진다. (1 ≤ A<sub>i</sub> ≤ 1,000,000)

## 출력 

첫째 줄에 수열 A의 가장 긴 증가하는 부분 수열의 길이를 출력한다.

## 예제 입력 1

```
6
10 20 10 30 20 50
```

## 예제 출력 1

```
4
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

    int[] arr = new int[N];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    int[] lis = new int[N];


    System.out.println(lis_dp(lis, arr, N));
  }
  static int binary_search(int[] lis, int left, int right, int target) {
    int mid;

    while (left < right) {
      mid = (left + right) / 2;

      if(lis[mid] < target) {
        left = mid + 1;
      }
      else {
        right = mid;
      }
    }

    return right;
  }

  static int lis_dp(int[] lis, int[] arr, int N) {
    lis[0] = arr[0];

    int len = 1;
    for (int i = 1; i < N; i++) {
      if (lis[len - 1] < arr[i]) {
        lis[len++] = arr[i];
      }
      else if (lis[len - 1] > arr[i]){
        int idx = binary_search(lis, 0, len - 1, arr[i]);
        lis[idx] = arr[i];
      }
    }
    return len;
  }
}
```
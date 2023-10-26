# 수 찾기 

**실버 4**

|시간 제한	|메모리 제한|	제출|	정답|	맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|1 초|	128 MB	|201015	|61216|	40542	|29.894%|

## 문제 

N개의 정수 A[1], A[2], …, A[N]이 주어져 있을 때, 이 안에 X라는 정수가 존재하는지 알아내는 프로그램을 작성하시오.

## 입력 

첫째 줄에 자연수 N(1 ≤ N ≤ 100,000)이 주어진다. 다음 줄에는 N개의 정수 A[1], A[2], …, A[N]이 주어진다. 다음 줄에는 M(1 ≤ M ≤ 100,000)이 주어진다. 다음 줄에는 M개의 수들이 주어지는데, 이 수들이 A안에 존재하는지 알아내면 된다. 모든 정수의 범위는 -2<sup>31</sup> 보다 크거나 같고 2<sup>31</sup>보다 작다.

## 출력 

M개의 줄에 답을 출력한다. 존재하면 1을, 존재하지 않으면 0을 출력한다.

## 예제 입력 1

```
5
4 1 5 2 3
5
1 3 7 9 5
```

## 예제 출력 1

```
1
1
0
0
1
```

## 코드 

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

    int[] nums = new int[N];

    st = new StringTokenizer(br.readLine());
    for (int i = 0 ; i < N; i++) {
      nums[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(nums);

    int M = Integer.parseInt(br.readLine());
    int[] findNums = new int[M];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < M; i++) {
      findNums[i] = Integer.parseInt(st.nextToken());
    }

    for (int i = 0; i < M; i++) {
      if (binarySearch(nums, 0, N - 1, findNums[i]) != -1) System.out.println(1);
      else System.out.println(0);
    }
  }
  static int binarySearch(int[] nums, int left, int right, int target) {
    if (left < right) {
      int mid = (left + right) / 2;

      if (nums[mid] < target) left = mid + 1;
      else right = mid;
      return binarySearch(nums, left, right, target);
    }
    else {
      if (nums[left] == target) return left;
      else return -1;
    }
  }
}
```
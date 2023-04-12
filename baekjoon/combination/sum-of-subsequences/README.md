# 부분수열의 합 

**실버 2**

|시간 제한|	메모리 제한	|제출	|정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초	|256 MB	|66476|	30579	|19884	|44.187%|

## 문제 

N개의 정수로 이루어진 수열이 있을 때, 크기가 양수인 부분수열 중에서 그 수열의 원소를 다 더한 값이 S가 되는 경우의 수를 구하는 프로그램을 작성하시오.

## 입력 

첫째 줄에 정수의 개수를 나타내는 N과 정수 S가 주어진다. (1 ≤ N ≤ 20, |S| ≤ 1,000,000) 둘째 줄에 N개의 정수가 빈 칸을 사이에 두고 주어진다. 주어지는 정수의 절댓값은 100,000을 넘지 않는다.

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

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
  static int count = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int S = Integer.parseInt(st.nextToken());

    int[] nums = new int[N];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      nums[i] = Integer.parseInt(st.nextToken());
    }

    ArrayList<Integer> list = new ArrayList<>();
    combination(nums, list, N, S, 0);

    System.out.println(count);
  }
  static void combination(int[] nums, ArrayList<Integer> list, int N, int S, int r) {
    if (r == N) {
      if (!list.isEmpty()) {
        int sum = 0;
        for (int num : list) {
          sum += num;
        }
        if (S == sum) count++;
      }
    }
    else {
      list.add(nums[r]);
      combination(nums, list, N, S, r + 1);
      list.remove(list.size() - 1);

      combination(nums, list, N, S, r + 1);
    }
  }
}
```

**최적의 코드**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, S;
  static int[] arr;

  static int ans = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    S = Integer.parseInt(st.nextToken());

    arr = new int[N];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    query(0, 0, 0);

    if (S == 0) ans--;

    System.out.println(ans);


  }
  static void query(int ptr, int sum, int size) {

    if (ptr == N) {
      if (sum == S) {
        ans++;
        return;
      }
    }
    else  {
      query(ptr + 1, sum + arr[ptr], size + 1);
      query(ptr + 1, sum, size);
    }
  }
}
```

재귀함수 방식의 특징에 주목하자

보면 알겠지만, 어차피 **모든 경우의 수는 최종 지점인 ptr == N까지 도달 할 수 있다.**

뽑고, 안뽑고를 진행해 나가면서 모든 경우의 수는 마지막 원소까지 체크를 하기 때문에 이와 같은 방식이 가능한 것이다. 

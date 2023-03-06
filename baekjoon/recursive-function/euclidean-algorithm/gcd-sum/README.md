# GCD 합

**실버 4**

|시간 제한	|메모리 제한	|제출	|정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|1 초	|128 MB	|29659|	11810|	9653|	40.711%|

## 문제

양의 정수 n개가 주어졌을 때, 가능한 모든 쌍의 GCD의 합을 구하는 프로그램을 작성하시오.

## 입력

첫째 줄에 테스트 케이스의 개수 t (1 ≤ t ≤ 100)이 주어진다. 각 테스트 케이스는 한 줄로 이루어져 있다. 각 테스트 케이스는 수의 개수 n (1 < n ≤ 100)가 주어지고, 다음에는 n개의 수가 주어진다. 입력으로 주어지는 수는 1,000,000을 넘지 않는다.

## 출력

각 테스트 케이스마다 가능한 모든 쌍의 GCD의 합을 출력한다.

## 예제 입력 1

```
3
4 10 20 30 40
3 7 5 12
3 125 15 25
```

## 예제 출력 1

```
70
3
35
```

## 나의 코드

**범위에 주의하자**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int tc = Integer.parseInt(br.readLine());

    for(int test = 0; test < tc; test++) {

      st = new StringTokenizer(br.readLine());
      int count = Integer.parseInt(st.nextToken());
      int[] nums = new int[count];

      if (count == 1) continue;

      for(int i = 0; i < count; i++) {
        nums[i] = Integer.parseInt(st.nextToken());
      }

      ArrayList<Integer> sumList = new ArrayList<>();
      for(int i = 0; i < nums.length - 1; i++) {
        for(int j = i + 1; j < nums.length; j++) {
          if (nums[i] > nums[j])
            sumList.add(euclidean(nums[i], nums[j]));
          else
            sumList.add(euclidean(nums[j], nums[i]));
        }
      }

      long ans = 0;
      for(int i = 0; i < sumList.size(); i++) {
        ans += sumList.get(i);
      }
      System.out.println(ans);
    }
  }
  static int euclidean(int x, int y) {
    if(y == 0)
      return x;
    else
      return euclidean(y, x % y);
  }
}
```
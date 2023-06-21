# 타일 채우기 

**골드 4**

|시간 제한|	메모리 제한	|제출	|정답|	맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|2 초|	128 MB	|46610|	16765	|13278|	35.864%|

## 문제 

3×N 크기의 벽을 2×1, 1×2 크기의 타일로 채우는 경우의 수를 구해보자.

## 입력 

첫째 줄에 N(1 ≤ N ≤ 30)이 주어진다.

## 출력 

첫째 줄에 경우의 수를 출력한다.

## 예제 입력 1

```
2
```

## 예제 출력 1

```
3
```

## 힌트 

아래 그림은 3×12 벽을 타일로 채운 예시이다.

![](https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/upload/images/2663_1.jpg)

## 코드 

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    int[] dp = new int[31];

    dp[0] = 1;
    dp[1] = 0;
    dp[2] = 3;
    dp[3] = 0;

    for (int i = 4; i <= N; i++) {
      int result = 0;

      result += 3 * dp[i - 2];

      for (int j = i - 4; j >= 0; j -= 2) result += 2 * dp[j];

      dp[i] = result;
    }

    System.out.println(dp[N]);
  }
}
```
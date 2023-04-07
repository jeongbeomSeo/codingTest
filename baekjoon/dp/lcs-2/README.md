# LCS 2

**골드 4**

|시간 제한|	메모리 제한	|제출	|정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|0.1 초 (하단 참고)|	256 MB|	31142	|11055	|8530	|37.580%|

## 문제 

LCS(Longest Common Subsequence, 최장 공통 부분 수열)문제는 두 수열이 주어졌을 때, 모두의 부분 수열이 되는 수열 중 가장 긴 것을 찾는 문제이다.

예를 들어, ACAYKP와 CAPCAK의 LCS는 ACAK가 된다.

## 입력

첫째 줄과 둘째 줄에 두 문자열이 주어진다. 문자열은 알파벳 대문자로만 이루어져 있으며, 최대 1000글자로 이루어져 있다.

## 출력 

첫째 줄에 입력으로 주어진 두 문자열의 LCS의 길이를, 둘째 줄에 LCS를 출력한다.

LCS가 여러 가지인 경우에는 아무거나 출력하고, LCS의 길이가 0인 경우에는 둘째 줄을 출력하지 않는다.

## 예제 입력 1

```
ACAYKP
CAPCAK
```

## 예제 출력 1

```
4
ACAK
```

## 코드 

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String str1 = br.readLine();
    String str2 = br.readLine();

    int str1Len = str1.length();
    int str2Len = str2.length();

    int[][] dp = new int[str1Len + 1][str2Len + 1];

    init_lcs(dp, str1, str2, str1Len, str2Len);

    System.out.println(dp[str1Len][str2Len]);

    if (dp[str1Len][str2Len] != 0)
      query_dp(dp, str1, str1Len, str2Len);

  }
  static void init_lcs(int[][] dp, String str1, String str2, int str1Len, int str2Len) {
    for (int i = 1; i < str1Len + 1; i++) {
      for (int j = 1; j < str2Len + 1; j++) {
        if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
        }
        else
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
      }
    }
  }

  static void query_dp(int[][] dp, String str, int str1Len, int str2Len) {
    StringBuilder sb = new StringBuilder();
    int i = str1Len;
    int j = str2Len;
    while (i > 0 && j > 0) {
      if (dp[i][j] == dp[i - 1][j]) i--;
      else if (dp[i][j] == dp[i][j - 1]) j--;
      else {
        sb.append(str.charAt(i - 1));
        i--;
        j--;
      }
      if (dp[i][j] == 0) break;
    }

    System.out.println(sb.reverse());
    return;
  }
}
```
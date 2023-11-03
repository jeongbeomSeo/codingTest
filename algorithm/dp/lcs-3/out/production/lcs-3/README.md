# LCS 3

**골드 3**

|시간 제한	|메모리 제한|	제출	|정답|	맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|2 초	|128 MB	7791|	3805	|3057|	49.804%|

## 문제

문자열과 놀기를 세상에서 제일 좋아하는 영식이는 오늘도 문자열 2개의 LCS(Longest Common Subsequence)를 구하고 있었다. 어느 날 영식이는 조교들이 문자열 3개의 LCS를 구하는 것을 보았다. 영식이도 도전해 보았지만 실패하고 말았다.

이제 우리가 할 일은 다음과 같다. 영식이를 도와서 문자열 3개의 LCS를 구하는 프로그램을 작성하라.

## 입력 

첫 줄에는 첫 번째 문자열이, 둘째 줄에는 두 번째 문자열이, 셋째 줄에는 세 번째 문자열이 주어진다. 각 문자열은 알파벳 소문자로 이루어져 있고, 길이는 100보다 작거나 같다.

## 출력 

첫 줄에 첫 번째 문자열과 두 번째 문자열과 세 번째 문자열의 LCS의 길이를 출력한다.

## 예제 입력 1

```
abcdefghijklmn
bdefg
efg
```

## 예제 출력 1

```
3
```

## 틀린 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String str1 = br.readLine();
    String str2 = br.readLine();

    int str1Len = str1.length();
    int str2Len = str2.length();

    int[][] first_dp = new int[str1Len + 1][str2Len + 1];

    String firstResult = lcs_first(first_dp, str1, str2, str1Len, str2Len);
    String str3 = br.readLine();

    int resultLen = firstResult.length();
    int str3Len = str3.length();

    if (resultLen == 0) {
      System.out.println(0);
      return;
    }

    int[][] second_dp = new int[resultLen + 1][str3Len + 1];

    lcs_dp(second_dp, firstResult, str3, resultLen, str3Len);

    System.out.println(second_dp[resultLen][str3Len]);
  }

  static void lcs_dp(int[][] dp, String str1, String str2, int str1Len, int str2Len) {
    for (int i = 1; i < str1Len + 1; i++) {
      for (int j = 1; j < str2Len + 1; j++) {
        if (str1.charAt(i - 1) == str2.charAt(j - 1))
          dp[i][j] = dp[i - 1][j - 1] + 1;
        else
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
      }
    }
  }
  static String lcs_first(int[][] dp, String str1, String str2, int str1Len, int str2Len) {
    lcs_dp(dp, str1, str2, str1Len, str2Len);

    StringBuffer sb = new StringBuffer();
    int i = str1Len;
    int j = str2Len;
    while (i > 0 && j > 0) {
      if (dp[i][j] == dp[i - 1][j]) i--;
      else if (dp[i][j] == dp[i][j - 1]) j--;
      else {
        sb.append(str1.charAt(i - 1));
        i--;
        j--;
      }
      if (dp[i][j] == 0) break;
    }
    if (sb.length() == 0) return "";
    else return sb.reverse().toString();
  }
}
```

**반례**

A: dababcf
B: ababdef
C: df

LCS(A,B): ababf
LCS(LCS(A,B),C):  f
LCS(A,B,C): df

LCS가 주는 해의 경우 유일한 해가 아닐 가능성도 있다.

또한 두 문자열에서 가장 긴 것을 뽑아 낸다고 한들 세 번째 문자열에선 오히려 짤린 부분에 더 많을 수 있다는 부분도 문제다.

해당 문제는 2차원에서 단순히 3차원으로 넓혀서 생각해보면 된다.

결국 2차원 배열에서 했던 것은 두 가지 입니다.

1. 두 문자가 같은 경우: dp[i][j] = dp[i - 1][j - 1] + 1;
2. 두 문자가 다른 경우: dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);

이것을 3차원으로 늘려서 생각을 해보면 된다.

1. 세 문자가 같은 경우: dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
2. 세 문자가 다른 경우: dp[i][j][k] = Math.max(Math.max(dp[i - 1][j][k], dp[i][j - 1][k]), dp[i][j][k - 1]);

2번의 경우 3차원으로 생각하면 정육면체가 축소되는 느낌으로 생각하면 될 것 같다.

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
    String str3 = br.readLine();

    int str1Len = str1.length();
    int str2Len = str2.length();
    int str3Len = str3.length();

    int[][][] dp = new int[str1Len + 1][str2Len + 1][str3Len + 1];

    lcs_dp(dp, str1, str2, str3, str1Len, str2Len, str3Len);

    System.out.println(dp[str1Len][str2Len][str3Len]);

  }
  static void lcs_dp(int[][][] dp, String str1, String str2, String str3, int str1Len, int str2Len, int str3Len) {
    for (int i = 1; i < str1Len + 1; i++) {
      for (int j = 1; j < str2Len + 1; j++) {
        for (int k = 1; k < str3Len + 1; k++) {
          if (str1.charAt(i - 1) == str2.charAt(j - 1) && str2.charAt(j - 1) == str3.charAt(k - 1)) {
            dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
          }
          else {
            dp[i][j][k] = Math.max(Math.max(dp[i - 1][j][k], dp[i][j - 1][k]), dp[i][j][k - 1]);
          }
        }
      }
    }
  }
}
```
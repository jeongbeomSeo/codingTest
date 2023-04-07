# LCS

**골드 5**

|시간 제한|	메모리 제한|	제출	|정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|0.1 초 (하단 참고)	|256 MB|	67737	|27534	|20201	|40.247%|

## 문제 

시간 제한	메모리 제한	제출	정답	맞힌 사람	정답 비율
0.1 초 (하단 참고)	256 MB	67737	27534	20201	40.247%
문제
LCS(Longest Common Subsequence, 최장 공통 부분 수열)문제는 두 수열이 주어졌을 때, 모두의 부분 수열이 되는 수열 중 가장 긴 것을 찾는 문제이다.

예를 들어, ACAYKP와 CAPCAK의 LCS는 ACAK가 된다.

## 입력 

첫째 줄과 둘째 줄에 두 문자열이 주어진다. 문자열은 알파벳 대문자로만 이루어져 있으며, 최대 1000글자로 이루어져 있다.

## 출력 

첫째 줄에 입력으로 주어진 두 문자열의 LCS의 길이를 출력한다.

## 예제 입력 1

```
ACAYKP
CAPCAK
```

## 예제 출력 1

```
4
```

## 잘못된 로직

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

    /*
      // A ~ Z;
      // boolean[] isVisited = new boolean[26];
    */
    int[] dp;
    int N = 0;
    // int[] dp
    // Length: [0, 길이가 작은 len)
    // value: 비교하는 문자열에서의 문자 Index

    boolean bigStr1 = false;
    if (str1Len > str2Len) {
      dp = new int[str2Len];
      N = str2Len;
      bigStr1 = true;
    } else {
      dp = new int[str1Len];
      N = str1Len;
    }

    if (bigStr1)
      init_dp(dp, str2, str1);
    else
      init_dp(dp, str1, str2);

    System.out.println(lis_dp(dp, N));


  }
  static void init_dp(int[] dp, String str, String pat) {
    int strLen = str.length();
    int patLen = pat.length();

    Arrays.fill(dp, -1);

    for (int i = 0; i < strLen; i++) {
      for (int j = 0; j < patLen; j++) {
        if (dp[i] < j && str.charAt(i) == pat.charAt(j)) {
          dp[i] = j;
        }
      }
    }
  }

  static int binary_input(int[] arr, int left, int right, int target) {
    int mid;

    while (left < right) {
      mid = (left + right) / 2;

      if (arr[mid] < target)
        left = mid + 1;
      else
        right = mid;
    }

    return right;
  }

  static int lis_dp(int[] dp, int N) {

    int[] arr = new int[N];
    arr[0] = arr[0];
    int len = 1;
    for (int i = 1; i < N; i++) {
      if (arr[len - 1] < dp[i]) {
        arr[len++] = dp[i];
      }
      else if (arr[len - 1] > dp[i]) {
        int idx = binary_input(arr, 0, len - 1, dp[i]);
        arr[idx] = dp[i];
      }
    }

    return len;
  }

}
```

해당 코드는 잘못 되었다.

최장 공통 부분 수열을 뽑아낼 때의 문자열에서 문자가 중복되는 경우 이 방식은 잘못된 값을 출력할 것입니다.

## 코드

**AC**

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

    lcs_dp(dp, str1, str2, str1Len, str2Len);

    System.out.println(dp[str1Len][str2Len]);
  }
  static void lcs_dp(int[][] dp, String str1, String str2, int str1Len, int str2Len) {
    for (int i = 1; i < str1Len + 1; i++) {
      for (int j = 1; j < str2Len + 1; j++) {
        if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
        }
        else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }
  }
}
```

두 문자열 비교하는데 2차원 배열을 많이 사용한다. 

또한 앞에 빈 문자열을 설정해서 i-1,j-1같은 것을 참조하기 쉽게 만들어 놓았다.

DP를 사용하는 문자열 비교 문제 중에 2차원 배열을 사용하는 것은 와일드카드 문제와 현재 이 문제를 접했다.

문자열을 비교하는 문제는 2차원 배열을 사용 가능한지 생각해보자.


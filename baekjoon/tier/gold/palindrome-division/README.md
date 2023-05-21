# 펠린드롬 분한

**골드 1**

|시간 제한	|메모리 제한	|제출	|정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초|	128 MB	|9609	|4502|	3291	|47.068%|

## 문제 

세준이는 어떤 문자열을 팰린드롬으로 분할하려고 한다. 예를 들어, ABACABA를 팰린드롬으로 분할하면, {A, B, A, C, A, B, A}, {A, BACAB, A}, {ABA, C, ABA}, {ABACABA}등이 있다.

분할의 개수의 최솟값을 출력하는 프로그램을 작성하시오.

## 입력  

첫째 줄에 문자열이 주어진다. 이 문자열은 알파벳 대문자로만 이루어져 있고, 최대 길이는 2,500이다.

## 출력 

첫째 줄에 팰린드롬 분할의 개수의 최솟값을 출력한다.

## 예제 입력 1

```
BBCDDECAECBDABADDCEBACCCBDCAABDBADD
```

## 예제 출력 1

```
22
```

## 예제 입력 2

```
AAAA
```

## 예제 출력 2

```
1
```

## 예제 입력 3

```
ABCDEFGH
```

## 예제 출력 3

```
8
```

## 예제 입력 4

```
QWERTYTREWQWERT
```

## 예제 출력 4

```
5
```

## 코드

**TLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
  static int MIN = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String str = br.readLine();

    int L = str.length();

    boolean[][] dp = new boolean[L][L];
    ArrayList<Palindrome> list = new ArrayList<>();

    for (int i = 0; i < L; i++) {
      dp[i][i] = true;

      if (i != L - 1 && str.charAt(i) == str.charAt(i + 1)) {
        dp[i][i + 1] = true;
        list.add(new Palindrome(i, i + 1));
      }
    }

    for (int i = 2; i <= L - 1; i++) {
      for (int j = 0; i + j < L; j++) {
        if (str.charAt(j) == str.charAt(j + i) && dp[j + 1][j + i - 1]) {
          dp[j][j + i] = true;
          list.add(new Palindrome(j, j + i));
        }
      }
    }

    Collections.sort(list);

    Combination(list, new Palindrome[list.size()], 0, 0, list.size(), L);
    System.out.println(MIN);

  }
  static void Combination(ArrayList<Palindrome> list, Palindrome[] palindromes, int ptr, int idx, int size, int L) {
    if (ptr == size) {
      int sum = 0;
      for (int i = 0; i < idx; i++) sum += palindromes[i].size;
      int remain = L - sum;
      MIN = Math.min(MIN, remain + idx);
    }
    else {
      if (idx > 0 && palindromes[idx - 1].dstIdx < list.get(ptr).srcIdx)  {
        palindromes[idx] = list.get(ptr);
        Combination(list, palindromes, ptr + 1, idx + 1, size, L);
        palindromes[idx] = null;
      }
      else if (idx == 0) {
        palindromes[idx] = list.get(ptr);
        Combination(list, palindromes, ptr + 1, idx + 1, size, L);
        palindromes[idx] = null;
      }
      Combination(list ,palindromes, ptr + 1, idx, size, L);
    }
  }
}
class Palindrome implements Comparable<Palindrome>{
  int srcIdx;
  int dstIdx;
  int size;

  Palindrome(int srcIdx, int dstIdx) {
    this.srcIdx = srcIdx;
    this.dstIdx = dstIdx;
    this.size = dstIdx - srcIdx + 1;
  }

  @Override
  public int compareTo(Palindrome o) {
    return this.srcIdx - o.dstIdx;
  }
}
```

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  static int INF = 2501;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String str = br.readLine();

    int L = str.length();

    boolean[][] is_pd = new boolean[L][L];
    for (int i = 0; i < L; i++) {
      is_pd[i][i] = true;

      if (i != L - 1 && str.charAt(i) == str.charAt(i + 1)) is_pd[i][i + 1] = true;
    }

    for (int i = 2; i <= L - 1; i++) {
      for (int j = 0; j + i < L; j++) {
        if (str.charAt(j) == str.charAt(j + i) && is_pd[j + 1][j + i - 1]) is_pd[j][j + i] = true;
      }
    }

    int[] dp = new int[L];
    dp[0] = 1;
    for (int i = 1; i < L; i++) dp[i] = INF;

    for (int i = 1; i < L; i++) {
      dp[i] = dp[i - 1] + 1;
      for (int j = 0; j < i; j++) {
        if (is_pd[j][i]) {
          if (j == 0) dp[i] = 1;
          else dp[i] = Math.min(dp[j - 1] + 1, dp[i]);
        }
      }
    }
    System.out.println(dp[L - 1]);
  }
}
```

결국 아래 사이트를 참고했습니다.

> **참고한 사이트**
> 
> [[python] 백준 1509 : 팰린드롬 분할](https://velog.io/@sunkyuj/python-%EB%B0%B1%EC%A4%80)
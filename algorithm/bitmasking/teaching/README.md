# 가르침 

|시간 제한	|메모리 제한	|제출	|정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|1 초	|128 MB (하단 참고)	|42457	|11654|	6833	|25.212%|

## 문제 

남극에 사는 김지민 선생님은 학생들이 되도록이면 많은 단어를 읽을 수 있도록 하려고 한다. 그러나 지구온난화로 인해 얼음이 녹아서 곧 학교가 무너지기 때문에, 김지민은 K개의 글자를 가르칠 시간 밖에 없다. 김지민이 가르치고 난 후에는, 학생들은 그 K개의 글자로만 이루어진 단어만을 읽을 수 있다. 김지민은 어떤 K개의 글자를 가르쳐야 학생들이 읽을 수 있는 단어의 개수가 최대가 되는지 고민에 빠졌다.

남극언어의 모든 단어는 "anta"로 시작되고, "tica"로 끝난다. 남극언어에 단어는 N개 밖에 없다고 가정한다. 학생들이 읽을 수 있는 단어의 최댓값을 구하는 프로그램을 작성하시오.

## 입력 

첫째 줄에 단어의 개수 N과 K가 주어진다. N은 50보다 작거나 같은 자연수이고, K는 26보다 작거나 같은 자연수 또는 0이다. 둘째 줄부터 N개의 줄에 남극 언어의 단어가 주어진다. 단어는 영어 소문자로만 이루어져 있고, 길이가 8보다 크거나 같고, 15보다 작거나 같다. 모든 단어는 중복되지 않는다.

## 출력

첫째 줄에 김지민이 K개의 글자를 가르칠 때, 학생들이 읽을 수 있는 단어 개수의 최댓값을 출력한다.

## 예제 입력 1

```
3 6
antarctica
antahellotica
antacartica
```

## 예제 출력 1

```
2
```

## 예제 입력 2

```
2 3
antaxxxxxxxtica
antarctica
```

## 예제 출력 2

```
0
```

## 예제 입력 3

```
9 8
antabtica
antaxtica
antadtica
antaetica
antaftica
antagtica
antahtica
antajtica
antaktica
```

## 예제 출력 3

```
3
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
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    String[] words = new String[N];
    for (int i = 0; i < N; i++) {
      words[i] = br.readLine();
    }

    int max = 0;
    if (K >= 5) {
      for (int i = (1 << K) - 1; i < (1 << 26); i++) {
        if (Integer.bitCount(i) == K) {
          if (!basic_condition(i)) continue;
          int count = 0;
          for (String word : words) {
            int j;
            for (j = 0; j < word.length(); j++) {
              int idx = convertToInt(word.charAt(j));
              if (((1 << idx) & i) != (1 << idx)) break;
            }
            if (j == word.length()) count++;
          }
          max = Math.max(max, count);
        }
      }
    }
    System.out.println(max);
  }
  static boolean basic_condition(int num) {
    int a = convertToInt('a');
    if (((1 << a) & num) != (1 << a)) return false;
    int n = convertToInt('n');
    if (((1 << n) & num) != (1 << n)) return false;
    int t = convertToInt('t');
    if (((1 << t) & num) != (1 << t)) return false;
    int i = convertToInt('i');
    if (((1 << i) & num) != (1 << i)) return false;
    int c = convertToInt('c');
    if (((1 << c) & num) != (1 << c)) return false;

    return true;
  }
  static int convertToInt(char c) {
    return c - 'a';
  }
}
```

**개선된 코드**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    String[] list = new String[N];
    for (int i = 0; i < N; i++) {
      list[i] = br.readLine();
    }
    if (K < 5) {
      System.out.println(0);
    } else {
      System.out.println(queryResult(list, N, K));
    }
  }
  private static int queryResult(String[] list, int N, int K) {

    int max = Integer.MIN_VALUE;
    for (int i = (1 << K) - 1; i < (1 << 26); i++) {
      if (Integer.bitCount(i) == K && checkValidation(i)) {
        int count = 0;
        for (int j = 0; j < N; j++) {
          if (checkCanReading(list[j], i)) {
            count++;
          }
        }
        max = Math.max(max, count);
      }
    }
    
    return max;
  }
  private static boolean checkCanReading(String word, int num) {
    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      
      if ((num & (1 << convertCharToInt(c))) == 0) return false;
    }
    return true;
  }
  private static boolean checkValidation(int num) {

    if ((num & (1 << convertCharToInt('a'))) == 0) return false;
    if ((num & (1 << convertCharToInt('n'))) == 0) return false;
    if ((num & (1 << convertCharToInt('t'))) == 0) return false;
    if ((num & (1 << convertCharToInt('i'))) == 0) return false;
    if ((num & (1 << convertCharToInt('c'))) == 0) return false;

    return true;
  }
  private static int convertCharToInt(char c) {
    return c - 'a';
  }
}
```
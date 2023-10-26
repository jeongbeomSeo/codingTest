# 단어 정렬

**실버 5**

|시간 제한	|메모리 제한|	제출|	정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초|	256 MB	|124285|	51670|	38566	|40.176%|

## 문제

알파벳 소문자로 이루어진 N개의 단어가 들어오면 아래와 같은 조건에 따라 정렬하는 프로그램을 작성하시오.

1. 길이가 짧은 것부터
2. 길이가 같으면 사전 순으로

## 입력 

첫째 줄에 단어의 개수 N이 주어진다. (1 ≤ N ≤ 20,000) 둘째 줄부터 N개의 줄에 걸쳐 알파벳 소문자로 이루어진 단어가 한 줄에 하나씩 주어진다. 주어지는 문자열의 길이는 50을 넘지 않는다.

## 출력 

조건에 따라 정렬하여 단어들을 출력한다. 단, 같은 단어가 여러 번 입력된 경우에는 한 번씩만 출력한다.

## 예제 입력 1

```
13
but
i
wont
hesitate
no
more
no
more
it
cannot
wait
im
yours
```

## 예제 출력 1

```
i
im
it
no
but
more
wait
wont
yours
cannot
hesitate
```

## 오류 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;


public class Main {
  static Comparator<String> str = new Comparator<String>() {
    @Override
    public int compare(String o1, String o2) {
      if(o1.length() - o2.length() != 0) return o1.length() - o2.length();
      else
        for(int i = 0; i < o1.length(); i++) {
          int alphabet = o1.charAt(i) - o2.charAt(i);
          if(alphabet != 0) return alphabet;
        }
        return 0;
    }
  };
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    String[] words = new String[N];
    for(int i = 0; i < N; i++) words[i] = br.readLine();

    Arrays.sort(words, str);

    for(int i = 0; i < N - 1; i++) {
      if(words[i].equals(words[i + 1])){
        int j;
        for(j = i + 1; j < N - 1; j++)
          words[j] = words[j + 1];
        words[j] = words[i];
        N--;
      }

    }

    for(int i = 0; i < N; i++) {
      if(words[i].length() == 0) break;
      System.out.println(words[i] + " ");
    }

  }
}

```

**반례**

```
4
a
a
a
b
```

**출력**

```
a
a
b
```

**정답**

```
a
b
```
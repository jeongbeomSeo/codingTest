# 가장 긴 문자열

**플래티넘 3**

|시간 제한|	메모리 제한|	제출|	정답|	맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|1 초|	256 MB|	6685|	1704|	968	|29.840%|

## 문제

상근이는 꿈에서 길이가 L인 문자열을 외웠다.

꿈에서 깬 상근이는 이 문자열을 종이에 적었다. 종이를 적던 중에 어떤 문자열은 두 번 이상 등장하는 것 같은 느낌을 받았다.

문자열이 주어졌을 때, 두 번 이상 등장한 부분 문자열 중 가장 길이가 긴 것을 찾는 프로그램을 작성하시오. (부분문자열은 겹쳐서 등장할 수도 있다)

## 입력 

첫째 줄에 L이 주어진다. (1 ≤ L ≤ 200,000) 다음 줄에는 길이가 L이면서 알파벳 소문자로 이루어진 문자열이 주어진다.

## 출력 

첫째 줄에 두 번 이상 등장하는 부분 문자열 중 길이가 가장 긴 것의 길이를 출력한다. 만약 그러한 문자열이 없을 때는 0을 출력한다.

## 예제 입력 1

```
11
sabcabcfabc
```

## 예제 출력 1

```
3
```

## 예제 입력 2

```
18
trutrutiktiktappop
```

## 예제 출력 2

```
4
```

## 예제 입력 3

```
6
abcdef
```

## 예제 출력 3

```
0
```

## 코드

**TLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  static boolean rkMatch(String txt, int size) {
    int txtLen = txt.length();

    long[] hashTable = new long[txtLen - size + 1];

    for(int i = 0; i <= txtLen - size; i++) {
      long power = 2;
      long hash = 0;
      for(int j = i; j < size; j++) {
        hash += txt.charAt(j) * power;
        power *= 2;
      }
      hashTable[i] = hash;
    }

    for(int i = 0; i < hashTable.length - 1; i++) {
      for(int j = i + 1; j < hashTable.length; j++) {
        if(hashTable[i] == hashTable[j]) {
          int pt = i; int pp = j;
          while (pt < i + size && txt.charAt(pt) == txt.charAt(pp)) {
            pt++;
            pp++;
          }
          if(pt == i + size) return true;
        }
      }
    }
    return false;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int L = Integer.parseInt(br.readLine());
    String txt = br.readLine();

    int size = L - 1;
    while (size > 1) {
      int i = 0;
      if(rkMatch(txt, size)) {
        System.out.println(size);
        break;
      }
      size--;
    }
    if(size == 1) System.out.println(0);

  }
}
```
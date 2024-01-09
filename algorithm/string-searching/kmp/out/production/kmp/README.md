# 찾기

**플래티넘 5**

|시간 제한	|메모리 제한|	제출	정답|	맞힌 사람|	정답 비율|
|---|---|---|---|---|
|2 초	|256 MB	|34026|	10322	|5919|	30.052%|

## 문제 

워드프로세서 등을 사용하는 도중에 찾기 기능을 이용해 본 일이 있을 것이다. 이 기능을 여러분이 실제로 구현해 보도록 하자.

두 개의 문자열 P와 T에 대해, 문자열 P가 문자열 T 중간에 몇 번, 어느 위치에서 나타나는지 알아내는 문제를 '문자열 매칭'이라고 한다. 워드프로세서의 찾기 기능은 이 문자열 매칭 문제를 풀어주는 기능이라고 할 수 있다. 이때의 P는 패턴이라고 부르고 T는 텍스트라고 부른다.

편의상 T의 길이를 n, P의 길이를 m이라고 하자. 일반적으로, n ≥ m이라고 가정해도 무리가 없다.  n<m이면 어차피 P는 T중간에 나타날 수 없기 때문이다. 또, T의 i번째 문자를 T[i]라고 표현하도록 하자. 그러면 물론, P의 i번째 문자는 P[i]라고 표현된다.

```
      1 2 3 4 5 6 7 8 9 …
T : [ A B C D A B C D A B D E ]
      | | | | | | X
P : [ A B C D A B D ]
      1 2 3 4 5 6 7
```

문자열 P가 문자열 T 중간에 나타난다는 것, 즉 문자열 P가 문자열 T와 매칭을 이룬다는 것이 어떤 것인지 위와 아래의 두 예를 통해 알아보자. 위의 예에서 P는, T의 1번 문자에서 시작하는 매칭에 실패했다. T의 7번 문자 T[7]과, P의 7번 문자 P[7]이 서로 다르기 때문이다.

그러나 아래의 예에서 P는, T의 5번 문자에서 시작하는 매칭에 성공했다. T의 5～11번 문자와 P의 1～7번 문자가 서로 하나씩 대응되기 때문이다.

```
      1 2 3 4 5 6 7 8 9 …
T : [ A B C D A B C D A B D E ]
              | | | | | | |
P :         [ A B C D A B D ]
              1 2 3 4 5 6 7
```

가장 단순한 방법으로, 존재하는 모든 매칭을 확인한다면, 시간복잡도가 어떻게 될까? T의 1번 문자에서 시작하는 매칭이 가능한지 알아보기 위해서, T의 1～m번 문자와 P의 1～m번 문자를 비교한다면 최대 m번의 연산이 필요하다. 이 비교들이 끝난 후, T의 2번 문자에서 시작하는 매칭이 가능한지 알아보기 위해서, T의 2～m+1번 문자와 P의 1～m번 문자를 비교한다면 다시 최대 m번의 연산이 수행된다. 매칭은 T의 n-m+1번 문자에서까지 시작할 수 있으므로, 이러한 방식으로 진행한다면 O( (n-m+1) × m ) = O(nm) 의 시간복잡도를 갖는 알고리즘이 된다.

더 좋은 방법은 없을까? 물론 있다. 위에 제시된 예에서, T[7] ≠ P[7] 이므로 T의 1번 문자에서 시작하는 매칭이 실패임을 알게 된 순간으로 돌아가자. 이때 우리는 매칭이 실패라는 사실에서, T[7] ≠ P[7] 라는 정보만을 얻은 것이 아니다. 오히려 i=1…6에 대해 T[i] = P[i] 라고 하는 귀중한 정보를 얻지 않았는가? 이 정보를 십분 활용하면, O(n)의 시간복잡도 내에 문자열 매칭 문제를 풀어내는 알고리즘을 설계할 수 있다.

P 내부에 존재하는 문자열의 반복에 주목하자. P에서 1, 2번 문자 A, B는 5, 6번 문자로 반복되어 나타난다. 또, T의 1번 문자에서 시작하는 매칭이 7번 문자에서야 실패했으므로 T의 5, 6번 문자도 A, B이다.

따라서 T의 1번 문자에서 시작하는 매칭이 실패한 이후, 그 다음으로 가능한 매칭은 T의 5번 문자에서 시작하는 매칭임을 알 수 있다! 더불어, T의 5～6번 문자는 P의 1～2번 문자와 비교하지 않아도, 서로 같다는 것을 이미 알고 있다! 그러므로 이제는 T의 7번 문자와 P의 3번 문자부터 비교해 나가면 된다.

이제 이 방법을 일반화 해 보자. T의 i번 문자에서 시작하는 매칭을 검사하던 중 T[i+j-1] ≠ P[j]임을 발견했다고 하자. 이렇게 P의 j번 문자에서 매칭이 실패한 경우, P[1…k] = P[j-k…j-1]을 만족하는 최대의 k(≠j-1)에 대해 T의 i+j-1번 문자와 P의 k+1번 문자부터 비교를 계속해 나가면 된다.

이 최대의 k를 j에 대한 함수라고 생각하고, 1～m까지의 각 j값에 대해 최대의 k를 미리 계산해 놓으면 편리할 것이다. 이를 전처리 과정이라고 부르며, O(m)에 완료할 수 있다.

이러한 원리를 이용하여, T와 P가 주어졌을 때, 문자열 매칭 문제를 해결하는 프로그램을 작성하시오.

## 입력 

첫째 줄에 문자열 T가, 둘째 줄에 문자열 P가 주어진다. T와 P의 길이 n, m은 1이상 100만 이하이고, 알파벳 대소문자와 공백으로만 이루어져 있다.

## 출력 

첫째 줄에, T 중간에 P가 몇 번 나타나는지를 나타내는 음이 아닌 정수를 출력한다. 둘째 줄에는 P가 나타나는 위치를 차례대로 공백으로 구분해 출력한다. 예컨대, T의 i～i+m-1번 문자와 P의 1～m번 문자가 차례로 일치한다면, i를 출력하는 식이다.

## 예제 입력 1

```
ABC ABCDAB ABCDABCDABDE
ABCDABD
```

## 예제 출력 1

```
1
16
```

## 풀이 방식 

해당 문제는 책에서 배운 KMP법을 문제를 풀면서 학습에 이해를 돕기 위해 풀었다.

KMP법에서 중요한 개념은 두가지가 있습니다.
1. **접두사(Prefix)** 와 **접미사(Suffix)** 입니다. 
2. **pi배열**을 만드는 것입니다. 즉, 건너뛰기 표를 만든다고 보면 됩니다. 

## 틀린 코드 

**WA**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

  static int[] skip;
  static int matchCount;
  static ArrayList<Integer> matchIdx;

  static void kmp(String txt, String pat) {
    int pt = 1;
    int pp = 0;

    skip[pt] = 0;
    // 일치하는 숫자 개수로 생각(Search 과정에선 커서 개념이기 때문에 잘 생각해야 한다.)
    while (pt != pat.length()) {
      if(txt.charAt(pt) == pat.charAt(pp))
        skip[++pt] = ++pp;
      else if(pp == 0)
        skip[++pt] = pp;
      else
        pp = skip[pp];
    }

    pt = pp = 0;
    while (pt != txt.length()) {
      // 숫자가 일치한 경우 커서를 옮겨주는 방식
      if(txt.charAt(pt) == pat.charAt(pp)) {
        pt++;
        pp++;
        // 전부 일치할 경우(해당 코드가 이 안에 위치해야만 다음 반복으로 넘어가기 전에 처리를 해줄 수 있다)
        if(pp == pat.length()) {
          // 마지막 숫자까지 일치한 경우 pt 커서 또한 움직여줘야 skip배열에 맞게 작용하는 것에 주의.
          matchCount++;
          matchIdx.add(pt - pp + 1);
          pp = skip[pp];
        }
      } else if(pp == 0)
        pt++;
      else
        pp = skip[pp];
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // 공백도 문자열에 포함되어 있기 때문에 StringTokenizer사용 X
    String txt = br.readLine();
    String pat = br.readLine();

    skip = new int[pat.length() + 1];
    matchCount = 0;
    matchIdx = new ArrayList<>();

    kmp(txt, pat);

    System.out.println(matchCount);
    for(int i = 0; i < matchCount; i++) {
      System.out.print(matchIdx.get(i) + " ");
    }
  }
}
```

**반례**

```
aabbaa
ab
```

**output**
```
2
2 3
```

해당 코드는 표만들기에서 오류가 있다.

```java
    while (pt != pat.length()) {
      if(txt.charAt(pt) == pat.charAt(pp))
        skip[++pt] = ++pp;
      else if(pp == 0)
        skip[++pt] = pp;
      else
        pp = skip[pp];
    }
```

여기서 조건문에 들어가야 될 것은 ```txt.charAt(pt) == pat.charAt(pp)```가 아니라 

```pat.charAt(pt) == pat.charAt(pp)```이다.

표 만들기의 원리는 기본적으로 index는 일치하는 숫자의 갯수 그리고 pt와 pp는 커서(포인터)라고 생각하면 된다.

## 나의 코드 

**AC**

- **메모리**: 106200 KB
- **시간**: 3232ms

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayaList;
import java.util.StringTokenizer;

public class Main {

  static int[] skip;
  static int matchCount;
  static ArrayList<Integer> matchIdx;

  static void kmp(String txt, String pat) {
    int pt = 1;
    int pp = 0;

    skip[pt] = 0;
    // pt와 pp는 커서를 의미하는 것을 기억하자
    while (pt != pat.length()) {
      if(pat.charAt(pt) == pat.charAt(pp))
        skip[++pt] = ++pp;
      else if(pp == 0)
        skip[++pt] = pp;
      else
        pp = skip[pp];
    }

    pt = pp = 0;
    while (pt != txt.length()) {
      // 숫자가 일치한 경우 커서를 옮겨주는 방식
      if(txt.charAt(pt) == pat.charAt(pp)) {
        pt++;
        pp++;
        // 전부 일치할 경우(해당 코드가 이 안에 위치해야만 다음 반복으로 넘어가기 전에 처리를 해줄 수 있다)
        if(pp == pat.length()) {
          // 마지막 숫자까지 일치한 경우 pt 커서 또한 움직여줘야 skip 배열에 맞게 작용하는 것에 주의
          matchCount++;
          matchIdx.add(pt - pp + 1);
          pp = skip[pp];
        }
      } else if(pp == 0)
        pt++;
      else
        pp = skip[pp];
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // 공백도 문자열에 포함되어 있기 때문에 StringTokenizer 사용 X
    String txt = br.readLine();
    String pat = br.readLine();

    skip = new int[pat.length() + 1];
    matchCount = 0;
    matchIdx = new ArrayList<>();

    kmp(txt, pat);

    System.out.println(matchCount);
    for(int i = 0; i < matchCount; i++) {
      if(i != matchCount - 1)
        System.out.print(matchIdx.get(i) + " ");
      else
        System.out.print(matchIdx.get(i));
    }
  }
}
```

## 참고할 만한 사이트 

- [[알고리즘 개념] KMP - 문자열 검색 알고리즘(JAVA 코드) ](https://cano721.tistory.com/21)
- [알고리즘 - KMP 알고리즘 : 문자열 검색을 위한 알고리즘 | ChanBLOG](https://chanhuiseok.github.io/posts/algo-14/) 
- [KMP : 문자열 검색 알고리즘 - 멍멍멍](https://bowbowbow.tistory.com/6)

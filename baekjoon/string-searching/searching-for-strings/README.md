# Searching for Strings

**플래티넘 4**

|시간 제한|	메모리 제한	|제출|	정답|	맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|2 초|	512 MB	|416|	93|	47	|16.319%|

## 문제 

You’re given a string N, called the needle, and a string H, called the haystack, both of which contain only lowercase letters “a”..“z”.

Write a program to count the number of distinct permutations of N which appear as a substring of H at least once. Note that N can have anywhere between 1 and |N|! distinct permutations in total – for example, the string “aab” has 3 distinct permutations (“aab”, “aba”, and “baa”).

## 입력 

The first line contains N (1 ≤ |N| ≤ 200 000), the needle string.

The second line contains H (1 ≤ |H| ≤ 200 000), the haystack string.

## 출력 

Output consists of one integer, the number of distinct permutations of N which appear as a substring of H.

## 예제 입력 1

```
aab
abacabaa
```

## 예제 출력 1

```
2
```

The permutations “aba” and “baa” each appear as substrings of H (the former appears twice), while the permutation “aab” does not appear.

## 풀이 과정

1. String N을 순열로 배치 하기
2. 그 이후 H와 비교해가며 그냥 나오는 index 몇개인지 확인하면 끝 

위와 같은 방식으로 해결하려고 했지만 생각보다 String N을 순열로 배치하는 것이 쉬워 보이지 않는다. 
맨 앞글자를 맨뒤로 넘겨주면서 바뀌는 배치 때마다 함수를 작동시키면 된다. 그 과정을 다시 맨 처음 정렬이 나올 때 까지 실행하면 된다.

하지만, 이 방식은 너무 오래 걸릴 것 같다. 먼저 N의 최대 개수가 20,000이고 H도 200,000인데 필요 과정 순서를 계산해보자면, 

먼저, N을 길이를 NLen 이라고 하고, H의 길이를 HLen이라고 할때, 

N을 순열로 넘겨 가면서 확인하려면 문자열을 확인한다면

**시간복잡도** = ( NLen(모든 요소) * NLen(이동 횟수) / (NLen / 26)(같은 문자 나올 경우) ) * (HLen * NLen)(Boyer Moore의 시간 복잡도)

아마도 이정도 걸린다고 추측된다.

배치 한다고 생각하지 말고 다르게 생각을 해보자.

결국 검색 키워드가 주어졌을 때 그것이 **하나의 문자열로 고정된 것이 아니라 해당 문자들로 구성된 모든 문자열을 검색 키워드로 가지겠다는 것**이니깐

굳이 문자열 탐색에 얽매일 필요가 없어 보인다.

1. 'a' ~ 'z'의 크기만큼 가지는 int형 배열을 생성
2. 검색 키워드를 탐색해 가면서 나오는 문자에 해당하는 배열 공간에 +1
   1. 예를 들어, c가 나왔을 때 ```keyword['c' - 'a']++;```
3. 본문을 검색 키워드의 문자열 길이 만큼 잘라가면서 탐색 시작
   1. keyword 배열을 다른 배열에 복사후 진행
   2. 본문의 문자를 탐색하면서 문자 나오면 -1씩 count
   3. 마지막 문자까지 도달했으면 성공
> **시간 단축**
> 1. 만약 배열의 요소가 음수로 가면 다른 문자가 존재하거나 더 많은 문자가 존재하는 것이므로 중단
> 2. 배열이 일치할시 본문에서 뽑아낸 문자열에서 가장 앞의 문자가 해당 문자열 다음 문자와 글자가 일치해야 한다. 
>    1. 즉, 본문의 문자열이 abcdefa와 같이 주어졌을 때 abcdef를 뽑아 냈을 때 갯수가 일치하다면 bcdefa도 일치 하기 때문에 바로 넘어간다.
> 3. Set Interface를 사용해서 중복된 형태의 순열을 방지
이렇게 해도 시간 초과가 나온다. 

---

그래서 Rabin Karp 방법도 응용해서 사용해 보기로 했다.

자리 값이 따로 있는 것이 아닌 그냥 char형 그 상태로 더해 가면서 해시값의 합을 구한다.

예를 들어 abc가 있다면 'a' + 'b' + 'c' 그냥 이와 같이 구하는 것이다.

그런 후 Rabin Karp 방법과 동일하게 해시 값으로 비교를 해준 후 일치 할 경우 그때 갯수를 확인하는 방법으로 하자.

넣어주는 방법은 Set Inteface에 넣어줘서 중복을 막았다.

## 코드 

**TLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
   static int charToInt(char c) {
      return c - 'a';
   }
   static int[] keyword(String pat) {
      // 'a' ~ 'z' => 26개
      int[] keyword = new int[26];
      for(int i = 0; i < pat.length(); i++) {
         int idx = charToInt(pat.charAt(i));
         keyword[idx]++;
      }

      for(int i = 0; i < keyword.length; i++) {
         if(keyword[i] == 0) keyword[i] = -1;
      }

      return keyword;
   }

   static int matchCount(String txt, int[] keyword, int patLen) {
      int shift = 0;
      int txtLen = txt.length();

      Set<String> list = new HashSet<>();


      while (shift <= txtLen - patLen) {
         int[] word = keyword.clone();
         int pt = patLen - 1;

         while (pt >= 0) {
            int idx = charToInt(txt.charAt(shift + pt));
            if(word[idx] <= 0) break;
            else {
               word[idx]--;
               pt--;
            }
         }
         // 탑색 성공
         if(pt < 0) {
            String str = txt.substring(shift, shift + patLen);
            list.add(str);
            while (shift != txtLen - patLen &&
                    txt.charAt(shift) == txt.charAt(shift + patLen)) {
               shift++;
               String strAlpha = txt.substring(shift, shift + patLen);
               list.add(strAlpha);
            }
            if(shift != txtLen - patLen &&
                    word[charToInt(txt.charAt(shift + patLen))] < 0) {
               shift += patLen + 1;
            }
            else
               shift += 2;
         } else
            shift += pt + 1;
      }
      return list.size();
   }
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      String N = br.readLine();
      String H = br.readLine();

      int[] keyword = keyword(N);
      int patLen = N.length();

      int count = matchCount(H, keyword, patLen);

      System.out.println(count);
   }
}
```

**Utilize Rabin-Karp Alogrihtm**

**TLE**
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
   static int charToInt(char c) {
      return c - 'a';
   }
   static int[] keyword(String pat) {
      // 'a' ~ 'z' => 26개
      int[] keyword = new int[26];
      for(int i = 0; i < pat.length(); i++) {
         int idx = charToInt(pat.charAt(i));
         keyword[idx]++;
      }
      return keyword;
   }

   static boolean checkKeyword(String txt, int pt, int patLen, int[] keyword) {
      for(int i = 0; i < patLen; i++) {
         int idx = charToInt(txt.charAt(pt + i));
         if(keyword[idx] > 0) keyword[idx]--;
         else return false;
      }
      return true;
   }

   static int matchCount(String txt, String pat) {
      int txtHash, patHash;
      txtHash = patHash = 0;
      int pt = 0;

      int txtLen = txt.length();
      int patLen = pat.length();

      int[] keyword = keyword(pat);

      Set<String> set = new HashSet<>();

      while (pt <= txtLen - patLen) {
         if(pt == 0) {
            for(int i = 0; i < patLen; i++) {
               txtHash += txt.charAt(i);
               patHash += pat.charAt(i);
            }
         } else {
            txtHash = txtHash - txt.charAt(pt - 1) + txt.charAt(pt + patLen - 1);
         }
         if(txtHash == patHash) {
            int[] word = keyword.clone();
            if(checkKeyword(txt, pt, patLen, word)) {
               String str = txt.substring(pt, pt + patLen);
               set.add(str);
            }
         }
         pt++;
      }
      return set.size();
   }
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      String N = br.readLine();
      String H = br.readLine();

      System.out.println(matchCount(H, N));
   }
}
```
## 문제점

1. 주어진 문자열에서 문자 갯수 검사 하는 최적화 방법을 모르는 상태 
2. Rabin Karp의 MOD(나머지)를 재대로 활용할 줄 모른다.

## 참고한 사이트

- [라빈 카프 알고리즘(Rabin-Karp Algorithm) (수정: 2019-08-29) : 네이버 블로그](https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=kks227&logNo=220927272165)
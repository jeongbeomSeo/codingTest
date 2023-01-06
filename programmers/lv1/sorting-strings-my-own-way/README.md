# 문자열 내 마음대로 정렬하기

## 문제 설명

문자열로 구성된 리스트 strings와, 정수 n이 주어졌을 때, 각 문자열의 인덱스 n번째 글자를 기준으로 오름차순 정렬하려 합니다. 예를 들어 strings가 ["sun", "bed", "car"]이고 n이 1이면 각 단어의 인덱스 1의 문자 "u", "e", "a"로 strings를 정렬합니다.

## 제한 조건

strings는 길이 1 이상, 50이하인 배열입니다.
strings의 원소는 소문자 알파벳으로 이루어져 있습니다.
strings의 원소는 길이 1 이상, 100이하인 문자열입니다.
모든 strings의 원소의 길이는 n보다 큽니다.
인덱스 1의 문자가 같은 문자열이 여럿 일 경우, 사전순으로 앞선 문자열이 앞쪽에 위치합니다.

## 입출력 예

- 입력값: {"sun", "bed", "car"}, 1
  - 기댓값: {"car", "bed", "sun"}
- 입력값: {"abce", "abcd", "cdx"}, 2
  - 기댓값: {"abcd", "abce", "cdx"}

|strings|	n|	return|
|---|---|---|
|["sun", "bed", "car"]|	1|	["car", "bed", "sun"]|
|["abce", "abcd", "cdx"]|	2|	["abcd", "abce", "cdx"]|

## 입출력 예 설명

**입출력 예 1**
"sun", "bed", "car"의 1번째 인덱스 값은 각각 "u", "e", "a" 입니다. 이를 기준으로 strings를 정렬하면 ["car", "bed", "sun"] 입니다.

**입출력 예 2**
"abce"와 "abcd", "cdx"의 2번째 인덱스 값은 "c", "c", "x"입니다. 따라서 정렬 후에는 "cdx"가 가장 뒤에 위치합니다. "abce"와 "abcd"는 사전순으로 정렬하면 "abcd"가 우선하므로, 답은 ["abcd", "abce", "cdx"] 입니다.

## 나의 코드

```java
import java.util.Arrays;

class Solution {
  public String[] solution(String[] strings, int n) {
    for(int i = 0; i < strings.length; i++){
      for(int j = i + 1; j < strings.length; j++) {
        if(strings[i].charAt(n) > strings[j].charAt(n)) {
          String temp = strings[i];
          strings[i] = strings[j];
          strings[j] = temp;
        }
        else  Arrays.sort(strings, i , j);
      }
    }
    return strings;
  }
}
```
**채점 결과**

정확성: 16.7
합계: 16.7 / 100.0
---
```java
import java.util.Arrays;

class Solution {
  public String[] solution(String[] strings, int n) {
    for(int i = 0; i < strings.length; i++){
      for(int j = i + 1; j < strings.length; j++) {
        if(strings[i].charAt(n) > strings[j].charAt(n)) {
          String temp = strings[i];
          strings[i] = strings[j];
          strings[j] = temp;
        }
        else if(strings[i].charAt(n) == strings[j].charAt(n)) {
          String[] temp = new String[2];
          temp[0] = strings[i];
          temp[1] = strings[j];
          Arrays.sort(temp);
          strings[i] = temp[0];
          strings[j] = temp[1];
        };
      }
    }
    return strings;
  }
}
```
**채점 결과**

통과 

## 다른 사람 풀이

```java
import java.util.*;

class Solution {
    public String[] solution(String[] strings, int n) {
        String[] answer = {};
        ArrayList<String> arr = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            arr.add("" + strings[i].charAt(n) + strings[i]);
        }
        Collections.sort(arr);
        answer = new String[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            answer[i] = arr.get(i).substring(1, arr.get(i).length());
        }
        return answer;
    }
}
```

해당 풀이 보고 소름이 돋았다. 문자를 앞으로 꺼내서 문자를 비교함으로써

Index n위치에서 char 다른 경우와 같은 경우 한꺼번에 처리가 되었다.

다만 코드의 실행 속도는 느린 편이다. 반복문안에서 실행하는 코드가 많고, sort를 전체 String Array에 하면서 대소 비교를 전부 다 해버리기 때문이다.

하지만, 해당 코드를 생각하지 못한 것이 아쉽다고 생각한다.

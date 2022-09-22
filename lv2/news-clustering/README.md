# 뉴스 클러스터링

## 문제 설명

여러 언론사에서 쏟아지는 뉴스, 특히 속보성 뉴스를 보면 비슷비슷한 제목의 기사가 많아 정작 필요한 기사를 찾기가 어렵다. Daum 뉴스의 개발 업무를 맡게 된 신입사원 튜브는 사용자들이 편리하게 다양한 뉴스를 찾아볼 수 있도록 문제점을 개선하는 업무를 맡게 되었다.

개발의 방향을 잡기 위해 튜브는 우선 최근 화제가 되고 있는 "카카오 신입 개발자 공채" 관련 기사를 검색해보았다.

- 카카오 첫 공채..'블라인드' 방식 채용
- 카카오, 합병 후 첫 공채.. 블라인드 전형으로 개발자 채용
- 카카오, 블라인드 전형으로 신입 개발자 공채
- 카카오 공채, 신입 개발자 코딩 능력만 본다
- 카카오, 신입 공채.. "코딩 실력만 본다"
- 카카오 "코딩 능력만으로 2018 신입 개발자 뽑는다"

기사의 제목을 기준으로 "블라인드 전형"에 주목하는 기사와 "코딩 테스트"에 주목하는 기사로 나뉘는 걸 발견했다. 튜브는 이들을 각각 묶어서 보여주면 카카오 공채 관련 기사를 찾아보는 사용자에게 유용할 듯싶었다.

유사한 기사를 묶는 기준을 정하기 위해서 논문과 자료를 조사하던 튜브는 "자카드 유사도"라는 방법을 찾아냈다.

자카드 유사도는 집합 간의 유사도를 검사하는 여러 방법 중의 하나로 알려져 있다. 두 집합 ```A```, ```B``` 사이의 자카드 유사도 ```J(A, B)```는 두 집합의 교집합 크기를 두 집합의 합집합 크기로 나눈 값으로 정의된다.

예를 들어 집합 ```A``` = {1, 2, 3}, 집합 ```B``` = {2, 3, 4}라고 할 때, 교집합 ```A ∩ B``` = {2, 3}, 합집합 ```A ∪ B``` = {1, 2, 3, 4}이 되므로, 집합 ```A```, ```B``` 사이의 자카드 유사도 ```J(A, B)``` = 2/4 = 0.5가 된다. 집합 A와 집합 B가 모두 공집합일 경우에는 나눗셈이 정의되지 않으니 따로 ```J(A, B) ```= 1로 정의한다.

자카드 유사도는 원소의 중복을 허용하는 다중집합에 대해서 확장할 수 있다. 다중집합 ```A```는 원소 "1"을 3개 가지고 있고, 다중집합 ```B```는 원소 "1"을 5개 가지고 있다고 하자. 이 다중집합의 교집합 ```A ∩ B```는 원소 "1"을 min(3, 5)인 3개, 합집합 ```A ∪ B```는 원소 "1"을 max(3, 5)인 5개 가지게 된다. 다중집합 ```A``` = {1, 1, 2, 2, 3}, 다중집합 ```B``` = {1, 2, 2, 4, 5}라고 하면, 교집합 ```A ∩ B``` = {1, 2, 2}, 합집합 ```A ∪ B``` = {1, 1, 2, 2, 3, 4, 5}가 되므로, 자카드 유사도 ```J(A, B)``` = 3/7, 약 0.42가 된다.

이를 이용하여 문자열 사이의 유사도를 계산하는데 이용할 수 있다. 문자열 "FRANCE"와 "FRENCH"가 주어졌을 때, 이를 두 글자씩 끊어서 다중집합을 만들 수 있다. 각각 {FR, RA, AN, NC, CE}, {FR, RE, EN, NC, CH}가 되며, 교집합은 {FR, NC}, 합집합은 {FR, RA, AN, NC, CE, RE, EN, CH}가 되므로, 두 문자열 사이의 자카드 유사도 ```J("FRANCE", "FRENCH")``` = 2/8 = 0.25가 된다.

## 입력 형식

- 입력으로는 ```str1```과 ```str2```의 두 문자열이 들어온다. 각 문자열의 길이는 2 이상, 1,000 이하이다.
- 입력으로 들어온 문자열은 두 글자씩 끊어서 다중집합의 원소로 만든다. 이때 영문자로 된 글자 쌍만 유효하고, 기타 공백이나 숫자, 특수 문자가 들어있는 경우는 그 글자 쌍을 버린다. 예를 들어 "ab+"가 입력으로 들어오면, "ab"만 다중집합의 원소로 삼고, "b+"는 버린다.
- 다중집합 원소 사이를 비교할 때, 대문자와 소문자의 차이는 무시한다. "AB"와 "Ab", "ab"는 같은 원소로 취급한다.

## 출력 형식

입력으로 들어온 두 문자열의 자카드 유사도를 출력한다. 유사도 값은 0에서 1 사이의 실수이므로, 이를 다루기 쉽도록 65536을 곱한 후에 소수점 아래를 버리고 정수부만 출력한다.

## 예제 입출력 

- 입력값: "FRANCE", "french"
  - 기댓값: 	16384
- 입력값: 	"handshake", "shake hands"
  - 기댓값: 65536
- 입력값: "aa1+aa2", "AAAA12"
  - 기댓값: 43690
- 입력값: "E=M*C^2", "e=m*c^2"
  - 기댓값: 65536

|str1|	str2|	answer|
|---|---|---|
|FRANCE|	french|	16384|
|handshake|	shake hands	|65536|
|aa1+aa2	|AAAA12|	43690|
|E=M*C^2	|e=m*c^2	|65536|

## 풀이 과정

일단, 처음에 코드를 짜면서 글자 두개씩 엮어 줄 때 2글짜씩 엮어가면서 진행해 나가는 줄 알았는데, 2글자 엮고 한 글자 뒤로 가서 두 글자 엮고 하는 방식이였다.

즉, 4글자가 있으면 두 쌍이 나오는 것이 아니라 세 쌍이 나오는 것이였다.

그리고 공집합 처리도 빼먹어서 처리해줬다.

하지만 가장 심각한 문제는 교집합 처리였다.

현재 첫번쨰 풀이로는 교집합 개념이 아니라 겹치는 글자쌍이 str2에 나오는 것보다 str1에 더 많다면 max(str1, str2) 개념이 되어 버린다.

해당 문제를 해결해야 한다.

## 개념 정리

- ArrayList에서 remove를 사용해서 특정 value 값을 삭제하려고 매개변수로 넣어 줬을 때 하나만 삭제되는 것이 아니라 전부 삭제된다.


## 나의 코드

```java
import java.util.ArrayList;

class Solution {
  public int solution(String str1, String str2) {
    int answer = 0;

    // 대문자 -> 소문자
    str1 = str1.toLowerCase();
    str2 = str2.toLowerCase();

    ArrayList<String> listStr1 = new ArrayList<>();
    ArrayList<String> listStr2 = new ArrayList<>();

    int i = 0;
    int j = 0;
    String eleStr1 = "";
    String eleStr2 = "";
    while(true) {
      if (i < str1.length()) {
        if (str1.charAt(i) >= 'a' && str1.charAt(i) <= 'z') eleStr1 += str1.charAt(i);
        else if(eleStr1.length() == 1) eleStr1 = "";
        if (eleStr1.length() == 2) {
          listStr1.add(eleStr1);
          eleStr1 = "";
          i--;
        }
        i++;
      }
      if (j < str2.length()) {
        if (str2.charAt(j) >= 'a' && str2.charAt(j) <= 'z') eleStr2 += str2.charAt(j);
        else if(eleStr2.length() == 1) eleStr2 = "";
        if (eleStr2.length() == 2) {
          listStr2.add(eleStr2);
          eleStr2 = "";
          j--;
        }
        j++;
      }
      if(i == str1.length() && j == str2.length()) break;
    }

    int sumCount = 0;
    int interCount = 0;

    for(i = 0; i < listStr1.size(); i++ ) {
      interCount = listStr2.contains(listStr1.get(i)) ? interCount + 1 : interCount;
    }

    sumCount = listStr1.size() + listStr2.size() - interCount;
    if(sumCount == 0) answer = 65536;
    else answer = (int)( ( (double) interCount / (double)sumCount ) * 65536 );


    return answer;
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    String str1 = 		"handshake";
    String str2 = "shake hands";
    System.out.println(solution.solution(str1, str2));
  }
}
```

**채점 결과**

정확성: 61.5

합계: 61.5 / 100.0

------


```java
import java.util.ArrayList;

class Solution {
  public int solution(String str1, String str2) {
    int answer = 0;

    // 대문자 -> 소문자
    str1 = str1.toLowerCase();
    str2 = str2.toLowerCase();

    ArrayList<String> listStr1 = new ArrayList<>();
    ArrayList<String> listStr2 = new ArrayList<>();

    int i = 0;
    int j = 0;
    String eleStr1 = "";
    String eleStr2 = "";
    while(true) {
      if (i < str1.length()) {
        if (str1.charAt(i) >= 'a' && str1.charAt(i) <= 'z') eleStr1 += str1.charAt(i);
        else if(eleStr1.length() == 1) eleStr1 = "";
        if (eleStr1.length() == 2) {
          listStr1.add(eleStr1);
          eleStr1 = "";
          i--;
        }
        i++;
      }
      if (j < str2.length()) {
        if (str2.charAt(j) >= 'a' && str2.charAt(j) <= 'z') eleStr2 += str2.charAt(j);
        else if(eleStr2.length() == 1) eleStr2 = "";
        if (eleStr2.length() == 2) {
          listStr2.add(eleStr2);
          eleStr2 = "";
          j--;
        }
        j++;
      }
      if(i == str1.length() && j == str2.length()) break;
    }

    int sumCount = 0;
    int interCount = 0;

    for(i = 0; i < listStr1.size(); i++ ) {
      interCount = listStr2.contains(listStr1.get(i)) ? interCount + 1 : interCount;
      listStr2.remove(listStr1.get(i));
    }

    sumCount = listStr1.size() + listStr2.size() - interCount;
    if(sumCount == 0) answer = 65536;
    else answer = (int)( ( (double) interCount / (double)sumCount ) * 65536 );


    return answer;
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    String str1 = 		"Ee";
    String str2 = "EEEEE";
    System.out.println(solution.solution(str1, str2));
  }
}
```

**채점 결과**

정확성: 46.2

합계: 46.2 / 100.0

------

위의 코드에서 remove를 사용해서 하나씩 사용한 것들을 지우려고 했는데 정확성이 낮아진 것을 보니 remove에 대한 정확한 개념을 알고 있는 것이 아닌 것 같다./

```java
import java.util.ArrayList;

class Solution {
  public int solution(String str1, String str2) {
    int answer = 0;

    // 대문자 -> 소문자
    str1 = str1.toLowerCase();
    str2 = str2.toLowerCase();

    ArrayList<String> listStr1 = new ArrayList<>();
    ArrayList<String> listStr2 = new ArrayList<>();

    int i = 0;
    int j = 0;
    String eleStr1 = "";
    String eleStr2 = "";
    while(true) {
      if (i < str1.length()) {
        if (str1.charAt(i) >= 'a' && str1.charAt(i) <= 'z') eleStr1 += str1.charAt(i);
        else if(eleStr1.length() == 1) eleStr1 = "";
        if (eleStr1.length() == 2) {
          listStr1.add(eleStr1);
          eleStr1 = "";
          i--;
        }
        i++;
      }
      if (j < str2.length()) {
        if (str2.charAt(j) >= 'a' && str2.charAt(j) <= 'z') eleStr2 += str2.charAt(j);
        else if(eleStr2.length() == 1) eleStr2 = "";
        if (eleStr2.length() == 2) {
          listStr2.add(eleStr2);
          eleStr2 = "";
          j--;
        }
        j++;
      }
      if(i == str1.length() && j == str2.length()) break;
    }

    int sumCount = 0;
    int interCount = 0;

    int listStr1Size= listStr1.size();
    int listStr2Size = listStr2.size();

    for(i = 0; i < listStr1.size(); i++ ) {
      interCount = listStr2.contains(listStr1.get(i)) ? interCount + 1 : interCount;
      for(j = 0; j < listStr2.size(); j++) {
        if(listStr2.get(j).equals(listStr1.get(i))) {
          listStr2.remove(j);
          break;
        }
      }
    }

    sumCount = listStr1Size + listStr2Size - interCount;
    if(sumCount == 0) answer = 65536;
    else answer = (int)( ( (double) interCount / (double)sumCount ) * 65536 );


    return answer;
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    String str1 = 		"Ee";
    String str2 = "EEEEE";
    System.out.println(solution.solution(str1, str2));
  }
}
```



## 참고한 사이트

- ArrayList remove
  - https://hianna.tistory.com/564
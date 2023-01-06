# 튜플

## 문제 설명

셀수있는 수량의 순서있는 열거 또는 어떤 순서를 따르는 요소들의 모음을 튜플(tuple)이라고 합니다. n개의 요소를 가진 튜플을 n-튜플(n-tuple)이라고 하며, 다음과 같이 표현할 수 있습니다.

- (a1, a2, a3, ..., an)

튜플은 다음과 같은 성질을 가지고 있습니다.

1. 중복된 원소가 있을 수 있습니다. ex : (2, 3, 1, 2)
2. 원소에 정해진 순서가 있으며, 원소의 순서가 다르면 서로 다른 튜플입니다. ex : (1, 2, 3) ≠ (1, 3, 2)
3. 튜플의 원소 개수는 유한합니다.

원소의 개수가 n개이고, 중복되는 원소가 없는 튜플 ```(a1, a2, a3, ..., an)```이 주어질 때(단, a1, a2, ..., an은 자연수), 이는 다음과 같이 집합 기호 '{', '}'를 이용해 표현할 수 있습니다.

- {{a1}, {a1, a2}, {a1, a2, a3}, {a1, a2, a3, a4}, ... {a1, a2, a3, a4, ..., an}}

예를 들어 튜플이 (2, 1, 3, 4)인 경우 이는

- {{2}, {2, 1}, {2, 1, 3}, {2, 1, 3, 4}}

와 같이 표현할 수 있습니다. 이때, 집합은 원소의 순서가 바뀌어도 상관없으므로

- {{2}, {2, 1}, {2, 1, 3}, {2, 1, 3, 4}}
- {{2, 1, 3, 4}, {2}, {2, 1, 3}, {2, 1}}
- {{1, 2, 3}, {2, 1}, {1, 2, 4, 3}, {2}}

는 모두 같은 튜플 (2, 1, 3, 4)를 나타냅니다.

특정 튜플을 표현하는 집합이 담긴 문자열 s가 매개변수로 주어질 때, s가 표현하는 튜플을 배열에 담아 return 하도록 solution 함수를 완성해주세요.

## 제한사항

- s의 길이는 5 이상 1,000,000 이하입니다.
- s는 숫자와 '{', '}', ',' 로만 이루어져 있습니다.
- 숫자가 0으로 시작하는 경우는 없습니다.
- s는 항상 중복되는 원소가 없는 튜플을 올바르게 표현하고 있습니다.
- s가 표현하는 튜플의 원소는 1 이상 100,000 이하인 자연수입니다.
- return 하는 배열의 길이가 1 이상 500 이하인 경우만 입력으로 주어집니다.

## 입출력 예시

- 입력값: 	"{{2},{2,1},{2,1,3},{2,1,3,4}}"
  - 기댓값: {2, 1, 3, 4}
- 입력값: "{{1,2,3},{2,1},{1,2,4,3},{2}}"
  - 기댓값: {2, 1, 3, 4}
- 입력값: "{{20,111},{111}}"
  - 기댓값: {111, 20}
- 입력값: "{{123}}"
  - 기댓값: {123}
- 입력값: "{{4,2,3},{3},{2,3,4,1},{2,3}}"
  - 기댓값: {3, 2, 4, 1}

## 입출력 예에 대한 설명 

**입출력 예 #1**

문제 예시와 같습니다.

**입출력 예 #2**

문제 예시와 같습니다.

**입출력 예 #3**

(111, 20)을 집합 기호를 이용해 표현하면 {{111}, {111,20}}이 되며, 이는 {{20,111},{111}}과 같습니다.

**입출력 예 #4**

(123)을 집합 기호를 이용해 표현하면 {{123}} 입니다.

**입출력 예 #5**

(3, 2, 4, 1)을 집합 기호를 이용해 표현하면 {{3},{3,2},{3,2,4},{3,2,4,1}}이 되며, 이는 {{4,2,3},{3},{2,3,4,1},{2,3}}과 같습니다.

## 코드 리뷰

```java
import java.util.ArrayList;
import java.util.HashSet;

class Solution {
  public int[] solution(String s) {
    ArrayList<ArrayList> tuple = new ArrayList<>();
    for(int i = 0; i < s.length(); i++) {
      if(s.charAt(i) >= '0' && s.charAt(i) <= '9') {
        ArrayList<Integer> element = new ArrayList<>();
        int j = 0;
        while (true)
        {
          element.add(s.charAt(i + j) -'0');
          if(s.charAt(i + j + 1) == '}') break;
          j += 2;
        }
        tuple.add(element);
        i += j + 2;
      }
    }

    HashSet<Integer> beforeAns = new HashSet<>();
    int size = 1;
    for(int i = 0; i < tuple.size(); i++) {
      if(tuple.get(i).size() == size) {
        for(int j = 0; j < size; j++) {
          beforeAns.add((Integer) tuple.get(i).get(j));
        }
        tuple.remove(i);
        size++;
        i = -1;
      }
    }

    int[] answer = beforeAns.stream().mapToInt(Integer::intValue).toArray();

    return answer;
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    String s = "{{2},{2,1},{2,1,3},{2,1,3,4}}";
    int[] answer = solution.solution(s);
    for(int i = 0; i < answer.length; i++) {
      System.out.print(answer[i] + ", ");
    }
  }
}
```

해당 코드로 했을 때 일단 HashSet 특성상 순서가 보장되지 않기 때문에, int값을 넣어준 대로 들어가야 될 텐데 정렬이 되어서 출력이 된다.

하지만 구글링 해서 찾아본 결과 정렬이 된 것이 아니라 HashSet에서 테이블 Index에 들어과는 과정에서 발생하는 방식 문제이다.

또한 해당 코드는 주어진 String이 한자리 숫자만 주어졌을 경우만 tuple 변수에 담을 수 있는 오류가 있다.

```java
    for(int i = 0; i < s.length(); i++) {
      if(s.charAt(i) >= '0' && s.charAt(i) <= '9') {
        ArrayList<Integer> nums = new ArrayList<>();
        while (s.charAt(i) >= '0' && s.charAt(i) <= '9' || s.charAt(i) == ',') {
          num += s.charAt(i);
          if(s.charAt(i) == ',') {
            int castNum = Integer.parseInt(num);
            nums.add(castNum);
            num = "";
            i++;
          }
          i++;
        }
        int[] castIntArray = nums.stream().mapToInt(Integer::intValue).toArray();
        sample.add(castIntArray);
      }
    }
```

이번엔 해당 코드를 살펴보자. 

해당 코드의 문제는 무엇일까? 

While문과 If문을 활용해서 숫자를 받아서 끝났을 시점에 sample List에 넣어주는 방식이다.

문제는 while문 안에 있다.

해당 코드의 경우 숫자 뒤에 ,가 나오는 경우 즉, 숫자 바로 뒤에 ```}``` 가 나온다면 nums에 들어가지 않는다.

이를테면 숫자가 하나 있고 {2} 이와 같이 처리가 되어 있으면 nums에 들어가지도 않을뿐더러 num도 초기화가 되지 않는다.

```java
        while (s.charAt(i) >= '0' && s.charAt(i) <= '9' || s.charAt(i) == ',' || s.charAt(i) == '}') {
          if(s.charAt(i) == '}') {
            num = "";
            break;
          }
          num += s.charAt(i);
          if(s.charAt(i) == ',' || s.charAt(i) == '}') {
            int castNum = Integer.parseInt(num);
            nums.add(castNum);
            num = "";
            i++;
          }
          i++;
        }

```

그렇다고 코드를 이렇게 한다면 쉼표가 num안에 들어가기 때문에 NumberFormatException 오류가 계속해서 발생할 것이다.

다시 고려해 볼 것을 생각해 보자.

1. 숫자가 두 개 이상 나오는 경우
2. 쉼표 처리
3. int 배열로 만들어 주는 과정
4. }가 나올때 적절하게 끊어주는 과정 필요

일단 저 숫자들을 나눠주는 과정이 중요하다. 

하지만 처음에도 생각한 문제는 저것들을 ```,```로 나눌 수가 없다. 

그래서 생각한 것이 ```}```로 나누는 것을 생각해봤다. ```}```를 이용해서 split을 한다면 숫자는 나눠질 것이다.

또한 마지막은 항상 ```}}```로 되어 있기 때문에 나름 처리하기 쉬울 수도 있을 것 같다.

차라리 split을 활용해 보는 방향으로 생각을 해봤다.

```java
    for(int i = 0; i < splitSample.length; i++) {
      String num = "";
      String ele = splitSample[i];
      ArrayList<Integer> nums = new ArrayList<>();
      for(int j = 0; j < ele.length(); j++) {
        if(ele.charAt(j) >= '0' && ele.charAt(j) <= '9') {
          num += ele.charAt(j);
        }
        else if(ele.charAt(j) == ',' || j == ele.length() - 1) {
          nums.add(Integer.parseInt(num));
          num = "";
        }
      }
      sample.add(nums.stream().mapToInt(Integer::intValue).toArray());
    }
```

해당 코드에도 오류가 있는데, **마지막 순번**일 때 nums에 넣어주기 위해서 ```else if``` 에 ```ele.length() -1```을 넣어준 것인데,

해당 코드가 실행이 될까?

**안된다!**

else if 특성상 if를 실행하면 else if문은 뛰어 넘는다. 하지만, 현재 for문에서 마지막일 때 넣어주려고 저렇게 한 것인데, 당연히 실행이 되지 않을 것이다.

```java
    for(int i = 0; i < sample.size(); i++) {
      if(sample.get(i).length == size) {
        int[] target = sample.get(i);
        for(int j = 0; j < target.length; j++) {
          if(beforeCastResult.get(target[j]) == -1) {
            beforeCastResult.add(target[j]);
            size++;
            break;
          }
        }
        sample.remove(i);
        i = -1;
      }
    }
```

해당 코드에는 무슨 문제가 있을까?

```java
if(beforeCastResult.get(target[j]) == -1) {
```

해당 코드에서 나는 target[j]의 value가 있는지 체크하려고 했다. 하지만, target[j]가 int라서 Index로 취급해버려서 IndexOutOfBoundsException오류가 발생한다.

## 참고한 사이트

- HashSet은 정렬을 지원하지 않는다고 들었는데?
  - https://jwdeveloper.tistory.com/278
- HashMap은 테이블 인덱스를 어떻게 구하는가?
  - https://jwdeveloper.tistory.com/279
- String 숫자 Int로 바꾸는 방법 (특히, 2글자 이상)
  - https://www.freecodecamp.org/korean/news/java-string-to-int-how-to-convert-a-string-to-an-integer/
- Cast String <-> Int
  - https://hianna.tistory.com/524
- chartat을 String으로 받는 방법
  - https://wakestand.tistory.com/611
- Java Split
  - https://codechacha.com/ko/java-substring-or-split/
- Java Split 특수문자
  - https://copycoding.tistory.com/289

## 나의 코드

```java
import java.util.ArrayList;

class Solution {
  public int[] solution(String s) {

    ArrayList<int[]> sample = new ArrayList<>();
    String[] splitSample = s.split("}");

    for(int i = 0; i < splitSample.length; i++) {
      splitSample[i] = splitSample[i].replaceAll("[{]", "").replaceAll("^,", "");
    }
    for(int i = 0; i < splitSample.length; i++) {
      String num = "";
      String ele = splitSample[i];
      ArrayList<Integer> nums = new ArrayList<>();
      for(int j = 0; j < ele.length(); j++) {
        if(ele.charAt(j) >= '0' && ele.charAt(j) <= '9') {
          num += ele.charAt(j);
        }
        if(ele.charAt(j) == ',' || j == ele.length() - 1) {
          nums.add(Integer.parseInt(num));
          num = "";
        }
      }
      sample.add(nums.stream().mapToInt(Integer::intValue).toArray());
    }

    int size = 1;
    ArrayList<Integer> beforeCastResult = new ArrayList<>();

    for(int i = 0; i < sample.size(); i++) {
      if(sample.get(i).length == size) {
        int[] target = sample.get(i);
        for(int j = 0; j < target.length; j++) {
          if(!beforeCastResult.contains(target[j])) {
            beforeCastResult.add(target[j]);
            size++;
            break;
          }
        }
        sample.remove(i);
        i = -1;
      }
    }
    int[] answer = beforeCastResult.stream().mapToInt(Integer::intValue).toArray();

    return answer;
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    String s = "{{2},{2,1},{2,1,3},{2,1,3,4}}";
    int[] answer = solution.solution(s);
    for(int i = 0; i < answer.length; i++) {
      System.out.print(answer[i] + ", ");
    }
  }
}
```

## 다른 사람 코드

```java
import java.util.*;
class Solution {
    public int[] solution(String s) {
        Set<String> set = new HashSet<>();
        String[] arr = s.replaceAll("[{]", " ").replaceAll("[}]", " ").trim().split(" , ");
        Arrays.sort(arr, (a, b)->{return a.length() - b.length();});
        int[] answer = new int[arr.length];
        int idx = 0;
        for(String s1 : arr) {
            for(String s2 : s1.split(",")) {
                if(set.add(s2)) answer[idx++] = Integer.parseInt(s2);
            }
        }
        return answer;
    }
}

```

이 코드는 내가 고민하고 있던 부분을 완벽히 해결하였다.

중간에 ```,```를 이용해서 나누고 싶었지만, 숫자를 나누는 ```,```와 배열을 나누는 ```,```를 구분하지 못해서 못했는데

그것을 띄어쓰기를 이용해서 나눠줬다.

또한 set.add에 응답값을 이용하는 방식도 처음 보았다.

- trim()
  - https://coding-factory.tistory.com/129
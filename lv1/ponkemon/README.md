# 폰켓몬

## 문제설명

당신은 폰켓몬을 잡기 위한 오랜 여행 끝에, 홍 박사님의 연구실에 도착했습니다. 홍 박사님은 당신에게 자신의 연구실에 있는 총 N 마리의 폰켓몬 중에서 N/2마리를 가져가도 좋다고 했습니다.
홍 박사님 연구실의 폰켓몬은 종류에 따라 번호를 붙여 구분합니다. 따라서 같은 종류의 폰켓몬은 같은 번호를 가지고 있습니다. 예를 들어 연구실에 총 4마리의 폰켓몬이 있고, 각 폰켓몬의 종류 번호가 [3번, 1번, 2번, 3번]이라면 이는 3번 폰켓몬 두 마리, 1번 폰켓몬 한 마리, 2번 폰켓몬 한 마리가 있음을 나타냅니다. 이때, 4마리의 폰켓몬 중 2마리를 고르는 방법은 다음과 같이 6가지가 있습니다.
  
1. 첫 번째(3번), 두 번째(1번) 폰켓몬을 선택
2. 첫 번째(3번), 세 번째(2번) 폰켓몬을 선택
3. 첫 번째(3번), 네 번째(3번) 폰켓몬을 선택
4. 두 번째(1번), 세 번째(2번) 폰켓몬을 선택
5. 두 번째(1번), 네 번째(3번) 폰켓몬을 선택
6. 세 번째(2번), 네 번째(3번) 폰켓몬을 선택

이때, 첫 번째(3번) 폰켓몬과 네 번째(3번) 폰켓몬을 선택하는 방법은 한 종류(3번 폰켓몬 두 마리)의 폰켓몬만 가질 수 있지만, 다른 방법들은 모두 두 종류의 폰켓몬을 가질 수 있습니다. 따라서 위 예시에서 가질 수 있는 폰켓몬 종류 수의 최댓값은 2가 됩니다.
당신은 최대한 다양한 종류의 폰켓몬을 가지길 원하기 때문에, 최대한 많은 종류의 폰켓몬을 포함해서 N/2마리를 선택하려 합니다. N마리 폰켓몬의 종류 번호가 담긴 배열 nums가 매개변수로 주어질 때, N/2마리의 폰켓몬을 선택하는 방법 중, 가장 많은 종류의 폰켓몬을 선택하는 방법을 찾아, 그때의 폰켓몬 종류 번호의 개수를 return 하도록 solution 함수를 완성해주세요.

## 제한사항

- nums는 폰켓몬의 종류 번호가 담긴 1차원 배열입니다.
- nums의 길이(N)는 1 이상 10,000 이하의 자연수이며, 항상 짝수로 주어집니다.
- 폰켓몬의 종류 번호는 1 이상 200,000 이하의 자연수로 나타냅니다.
- 가장 많은 종류의 폰켓몬을 선택하는 방법이 여러 가지인 경우에도, 선택할 수 있는 폰켓몬 종류 개수의 최댓값 하나만 return 하면 됩니다.

## 입출력 예

- 입력값: 	[3, 1, 2, 3]
  - 기댓값: 3
- 입력값: [3, 3, 3, 2, 2, 4]
  - 기댓값: 3
- 입력값: 	[3, 3, 3, 2, 2, 2]
  - 기댓값: 2

| nums          | result |
|---------------|--------|
|  [3,1,2,3]    | 2      |
| [3,3,3,2,2,4] | 3      |
| [3,3,3,2,2,2] | 2      |

## 입출력 예 설명

**입출력 예 #1**

문제의 예시와 같습니다.

**입출력 예 #2**

6마리의 폰켓몬이 있으므로, 3마리의 폰켓몬을 골라야 합니다.
가장 많은 종류의 폰켓몬을 고르기 위해서는 3번 폰켓몬 한 마리, 2번 폰켓몬 한 마리, 4번 폰켓몬 한 마리를 고르면 되며, 따라서 3을 return 합니다.

**입출력 예 #3**

6마리의 폰켓몬이 있으므로, 3마리의 폰켓몬을 골라야 합니다.
가장 많은 종류의 폰켓몬을 고르기 위해서는 3번 폰켓몬 한 마리와 2번 폰켓몬 두 마리를 고르거나, 혹은 3번 폰켓몬 두 마리와 2번 폰켓몬 한 마리를 고르면 됩니다. 따라서 최대 고를 수 있는 폰켓몬 종류의 수는 2입니다.

## 필요 개념

- List와 Array의 가장 큰 차이는 배열의 크기가 정해져 있냐의 차이이다.
  - List의 경우 배열의 크기가 정해져 있지 않지만 Array경우 크기가 정해져 있다.
  - Set의 경우 List와 다르게 **객체(데이터)를 중복해서 저장할 수 없다.**
  - **HashSet**의 경우 순서보장 X, **TreeSet**의 경우 기본적으로 오름차순 정렬, **LinkedHashSet**의 경우 입력된 순서대로 데이터를 관리.
- **int[]** 의 경우 **primitive 타입의 배열**인데, **Arrays.asList()** 메소드는 primitive타입을 Wrapper클래스로(해당 경우에서는, int를 Integer로)형변환해주지 않기 때문에,
**primitive타입의 배열은 Arrays.asList()로는 List로 변환할 수 없습니다.**
- Java의 Data type의 경우 기본형 타입(Primitive type)과 참조형 타입(Reference type)으로 나누어 진다.
- boxed() 메소드는 IntStream 같이 원시타입에 대한 스트림 지원을 클래스 타입(예: IntStream -> Stream<Integer>)으로 전환하여 전용으로 실행가능한 기능을 수행한다.
  - int 자체로는 Collection에 못 담는다.
## 참고한 사이트
- List와 Set의 전체적인 개념 과 LinkedHashSet의 개념
  - https://wikidocs.net/207
  - https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=heartflow89&logNo=220994601249
  - https://crazykim2.tistory.com/582
- int배열을 List로 변환하기
  - https://hianna.tistory.com/552
- Java의 데이터 타입(Primitive type, Reference type)
  - https://gbsb.tistory.com/6
- Java Stream의 개념
  - https://futurecreator.github.io/2018/08/26/java-8-streams/
- int[] Array -> Set
  - https://stackoverflow.com/questions/12030661/java-int-array-to-hashsetinteger
- boxed()
  - https://okky.kr/articles/1004937?note=2441078

## 나의 코드
```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public int solution(int[] nums) {
    int answer = 0;

    // 뽑을 수 있는 갯수 담아 두기
    int numberOfDraws = nums.length/2;

    // 중복 제거
    Set<Integer> setNums = Arrays.stream(nums).boxed().collect(Collectors.toSet());

    if(numberOfDraws > setNums.size()) {
      return setNums.size();
    }
    answer = numberOfDraws;
    return answer;
  }
  public static void main(String args[]) {
    Solution solution = new Solution();
    int[] nums = {3, 1, 2, 3};
    System.out.println(solution.solution(nums));
  }
}
```

## 다른 사람 풀이
```java
import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {
    public int solution(int[] nums) {
        return Arrays.stream(nums)
                .boxed()
                .collect(Collectors.collectingAndThen(Collectors.toSet(),
                        phonekemons -> Integer.min(phonekemons.size(), nums.length / 2)));
    }
}

```
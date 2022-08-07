# 없는 숫자 더하기

## 문제 설명

0부터 9까지의 숫자 중 일부가 들어있는 정수 배열 **numbers**가 매개변수로 주어집니다. **numbers**에서 찾을 수 없는 0부터 9까지의 숫자를 모두 찾아 더한 수를 return 하도록 solution 함수를 완성해주세요.

## 제한사항

- 1 ≤ **numbers**의 길이 ≤ 9
  - 0 ≤ **numbers**의 모든 원소 ≤ 9
  - **numbers**의 모든 원소는 서로 다릅니다.

## 입출력 예

|numbers| result |
|---|--------|
|[1,2,3,4,6,7,8,0]| 14     |
|[5,8,4,0,6,7,9]| 6      |

## 입출력 예 설명

- 입력값: [1, 2, 3, 4, 6, 7, 8, 0]
  - 기댓값: 	14
  - 설명: 5, 9가 **numbers**에 없으므로, 5 + 9 = 14를 return 해야 합니다.

- 입력값: [5, 8, 4, 0, 6, 7, 9]
  - 기댓값: 	6
  - 설명: 1, 2, 3이 **numbers**에 없으므로, 1 + 2 + 3 = 6을 return 해야 합니다.

## 필요 개념
- int[]를 asList를 사용하여 ArrayList로 변환했을 때 일어나는 일

## 참고한 사이트
- ArrayList asList
  - https://m.blog.naver.com/roropoly1/221140156345
- List 자료구조
  - https://velog.io/@bahar-j/%EC%9E%90%EB%B0%94%EC%9D%98-Arrays.asList
- int 배열을 List로 변환하기
  - https://hianna.tistory.com/552
- 배열을 List로, List를 배열로 변환하기
  - https://hianna.tistory.com/551
## 나의 코드
```java
import java.util.Arrays;

class Solution {
  public int solution(int[] numbers) {
    int answer = 0;

    int[][] checkingNumber = new int[10][2];

    for(int i = 0; i < checkingNumber.length; i++) {
      if(Arrays.asList(numbers).indexOf(i) != -1) {
        checkingNumber[i][1] = 1;
      }
    }
    for(int i = 0; i < checkingNumber.length; i++) {
      if(checkingNumber[i][1] == 0) {
        answer += checkingNumber[i][0];
      }
    }

    return answer;
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] input = 	{1, 2, 3, 4, 6, 7, 8, 0};
    System.out.println(solution.solution(input));
  }
}

이와 같이 코드를 짰지만 int배열이 내가 원하는 방식으로 list형태가 되지 않아서
구글링을 통해 int배열의 경우 asList로 바로 바꿔주면 안된다는 것을 알았다.

        import java.util.Arrays;
        import java.util.stream.Collectors;

class Solution {
  public int solution(int[] numbers) {
    int answer = 0;

    int[]checkingNumber = new int[10];

    for(int i = 0; i < numbers.length; i++) {
      int target = numbers[i];
      for(int j = 0; j < checkingNumber.length; j++) {
        if(j == target) {
          checkingNumber[j] = 1;
          break;
        }
      }
    }
    for(int i = 0; i < checkingNumber.length; i++) {
      if(checkingNumber[i] == 0) {
        answer += i;
      }
    }

    return answer;
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] input = 	{1, 2, 3, 4, 6, 7, 8, 0};
    System.out.println(solution.solution(input));
  }
}

반복문을 통해서 처리를 하였고, 2차원 배열의 필요성을 느끼지 못해서 1차원 배열로 처리한 코드다.

하지만, 불필요한 작업이 보인다. 이를테면, 이미 checkingNumber에 채워진 숫자임에도 불구하고, j == target을 비교하는 작업을 한다.
        
        if(checkingNumber[j] != 1 && j == target) 

이와 같이 코드를 처리하는 것이 좋아 보인다. 
```

```java
문제에서 요구하는 것이 0부터 9까지의 숫자중에 없는 숫자를 더해서 return 하라는 것을 요구하고 있지만,

사실 0부터 9까지 더한 합계에서 numbers의 원소를 하나씩 뽑아서 빼도 같은 결과가 나올 것이고 훨씬, 간단하게 코드가 짜질 것이다.

푸는 과정에서 떠올랐지만, 굳이 코드로 구현하지는 않았다. 굉장히 간단해 보이기도 하고 다른 방식으로 생각하는 것도 중요하다고 생각하지만, 
연습 때는 위의 코드를 구현하는 것이 의미있다고 생각했다.
```

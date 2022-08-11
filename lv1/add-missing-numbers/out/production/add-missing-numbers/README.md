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


```

## 본받을만한 코드 
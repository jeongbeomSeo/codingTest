# The highest and lowest ranks of the lottery

## 문제 설명

```로또 6/45```(이하 '로또'로 표기)는 1부터 45까지의 숫자 중 6개를 찍어서 맞히는 대표적인 복권입니다. 아래는 로또의 순위를 정하는 방식입니다. 1

|순위|당첨 내용|
|---|------|
|1|6개 번호가 모두 일치 |
|2|5개 번호가 일치|
|3|4개 번호가 일치|
|4|3개 번호가 일치|
|5|2개 번호가 일치|
|6(낙첨)|그 외|

로또를 구매한 민우는 당첨 번호 발표일을 학수고대하고 있었습니다. 하지만, 민우의 동생이 로또에 낙서를 하여, 일부 번호를 알아볼 수 없게 되었습니다. 당첨 번호 발표 후, 민우는 자신이 구매했던 로또로 당첨이 가능했던 최고 순위와 최저 순위를 알아보고 싶어 졌습니다.
알아볼 수 없는 번호를 0으로 표기하기로 하고, 민우가 구매한 로또 번호 **6**개가 **44, 1, 0, 0, 31 25**라고 가정해보겠습니다. 당첨 번호 6개가 **31, 10, 45, 1, 6, 19**라면, 당첨 가능한 최고 순위와 최저 순위의 한 예는 아래와 같습니다.

|당첨 번호|31|10|45|1|6|19|결과|
|------|---|---|---|---|---|---|-------|
|최고 순위 번호|31|0→10|44|1|0→6|25|4개 번호 일치, 3등|
|최저 순위 번호|31|0→11|44|1|0→7|25|2개 번호 일치, 5등|

- 순서와 상관없이, 구매한 로또에 당첨 번호와 일치하는 번호가 있으면 맞힌 걸로 인정됩니다.
- 알아볼 수 없는 두 개의 번호를 각각 10, 6이라고 가정하면 3등에 당첨될 수 있습니다.
  - 3등을 만드는 다른 방법들도 존재합니다. 하지만, 2등 이상으로 만드는 것은 불가능합니다.
- 알아볼 수 없는 두 개의 번호를 각각 11, 7이라고 가정하면 5등에 당첨될 수 있습니다.
  - 5등을 만드는 다른 방법들도 존재합니다. 하지만, 6등(낙첨)으로 만드는 것은 불가능합니다.

민우가 구매한 로또 번호를 담은 배열 lottos, 당첨 번호를 담은 배열 win_nums가 매개변수로 주어집니다. 이때, 당첨 가능한 최고 순위와 최저 순위를 차례대로 배열에 담아서 return 하도록 solution 함수를 완성해주세요.

## 제한 사항

- lottos는 길이 6인 정수 배열입니다.
- lottos의 모든 원소는 0 이상 45 이하인 정수입니다.
  - 0은 알아볼 수 없는 숫자를 의미합니다.
  - 0을 제외한 다른 숫자들은 lottos에 2개 이상 담겨있지 않습니다.
  - lottos의 원소들은 정렬되어 있지 않을 수도 있습니다.
- win_nums은 길이 6인 정수 배열입니다. 
- win_nums의 모든 원소는 1 이상 45 이하인 정수입니다.
  - win_nums에는 같은 숫자가 2개 이상 담겨있지 않습니다.
  - win_nums의 원소들은 정렬되어 있지 않을 수도 있습니다.

## 입출력 예

| lottos                |win_nums| result |
|-----------------------|------|--------|
| [44, 1, 0, 0, 31, 25] |[31, 10, 45, 1, 6, 19]| [3, 5] |
|[0, 0, 0, 0, 0, 0]|[38, 19, 20, 40, 15, 25]|[1, 6]|


## 입출력 예 설명

입출력 예 #1
문제 예시와 같습니다.

입출력 예 #2
알아볼 수 없는 번호들이 아래와 같았다면, 1등과 6등에 당첨될 수 있습니다.

|당첨 번호|38|19|20|40|15|25|결과|
|------|---|---|---|---|---|---|------|
|최고 순위 번호|0→38|0→19|0→20|0→40|0→15|0→25|6개 번호 일치, 1등|
|최저 순위 번호|0→21|0→22|0→23|0→24|0→26|0→27|0개 번호 일치, 6등|

입출력 예 #3
민우가 구매한 로또의 번호와 당첨 번호가 모두 일치하므로, 최고 순위와 최저 순위는 모두 1등입니다.

### 테스트 표본

1. 입력값: [44, 1, 0, 0, 31, 25], [31, 10, 45, 1, 6, 19]
   - 출력값: [44, 1, 0, 0, 31, 25], [31, 10, 45, 1, 6, 19]
2. 입력값: 	[0, 0, 0, 0, 0, 0], [38, 19, 20, 40, 15, 25]
   - 출력값: 	[1, 6]
3. 입력값: [45, 4, 35, 20, 3, 9], [20, 9, 3, 45, 4, 35]
   - 출력값: 	[1, 1]


## 접근 방식

일단 문제에서 중복되는 숫자의 경우를 생각하지 않아도 되고, 순서도 상관없다고 했다.

그래서 lottos배열을 기준으로 삼고 반복문을 돌려야 된다고 생각했고, 

만약 0은 나온다면 highestRank를 구할 때 count된 것에 더해주기만 하면 되고 lowestRank는 count로만 계산하면 된다고 생각했다.

여기서 비교과정에서 lottos를 기준으로 win_nums의 요소들과 비교하는 과정에서 일치하는 요소가 나왔을 때 요소를 삭제 해주는 것이 더 효율적인지, 

아니면 요소를 냅두고 계속 해서 반복해주는 것이 효율적인지 생각해보게 되었다. 

요소를 삭제하는 방식을 찾아본 결과 내 생각엔 요소를 삭제하는 과정이 추가 되는 것이 시간이 더 오래 걸릴 것이라고 생각되어 삭제하지 않은 채 코드를 구현하였다.

삭제 하지 않아도 되는 것은 중복이 애초부터 배제되어 있기 때문이라고 생각된다.

## 참고 사이트

- 배열의 원소 삽입과 삭제
  - https://classicismist.blogspot.com/2018/03/java-java-array-insert-or-remove-element_16.html

## 오류 잡기

```java
  int[] ranking(int highestScore, int count){
    int highestRank;
    int lowestRank;
    switch (highestScore) {
        ...
    }
    switch (count) {
        ...
    }
    return [highestRank,lowestRank];
  }
  
이렇게 하니 오류가 나왔다.
        
그래서 배열을 만들 때 int[] intArray = {10, 20};
이러한 식을 만드니깐,
    reutrn  {highestRank, lowestRank};
이렇게 하면 오류가 안 나올 줄 알았는데 오류가 나온다.
        int[] result = {highestRank,lowestRank};
        return result;
마지막 부분을 이렇게하니 오류가 해결되었는데 이유가 무엇일까?
Java에서 지원하지 않는 코드라고 생각을 하고 변수 생성을 다시 한번 찾아 보았다.
메모리 생성에 관해서 생각을 해보았다.        
만약 나처럼 사용 한다고 하면 Array의 경우 여러 메모리리 칸을 요구하게 되는데 그것을 value로써 건네 주긴 힘들 것이라고 생각된다.
그래서 배열의 경우 value로 넘겨 주기 힘드니 주소 값을 생성해서 주소 값을 통해서 value를 건네주는 방식으로 하는 것이 맞다고 생각해서 저렇게 한다고 생각된다.
만약 나 처럼 지역 변수를 생성하지 않은 채로 return 하고 싶다면 다음과 같이 해야하는 것 같다.
return new int[]{highestRank, lowestRank};
```

## 나의 코드
```java

class Solution {
  public int[] solution(int[] lottos, int[] win_nums) {
    int[] answer = {};
    int count = 0;
    int zeroCount = 0;

    for(int i = 0; i < lottos.length; i++) {
      if(lottos[i] == 0) {
        zeroCount++;
      }
      else {
        // 중복인 경우가 제외되어 있기 때문에 고려 X
        for(int j = 0; j < win_nums.length; j++) {
          if(lottos[i] == win_nums[j]) {
            count++;
          }
        }
      }
    }

    int highestScore = count + zeroCount;

    answer = ranking(highestScore, count);

    return answer;
  }

  int[] ranking(int highestScore, int count){
    int highestRank;
    int lowestRank;
    switch (highestScore) {
      case 6: highestRank = 1;
        break;
      case 5: highestRank = 2;
        break;
      case 4: highestRank = 3;
        break;
      case 3: highestRank = 4;
        break;
      case 2: highestRank = 5;
        break;
      default: highestRank = 6;
        break;
    }
    switch (count) {
      case 6: lowestRank = 1;
        break;
      case 5: lowestRank = 2;
        break;
      case 4: lowestRank = 3;
        break;
      case 3: lowestRank = 4;
        break;
      case 2: lowestRank = 5;
        break;
      default: lowestRank = 6;
        break;
    }

    int[] result = {highestRank,lowestRank};
    return result;
  }
}
```
## 본 받아야 되는 코드
```java
class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];

        int cnt1 = 0;
        int cnt2 = 0;
        for(int i : lottos) {
            if(i == 0) {
                cnt1++;
                continue;
            }
            for(int j : win_nums) {
                if(i == j) cnt2++;
            }
        }


        answer[0] = getGrade(cnt1+cnt2);
        answer[1] = getGrade(cnt2);

        return answer;
    }

    public int getGrade(int n) {
        switch(n) {
            case 6 :
                return 1;
            case 5 :
                return 2;
            case 4 :
                return 3;
            case 3 :
                return 4;
            case 2 :
                return 5;
            default :
                return 6;
        }
    }
}
```
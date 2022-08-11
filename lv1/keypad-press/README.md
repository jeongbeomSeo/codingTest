# [카카오 인턴]Keypad Press

## 문제 설명

스마트폰 전화 키패드의 각 칸에 다음과 같이 숫자들이 적혀 있습니다.

![kakao_phone](kakao_phone.png)

이 전화 키패드에서 왼손과 오른손의 엄지손가락만을 이용해서 숫자만을 입력하려고 합니다.
맨 처음 왼손 엄지손가락은 __*__ 키패드에 오른손 엄지손가락은 **#** 키패드 위치에서 시작하며, 엄지손가락을 사용하는 규칙은 다음과 같습니다.

1. 엄지손가락은 상하좌우 4가지 방향으로만 이동할 수 있으며 키패드 이동 한 칸은 거리로 1에 해당합니다.
2. 왼쪽 열의 3개의 숫자 **1, 4, 7**을 입력할 때는 왼손 엄지손가락을 사용합니다.
3. 오른쪽 열의 3개의 숫자 **3, 6, 9**를 입력할 때는 오른손 엄지손가락을 사용합니다.
4. 가운데 열의 4개의 숫자 **2, 5, 8, 0**을 입력할 때는 두 엄지손가락의 현재 키패드의 위치에서 더 가까운 엄지손가락을 사용합니다.
   4-1. 만약 두 엄지손가락의 거리가 같다면, 오른손잡이는 오른손 엄지손가락, 왼손잡이는 왼손 엄지손가락을 사용합니다.

순서대로 누를 번호가 담긴 배열 numbers, 왼손잡이인지 오른손잡이인 지를 나타내는 문자열 hand가 매개변수로 주어질 때, 각 번호를 누른 엄지손가락이 왼손인 지 오른손인 지를 나타내는 연속된 문자열 형태로 return 하도록 solution 함수를 완성해주세요.

## 제한 사항

- numbers 배열의 크기는 1 이상 1,000 이하입니다.
- numbers 배열 원소의 값은 0 이상 9 이하인 정수입니다.
- hand는 **"left"** 또는 **"right"** 입니다.
  - **"left"** 는 왼손잡이, **"right"** 는 오른손잡이를 의미합니다.
- 왼손 엄지손가락을 사용한 경우는 **L**, 오른손 엄지손가락을 사용한 경우는 **R**을 순서대로 이어붙여 문자열 형태로 return 해주세요.

## 입출력 예

| numbers   |hand|result|
|-----------|---|------|
|[1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5]|"right"|"LRLLLRLLRRL"|
|[7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2]|"left"|"LRLLRRLLLRR"|
|[1, 2, 3, 4, 5, 6, 7, 8, 9, 0]|"right"|"LLRLLRLLRL"|

1. 입력값: [1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5], "right"
   - 기댓값: 	"LRLLLRLLRRL"
2. 입력값: [7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2], "left"
   - 기댓값: "LRLLRRLLLRR"
3. 입력값: 	[1, 2, 3, 4, 5, 6, 7, 8, 9, 0], "right"
   - 기댓값: 	"LLRLLRLLRL"

## 입출력 예에 대한 설명

1. 입출력 예 #1
  - 순서대로 눌러야 할 번호가 [1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5]이고, 오른손잡이입니다.

| 왼손 위치 |오른손 위치|눌러야 할 숫자|사용한 손|설명|
|-------|---|---|---|---|
| #     |1|L|1은 왼손으로 누릅니다.|
| 1     |#|3|R|3은 오른손으로 누릅니다.|
 | 1     |3|4|L|4는 왼손으로 누릅니다.|
 | 4     |3|5|L|왼손 거리는 1, 오른손 거리는 2이므로 왼손으로 5를 누릅니다.|
 | 5     |3|8|L|왼손 거리는 1, 오른손 거리는 3이므로 왼손으로 8을 누릅니다.|
 | 8     |3|2|R|왼손 거리는 2, 오른손 거리는 1이므로 오른손으로 2를 누릅니다.|
 | 8     |2|1|L|1은 왼손으로 누릅니다.|
 | 1     |2|4|L|4는 왼손으로 누릅니다.|
 | 4     |2|5|R|왼손 거리와 오른손 거리가 1로 같으므로, 오른손으로 5를 누릅니다.|
 | 4     |5|9|R|9는 오른손으로 누릅니다.|
 | 4     |9|5|L|왼손 거리는 1, 오른손 거리는 2이므로 왼손으로 5를 누릅니다.|
 | 5     |9|-|-|

따라서 **"LRLLLRLLRRL"** 를 return 합니다.

2. 입출력 예 #2
   - 왼손잡이가 [7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2]를 순서대로 누르면 사용한 손은 **"LRLLRRLLLRR"** 이 됩니다.
3. 입출력 예 #3
   - 오른손잡이가 [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]를 순서대로 누르면 사용한 손은 **"LLRLLRLLRL"** 이 됩니다.

## 참고한 사이트

- Java에는 Call By Reference가 존재하지 않는다.
  - https://deveric.tistory.com/92
- Call By Value vs Call By Reference
  - https://re-build.tistory.com/3
- 거리를 숫자로 구현할 때는 절댓값이 항상 필요하다.

## 필요 개념

- Java에는 Call By Reference가 존재하지 않는다. **Call by Value** 점을 명심하자.
- 프로그래밍에서 **거리**가 나올 때는 **절댓값**을 사용해야 되는 부분을 잊지말자.

## 나의 코드

Call By Reference 와 Call By Value 를 주의하기.

```java
class Solution {
  public String solution(int[] numbers, String hand) {
    String answer = "";
    Hands leftHand = new LeftHand();
    Hands rightHand = new RightHand();

    for(int number : numbers) {
      if(number == 1 || number == 4 || number == 7) {
        answer += "L";
        switch (number) {
          case 1:
            leftHand.putIndex(0, 0);
            break;
          case 4:
            leftHand.putIndex(1, 0);
            break;
          case 7:
            leftHand.putIndex(2, 0);
            break;
        }
        continue;
      }
      else if(number == 3 || number == 6 || number == 9) {
        answer += "R";
        switch (number) {
          case 3:
            rightHand.putIndex(0, 2);
            break;
          case 6:
            rightHand.putIndex(1, 2);
            break;
          case 9:
            rightHand.putIndex(2, 2);
            break;
        }
        continue;
      }
      else if(number == 2 || number == 5 || number == 8 || number == 0){
        switch (number) {
          case 2:
            answer += Hands.midKeyPadString(leftHand, rightHand, 0, 1 , hand);
            break;
          case 5:
            answer += Hands.midKeyPadString(leftHand, rightHand, 1, 1 , hand);
            break;
          case 8:
            answer += Hands.midKeyPadString(leftHand, rightHand, 2, 1 , hand);
            break;
          case 0:
            answer += Hands.midKeyPadString(leftHand, rightHand, 3, 1 , hand);
            break;
        }
        continue;
      }
    }

    return answer;
  }
}

abstract class Hands {
  int rowIndex;
  int columnIndex;

  void putIndex(int rowIndex, int columnIndex) {
    this.rowIndex  = rowIndex;
    this.columnIndex = columnIndex;
  }

  int calcLength(int keypadRowIndex, int  keypadColumnIndex) {
    return Math.abs(this.rowIndex - keypadRowIndex) + Math.abs(this.columnIndex - keypadColumnIndex);
  }

  // Call By Value인 것을 인지하고 만든 Method 형태
  static String midKeyPadString(Hands leftHand, Hands rightHand, int keypadRowIndex, int keypadColumnIndex, String hand) {
    if(leftHand.calcLength(keypadRowIndex, keypadColumnIndex) > rightHand.calcLength(keypadRowIndex,keypadColumnIndex)) {
      rightHand.putIndex(keypadRowIndex, keypadColumnIndex);
      return "R";
    }
    else if (leftHand.calcLength(keypadRowIndex, keypadColumnIndex) < rightHand.calcLength(keypadRowIndex,keypadColumnIndex)){
      leftHand.putIndex(keypadRowIndex, keypadColumnIndex);
      return "L";
    }
    else {
      if(hand.equals("left")) {
        leftHand.putIndex(keypadRowIndex, keypadColumnIndex);
        return "L";
      }
      else if(hand.equals("right")) {
        rightHand.putIndex(keypadRowIndex, keypadColumnIndex);
        return "R";
      }
      else return "";
    }
  }

  // Call By Value를 재대로 이해하지 못한 형태 
  static void midKeyPadAnswer(Hands leftHand, Hands rightHand, int keypadRowIndex, int keypadColumnIndex, String hand,String answer) {
    if(leftHand.calcLength(keypadRowIndex, keypadColumnIndex) > rightHand.calcLength(keypadRowIndex,keypadColumnIndex)) {
      answer += "R";
      rightHand.putIndex(keypadRowIndex,keypadColumnIndex);
    }
    else if(leftHand.calcLength(keypadRowIndex, keypadColumnIndex) < rightHand.calcLength(keypadRowIndex,keypadColumnIndex)){
      answer += "L";
      leftHand.putIndex(keypadRowIndex, keypadColumnIndex);
    }
    else {
      if(hand.equals("left")) {
        leftHand.putIndex(keypadRowIndex, keypadColumnIndex);
        answer += "L";
      }
      else if(hand.equals("right")) {
        rightHand.putIndex(keypadRowIndex, keypadColumnIndex);
        answer += "R";
      }
    }
  }
}


class LeftHand extends Hands{

  LeftHand() {
    rowIndex = 3;
    columnIndex = 0;
  }
}

class RightHand extends Hands {
  RightHand() {
    rowIndex = 3;
    columnIndex = 2;
  }

}
```

## 코드 리뷰 

leftHand와 rightHand객체 즉, class를 만들어서 index를 줄 까 생각을 해보았다.

실제로 그렇게 풀이를 해 나갔고 Hands로 Class상위 클래스를 생성해서 하위 클래스인 LeftHand와 RightHand로 나눠 주었다.

이후 필요한 메서드를 생각해 가며 풀이를 해나갔다.

## 본받아야 될 코드

```java
class Solution {
    //        0부터 9까지 좌표 {y,x}
    int[][] numpadPos = {
            {3,1}, //0
            {0,0}, //1
            {0,1}, //2
            {0,2}, //3
            {1,0}, //4
            {1,1}, //5
            {1,2}, //6
            {2,0}, //7
            {2,1}, //8
            {2,2}  //9
    };
    //초기 위치
    int[] leftPos = {3,0};
    int[] rightPos = {3,2};
    String hand;
    public String solution(int[] numbers, String hand) {
        this.hand = (hand.equals("right")) ? "R" : "L";

        String answer = "";
        for (int num : numbers) {
            String Umji = pushNumber(num);
            answer += Umji;

            if(Umji.equals("L")) {leftPos = numpadPos[num]; continue;}
            if(Umji.equals("R")) {rightPos = numpadPos[num]; continue;}
        }
        return answer;
    }

    //num버튼을 누를 때 어디 손을 사용하는가
    private String pushNumber(int num) {
        if(num==1 || num==4 || num==7) return "L";
        if(num==3 || num==6 || num==9) return "R";

        // 2,5,8,0 일때 어디 손가락이 가까운가
        if(getDist(leftPos, num) > getDist(rightPos, num)) return "R";
        if(getDist(leftPos, num) < getDist(rightPos, num)) return "L";

        //같으면 손잡이
        return this.hand;
    }

    //해당 위치와 번호 위치의 거리
    private int getDist(int[] pos, int num) {
        return Math.abs(pos[0]-numpadPos[num][0]) + Math.abs(pos[1]-numpadPos[num][1]);
    }
}

```

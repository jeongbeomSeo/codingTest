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


  public static void main(String args[]) {
    int[] numbers = new int[]{1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
    String hand = "right";

    Solution solution = new Solution();

    System.out.println(solution.solution(numbers, hand));
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
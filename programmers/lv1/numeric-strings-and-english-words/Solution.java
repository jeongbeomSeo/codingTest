import java.util.ArrayList;

class Solution {
  public int solution(String s) {
    int answer = 0;

    char[] charArray = s.toCharArray();
    String answerString = "";
    for(int i = 0; i < charArray.length; i++) {
      if(charArray[i] >= 48 && charArray[i] <= 57){
        answerString += charArray[i];
        continue;
      }
      else if(charArray[i] == 'z') {
        answerString += '0';
        // answerString += '0' ? 둘의 차이가 없는 것인가? =
        i += 3;
        continue;
      }
      else if(charArray[i] == 'o') {
        answerString += '1';
        i += 2;
        continue;
      }
      else if (charArray[i] == 't') {
        if(charArray[i + 1] == 'w') {
          answerString += '2';
          i += 2;
          continue;
        }
        if(charArray[i + 1] == 'h') {
          answerString += '3';
          i+= 4;
          continue;
        }
      }
      else if (charArray[i] == 'f') {
        if(charArray[i + 1] == 'o' ) {
          answerString += '4';
          i += 3;
          continue;
        }
        if(charArray[i + 1] == 'i' ) {
          answerString += '5';
          i += 3;
          continue;
        }
      }
      else if (charArray[i] == 's') {
          if(charArray[i + 1] == 'i' ) {
            answerString += '6';
            i += 2;
            continue;
          }
          if(charArray[i + 1] == 'e' ) {
            answerString += '7';
            i += 4;
            continue;
          }
        }
      else if (charArray[i] == 'e') {
        answerString += '8';
          i += 4;
          continue;
        }
      else if(charArray[i] == 'n') {
        answerString += '9';
          i += 3;
          continue;
      }
    }

    answer = Integer.parseInt(answerString);
    return answer;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    String input = 	"one4seveneight";

    int answer = solution.solution(input);

    System.out.println("answer = " + answer);
  }
}
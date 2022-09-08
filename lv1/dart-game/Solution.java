class Solution {
  public int solution(String dartResult) {
    int answer = 0;

    String[] number = new String[3];
    int[] intNum = new int[3];

    if(dartResult.indexOf('*') == -1 && dartResult.indexOf('#') == -1) {
      if(dartResult.length() == 6) {
        number[0] = dartResult.substring(0, 2);
        number[1] = dartResult.substring(2, 4);
        number[2] = dartResult.substring(4, 6);
      }
      else {
        for(int i = 0; i < number.length; i++) {
          if(dartResult.charAt(1) > '9') {
            number[i] = dartResult.substring(0, 2);
            dartResult = dartResult.substring(2);
          }
          else {
            number[i] = dartResult.substring(0, 3);
            dartResult = dartResult.substring(3);
          }
        }
      }
    }
    else {
      for (int i = 0; i < number.length; i++) {
        if(dartResult.indexOf('*') == 2 || dartResult.indexOf('#') == 2
        ) {
          number[i] = dartResult.substring(0, 3);
          if(i != 2) {
            dartResult = dartResult.substring(3);
          }
          continue;
        }
        else if(dartResult.indexOf('*') == 3 || dartResult.indexOf('#') == 3) {
          number[i] = dartResult.substring(0, 4);
          if(i != 2) {
            dartResult = dartResult.substring(4);
          }
          continue;
        }
        else if(dartResult.length() > 2) {
          if(dartResult.charAt(2) == 'S' || dartResult.charAt(2) == 'D' || dartResult.charAt(2) == 'T') {
            number[i] = dartResult.substring(0, 3);
            if(i != 2) {
              dartResult = dartResult.substring(3);
            }
            continue;
          }
        }
        number[i] = dartResult.substring(0, 2);
        if(i != 2) {
          dartResult = dartResult.substring(2);
        }
      }
    }

    for(int i = 0; i < number.length; i++){
      int num = 0;
      boolean ten = false;
      if(number[i].length() == 2) num = number[i].charAt(0) - '0';
      else if(number[i].length() == 3) {
        if(number[i].charAt(2) == '*' || number[i].charAt(2) == '#') {
          num = number[i].charAt(0)  - '0';
        }
        else {
          num = 10;
          ten = true;
        }
      }
      else if(number[i].length() == 4) {
        num = 10;
        ten = true;
      }

      if(ten) {
        if(number[i].charAt(2) == 'D') num *= num;
        else if(number[i].charAt(2) == 'T') num *= num * num;
      }
      else {
        if(number[i].charAt(1) == 'D') num *= num;
        else if(number[i].charAt(1) == 'T') num *= num * num;
      }

      if(!ten && number[i].length() == 3 ) {
        if(number[i].charAt(2) == '*') num *= 2;
        else num *= (-1);
        if(i != 0) {
          if(number[i].charAt(2) == '*') intNum[i -1] *= 2;
        }
      }
      else if(ten && number[i].length() == 4) {
        if(number[i].charAt(3) == '*') num *= 2;
        else num *= (-1);
        if(i != 0) {
          if(number[i].charAt(3) == '*') intNum[i -1] *= 2;
        }
      }
      intNum[i] = num;
    }

    answer = intNum[0] + intNum[1] + intNum[2];

    return answer;
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    String dartResult = "0S0D10T";
    System.out.println(solution.solution(dartResult));
  }
}
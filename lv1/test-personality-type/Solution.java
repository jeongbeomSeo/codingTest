class Solution {
  int[] score = new int[8];
  // 0 -> R
  // 1 -> T
  // 2 -> C
  // 3 -> F
  // 4 -> J
  // 5 -> M
  // 6 -> A
  // 7 -> N

  public String solution(String[] survey, int[] choices) {
    String answer = "";

    for(int i = 0; i < survey.length; i++) {
      boolean right = choices[i] > 4 ? true : false;
      int number = choices[i];
      if(survey[i].equals("RT") || survey[i].equals("TR")) {
        if(right) {
          number -= 4;
          if(survey[i].charAt(1) == 'R') score[0] += number;
          else score[1] += number;
        }
        else{
          number = 4 - number;
          if(survey[i].charAt(0) == 'R') score[0] += number;
          else score[1] += number;
        }
      }
      if(survey[i].equals("CF") || survey[i].equals("FC")) {
        if(right) {
          number -= 4;
          if(survey[i].charAt(1) == 'C') score[2] += number;
          else score[3] += number;
        }
        else {
          number = 4 - number;
          if(survey[i].charAt(0) == 'C') score[2] += number;
          else score[3] += number;
        }
      }
      if(survey[i].equals("JM") || survey[i].equals("MJ")) {
        if(right) {
          number -= 4;
          if(survey[i].charAt(1) == 'J') score[4] += number;
          else score[5] += number;
        }
        else {
          number = 4 - number;
          if(survey[i].charAt(0) == 'J') score[4] += number;
          else score[5] += number;
        }
      }
      if(survey[i].equals("AN") || survey[i].equals("NA")) {
        if(right) {
          number -= 4;
          if(survey[i].charAt(1) == 'A') score[6] += number;
          else score[7] += number;
        }
        else {
          number = 4 - number;
          if(survey[i].charAt(0) == 'A') score[6] += number;
          else score[7] += number;
        }
      }
    }
    answer += score[0] >= score[1] ? "R" : "T";
    answer += score[2] >= score[3] ? "C" : "F";
    answer += score[4] >= score[5] ? "J" : "M";
    answer += score[6] >= score[7] ? "A" : "N";


    return answer;
  }
  public static void main(String args[]) {
    Solution solution = new Solution();
    String[] survey = {"AN", "CF", "MJ", "RT", "NA"};
    int[] choices = {5, 3, 2, 7, 5};

    System.out.println(solution.solution(survey, choices));
  }
}
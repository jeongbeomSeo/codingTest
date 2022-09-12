import java.util.Arrays;

class Solution {
  String[] week = {
          "THU",  // 나머지: 0
          "FRI",  // 나머지: 1
          "SAT",  // 나머지: 2
          "SUN",  // 나머지: 3
          "MON",  // 나머지: 4
          "TUE",  // 나머지: 5
          "WED"   // 나머지: 6
  };
  int[] daysOfWeek = {
          31, // 1 월
          29, // 2 월
          31, // 3 월
          30, // 4 월
          31, // 5 월
          30, // 6 월
          31, // 7 월
          31, // 8 월
          30, // 9 월
          31, // 10 월
          30, // 11 월
          31  // 12 월
  };
  public String solution(int a, int b) {
    String answer = "";
    if(a < 2) {
      answer = week[b % 7];
    }
    else if(a >= 2) {
      int days = 0;
      for(int i = 0; i < a-1; i++) {
        days += daysOfWeek[i];
      }
      answer = week[(days + b) % 7];
    }
    return answer;
  }
}
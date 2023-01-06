class Solution {
  public String solution(String s, int n) {
    String answer = "";

    for(int i = 0; i< s.length(); i++) {
      char c = s.charAt(i);
      if(c >= 97 && c <= 122) {
        c += n;
        if(c > 122) c -= 26;
      }
      else if(c >= 65 && c <= 90) {
        c += n;
        if(c > 90) c -= 26;
      }
      answer += c;
    }
    return answer;
  }
}
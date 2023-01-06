class Solution {
  public int[] solution(String s) {
    int[] answer = new int[2];

    int count = 0;
    while (!s.equals("1")) {
      count++;
      String replaceString = s.replaceAll("0", "");
      answer[1] += s.length() - replaceString.length();
      int c = replaceString.length() ;
      s = Integer.toBinaryString(c);
    }
    answer[0] = count;

    return answer;
  }
}
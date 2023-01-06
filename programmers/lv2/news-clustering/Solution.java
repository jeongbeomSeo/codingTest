import java.util.ArrayList;

class Solution {
  public int solution(String str1, String str2) {
    int answer = 0;

    // 대문자 -> 소문자
    str1 = str1.toLowerCase();
    str2 = str2.toLowerCase();

    ArrayList<String> listStr1 = new ArrayList<>();
    ArrayList<String> listStr2 = new ArrayList<>();

    int i = 0;
    int j = 0;
    String eleStr1 = "";
    String eleStr2 = "";
    while(true) {
      if (i < str1.length()) {
        if (str1.charAt(i) >= 'a' && str1.charAt(i) <= 'z') eleStr1 += str1.charAt(i);
        else if(eleStr1.length() == 1) eleStr1 = "";
        if (eleStr1.length() == 2) {
          listStr1.add(eleStr1);
          eleStr1 = "";
          i--;
        }
        i++;
      }
      if (j < str2.length()) {
        if (str2.charAt(j) >= 'a' && str2.charAt(j) <= 'z') eleStr2 += str2.charAt(j);
        else if(eleStr2.length() == 1) eleStr2 = "";
        if (eleStr2.length() == 2) {
          listStr2.add(eleStr2);
          eleStr2 = "";
          j--;
        }
        j++;
      }
      if(i == str1.length() && j == str2.length()) break;
    }

    int sumCount = 0;
    int interCount = 0;

    int listStr1Size= listStr1.size();
    int listStr2Size = listStr2.size();

    for(i = 0; i < listStr1.size(); i++ ) {
      interCount = listStr2.contains(listStr1.get(i)) ? interCount + 1 : interCount;
      for(j = 0; j < listStr2.size(); j++) {
        if(listStr2.get(j).equals(listStr1.get(i))) {
          listStr2.remove(j);
          break;
        }
      }
    }

    sumCount = listStr1Size + listStr2Size - interCount;
    if(sumCount == 0) answer = 65536;
    else answer = (int)( ( (double) interCount / (double)sumCount ) * 65536 );


    return answer;
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    String str1 = 		"Ee";
    String str2 = "EEEEE";
    System.out.println(solution.solution(str1, str2));
  }
}
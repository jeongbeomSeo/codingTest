import java.util.Arrays;

class Solution {
  public String[] solution(String[] strings, int n) {
    for(int i = 0; i < strings.length; i++){
      for(int j = i + 1; j < strings.length; j++) {
        if(strings[i].charAt(n) > strings[j].charAt(n)) {
          String temp = strings[i];
          strings[i] = strings[j];
          strings[j] = temp;
        }
        else if(strings[i].charAt(n) == strings[j].charAt(n)) {
          String[] temp = new String[2];
          temp[0] = strings[i];
          temp[1] = strings[j];
          Arrays.sort(temp);
          strings[i] = temp[0];
          strings[j] = temp[1];
        };
      }
    }
    return strings;
  }
}
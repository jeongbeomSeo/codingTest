import java.util.ArrayList;

class Solution {
  public int[] solution(int n, String[] words) {
    for(int i = 0; i < words.length; i++) {
      // 맨 처음 처리
      if(i == 0) {
        continue;
      }
      // 앞 뒤 String 알파벳 확인
      if(words[i].charAt(0) != words[i-1].charAt(words[i-1].length() - 1)) {
        int personNumber = i % n + 1;
        int positionOfWord = (i / n) + 1;
        return  new int[]{personNumber, positionOfWord};
      }
      // 앞에서 나온 단어인지 확인
      for(int j = 0; j < i; j++) {
        if(words[i].equals(words[j])) {
          int personNumber = i % n + 1;
          int positionOfWord = (i / n) + 1;
          return  new int[]{personNumber, positionOfWord};
        }
      }
    }
    int[] answer = {0, 0};
    // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
    System.out.println("Hello Java");

    return answer;
  }
}
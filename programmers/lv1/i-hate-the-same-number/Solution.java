import java.util.*;

public class Solution {
  public int[] solution(int []arr) {
    ArrayList<Integer> numbers = new ArrayList<>();

    for(int i : arr) {
      if(numbers.size() == 0) {
        numbers.add(i);
      }
      else if(numbers.get(numbers.size() - 1) == i) continue;
      else numbers.add(i);
    }

    /*
    int[] answer = new int[numbers.size()];

    for(int i = 0; i < answer.length; i++) {
      answer[i] = numbers.get(i).intValue();
    }*/

    int[] answer = numbers.stream().mapToInt(i -> i).toArray();

    // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
    System.out.println("Hello Java");

    return answer;
  }
}
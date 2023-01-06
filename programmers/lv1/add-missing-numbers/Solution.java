import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {
  public int solution(int[] numbers) {
    int answer = 0;

    int[]checkingNumber = new int[10];

    for(int i = 0; i < numbers.length; i++) {
      int target = numbers[i];
      for(int j = 0; j < checkingNumber.length; j++) {
        if(checkingNumber[j] != 1 && j == target){
          checkingNumber[j] = 1;
          break;
        }
      }
    }
    for(int i = 0; i < checkingNumber.length; i++) {
      if(checkingNumber[i] == 0) {
        answer += i;
      }
    }

    return answer;
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] input = 	{1, 2, 3, 4, 6, 7, 8, 0};
    System.out.println(solution.solution(input));
  }
}
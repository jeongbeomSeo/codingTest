import java.util.Arrays;

class Solution {
  public int[] solution(int[] array, int[][] commands) {
    int[] answer = new int[commands.length];
    int i = 0;
    for(int[] command : commands) {
      int[] newArray = Arrays.copyOfRange(array, command[0] -1 , command[1]);
      int[] sortArray =  Arrays.stream(newArray).sorted().toArray();
      answer[i] = sortArray[command[2] -1];
      i++;
    }
    return answer;
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] array = {1, 5, 2, 6, 3, 7, 4};
    int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};

    System.out.println(solution.solution(array, commands));
  }
}
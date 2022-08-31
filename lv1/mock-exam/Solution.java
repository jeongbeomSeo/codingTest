import java.util.ArrayList;
import java.util.List;

class Solution {
  public int[] solution(int[] answers) {
    int[] count = new int[3];
    int[] first = {1,2,3,4,5};
    int[] second = {2,1,2,3,2,4,2,5};
    int[] third = {3,3,1,1,2,2,4,4,5,5};

    for(int i = 0; i < answers.length; i++) {
      int firstArrayIndex = i % 5;
      int secondArrayIndex = i % 8;
      int thirdArrayIndex = i % 10;
      if(answers[i] == first[firstArrayIndex]) {
        count[0]++;
      }
      if(answers[i] == second[secondArrayIndex]) {
        count[1]++;
      }
      if(answers[i] == third[thirdArrayIndex]) {
        count[2]++;
      }
    }

    //배열에서 가장 큰 값을 가진 Index 꺼내기
    int max = count[0];
    int eleCount = 0;
    for(int i = 1; i < count.length; i ++) {
      if(max < count[i]) {
        max = count[i];
      }
    }
    List<Integer> answer = new ArrayList<>();
    for(int i = 0; i < count.length; i++) {
      if(max == count[i]) {
        answer.add(i + 1);
      }
    }
    int[] result = answer.stream().mapToInt(i -> i).toArray();
    return result;
  }
  public static void main(String[] args){
    Solution solution= new Solution();
    int[] answers = {1, 2, 3, 4, 5};
    System.out.println(solution.solution(answers));
  }
}
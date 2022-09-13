import java.util.HashSet;

class Solution {
  public int[] solution(int[] numbers) {
    HashSet<Integer> sums = new HashSet<>();

    for(int i = 0; i < numbers.length; i++) {
      for(int j = i + 1; j < numbers.length; j++) {
        sums.add(numbers[i] + numbers[j]);
      }
    }

    int[] answer = sums.stream().mapToInt(Integer::intValue).sorted().toArray();

    return answer;
  }
}

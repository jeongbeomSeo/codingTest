import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public int solution(int[] nums) {
    int answer = 0;

    // 뽑을 수 있는 갯수 담아 두기
    int numberOfDraws = nums.length/2;

    // 중복 제거
    Set<Integer> setNums = Arrays.stream(nums).boxed().collect(Collectors.toSet());

    if(numberOfDraws > setNums.size()) {
      return setNums.size();
    }
    answer = numberOfDraws;
    return answer;
  }
  public static void main(String args[]) {
    Solution solution = new Solution();
    int[] nums = {3, 1, 2, 3};
    System.out.println(solution.solution(nums));
  }
}
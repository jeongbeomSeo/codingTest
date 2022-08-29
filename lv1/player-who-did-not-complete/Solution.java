import java.util.Arrays;

class Solution {
  public String solution(String[] participant, String[] completion) {
    String answer = "";
    Arrays.sort(participant);
    Arrays.sort(completion);
    int i;
    for(i = 0; i < participant.length; i++) {
      if(i == participant.length -1 || !participant[i].equals(completion[i])) {
        return participant[i];
      }
    }
    return participant[i];
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    String[] participant = {"leo", "kiki", "eden"};
    String[] completion = {"eden", "kiki"};

    System.out.println(solution.solution(participant,completion));
  }
}

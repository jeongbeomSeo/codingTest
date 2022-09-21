import java.util.*;

class Solution {
  public int[] solution(String s) {
        Set<String> set = new HashSet<>();
        s = s.replaceAll("[{]", " ");
                s = s.replaceAll("[}]", " ");
                        s = s.trim();
                              String[]  arr= s.split(" , ");
        Arrays.sort(arr, (a, b)->{return a.length() - b.length();});
        int[] answer = new int[arr.length];
        int idx = 0;
        for(String s1 : arr) {
          for(String s2 : s1.split(",")) {
            if(set.add(s2)) answer[idx++] = Integer.parseInt(s2);
          }
        }
        return answer;
      }
  public static void main(String[] args) {
    Solution solution = new Solution();
    String s = "{{2},{2,1},{2,1,3},{2,1,3,4}}";
    int[] answer = solution.solution(s);
    for(int i = 0; i < answer.length; i++) {
      System.out.print(answer[i] + ", ");
    }
  }
}
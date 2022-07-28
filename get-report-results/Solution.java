import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.ArrayList;
class Solution {
  public int[] solution(String[] id_list, String[] report, int k) {
    int[] answer = new int[id_list.length];
    int count[] = new int[id_list.length];
    //report 중복 제거
    LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>(Arrays.asList(report));
    String[] reportResult = linkedHashSet.toArray(new String[0]);

    String[][] reportResultPart = new String[reportResult.length][2];

    // 신고 당한 사람 count 하기
    for(int i = 0; i < reportResult.length; i++) {
      String[] reportPart = reportResult[i].split(" ");
      // report에서 신고 당한 사람 찾아서 신고한 사람 answer에 +1 해주기.
      reportResultPart[i][0] = reportPart[0];
      reportResultPart[i][1] = reportPart[1];
      int index;
      index = Arrays.asList(id_list).indexOf(reportPart[1]);
      if(index != -1) {
        count[index]++;
      }
    }

    for(int i = 0; i < count.length; i++) {
    // k값 이상 count된 사람
      if(count[i] >= k) {
        // report에서 신고 당한 사람 찾아서 신고한 사람 answer에 +1 해주기.
        for(int j = 0; j < reportResult.length; j++){
        if(reportResultPart[j][1].equals(id_list[i])) {
            int index = Arrays.asList(id_list).indexOf(reportResultPart[j][0]);
            answer[index]++;
          }
        }
      }
    }
    return answer;
  }
  public static void main(String[] args) {
    Solution sol = new Solution();
    String[] id_list = {"muzi", "frodo", "apeach", "neo"};
    String[] report = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
    int k=2;
    System.out.println(Arrays.toString(sol.solution(id_list, report, k)));
  }
}
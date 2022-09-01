class Solution {
  public int solution(int n, int[] lost, int[] reserve) {
    int answer = 0;

    int[] student = new int[n];

    for(int index : reserve) {
      // 체육복을 여분으로 가지고 사람 -> 1
      // 체육복이 있는 사람 -> 0
      student[index - 1] = 1;
    }
    //본인 -> 앞사람 -> 뒷사람순으로 여분 체육복 확인
    //이때 다음 사람이 체육복을 잃어 버렸을 경우를 생각해야함.
    for(int i = 0; i < lost.length; i++) {
      int index = lost[i] - 1;
      if(student[index] == 1) {
        student[index] = 0;
        continue;
      }
      else if(index != 0 && student[index-1] == 1 ) {
        student[index -1] = 0;
        continue;
      }
      else if(index != student.length - 1 && student[index + 1] == 1) {
        // 뒷사람이 잃어버리지도 않았고 여분의 체육복을 가지고 있는 상태
        if(i+1 == lost.length || lost[i] + 1 != lost[i + 1]) {
          student[index + 1] = 0;
          continue;
        }
      }
      //뒷사람이 여분의 체육복을 가져온 동시에 잃어 버린 경우 -> 빌릴 수 없음.
      student[index] = -1;
    }
    for(int i = 0; i < student.length; i++) {
      if (student[i] != -1) answer++;
    }

    return answer;
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    int n = 5;
    int[] lost = {2, 4};
    int[] reserve = {3};
    System.out.println(solution.solution(n, lost, reserve));
  }
}
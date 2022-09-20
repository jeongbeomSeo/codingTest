import java.util.ArrayList;

class Solution {
  public int[] solution(String[][] places) {
    int[] answer = new int[places.length];
    for(int i = 0; i < places.length; i++) {
      ArrayList<Integer[]> pIndex = new ArrayList<>();
      boolean fail = false;
      for(int j = 0; j < places[i].length; j++) {
        for(int z = 0; z < 5; z++) {
          if(places[i][j].charAt(z) == 'P') {
            pIndex.add(new Integer[]{j,z});
          }
        }
      }
      for(int j = 0; j < pIndex.size() -1; j++) {
        for(int z = j; z < pIndex.size(); z++) {
          int x = pIndex.get(j)[0];
          int x1 = pIndex.get(z)[0];
          int y = pIndex.get(j)[1];
          int y1 = pIndex.get(z)[1];
          if(checkDistance(x, y, x1, y1) == 1) {
            answer[i] = 0;
            fail = true;
            break;
          }
          if(checkDistance(x, y, x1, y1) == 2) {
            if(x == x1) {
              if(places[i][x].charAt((y+y1)/2) == 'O') {
                answer[i] = 0;
                fail = true;
                break;
              }
            }
            if(y == y1) {
              if(places[i][(x + x1) / 2].charAt((y)) == 'O') {
                answer[i] = 0;
                fail = true;
                break;
              }
            }
            if(places[i][x1].charAt(y) == 'O' || places[i][x].charAt(y1) == 'O') {
              answer[i] = 0;
              fail = true;
              break;
            }
          }
        }
        if(fail) break;
      }
      if(!fail) answer[i] = 1;
    }
    return answer;
  }
  int checkDistance(int x, int y, int x1, int y1) {
    return Math.abs(x - x1) + Math.abs(y - y1);
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    String[][] input = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
    int[] array = solution.solution(input);
    for(int i = 0 ; i < array.length; i++) {
      System.out.print(array[i] + ", ");
    }
  }
}
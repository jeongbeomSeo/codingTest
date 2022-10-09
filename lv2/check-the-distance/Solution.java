class Solution {
  static int[] x = {-1, 1, 1, -1};
  static int[] y = {0, 1, -1, -1};
  static boolean correct;
  public int[] solution(String[][] places) {
    int[] answer = new int[places.length];

    for(int i = 0; i < places.length; i++) {
      String[] place = places[i];
      correct = true;
      for(int j = 0; j < place.length; j++) {
        boolean[][] visited = new boolean[5][5];
        for(int z = 0; z < place[j].length(); z++) {
          char curPos = place[j].charAt(z);
          if(curPos == 'P') {
            visited[j][z] = true;
            dfs(j, z, 0, place, visited);
            visited[j][z] = false;
          }
        }
      }
      if(correct) answer[i] = 1;
      else answer[i] = 0;
    }

    return answer;
  }

  static void dfs(int j, int z, int distance, String[] place, boolean[][] visited) {
    if(!correct) return;
    if(distance > 2) return;

    char curPos = place[j].charAt(z);

    if(distance != 0 && curPos == 'P') {
      correct = false;
      return;
    }

   if(distance == 0 || distance == 1 && curPos == 'O') {
        for(int i = 0; i< 4; i++) {
          z += x[i];
          j += y[i];
          if(j >=0 && j <= 4 && z >= 0 && z <= 4) {
            if(!visited[j][z]) {
              visited[j][z] = true;
              dfs(j, z, distance + 1, place, visited);
              visited[j][z] = false;
            }
          }
        }
   }
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    String[][] places = {{"POOOX", "XPOXO", "PXXXP", "OXXXO", "OOOOO"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
    int[] result = solution.solution(places);
    for(int i = 0; i < result.length; i++) {
      System.out.print(result[i] + " ");
    }
  }

}
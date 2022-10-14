import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

class Solution {

  static ArrayList<Integer[]>[] nodeAndCost;
  static boolean[] checkDistance;
  public int solution(int N, int[][] road, int K) {
    int answer = 0;
    checkDistance = new boolean[N + 1];
    nodeAndCost = new ArrayList[N + 1];
    boolean[] visited = new boolean[N + 1];

    for(int i = 0; i < road.length; i++) {
      int node1= road[i][0];
      int node2 = road[i][1];
      int cost = road[i][2];

      if(nodeAndCost[node1] == null) nodeAndCost[node1] = new ArrayList<>();
      if(nodeAndCost[node2] == null) nodeAndCost[node2] = new ArrayList<>();

      nodeAndCost[node1].add(new Integer[]{node2, cost});
      nodeAndCost[node2].add(new Integer[]{node1, cost});
    }

    checkDistance[1] = true;
    for(int i = 2; i < nodeAndCost.length; i++) {
      visited[1] = true;
      dfs(-1,1, i, K, 0, visited);
    }

    for(int i = 1; i < checkDistance.length; i++) {
      if(checkDistance[i]) answer++;
    }

    // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
    System.out.println("Hello Java");

    return answer;
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    int N = 5;
    int[][] road = 	{{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};
    int K = 3;

    System.out.println(solution.solution(N, road, K));
  }

  static void dfs(int curNode, int targetNode, int K, int cost, boolean[] visited) {

    // 바로 직행 거리에 있는지 체크.
    if(curNode == 1) {
      for(int i = 0; i < nodeAndCost[1].size(); i++) {
        if(targetNode == nodeAndCost[1].get(i)[0]) {
          if(K >= nodeAndCost[1].get(i)[1]) {
            checkDistance[targetNode] = true;
            return;
          }
        }
      }
    }

    if(checkDistance[targetNode] || cost > K) {
      return;
    }

    if(curNode == targetNode) {
      if(cost <= K)  {
        checkDistance[targetNode] = true;
        return;
      }
    }
  }
}
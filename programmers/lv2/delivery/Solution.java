import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Node {
  int idx;
  int cost;

  Node(int idx, int cost) {
    this.idx = idx;
    this.cost = cost;
  }
}
class Solution {
  static int INF = Integer.MAX_VALUE;
  public int solution(int N, int[][] road, int K) {
    int answer = 0;

    ArrayList<Node>[] graph = new ArrayList[N + 1];
    for(int i = 0; i < N + 1 ; i++) {
      graph[i] = new ArrayList<>();
    }

    for(int i = 0; i < road.length; i++) {
      int[] roadInfo = road[i];
      // 양방향 그래프
      graph[roadInfo[0]].add(new Node(roadInfo[1], roadInfo[2]));
      graph[roadInfo[1]].add(new Node(roadInfo[0], roadInfo[2]));
    }

    int[] dist = new int[N + 1];
    Arrays.fill(dist, INF);
    dist[1] = 0;

    answer = DijkStra(K, graph, dist);

    // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
    System.out.println("Hello Java");

    return answer;
  }
  static int DijkStra(int K, ArrayList<Node>[] graph, int[] dist) {
    PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));

    q.add(new Node(1, 0));

    while (!q.isEmpty()) {
      Node curNode = q.poll();

      if(dist[curNode.idx] < curNode.cost) continue;

      for(int i = 0; i < graph[curNode.idx].size(); i++) {
        Node adjNode = graph[curNode.idx].get(i);

        if(dist[adjNode.idx] > dist[curNode.idx] + adjNode.cost) {
          dist[adjNode.idx] = dist[curNode.idx] + adjNode.cost;
          q.add(new Node(adjNode.idx, dist[adjNode.idx]));
        }
      }
    }
    int canDelivery = 0;

    for(int i = 1; i < dist.length; i++) {
      if(dist[i] <= K) canDelivery++;
    }

    return canDelivery;
  }

  public static void main(String[] args) {

  }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    ArrayList<ArrayList<Node>> garph = new ArrayList<>();

    for (int i = 0; i < N + 1; i++) {
      garph.add(new ArrayList<>());
    }

    for (int i = 0; i < N -1; i++) {
      st = new StringTokenizer(br.readLine());
      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      // 무방향이라서 원래는 양쪽에 전부 넣어줘야 하지만, 풀이 방식에는 도움 되지 않기에 안 넣어줌.
      garph.get(n1).add(new Node(n2, cost));
    }

    ArrayList<Integer> diameters = new ArrayList<>();
    for (int i = 1; i < N + 1; i++) {
      int size = garph.get(i).size();
      // !주의: Skewed Binary Tree 인 경우 신경 쓰기
      if (i == 1 && size == 1 || size >= 2) {
        boolean[] isVisited = new boolean[N + 1];
        diameters.add(calcDiameter(garph, isVisited, i, 0, i));
      }
    }
    int max = 0;
    for (int i = 0; i < diameters.size(); i++) {
      if (max < diameters.get(i)) max = diameters.get(i);
    }

    System.out.println(max);
  }

  /**
   * 해당 함수는 DFS의 방식을 사용하였고, 자식을 두 개 이상 가지고 있는 루트 노드를 대상으로 지름의 길이를 구하는 함수이다.
   * 자식 노드의 개수로 처리 방식을 나누었고, 재귀 함수를 사용해서 길이의 합 중에 가장 큰 값을 찾아내었습니다.
   * 최종적으로 루트 노드에서 자식을 두개 이상 가지고 있기 때문에, 가장 큰 값과, 두번째로 큰 값을 꺼내주어서 더해서 마무리
   *
   * @param graph: 모든 경로의 정보를 담고있는 그래프
   * @param isVisited: 방문 여부를 확인하는 배열(해당 풀이에서는 굳이 필요 없을 것 같다. 이유는 자식 노드에서 부모 노드로 향하는 것을 안 넣어 주었기 때문이다.)
   * @param nodeIdx: 현재 방문하고 있는 노드의 인덱스
   * @param cost: 루트 노드부터 현재 방문한 노드까지의 경로의 길이 합
   * @param root: 맨 처음 방문한 노드
   * @return 최종 지름의 길이
   */
  static int calcDiameter(ArrayList<ArrayList<Node>> graph, boolean[] isVisited, int nodeIdx, int cost, int root) {
    isVisited[nodeIdx] = true;

    if (graph.get(nodeIdx).size() == 0) {
      return cost;
    }
    if (graph.get(nodeIdx).size() == 1) {
      if (!isVisited[graph.get(nodeIdx).get(0).idx]) {
        cost = calcDiameter(graph, isVisited, graph.get(nodeIdx).get(0).idx, cost + graph.get(nodeIdx).get(0).cost, root);
      }
    }
    else {
      int max = 0;
      int secondMax = 0;
      for (Node childNode : graph.get(nodeIdx)) {
        if (!isVisited[childNode.idx]) {
          int distance = calcDiameter(graph, isVisited, childNode.idx, cost + childNode.cost, root);
          if(max <= distance) {
            secondMax = max;
            max = distance;
          }
          // !주의
          // 루트노드에서 만약 처음에 탐색한 경로의 길이가 두번째로 탐색한 경로보다 긴 경우 secondMax는 해당 경로의 길이를 받아야 한다.
          else if (secondMax <= distance) secondMax = distance;
        }
      }
      if (nodeIdx == root) {
        cost = (max + secondMax);
      }
      else {
        cost = max;
      }
    }
    return cost;
  }

}

class Node {
  int idx;
  int cost;

  Node(int idx, int cost) {
    this.idx = idx;
    this.cost = cost;
  }
}
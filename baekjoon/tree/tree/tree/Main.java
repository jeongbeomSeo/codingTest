import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    ArrayList<Integer>[] tree = new ArrayList[N];

    for (int i = 0; i < tree.length; i++) {
      tree[i] = new ArrayList<>();
    }

    StringTokenizer st = new StringTokenizer(br.readLine());

    int idx = 0;
    int parNode = 0;
    while (st.hasMoreTokens()) {
      int node = Integer.parseInt(st.nextToken());
      if (node == -1) {
        parNode = idx++;
        continue;
      }
      tree[node].add(idx++);
    }

    int deleteNode = Integer.parseInt(br.readLine());

    boolean[] isVisited = new boolean[N];


    // 삭제할 노드를 방문 처리 하면서 노드 삭제 처리
    isVisited[deleteNode] = true;

    // 삭제할 노드 = 루트 노드 => 탐색 X
    if (parNode != deleteNode) {
      findLeafNode(tree, parNode, isVisited);
    }

    System.out.println(leafNodeCount);
  }
  static int leafNodeCount = 0;
  static void findLeafNode(ArrayList<Integer>[] tree, int curNode, boolean[] isVisited) {

    isVisited[curNode] = true;

    boolean hasChildNode = false;
    for (int childNode : tree[curNode]) {
      if (childNode >= 0 && !isVisited[childNode]) {
        hasChildNode = true;
        findLeafNode(tree, childNode, isVisited);
      }
    }
    if (!hasChildNode) leafNodeCount++;
  }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    Long N = Long.parseLong(br.readLine());

    System.out.println(queryResult(N));
  }
  private static int queryResult(Long N) {

    Queue<Node> q = new ArrayDeque<>();

    Map<Long, Boolean> isVisited = new HashMap<>();

    q.add(new Node(N, 0));

    while (!q.isEmpty()) {
      Node node = q.poll();

      if (node.value <= 0) continue;
      else if (node.value == 1) {
        return node.count;
      }


      if (!isVisited.getOrDefault(node.value / 3, false) && node.value % 3 == 0) {
        q.add(new Node(node.value / 3, node.count + 1));
        isVisited.put(node.value / 3, true);
      }
      if (!isVisited.getOrDefault(node.value / 2, false) && node.value % 2 == 0) {
        q.add(new Node(node.value / 2, node.count + 1));
        isVisited.put(node.value / 2, true);
      }
      if (!isVisited.getOrDefault(node.value - 1, false)) {
        q.add(new Node(node.value - 1, node.count + 1));
        isVisited.put(node.value - 1, true);
      }
    }

    return -1;
  }
}
class Node {
  long value;
  int count;

  Node(long value, int count) {
    this.value = value;
    this.count = count;
  }
}
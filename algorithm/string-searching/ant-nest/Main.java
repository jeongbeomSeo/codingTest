import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    final Tree tree = new Tree();
    for (int i = 0; i < N; i++) {

      String str = br.readLine();

      int count = Integer.parseInt(str.split(" ", 2)[0]);
      String keyList = str.split(" ", 2)[1];

      tree.update(keyList, count);
    }

    tree.allPrint();
  }
}
class Tree {
  private final Node root = new Node();

  public void update(String key, int count) {
    Node curNode = root;

    String[] strings = key.split(" ");
    for (int i = 0; i < count; i++) {
      String curStr = strings[i];

      curNode = curNode.map.computeIfAbsent(curStr, k -> new Node());
    }
  }

  public void allPrint() {
    allPrint(root, 0);
  }
  private void allPrint(Node node, int layer) {

    List<String> keyListCurLayer = new ArrayList<>(node.map.keySet());

    for (int i = 0; i < keyListCurLayer.size(); i++) {
      String key = keyListCurLayer.get(i);
      for (int j = 0; j < layer; j++) {
        System.out.print("--");
      }

      System.out.println(key);
      allPrint(node.map.get(key), layer + 1);
    }

  }
}
class Node {
  Map<String, Node> map;

  Node () {
    map = new TreeMap<>();
  }
}
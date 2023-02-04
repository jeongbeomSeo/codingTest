import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeMap;

class Node {
  Node root;
  int layer;
  boolean visitied;
  String val;
  Map<String, Node> childNode;

  Node() {
    if(root == null) root = this;
    childNode = new TreeMap<>();
    layer = 0;
    visitied = false;
    val = "";
  }

  void insert(String[] txts) {
    Node curNode = this.root;
    int layer = 0;
    for(int i = 0; i < txts.length; i++) {
      curNode = curNode.childNode.computeIfAbsent(txts[i], key -> new Node());
      curNode.layer = ++layer;
      curNode.val = txts[i];
    }
  }

  void printDfs(Node curNode) {

    curNode.visitied = true;

    if(curNode != root) {
      for(int i = 1 ; i < curNode.layer; i++)
        System.out.print("--");
      System.out.println(curNode.val);
    }

    curNode.childNode.forEach((key, node) -> {
      if(!node.visitied) {
        printDfs(node);
      }
    });
  }

}
public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    StringTokenizer st;

    Node node = new Node();

    for(int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      String[] txts = new String[n];
      for(int j = 0; j < n; j++) {
        txts[j] = st.nextToken();
      }
      node.insert(txts);
    }

    node.printDfs(node.root);

  }
}

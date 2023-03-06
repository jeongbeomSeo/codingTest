import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Main {
  static int totalNumber = 0;

  static Node rootNode = new Node();
  static class Node {
    Map<Character, Node> childNode = new TreeMap<>();
    boolean isTerminal;
    int count = 0;
    String value;
  }

  static void insertAndFind(String str) {
    Node curNode = rootNode;

    for(int i = 0; i < str.length(); i++) {
      curNode = curNode.childNode.computeIfAbsent(str.charAt(i), key -> new Node());
    }

    if(curNode.isTerminal) {
      curNode.count++;
      curNode.value = str;
    }
    else {
      curNode.isTerminal = true;
      curNode.count++;
    }
  }

  static public void printAll() {
    printAll(rootNode, ' ',new StringBuilder());
  }

  static private void printAll(Node curNode, char c, StringBuilder sb) {
    StringBuilder builder = new StringBuilder(sb);

    if(!curNode.equals(rootNode)) {
      builder.append(c);
    }

    if(curNode.isTerminal) {
      System.out.printf("%s %.4f\n", builder, ((double)curNode.count/(double)totalNumber) * 100);
    }

    curNode.childNode.forEach((key, node) -> {
      printAll(node, key, builder);
    });
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String input = "";
    while ((input = br.readLine()) != null && input.length() != 0) {
      totalNumber++;
      insertAndFind(input);
    }
    printAll();

  }
}

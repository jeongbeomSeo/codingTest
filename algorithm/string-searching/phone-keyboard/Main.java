import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


class Tree {
  private Node root;

  Tree() {
    if(root == null) root = new Node();
  }

  void insert(String txt){
    Node curNode = this.root;

    for(int i = 0; i < txt.length(); i++) {
      curNode = curNode.childNode.computeIfAbsent(txt.charAt(i), key -> new Node());
    }

    curNode.isTerminal = true;
  }

  int touchCount(String txt){
    Node curNode = this.root;
    int count = 0;

    for(int i = 0 ; i < txt.length(); i++) {
      // 첫번째 문자는 무조건 입력
      if(i == 0) {
        count++;
      }
      else {
        // 다음 순서의 문자가 하나인 경우
        if(curNode.childNode.size() == 1) {
          // 현재 문자가 접미사인 경우
          if(curNode.isTerminal) {
            count++;
          }
        } else {
          // 다음 순서의 문자가 여러 개인 경우
          count++;
        }
      }
      curNode = curNode.childNode.get(txt.charAt(i));
    }
    return count;
  }

}
class Node {

  Map<Character, Node> childNode;
  boolean isTerminal;

  Node() {
    childNode = new HashMap<>();
    isTerminal = false;
  }
}

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String input = "";

    while((input = br.readLine()) != null) {
      Tree tree = new Tree();
      int wordNum = Integer.parseInt(input);

      String[] txts = new String[wordNum];
      for(int i = 0; i < wordNum; i++) {
        txts[i] = br.readLine();
        tree.insert(txts[i]);
      }

      int sum = 0;
      for(int i = 0; i < wordNum; i++) {
        sum += tree.touchCount(txts[i]);
      }

      System.out.printf("%.2f\n", (double)sum / (double)wordNum);
    }
  }
}

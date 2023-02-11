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

  void insert(String txt, boolean notCleanUp) {
    Node curNode = this.root;

    for(int i = 0 ; i < txt.length(); i++) {
      curNode = curNode.childNode.computeIfAbsent(txt.charAt(i), key -> new Node());
      if(notCleanUp) curNode.notCleanUp = true;
    }
    curNode.isTermial = true;
  }

  boolean commandCount(String txt) {
    Node curNode = this.root;

    for(int i = 0 ; i < txt.length(); i++) {
      curNode = curNode.childNode.getOrDefault(txt.charAt(i), null);

      if(curNode.delete == true) return false;

      else if(!curNode.notCleanUp) {
        curNode.delete = true;
        return true;
      }

    }
    if(curNode.isTermial && !curNode.notCleanUp && !curNode.delete)
      curNode.delete = true;

    return true;
  }
}
class Node {
  Map<Character, Node> childNode;
  boolean isTermial;
  boolean notCleanUp;
  boolean delete;

  Node() {
    childNode = new HashMap<>();
    isTermial = false;
    notCleanUp = false;
    delete = false;
  }

}
public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int testNum = Integer.parseInt(br.readLine());

    for(int tc = 0; tc < testNum; tc++) {
      Tree tree = new Tree();

      int deleteFileNum = Integer.parseInt(br.readLine());
      String[] deleteFile = new String[deleteFileNum];
      for(int i = 0; i < deleteFileNum; i++) {
        deleteFile[i] = br.readLine();
        tree.insert(deleteFile[i], false);
      }

      int notDeleteFineNum = Integer.parseInt(br.readLine());
      for(int i = 0; i < notDeleteFineNum; i++) {
        tree.insert(br.readLine(), true);
      }


      int count = 0;
      if(notDeleteFineNum == 0) count = 1;
      else {
        for(int i = 0; i < deleteFileNum; i++) {
          if(tree.commandCount(deleteFile[i])) count++;
        }
      }
      System.out.println(count);
    }
  }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Node {
  private Node root;
  private Node[] childNode;
  private int childNum;
  private boolean isTerminal;
  Node() {
    if(root == null) root = this;
    childNode = new Node[10];
    childNum = 0;
    isTerminal = false;
  }

  boolean insertAndCheck(String nums) {
    Node curNode = this.root;

    for(int i = 0; i < nums.length(); i++) {
      int num = nums.charAt(i) - '0';

      if(curNode.childNode[num] == null) {
        curNode.childNode[num] = new Node();
        curNode.childNum++;
      }

      curNode = curNode.childNode[num];

      if(curNode.isTerminal || (i == nums.length() - 1 && curNode.childNum != 0)) return false;
    }
    curNode.isTerminal = true;

    return true;
  }
}
public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int caseNum = Integer.parseInt(br.readLine());

    for(int tc = 0; tc < caseNum; tc++) {
      Node node = new Node();
      int N = Integer.parseInt(br.readLine());
      boolean consistency = true;

    /*
      for(int i = 0; i < N; i++) {
        이와 같이 하면 입력 중간에 break가 걸려버리면 백준에서 입력해둔 예제가 문제가 될 수 있다.
        심지어 해당 문제는 Test Case가 있는 문제라서 오류가 나올 가능성이 있다. => 실제로 실행 결과 오류 나옴
        if(!node.insertAndCheck(br.readLine())) {
          consistency = false;
          break;
        }
        numbers[i] = br.readLine();
      }
    */

      for(int i = 0; i < N; i++) {
        if(!node.insertAndCheck(br.readLine())) consistency = false;
      }
      if(consistency) System.out.println("YES");
      else System.out.println("NO");
    }
  }
}
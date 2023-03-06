import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Tree {
  private Node root;

  Tree() {
    root = null;
  }

  boolean insertAndCompare(Node node) {
    if(root == null) {
      root = node;
      return false;
    }

    Queue<Node> q = new LinkedList<>();
    int length = node.val.length();
    q.add(root);
    while (!q.isEmpty()) {
      Node curNode = q.poll();

      int diff = node.compareTo(curNode);
      if(diff == 0) {
        if(strCompare(node.val, curNode.val, length)) return true;
        else break;
      }
      else if(diff > 0) {
        if(curNode.right == null) {
          curNode.right = node;
          break;
        }
        else {
          q.add(curNode.right);
        }
      }
      else {
        if(curNode.left == null) {
          curNode.left = node;
          break;
        }
        else {
          q.add(curNode.left);
        }
      }
    }
    return false;
  }

  private boolean strCompare(String str, String txt, int length) {
    for(int i = 0; i < length; i++) {
      if(str.charAt(i) != txt.charAt(i)) return false;
    }
    return true;
  }

}

class Node implements Comparable<Node>{
  Node left;
  Node right;
  // Node parent;
  String val;
  int hashValue;

  Node(int hashValue, String val) {
    left = null;
    right = null;
    this.hashValue = hashValue;
    this.val = val;
  }

  @Override
  public int compareTo(Node o) {
    return this.hashValue - o.hashValue;
  }
}

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int L = Integer.parseInt(br.readLine());

    String str = br.readLine();
    int length = str.length() - 1;

    while (length >= 2) {
      if(logestMatch(str, length)) break;
      length--;
    }
    if(length >= 2) System.out.println(length);
    else System.out.println(0);
  }
  static boolean logestMatch(String str, int length) {
    Tree binaryTree = new Tree();
    int hashValue = 0;
    int power = 1;

    for(int i = 0; i <= str.length() - length; i++) {
      if(i == 0) {
        for(int j = 0 ; j < length; j++) {
          hashValue += str.charAt(length - 1 - j) * power;

          if(j != length - 1) {
            power *= 7;
          }
        }
      } else {
        hashValue = 2 * (hashValue - str.charAt(i - 1) * power) + str.charAt(length - 1 + i);
      }

      if(binaryTree.insertAndCompare(new Node(hashValue, str.substring(i, i + length)))){
        return true;
      }
    }
    return false;
  }
}
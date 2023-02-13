import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

class Trie {

  private Node root;

  Trie() {
    if(root == null) root = new Node();
  }

  private int charToInt(char c) {
    return c - 'a';
  }

  void insert(String txt) {
    Node curNode = this.root;

    for(int i = 0; i < txt.length(); i++) {
      int num = charToInt(txt.charAt(i));
      if(curNode.childNode[num] == null) {
        curNode.childNode[num] = new Node();
        curNode.childNum++;
      }
      curNode = curNode.childNode[num];
    }
    curNode.isTerminal = true;
  }

  ArrayList<Integer> findIndex(String txt) {
    ArrayList<Integer> idxs = new ArrayList<>();
    Node curNode = this.root;

    for(int i = 0 ; i < txt.length(); i++) {
      int idx = charToInt(txt.charAt(i));

      curNode = curNode.childNode[idx];
      if(curNode == null) break;
      else if(curNode.isTerminal) idxs.add(i + 1);
    }
    return idxs;
  }
}

class Node {
  Node[] childNode;
  boolean isTerminal;
  int childNum;

  Node() {
    childNode = new Node[26];
    isTerminal = false;
    childNum = 0;
  }
}

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int C = Integer.parseInt(st.nextToken());
    int N = Integer.parseInt(st.nextToken());

    Trie trie = new Trie();
    for(int i = 0; i < C; i++)
      trie.insert(br.readLine());
    Set<String> nickNames = new HashSet<>();
    for(int i = 0; i < N; i++)
      nickNames.add(br.readLine());

    int teamNum = Integer.parseInt(br.readLine());

    for(int team = 0; team < teamNum; team++) {
      String teamName = br.readLine();
      ArrayList<Integer> colorIndex = trie.findIndex(teamName);
      boolean success = false;
      for(int i = 0; i < colorIndex.size(); i++) {
        int idx = colorIndex.get(i);
        if(idx == teamName.length()) continue;
        String subString = teamName.substring(idx);
        if(nickNames.contains(subString)) {
          success = true;
          break;
        }
      }
      if(success) System.out.println("Yes");
      else System.out.println("No");
    }
  }
}
import java.io.*;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N = Integer.parseInt(br.readLine());

    char[][] tree = new char[26][2];
    for (int i = 0; i < N; i++) {
      String nodes = br.readLine();
      int parNode = nodes.charAt(0) - 'A';
      tree[parNode][0] = nodes.charAt(2);
      tree[parNode][1] = nodes.charAt(4);
    }

    preOrder(tree, 'A');

    System.out.println();

    inOrder(tree, 'A');

    System.out.println();

    postOrder(tree, 'A');


  }
  static int charToInt(char c) {
    return c - 'A';
  }

  static void preOrder(char[][] tree, char node) {
    int idx = charToInt(node);
    if (idx < 0 || idx > 26) return;
    System.out.print(node);
    preOrder(tree, tree[idx][0]);
    preOrder(tree, tree[idx][1]);
  }

  static void inOrder(char[][] tree, char node) {
    int idx = charToInt(node);
    if (idx < 0 || idx > 26) return;
    inOrder(tree, tree[idx][0]);
    System.out.print(node);
    inOrder(tree, tree[idx][1]);
  }

  static void postOrder(char[][] tree, char node) {
    int idx = charToInt(node);
    if (idx < 0 || idx > 26) return;
    postOrder(tree, tree[idx][0]);
    postOrder(tree, tree[idx][1]);
    System.out.print(node);
  }
}

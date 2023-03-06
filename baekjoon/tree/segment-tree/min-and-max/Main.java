import java.io.*;
import java.util.StringTokenizer;

class Node {
  int min;
  int max;
  int val;

  Node(int val) {
    this.val = val;
  }

  Node(int min, int max) {
    this.min = min;
    this.max = max;
    val = -1;
  }
}

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[] nums = new int[N + 1];

    int h = (int)Math.ceil(Math.log(N) / Math.log(2));
    int tree_size = 1 << (h + 1);

    Node[] tree = new Node[tree_size];

    for (int i = 1; i < N + 1; i++) {
      nums[i] = Integer.parseInt(br.readLine());
    }

    init(nums, tree, 1, 1, N);

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int left = Integer.parseInt(st.nextToken());
      int right = Integer.parseInt(st.nextToken());
      Node queryNode = query(tree, 1, 1, N, left, right, new Node(Integer.MAX_VALUE, 0));
      bw.write(queryNode.min + " " + queryNode.max + "\n");
    }

    bw.flush();
    bw.close();
  }

  static void init(int[] nums, Node[] tree, int node, int start, int end) {
    if (start == end) {
      tree[node] = new Node(nums[start]);
    }
    else {
      init(nums, tree, node * 2, start, (start + end) / 2);
      init(nums, tree, node * 2 + 1, (start + end) / 2 + 1, end);
      if (tree[node * 2].val != -1 && tree[node * 2 + 1].val != -1) {
        int min = Math.min(tree[node * 2].val, tree[node * 2 + 1].val);
        int max = Math.max(tree[node * 2].val, tree[node * 2 + 1].val);
        tree[node] = new Node(min, max);
      }
      else if (tree[node * 2].val != -1) {
        int min = Math.min(tree[node * 2].val, tree[node * 2 + 1].min);
        int max = Math.max(tree[node * 2].val, tree[node * 2 + 1].max);
        tree[node] = new Node(min, max);
      }
      else if (tree[node * 2 + 1].val != -1) {
        int min = Math.min(tree[node * 2].min, tree[node * 2 + 1].val);
        int max = Math.max(tree[node * 2].max, tree[node * 2 + 1].val);
        tree[node] = new Node(min, max);
      }
      else {
        int min = Math.min(tree[node * 2].min, tree[node * 2 + 1].min);
        int max = Math.max(tree[node * 2].max, tree[node * 2 + 1].max);
        tree[node] = new Node(min, max);
      }
    }
  }

  static Node query(Node[] tree, int node, int start, int end, int left, int right, Node curNode) {
    if (right < start || end < left) {
      return curNode;
    }
    if (left <= start && end <= right) {
      int min, max;
      if (tree[node].val != -1) {
        min = max = tree[node].val;
      }
      else {
        min = tree[node].min;
        max = tree[node].max;
      }
      if (curNode.min > min) curNode.min = min;
      if (curNode.max < max) curNode.max = max;
      return curNode;
    }
    query(tree, node * 2, start, (start + end) / 2, left, right, curNode);
    query(tree, node * 2 + 1, (start + end) / 2 + 1, end, left, right, curNode);
    return curNode;
  }

}

import java.io.*;
import java.util.StringTokenizer;

public class Main {
  static int INF = Integer.MAX_VALUE;
  static int MIN = Integer.MIN_VALUE;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int T = Integer.parseInt(br.readLine());

    while (T-- > 0) {
      st = new StringTokenizer(br.readLine());

      int N = Integer.parseInt(st.nextToken());
      int K = Integer.parseInt(st.nextToken());

      int h = (int)Math.ceil(Math.log(N) / Math.log(2));
      int tree_size = 1 << (h + 1);

      int[] shelves = new int[N];
      for (int i = 0 ; i < N; i++) {
        shelves[i] = i;
      }

      Node[] tree = new Node[tree_size];

      initST(tree, 1, 0, N - 1);

      for (int i = 0; i < K; i++) {
        st = new StringTokenizer(br.readLine());

        int Q = Integer.parseInt(st.nextToken());
        int n1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());

        switch (Q) {
          case 0:
            update(tree, 1, 0, N - 1, n2, shelves[n1]);
            update(tree, 1, 0, N - 1, n1, shelves[n2]);
            int temp = shelves[n1];
            shelves[n1] = shelves[n2];
            shelves[n2] = temp;
            break;
          case 1:
            boolean result = query(tree, 1, 0, N - 1, n1, n2);
            if (result) bw.write("YES\n");
            else bw.write("NO\n");
            break;
        }
      }
    }
    bw.flush();
    bw.close();
  }

  static void initST(Node[] tree, int node, int start, int end) {
    if (start == end) {
      tree[node] = new Node(start, end);
    } else {
      initST(tree, node * 2, start, (start + end) / 2);
      initST(tree, node * 2 + 1, (start + end) / 2 + 1, end);

      tree[node] = new Node(Math.min(tree[node * 2].min, tree[node * 2 + 1].min),
              Math.max(tree[node * 2].max, tree[node * 2 + 1].max));
    }
  }

  static void update(Node[] tree, int node, int start, int end, int idx, int val) {
    if (idx < start || end < idx) {
      return;
    }
    if (start == end) {
      tree[node].min = tree[node].max = val;
      return;
    }

    update(tree, node * 2, start, (start + end) / 2, idx, val);
    update(tree, node * 2 + 1, (start + end) / 2 + 1, end, idx, val);

    tree[node].min = Math.min(tree[node * 2].min, tree[node * 2 + 1].min);
    tree[node].max = Math.max(tree[node * 2].max, tree[node * 2 + 1].max);
  }

  static boolean query(Node[] tree, int node, int start, int end, int left, int right) {
    if (right < start || end < left) {
      return true;
    }
    if (left <= start && end <= right) {
      return left <= tree[node].min && tree[node].max <= right;
    }

    int mid = (start + end) / 2;
    return query(tree, node * 2, start, mid, left, right) && query(tree, node * 2 + 1, mid + 1, end, left, right);
  }
}

class Node {
  int max;
  int min;

  Node(int max, int min) {
    this.max = max;
    this.min = min;
  }
}
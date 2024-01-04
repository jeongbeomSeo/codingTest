import java.io.*;
import java.util.StringTokenizer;

public class Main {
  private static final int MAX_SIZE = 65536 + 1;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    int[] array = new int[N];
    for (int i = 0; i < N; i++) {
      array[i] = Integer.parseInt(br.readLine());
    }

    int h = (int)Math.ceil(Math.log(MAX_SIZE) / Math.log(2));
    int treeSize = 1 << (h + 1);

    int[] segTree = new int[treeSize];

    for (int i = 0; i < K; i++) {
      updateTree(segTree, 1, 0, MAX_SIZE, array[i], true);
    }

    long result = 0L;
    final int COUNT = (K + 1) / 2;
    result += queryTree(segTree, 1, 0, MAX_SIZE, COUNT);
    for (int i = K; i < N; i++) {
      updateTree(segTree, 1, 0, MAX_SIZE, array[i - K], false);
      updateTree(segTree, 1, 0, MAX_SIZE, array[i], true);

      result += queryTree(segTree, 1, 0, MAX_SIZE, COUNT);
    }

    System.out.println(result);
  }
  private static void updateTree(int[] segTree, int node, int left, int right, int value, boolean isAdd) {
    if (value < left || right < value) return;
    else if (left == right) {
      if (isAdd) segTree[node]++;
      else segTree[node]--;
      return;
    }

    int mid = (left + right) / 2;
    updateTree(segTree, 2 * node, left, mid, value, isAdd);
    updateTree(segTree, 2 * node + 1,mid + 1, right, value, isAdd);

    segTree[node] = segTree[2 * node] + segTree[2 * node + 1];
  }
  private static int queryTree(int[] segTree, int node, int left, int right, int count) {
    if (left == right) {
      return left;
    } else if (segTree[2 * node] < count) {
      return queryTree(segTree, 2 * node + 1, (left + right) / 2 + 1, right, count - segTree[2 * node]);
    }
    return queryTree(segTree, 2 * node, left, (left + right) / 2, count);
  }
}
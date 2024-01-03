import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    Number[] array = new Number[N];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      int value = Integer.parseInt(st.nextToken());

      array[i] = new Number(i, value);
    }

    Arrays.sort(array);

    int h = getHeight(N);
    int size = (int)Math.pow(2, h + 1);

    int[] tree = new int[size];

    long result = 0L;
    for (int i = 0; i < N; i++) {
      int idx = array[i].idx;
      result += query(tree, 1, 0, N - 1, idx + 1, N - 1);
      update(tree, 1, 0, N - 1, idx);
    }

    System.out.println(result);
  }
  private static int query(int[] tree, int node, int left, int right, int start, int end) {
    if (end < left || right < start) return 0;

    else if (start <= left && right <= end) {
      return tree[node];
    }

    int mid = (left + right) / 2;
    int leftQuery = query(tree, 2 * node, left, mid, start, end);
    int rightQuery = query(tree, 2 * node + 1, mid + 1, right, start, end);

    return leftQuery + rightQuery;
  }
  private static void update(int[] tree, int node, int left, int right, int idx) {
    if (idx < left || right < idx) return;
    else if (left == right) {
      tree[node] = 1;
      return;
    }
    int mid = (left + right) / 2;
    update(tree, 2 * node, left, mid, idx);
    update(tree, 2 * node + 1, mid + 1, right, idx);
    tree[node] = tree[2 * node] + tree[node * 2 + 1];
  }
  private static int getHeight(int argSize) {
    return (int)Math.ceil(Math.log(argSize) / Math.log(2));
  }
}
class Number implements Comparable<Number>{
  int idx;
  int value;

  Number(int idx, int value) {
    this.idx = idx;
    this.value = value;
  }

  @Override
  public int compareTo(Number o) {
    return this.value - o.value;
  }
}
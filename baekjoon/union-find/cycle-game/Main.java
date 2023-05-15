import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    int[] parent = new int[n];
    for (int i = 0; i < n; i++) parent[i] = i;

    int count = 0;
    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());

      if (count == 0 && union_merge(parent, x, y)) count = i + 1;
    }
    System.out.println(count);
  }
  static int union_find(int[] parent, int x) {
    if (parent[x] == x) return x;

    return parent[x] = union_find(parent, parent[x]);
  }
  static boolean union_merge(int[] parent, int x, int y) {
    int parentX = union_find(parent, x);
    int parentY = union_find(parent, y);

    if (parentX == parentY && (x != parentX || y != parentY)) {
      parent[parentX] = parentY;
      return true;
    }
    parent[parentX] = parentY;
    return false;
  }
}

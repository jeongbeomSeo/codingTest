import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int tc = Integer.parseInt(br.readLine());

    while (tc-- > 0) {
      int F = Integer.parseInt(br.readLine());

      Map<String, Integer> convertNameToIdx = new HashMap<>();
      int idx = 1;

      int[] parent = new int[100000 * 2 + 1];
      int[] connectSize = new int[100000 * 2 + 1];

      for (int i = 0; i < F; i++) {
        st = new StringTokenizer(br.readLine());
        String f1 = st.nextToken();
        String f2 = st.nextToken();

        if (!convertNameToIdx.containsKey(f1)) {
          convertNameToIdx.put(f1, idx);
          parent[idx] = idx;
          connectSize[idx] = 1;
          idx++;
        }

        if (!convertNameToIdx.containsKey(f2)) {
          convertNameToIdx.put(f2, idx);
          parent[idx] = idx;
          connectSize[idx] = 1;
          idx++;
        }

        int x = convertNameToIdx.get(f1);
        int y = convertNameToIdx.get(f2);
        System.out.println(union_merge(parent, connectSize, x, y));
      }
    }
  }
  static int union_find(int[] parent, int x) {
    if (parent[x] == x) return x;

    return parent[x] = union_find(parent, parent[x]);
  }
  static int union_merge(int[] parent, int[] connectSize, int x, int y) {
    x = union_find(parent, x);
    y = union_find(parent, y);

    if (x == y) return connectSize[x];
    parent[x] = y;
    return connectSize[y] += connectSize[x];
  }
}
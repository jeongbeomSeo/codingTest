import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
  static int id;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int tc = Integer.parseInt(br.readLine());

    while (tc-- > 0) {
      id = 0;
      Map<String, Integer> convert_name_to_id = new HashMap<>();
      int F = Integer.parseInt(br.readLine());

      int[] parent = new int[2 * F];
      int[] count = new int[2 * F];
      for (int i = 0; i < 2 * F; i++) {
        parent[i] = i;
        count[i] = 1;
      }

      while (F-- > 0) {
        st = new StringTokenizer(br.readLine());
        String str1 = st.nextToken();
        String str2 = st.nextToken();

        if (!convert_name_to_id.containsKey(str1)) convert_name_to_id.put(str1, id++);
        if (!convert_name_to_id.containsKey(str2)) convert_name_to_id.put(str2, id++);
        int id1 = convert_name_to_id.get(str1);
        int id2 = convert_name_to_id.get(str2);

        System.out.println(union_merge(parent, count, id1, id2));
      }
    }
  }
  static int union_find(int[] parent, int id) {
    if (parent[id] == id) return id;

    return parent[id] = union_find(parent, parent[id]);
  }
  static int union_merge(int[] parent, int[] count, int id1, int id2) {
    id1 = union_find(parent, id1);
    id2 = union_find(parent, id2);


    if (id1 == id2) return count[id1];

    parent[id1] = id2;
    count[id2] += count[id1];
    return count[id2];
  }
}
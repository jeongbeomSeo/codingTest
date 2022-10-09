import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  static boolean[] check;
  static List<Integer>[] graph;
  static int[] ans;
  static int idx;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    graph = new ArrayList[N+1];
    check = new boolean[N+1];
    ans = new int[N];

    for(int i = 0; i < N-1; i++) {
      String s = br.readLine();
      StringTokenizer st = new StringTokenizer(s);

      int node1 = Integer.parseInt(st.nextToken());
      int node2 = Integer.parseInt(st.nextToken());

      if(graph[node1] == null) graph[node1] = new ArrayList<>();
      if(graph[node2] == null) graph[node2] = new ArrayList<>();

      graph[node1].add(node2);
      graph[node2].add(node1);
    }

    String s = br.readLine();
    StringTokenizer st = new StringTokenizer(s);
    int index = 0;
    while (st.hasMoreTokens()) {
        ans[index++] = Integer.parseInt(st.nextToken());
    }

    if(ans[0] == 1) {
      check[1] = true;
      idx = 1;
      dfs(-1,1);
    }

    if(N == idx) System.out.print(1);
    else System.out.print(0);
  }
  static void dfs(int par, int curNode) {
    Set<Integer> s = new HashSet<>();
    for(int i = 0; i < graph[curNode].size(); i++) {
      int node = graph[curNode].get(i);
      if(node != par) s.add(node);
    }
    int len = s.size();
    if(len == 0) return;
    while (len > 0) {
      int target  = ans[idx];
      if(!check[target] && s.contains(target)) {
        idx++;
        len--;
        check[target] = true;
        dfs(curNode, target);
      }
      else break;
    }
  }
}
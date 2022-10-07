import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  static boolean[] isVisited;
  static ArrayList<Integer>[] lines;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s = br.readLine();

    StringTokenizer st = new StringTokenizer(s);
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int V = Integer.parseInt(st.nextToken());

    lines = new ArrayList[N + 1];
    isVisited = new boolean[N + 1];

    for(int i = 0; i < M; i++) {
      String nums = br.readLine();
      StringTokenizer num = new StringTokenizer(nums);
      int node1 = Integer.parseInt(num.nextToken());
      int node2 = Integer.parseInt(num.nextToken());
      if(lines[node1] == null) {
        lines[node1] = new ArrayList<>();
      }
      if(lines[node2] == null) {
        lines[node2] = new ArrayList<>();
      }
      lines[node1].add(node2);
      lines[node2].add(node1);
    }

    for(int i = 1; i <= N; i++) {
      if(lines[i] != null) Collections.sort(lines[i]);
    }

    dfs(V);

    System.out.println();

    Arrays.fill(isVisited, false);

    bfs(V);
  }

  static void dfs(int node) {

    isVisited[node] = true;
    System.out.print(node + " ");

    Deque<Integer> stack = new LinkedList<>();
    stack.push(node);

    while(!stack.isEmpty()) {
      int now = stack.peek();

      boolean hasNearNode = false;
      for(int i : lines[now]) {
        if(!isVisited[i]) {
          hasNearNode = true;
          stack.push(i);
          isVisited[i] = true;
          System.out.println(i + " ");
          break;
        }
      }
      if(hasNearNode == false) {
        stack.pop();
      }
    }
  }

  static void bfs(int node) {

    Queue<Integer> queue = new LinkedList<>();
    queue.add(node);

    isVisited[node] = true;

    while (!queue.isEmpty()) {
      int v = queue.poll();
      System.out.println(v + " ");

      for(int i : lines[v]) {
        if(!isVisited[v]) {
          isVisited[v] = true;
          queue.add(v);
        }
      }
    }
  }
}
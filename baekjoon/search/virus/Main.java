import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    // 컴퓨터 수
    int n = Integer.parseInt(br.readLine());
    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    for(int i = 0; i < n + 1; i++) {
      graph.add(new ArrayList<>());
    }
    boolean[] isVisited = new boolean[n + 1];

    // 간선 수
    int m = Integer.parseInt(br.readLine());

    for(int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());

      graph.get(n1).add(n2);
      graph.get(n2).add(n1);
    }

    int count = bfs(graph, isVisited);
    System.out.println(count);

  }
  static int bfs(ArrayList<ArrayList<Integer>> graph, boolean[] isVisited) {
    Stack<Integer> stack = new Stack<>();

    int count = 0;

    stack.add(1);
    isVisited[1] = true;


    while (!stack.isEmpty()) {
      int com = stack.pop();

      for(int i = 0; i < graph.get(com).size(); i++) {
        int adjCom = graph.get(com).get(i);
        if(!isVisited[adjCom]) {
          stack.add(adjCom);
          isVisited[adjCom] = true;
          count++;
        }
      }
    }
    return count;
  }
}

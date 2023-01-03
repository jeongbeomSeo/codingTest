import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
  static boolean[] visited;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    // 정점의 수, 간선의 수
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    // Check Multiple Edge Using Set Collections
    ArrayList<Set<Integer>> graph = new ArrayList<>();
    for(int i = 0; i < N + 1; i++) {
      graph.add(new LinkedHashSet<>());
    }

    for(int i = 0 ; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());

      // Check Self-loop Edge
      if(n1 == n2) continue;

      graph.get(n1).add(n2);
      graph.get(n2).add(n1);
    }

    visited = new boolean[N + 1];
    minsik(1, graph);
  }
  static void minsik(int start, ArrayList<Set<Integer>> graph) {

    Stack<Integer> stack = new Stack<>();
    ArrayList<Integer> notVisitedNode = new ArrayList<>();

    stack.add(start);
    System.out.print(start + " ");
    visited[start] = true;

    while (!stack.isEmpty()) {
      int curNode = stack.peek();

      Iterator<Integer> itrAdjNode = graph.get(curNode).iterator();
      while (itrAdjNode.hasNext()) {
        int adjNode = itrAdjNode.next();
        if(!visited[adjNode]) {
          notVisitedNode.add(adjNode);
        }
      }

      // have notVisited Child Node
      if(!notVisitedNode.isEmpty()) {
        int cnt = notVisitedNode.size();
        if(cnt == 1) {
          stack.add(notVisitedNode.get(0));
          visited[notVisitedNode.get(0)] = true;
          System.out.print(notVisitedNode.get(0) + " ");
        }
        else {
          Collections.sort(notVisitedNode);
          // If child Node Num is Even
          if(cnt % 2 == 0) {
            stack.add(notVisitedNode.get(0));
            visited[notVisitedNode.get(0)] = true;
            System.out.print(notVisitedNode.get(0) + " ");
          }
          else {
            stack.add(notVisitedNode.get(cnt/2));
            visited[notVisitedNode.get(cnt/2)] = true;
            System.out.print(notVisitedNode.get(cnt/2) + " ");
          }
        }
      }
      // no have notVisited Child Node
      else stack.pop();

      // clear notVisitedNode List
      notVisitedNode.clear();
    }
  }
}
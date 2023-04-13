import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

  static int INF = Integer.MAX_VALUE;
  static int[] person;
  static int min = INF;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    person = new int[N + 1];
    int[] areaA = new int[N + 1];
    int[] areaB = new int[N + 1];

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i < N + 1; i++) {
      person[i] = Integer.parseInt(st.nextToken());
    }

    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < N + 1; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 1; i < N + 1; i++) {
      st = new StringTokenizer(br.readLine());

      int size = Integer.parseInt(st.nextToken());
      for (int j = 0; j < size; j++) {
        graph.get(i).add(Integer.parseInt(st.nextToken()));
      }
    }

    areaA[0] = 1;
    combination(graph, 2, N, areaA, areaB, 1, 0);

    if (min == INF) System.out.println(-1);
    else System.out.println(min);
  }
  static void combination(ArrayList<ArrayList<Integer>> graph, int ptr, int N, int[] areaA, int[] areaB, int sizeA, int sizeB) {

    if (ptr == N + 1) {
      if (sizeB != 0) {
        if (bfs(graph, areaA, sizeA, N) && bfs(graph, areaB, sizeB, N)) {
          int sumA = 0;
          int sumB = 0;
          for (int i = 0; i < sizeA; i++) {
            sumA += person[areaA[i]];
          }
          for (int i = 0; i < sizeB; i++) {
            sumB += person[areaB[i]];
          }
          min = Math.min(min, Math.abs(sumA - sumB));
        }
      }
    }
    else {
      areaA[sizeA] = ptr;
      combination(graph, ptr + 1, N, areaA, areaB, sizeA + 1, sizeB);

      areaB[sizeB] = ptr;
      combination(graph, ptr + 1, N, areaA, areaB, sizeA, sizeB + 1);
    }
  }

  static boolean bfs(ArrayList<ArrayList<Integer>> graph, int[] area, int size, int N) {
    Queue<Integer> q = new ArrayDeque<>();
    boolean[] isVisited = new boolean[N + 1];
    int check = 0;

    q.add(area[0]);
    isVisited[area[0]] = true;
    check++;

    while (!q.isEmpty()) {
      int curNode = q.poll();

      for (int i = 0; i < graph.get(curNode).size(); i++) {
        int adjNode = graph.get(curNode).get(i);

        for (int j = 0; j < size; j++) {
          // 방문 X + 구역에 맞는 도시인지 체크
          if (!isVisited[adjNode] && area[j] == adjNode) {
            q.add(adjNode);
            isVisited[adjNode] = true;
            check++;
            break;
          }
        }
      }
    }
    if (check == size) return true;
    return false;
  }
}
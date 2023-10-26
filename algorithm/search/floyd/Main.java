import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int INF = 10000000;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    // 도시의 개수
    int n = Integer.parseInt(br.readLine());
    int[][] graph = new int[n + 1][n + 1];

    // 버스의 개수
    int m = Integer.parseInt(br.readLine());

    // 그래프 초기화
    for(int i = 0; i < n + 1; i++) {
      for(int j = 0; j < n + 1; j++) {
        if(i != j) graph[i][j] = INF;
      }
    }

    // 버스 정보 입력
    for(int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      graph[n1][n2] = Math.min(cost, graph[n1][n2]);
    }

    // 플로이드 워셜 알고리즘 적용
    for(int k = 1; k < n + 1; k++) {
      for(int i = 1; i < n + 1; i++) {
        for(int j = 1; j < n + 1; j++) {
          graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
        }
      }
    }


    // 전부 출력 단,INF -> 으로 출력
    for(int i = 1; i < n + 1; i++) {
      for (int j = 1; j < n + 1; j++) {
        if (graph[i][j] != INF) System.out.print(graph[i][j] + " ");
        else System.out.print(0 + " ");
      }
      if(i != n) System.out.println();
    }
  }
}

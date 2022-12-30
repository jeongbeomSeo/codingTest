import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  // 좌 -> 상 -> 우 -> 하
  static int[] x = {-1, 1, 1, -1};
  static int[] y = {0, -1, 1, 1};
  static int INF = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    // N X M 배열
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[][] list = new int[N][M];

    for(int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      String row = st.nextToken();
      for(int j = 0; j < M; j++) {
        list[i][j] = row.charAt(j) - '0';
      }
    }

    int[][] dist = new int[N][M];
    for(int i = 0; i < N; i++) {
      for(int j = 0; j < M; j++) {
        dist[i][j] = INF;
      }
    }
    dist[0][0] = 1;

    boolean[][] visited = new boolean[N][M];

    int ans = checkDistance(list, dist, M, N, visited);
    System.out.println(ans);
  }
  static int checkDistance(int[][] list, int[][] dist, int M, int N, boolean[][] visited) {

    for(int count = 0 ; count < N * M; count++) {

      int[] nodeIdx = {0 ,0};
      int nodeValue = INF;

      for(int i = 0; i < N; i++) {
        for(int j = 0; j < M; j++) {
          if(!visited[i][j] && dist[i][j] < nodeValue) {
            nodeValue = dist[i][j];
            nodeIdx[0] = i; nodeIdx[1] = j;
          }
        }
      }

      if(nodeIdx[0] == N - 1 && nodeIdx[1] == M - 1) return nodeValue;

      visited[nodeIdx[0]][nodeIdx[1]] = true;

      for(int i = 0; i < 4; i++) {
        nodeIdx[0] += y[i];
        nodeIdx[1] += x[i];
        // 참고로 갈수 있는 길인지 먼저 체크를 한 다음 다른 것을 처리 -> index 먼저 확인 필수
        // 갈 수 있는 길인지 체크
        if(checkIndex(nodeIdx[1], nodeIdx[0], M, N, list)) {
          // 방문 했던 길인지 체크
          if(!visited[nodeIdx[0]][nodeIdx[1]]) {
            // 더 짧은 길인지 체크
            if(dist[nodeIdx[0]][nodeIdx[1]] > nodeValue) {
              dist[nodeIdx[0]][nodeIdx[1]] = nodeValue + 1;
            }
          }
        }
      }
    }
    return dist[N -1][M - 1];
  }
  static boolean checkIndex(int x, int y, int M, int N, int[][] list) {
    if(x >= 0 && x < M && y >= 0 && y > N) {
      if(list[y][x] == 1) return true;
      else return false;
    }
    else return false;
  }
}

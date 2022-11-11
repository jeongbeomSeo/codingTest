import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int[] a = {-1, 0, 1, 0};
  static int[] b = {0, 1, 0, -1};
  static int INF = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int count = 1;
    while(true) {
      int V = Integer.parseInt(br.readLine());

      if(V == 0) break;

      int[][] cave= new int[V][V];
      int[][] dist = new int[V][V];

      StringTokenizer st;
      for(int i = 0; i < V; i++) {
        st = new StringTokenizer(br.readLine());

        for(int j = 0; j < V; j++) {
          cave[i][j] = Integer.parseInt(st.nextToken());
          dist[i][j] = INF;
        }
      }

      int shortestDist = zeldaDijkstra(V, cave, dist);

      System.out.println("Problem " + count++ + ": " + shortestDist);

    }

  }
  static boolean boundaryCheck(int x, int y, int V) {
    if(0 <= x && x < V && 0 <= y && y < V) return true;
    else return false;
  }

  static int zeldaDijkstra(int V, int[][] cave, int[][] dist) {
    dist[0][0] = cave[0][0];
    boolean[][] isVisited = new boolean[V][V];

    int x = 0;
    int y = 0;

    for(int i = 0 ; i < V * V; i++) {
      if(x == V - 1 && y == V - 1) {
        return dist[x][y];
      }

      isVisited[x][y] = true;
      int cost = dist[x][y];

      for(int j = 0; j < 4; j++) {
        int X = x + a[j];
        int Y = y + b[j];
        if(boundaryCheck(X, Y, V)) {
          dist[X][Y] = Math.min(dist[X][Y], cost + cave[X][Y]);
        }
      }

      int min = Integer.MAX_VALUE;

      for(int j = 0; j < V; j++) {
        for(int z = 0; z < V; z++) {
          if(!isVisited[j][z] && min > dist[j][z]) {
            x = j;
            y = z;
            min = dist[j][z];
          }
        }
      }
    }
    return dist[V-1][V-1];
  }
}

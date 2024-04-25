import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final int[] DR = {-1, 0, 1, 0};
    private static final int[] DC = {0, 1, 0, -1};

    private static final int[] HORSE_DR = {-1, -2, -2, -1, 1, 2, 2, 1};
    private static final int[] HORSE_DC = {-2, -1, 1, 2, 2, 1, -1, -2};

    private static final int INF = Integer.MAX_VALUE;

    private static int H;
    private static int W;
    private static int[][] grid;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        grid = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = bfs(K);
        System.out.println(result != INF ? result : -1);
    }
    private static int bfs(int K) {
        boolean[][][] isVisited = new boolean[H][W][K + 1];

        Queue<Item> q = new ArrayDeque<>();
        q.add(new Item(0, 0, 0, K));
        isVisited[0][0][K] = true;

        while (!q.isEmpty()) {
            Item curItem = q.poll();

            if (curItem.row == H - 1 && curItem.col == W - 1) {
                return curItem.count;
            }

            for (int i = 0; i < 4; i++) {
                int nxtRow = curItem.row + DR[i];
                int nxtCol = curItem.col + DC[i];

                if (isValidPoint(nxtRow, nxtCol) && !isVisited[nxtRow][nxtCol][curItem.k] && grid[nxtRow][nxtCol] != 1) {
                    isVisited[nxtRow][nxtCol][curItem.k] = true;
                    q.add(new Item(nxtRow, nxtCol, curItem.count + 1, curItem.k));
                }
            }
            if (curItem.k > 0) {
                for (int i = 0; i < 8; i++) {
                    int nxtRow = curItem.row + HORSE_DR[i];
                    int nxtCol = curItem.col + HORSE_DC[i];

                    if (isValidPoint(nxtRow, nxtCol) && !isVisited[nxtRow][nxtCol][curItem.k] && grid[nxtRow][nxtCol] != 1) {
                        isVisited[nxtRow][nxtCol][curItem.k - 1] = true;
                        q.add(new Item(nxtRow, nxtCol, curItem.count + 1, curItem.k - 1));
                    }
                }
            }
        }

        return INF;
    }
    private static boolean isValidPoint(int row, int col) {
        return row >= 0 && col >= 0 && row < H && col < W;
    }
}
class Item {
    int row;
    int col;
    int count;
    int k;

    Item(int row, int col, int count, int k) {
        this.row = row;
        this.col = col;
        this.count = count;
        this.k = k;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] lines = new int[N][4];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                lines[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (isCross(lines[i], lines[j])) {
                    union(parent, i, j);
                }
            }
        }

        Map<Integer, Integer> groups = new HashMap<>();
        for (int i = 0; i < N; i++) {
            groups.put(getParent(parent, i), groups.getOrDefault(getParent(parent, i), 0) + 1);
        }

        System.out.println(groups.size());
        System.out.println(groups.values().stream().max(Integer::compare).get());
    }
    private static boolean isCross(int[] line1, int[] line2) {

        int ccw1 = ccw(line1[0], line1[1], line1[2], line1[3], line2[0], line2[1]) * ccw(line1[0], line1[1], line1[2], line1[3], line2[2], line2[3]);
        int ccw2 = ccw(line2[0], line2[1], line2[2], line2[3], line1[0], line1[1]) * ccw(line2[0], line2[1], line2[2], line2[3], line1[2], line1[3]);

        if (ccw1 == 0 && ccw2 == 0) {
            if (Math.min(line1[0], line1[2]) > Math.max(line2[0], line2[2]) || Math.min(line2[0], line2[2]) > Math.max(line1[0], line1[2])) {
                return false;
            }

            if (Math.min(line1[1], line1[3]) > Math.max(line2[1], line2[3]) || Math.min(line2[1], line2[3]) > Math.max(line1[1], line1[3])) {
                return false;
            }
        }

        return ccw1 <= 0 && ccw2 <= 0;
    }
    private static int ccw(int x1, int y1, int x2, int y2, int x3, int y3) {
        int result = x1 * y2 + x2 * y3 + x3 * y1 - (x2 * y1 + x3 * y2 + x1 * y3);
        return Integer.compare(result, 0);
    }
    private static int getParent(int[] parent, int x) {
        if (parent[x] == x) return parent[x];

        return parent[x] = getParent(parent, parent[x]);
    }
    private static void union(int[] parent, int p1, int p2) {
        int parent1 = getParent(parent, p1);
        int parent2 = getParent(parent, p2);

        if (parent1 < parent2) {
            parent[parent2] = parent1;
        } else if (parent1 > parent2) {
            parent[parent1] = parent2;
        }
    }
}
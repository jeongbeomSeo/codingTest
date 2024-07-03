import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());

            points[i] = new Point(x, y);
        }

        Point root = new Point(Long.MAX_VALUE, Long.MAX_VALUE);

        for (int i = 0; i < N; i++) {
            if (root.y > points[i].y) root = points[i];
            else if (root.y == points[i].y) {
                if (root.x > points[i].x) root = points[i];
            }
        }

        Point finalRoot = root;
        Arrays.sort(points, (o1, o2) -> {
            int ccw = getCCW(finalRoot, o1, o2);

            if (ccw == 0) {
                long d1 = getDist(finalRoot, o1);
                long d2 = getDist(finalRoot, o2);

                if (d1 > d2) return 1;
                else if (d1 < d2) return -1;
                return 0;
            }
            if (ccw > 0) return -1;
            else return 1;
        });

        Deque<Point> stack = new ArrayDeque<>();
        stack.push(root);

        for (int i = 1; i < N; i++) {
            Point targetPoint = points[i];

            while (stack.size() > 1) {
                Point prevPoint = stack.pop();
                Point prevprevPoint = stack.pop();

                int ccw = getCCW(prevprevPoint, prevPoint, targetPoint);

                stack.push(prevprevPoint);
                if (ccw > 0) {
                    stack.push(prevPoint);
                    break;
                }
            }
            stack.push(targetPoint);
        }

        System.out.println(stack.size());
    }

    private static long getDist(Point p1, Point p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }
    private static int getCCW(Point stdP, Point p1, Point p2) {
        long v = (stdP.x * p1.y + p1.x * p2.y + p2.x * stdP.y) - (p1.x * stdP.y + p2.x * p1.y + stdP.x * p2.y);

        if (v > 0) return 1;
        else if (v < 0) return -1;
        return 0;
    }
}
class Point {
    long x;
    long y;

    Point(long x, long y) {
        this.x = x;
        this.y = y;
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long score = Long.parseLong(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        long[] scores = new long[N];
        if (N >= 1) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                scores[i] = Long.parseLong(st.nextToken());
            }
        }

        int lowerIdx = lowerBound(scores, 0, N, score);
        int upperIdx = upperBound(scores, 0, N, score);

        if (upperIdx + 1 > P) System.out.println(-1);
        else System.out.println(lowerIdx + 1);

    }
    private static int upperBound(long[] scores, int left, int right, long score) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (scores[mid] >= score) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
    private static int lowerBound(long[] scores, int left, int right, long score) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (scores[mid] > score) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}

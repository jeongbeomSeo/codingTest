import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken());

        int[] result = recursive(N, 1, 3, K);

        System.out.println(result[0] + " " + result[1]);
    }
    private static int[] recursive(int N, int start, int end, long remain) {

        long curCount = (long)Math.pow(2, N) - 1;
        int[] result = null;
        if (remain - curCount > 0) {
            // N개 start -> end
            remain -= curCount;

            // N + 1블럭 start -> 6 - start - end로 이동
            remain--;

            if (remain == 0) {
                return new int[]{start, 6 - start - end};
            } else {
                result = recursive(N, end, 6 - start - end, remain);
            }
        } else {
            result = recursive(N - 1, start, 6 - start - end, remain);
        }

        return result;
    }
}

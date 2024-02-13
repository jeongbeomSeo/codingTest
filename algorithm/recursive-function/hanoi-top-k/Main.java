import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long K = Long.parseLong(st.nextToken());

        query(N, K, 1, 3);
    }
    private static void query(int N, long K, int start, int end) {

        if (K <= (long)Math.pow(2, N - 1) - 1) {
            query(N - 1, K, start, 6 - start - end);
            return;
        }

        K -= (long)Math.pow(2, N - 1) - 1;

        if (K == 1) {
            System.out.println(start + " " + end);
            return;
        }

        query(N - 1, K - 1, 6 - start - end, end);
    }
}
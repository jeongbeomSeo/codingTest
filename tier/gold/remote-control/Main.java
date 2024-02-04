import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int INF = Integer.MAX_VALUE;
    private static int MIN;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        boolean[] isBrokenBtn = new boolean[10];
        if (M != 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int btnNum = Integer.parseInt(st.nextToken());

                isBrokenBtn[btnNum] = true;
            }
        }

        int result = Math.abs(100 - N);

        int len = String.valueOf(N).length();
        MIN = INF;
        query(isBrokenBtn, "", 0, len != 6 ? len : 5, N);

        System.out.println(Math.min(result, MIN));
    }
    private static void query(boolean[] isBrokenBtn, String buffer, int len, int maxLen, int N) {

        if (len > 0) {
            MIN = Math.min(MIN, Math.abs(Integer.parseInt(buffer) - N) + len);
        }

        if (len <= maxLen) {
            for (int i = 0; i < 10; i++) {
                if (!isBrokenBtn[i]) {
                    query(isBrokenBtn, buffer + i, len + 1, maxLen, N);
                }
            }
        }
    }
}
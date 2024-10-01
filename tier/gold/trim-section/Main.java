import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] bars = new int[N][2];

        long maxLength = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            bars[i][0] = Integer.parseInt(st.nextToken());
            bars[i][1] = Integer.parseInt(st.nextToken());

            maxLength = Math.max(maxLength, bars[i][1]);
        }

        long resultS = maxLength + 1;
        long resultE = maxLength + 1;
        int e = 0;

        long sum = 0;

        for (int s = 0; s < maxLength; s++) {
            while (e < maxLength && sum < K) {
                for (int i = 0; i < N; i++) {
                    if (bars[i][0] < e && e <= bars[i][1]) {
                        sum++;
                    }
                }
                e++;
            }

            if (sum == K) {
                if (resultS > s || resultS == s && resultE > e) {
                    resultE = e - 1;
                    resultS = Math.max(s - 1, 0);
                }
            }

            for (int i = 0; i < N; i++) {
                if (bars[i][0] < s && s <= bars[i][1]) sum--;
            }
        }

        if (resultS == 0 && resultE == maxLength + 1) System.out.println("0 0");
        else System.out.println(resultS + " " + resultE);
    }
}
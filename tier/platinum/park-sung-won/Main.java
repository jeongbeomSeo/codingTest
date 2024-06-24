import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] arr = new String[N];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
        }

        int K = Integer.parseInt(br.readLine());

        int[] regist = getRegist(arr, K);
        int[] tenRegist = new int[51];
        tenRegist[0] = 1 % K;
        for (int i = 1; i < 51; i++) {
            tenRegist[i] = (tenRegist[i - 1] * 10) % K;
        }

        long[][] dp = new long[K][(1 << N)];

        dp[0][0] = 1;

        for (int cur = 0; cur < (1 << N); cur++) {
            for (int i = 0; i < N; i++) {
                if (((1 << i) & cur) != 0) continue;
                int nxtState = cur | (1 << i);

                for (int j = 0; j < K; j++) {
                    int nxtK = (j * tenRegist[arr[i].length()] + regist[i]) % K;
                    dp[nxtK][nxtState] += dp[j][cur];
                }
            }
        }

        long denominator = 1L;
        for (int i = 2; i <= N; i++) denominator *= i;
        long numerator = dp[0][(1 << N) - 1];
        long gcd = getGCD(denominator, numerator);
        System.out.println(numerator / gcd + String.valueOf("/") + denominator / gcd);
    }
    private static long getGCD(long a, long b) {
        if (b == 0) {
            return a;
        }

        return getGCD(b, a % b);
    }
    private static int[] getRegist(String[] arr, int K) {
        int[] regist = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            regist[i] = getRemain(arr[i], K);
        }

        return regist;
    }
    private static int getRemain(String numStr, int K) {

        int num = 0;
        for (int i = 0; i < numStr.length(); i++) {
            num *= 10;
            num += numStr.charAt(i) - '0';
            num %= K;
        }

        return num;
    }
}

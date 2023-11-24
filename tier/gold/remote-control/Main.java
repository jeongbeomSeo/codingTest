import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int result = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int M = Integer.parseInt(br.readLine());

        boolean[] isBrokenBtn = new boolean[10];
        if (M > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                isBrokenBtn[Integer.parseInt(st.nextToken())] = true;
            }
        }

        int MAX = getMAXNum(N);

        System.out.println(queryResult(MAX, isBrokenBtn, N));
    }
    private static int queryResult(int MAX, boolean[] isBrokenBtn, int target) {
        result = Math.abs(100 - target);

        recursive(MAX, isBrokenBtn, target, "");

        return result;
    }
    private static void recursive(int MAX, boolean[] isBrokenBtn, int target, String str) {

        int curNum = 0;
        if (!str.isEmpty()) {
            curNum = Integer.parseInt(str);
        }
        int length = str.length();
        if (curNum <= MAX && length <= String.valueOf(MAX).length()) {
            if (!str.isEmpty()) {
                result = Math.min(result, Math.abs(target - curNum) + length);
            }

            for (int i = 0; i <= 9; i++) {
                if (!isBrokenBtn[i]) {
                    recursive(MAX, isBrokenBtn, target, i + str);
                }
            }
        }
    }
    private static int getMAXNum(int N) {

        String str = String.valueOf(N);
        int length = str.length();

        String result = "2" +
                "0".repeat(length);

        return Integer.parseInt(result);
    }
}
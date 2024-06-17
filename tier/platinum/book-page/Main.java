import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int[] result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        result = new int[10];

        solve(1, N, 0);

        for (int i = 0; i < 10; i++) {
            System.out.print(result[i] + " ");
        }
    }
    private static void solve(int A, int B, int pow) {

        while (A % 10 != 0 && A <= B) {
            calc(A, pow);
            A++;
        }

        if (A > B) return;

        while (B % 10 != 9 && A <= B) {
            calc(B, pow);
            B--;
        }

        int cnt = (B / 10 - A / 10) + 1;
        for (int i = 0; i < 10; i++) {
            result[i] += cnt * (int) Math.pow(10, pow);
        }

        solve(A / 10, B / 10, pow + 1);
    }
    private static void calc(int num, int pow) {
        while (num != 0) {
            result[num % 10] += (int) Math.pow(10, pow);
            num /= 10;
        }
    }
}
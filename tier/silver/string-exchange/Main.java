import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int N = s.length();
        int K = 0; // 'a'의 개수

        // 'a'의 개수 세기
        for (int i = 0; i < N; i++) {
            if (s.charAt(i) == 'a') K++;
        }

        // 예외 처리: 'a'가 없거나 문자열 전체가 'a'인 경우
        if (K == 0 || K == N) {
            System.out.println(0);
            return;
        }

        // 문자열 두 배로 늘리기
        String doubleS = s + s;

        // 접두사 합 배열 생성 ('b'의 개수 누적)
        int[] prefixSum = new int[2 * N + 1];
        for (int i = 0; i < 2 * N; i++) {
            prefixSum[i + 1] = prefixSum[i] + (doubleS.charAt(i) == 'b' ? 1 : 0);
        }

        // 슬라이딩 윈도우로 최소 'b'의 개수 찾기
        int minB = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int currentB = prefixSum[i + K] - prefixSum[i];
            if (currentB < minB) {
                minB = currentB;
            }
        }

        // 최소 교환 횟수 출력
        System.out.println(minB);
    }
}

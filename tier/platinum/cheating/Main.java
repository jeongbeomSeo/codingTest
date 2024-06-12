import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        boolean[] canSet = initCanSet();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (T-- != 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            char[][] grid = new char[N][M];
            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                for (int j = 0; j < M; j++) {
                    grid[i][j] = str.charAt(j);
                }
            }

            int[][] dp = new int[N][1 << M];

            for (int j = 0; j < (1 << M); j++) {
                if (checkValidation(canSet, grid[0], j)) {
                    dp[0][j] = Integer.bitCount(j);
                }
            }

            for (int i = 1; i < N; i++) {
                for (int j = 0; j < (1 << M); j++) {
                    if (checkValidation(canSet, grid[i], j)) {
                        char[] prevCanSeats = Arrays.copyOf(grid[i - 1], grid[i - 1].length);
                        updatePrevCanSeats(prevCanSeats, j);
                        for (int k = 0; k < (1 << M); k++) {
                            if (checkValidation(canSet, prevCanSeats, k)) {
                                dp[i][j] = Math.max(dp[i][j], dp[i - 1][k] + Integer.bitCount(j));
                            }
                        }
                    }
                }
            }

            int max = 0;
            for (int i = 0; i < (1 << M); i++) {
                max = Math.max(max, dp[N - 1][i]);
            }

            bw.write(max + "\n");
        }
        bw.flush();
        bw.close();
    }
    private static void updatePrevCanSeats(char[] seats, int set) {

        for (int i = 0; i < seats.length; i++) {
            if ((set & (1 << i)) != 0) {
                if (i == 0) {
                    if (i + 1 < seats.length) seats[i + 1] = 'x';
                } else if (i == seats.length - 1) {
                    seats[i - 1] = 'x';
                } else {
                    seats[i - 1] = seats[i + 1] = 'x';
                }
            }
        }
    }
    private static boolean checkValidation(boolean[] canSet, char[] seats, int set) {
        if (!canSet[set]) return false;

        for (int i = 0; i < seats.length; i++) {
            if ((set & (1 << i)) != 0 && seats[i] == 'x') return false;
        }

        return true;
    }
    private static boolean[] initCanSet() {
        boolean[] canSet = new boolean[1 << 10];

        for (int i = 0; i < (1 << 10); i++) {
            int j;
            for (j = 0; j < 10; j++) {
                if ((i & (1 << j)) != 0) {
                    if (j == 0) {
                        if ((i & (1 << (j + 1))) != 0) {
                            break;
                        }
                    } else if (j == 9) {
                        if ((i & (1 << (j - 1))) != 0) {
                            break;
                        }
                    } else {
                        if ((i & (1 << (j - 1))) != 0 || (i & (1 << (j + 1))) != 0) {
                            break;
                        }
                    }
                }
            }
            if (j == 10) {
                canSet[i] = true;
            }
        }
        return canSet;
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int R;
    private static int C;
    private static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        count = 0;
        // query_cut((int)Math.pow(2, N), R, C);
        query_up((int)Math.pow(2, N), 0, 0);
        System.out.println(count);

    }
    private static void query_up(int size, int row, int col) {
        if (size == 1) return;

        int newSize = size / 2;
        if (R < row + newSize && C < col + newSize) {
            query_up(newSize, row, col);
        } else if (R < row + newSize) {
            count += (newSize * newSize);
            query_up(newSize, row, col + newSize);
        } else if (C < col + newSize) {
            count += (newSize * newSize) * 2;
            query_up(newSize, row + newSize, col);
        } else {
            count += (newSize * newSize) * 3;
            query_up(newSize, row + newSize, col + newSize);
        }
    }
    private static void query_cut(int size, int row, int col) {
        if (size == 1) return;

        int newSize = size / 2;
        if (row < newSize && col < newSize) {
            query_cut(newSize, row, col);
        } else if (row < newSize) {
            count += (newSize * newSize);
            query_cut(newSize, row, col - newSize);
        } else if (col < newSize) {
            count += (newSize * newSize) * 2;
            query_cut(newSize, row - newSize, col);
        } else {
            count += (newSize * newSize) * 3;
            query_cut(newSize, row - newSize, col - newSize);
        }
    }
}
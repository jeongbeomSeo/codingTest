import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final int MAX = 10000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            String commandResult = query(A, B);

            bw.write(commandResult + "\n");
        }
        bw.flush();
        bw.close();
    }
    private static String query(int initNum, int target) {

        Queue<Registry> q = new ArrayDeque<>();
        boolean[] isVisited = new boolean[MAX];
        q.add(new Registry(initNum, ""));
        isVisited[initNum] = true;

        Registry result = null;
        while (!q.isEmpty()) {
            Registry curRegistry = q.poll();

            if (curRegistry.num == target) {
                result = curRegistry;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nextNum = calc(curRegistry.num, i);

                if (!isVisited[nextNum]) {
                    q.add(new Registry(nextNum, curRegistry.command + convertCalcNum(i)));
                    isVisited[nextNum] = true;
                }
            }
        }

        return result.command;
    }
    private static char convertCalcNum(int calcNum) {
        switch (calcNum) {
            case 0:
                return 'D';
            case 1:
                return 'S';
            case 2:
                return 'L';
            default:
                return 'R';
        }
    }
    private static int calc(int num, int calcNum) {

        int nextNum = 0;
        switch (calcNum) {
            case 0:
                nextNum = calc_D(num);
                break;
            case 1:
                nextNum = calc_S(num);
                break;
            case 2:
                nextNum = calc_L(num);
                break;
            default:
                nextNum = calc_R(num);
                break;
        }

        return nextNum;
    }
    private static int[] getBuffer(int num) {

        int[] buffer = new int[4];

        for (int i = 0; i < 4; i++) {
            buffer[i] = num % 10;
            num /= 10;
        }

        return buffer;
    }
    private static int calc_L(int num) {

        int[] buffer = getBuffer(num);

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i >= 0; i--) {
            sb.append(buffer[i]);
        }
        sb.append(buffer[3]);
        return Integer.parseInt(sb.toString());
    }
    private static int calc_R(int num) {

        int[] buffer = getBuffer(num);

        StringBuilder sb = new StringBuilder();
        sb.append(buffer[0]);

        for (int i = 3; i >= 1; i--) {
            sb.append(buffer[i]);
        }
        return Integer.parseInt(sb.toString());
    }
    private static int calc_D(int num) {

        int nextNum = num * 2;

        if (nextNum >= MAX) {
            nextNum %= MAX;
        }

        return nextNum;
    }
    private static int calc_S(int num) {

        int nextNum = num - 1;

        if (nextNum == -1) {
            nextNum = 9999;
        }

        return nextNum;
    }
}
class Registry {
    int num;
    String command;
    Registry(int num, String command) {
        this.num = num;
        this.command = command;
    }
}
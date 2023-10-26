import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            String result = queryResult(a, b);
            bw.write(result + "\n");
        }
        bw.flush();
        bw.close();
    }
    private static String queryResult(int a, int b) {
        Queue<Node> q = new ArrayDeque<>();

        boolean[] isVisited = new boolean[10000];

        q.add(new Node(a, ""));
        isVisited[a] = true;

        String result = "";
        while (!q.isEmpty()) {
            Node curNode = q.poll();

            if (curNode.value == b) {
                result = curNode.command;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nextValue = getNextValue(curNode.value, i);

                if (!isVisited[nextValue]) {
                    q.add(new Node(nextValue, getNextCommand(curNode.command, i)));
                    isVisited[nextValue] = true;
                }
            }
        }

        return result;
    }
    private static String getNextCommand(String command, int i) {
        if (i == 0) return command + "D";
        else if (i == 1) return command + "S";
        else if (i == 2) return command + "L";
        else return command + "R";
    }
    private static int getNextValue(int num, int i) {
        if (i == 0) return calcCase_D(num);
        else if (i == 1) return calcCase_S(num);
        else if (i == 2) return calcCase_L(num);
        else return calcCase_R(num);
    }

    private static int calcCase_D(int num) {
        return (2 * num) % 10000;
    }

    private static int calcCase_S(int num) {
        return num != 0 ? num - 1 : 9999;
    }

    private static int calcCase_L(int num) {
        StringBuilder sb = new StringBuilder();

        String strNum = fotmatStr(String.valueOf(num));

        int size = strNum.length();
        for (int i = 1; i < size; i++) {
            sb.append(strNum.charAt(i));
        }

        sb.append(strNum.charAt(0));

        return Integer.parseInt(sb.toString());
    }

    private static int calcCase_R(int num) {
        StringBuilder sb = new StringBuilder();

        String strNum = fotmatStr(String.valueOf(num));

        int size = strNum.length();
        sb.append(strNum.charAt(size - 1));

        for (int i = 0; i < size - 1; i++) {
            sb.append(strNum.charAt(i));
        }

        return Integer.parseInt(sb.toString());
    }
    private static String fotmatStr(String strNum) {
        int size = strNum.length();

        if (size < 4) {
            StringBuilder strNumBuilder = new StringBuilder(strNum);
            while (strNumBuilder.length() != 4) {
                strNumBuilder.insert(0, "0");
            }
            strNum = strNumBuilder.toString();
        }
        return strNum;
    }
}
class Node {
    int value;
    String command;

    Node (int value, String command) {
        this.value = value;
        this.command = command;
    }
}

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

        while (T-- != 0) {
            st = new StringTokenizer(br.readLine());

            char[] array = convertToCharArray(Integer.parseInt(st.nextToken()));
            char[] target = convertToCharArray(Integer.parseInt(st.nextToken()));

            String resultCommand = bfs(target, array);

            bw.write(resultCommand + "\n");
        }

        bw.flush();
        bw.close();
    }
    private static String bfs(char[] target, char[] initArray) {
        Queue<Registry> buffer = new ArrayDeque<>();
        // 핵심적인 요소임 -> 없으면 메모리 초과나옴
        boolean[] isVisited = new boolean[10000];
        buffer.add(new Registry(initArray));
        isVisited[Integer.parseInt(String.valueOf(initArray))] = true;
        while (!buffer.isEmpty()) {
            Registry curRegistry = buffer.poll();

            for (int i = 0; i < 4; i++) {
                Registry nxtRegistry = modulate(curRegistry, i);
                if (compareCharArray(nxtRegistry.value, target)) return nxtRegistry.command;
                if (isVisited[Integer.parseInt(String.valueOf(nxtRegistry.value))]) continue;
                buffer.add(nxtRegistry);
                isVisited[Integer.parseInt(String.valueOf(nxtRegistry.value))] = true;
            }
        }

        return null;
    }
    private static Registry modulate(Registry registry, int i) {
        if (i == 0) return new Registry(registry.command + "D", modulate_D(registry.value));
        else if (i == 1) return new Registry(registry.command + "S", modulate_S(registry.value));
        else if (i== 2) return new Registry(registry.command + "L", modulate_L(registry.value));
        else return new Registry(registry.command + "R", modulate_R(registry.value));
    }
    private static boolean compareCharArray(char[] arrayA, char[] arrayB) {

        for (int i = 0; i < 4; i++) {
            if (arrayA[i] != arrayB[i]) return false;
        }

        return true;
    }
    private static char[] convertToCharArray(int num) {
        char[] array = new char[4];

        for (int i = 3; i >= 0; i--) {
            // Check: (char)3 is '3'?
            array[i] = (char)(num % 10 + '0');

            num /= 10;
        }

        return array;
    }
    private static char[] modulate_D(char[] array) {
        int num = Integer.parseInt(String.valueOf(array));

        num = (num * 2) % 10000;

        return convertToCharArray(num);
    }
    private static char[] modulate_S(char[] array) {

        if (String.valueOf(array).equals("0000")) return new char[]{'9', '9', '9', '9'};

        char[] nextArray = new char[4];
        int i;
        for (i = 3; i >= 0; i--) {
            if (array[i] == '0') {
                nextArray[i] = '9';
            } else {
                nextArray[i] = (char)(array[i] - 1);
                i--;
                break;
            }
        }
        while (i >= 0) {
            nextArray[i] = array[i];
            i--;
        }

        return nextArray;
    }
    private static char[] modulate_R(char[] array) {

        char[] nextArray = new char[4];
        nextArray[0] = array[3];

        for (int i = 3; i >= 1; i--) {
            nextArray[i] = array[i - 1];
        }

        return nextArray;
    }
    private static char[] modulate_L(char[] array) {

        char[] nextArray = new char[4];
        nextArray[3] = array[0];
        for (int i = 1; i < 4; i++) {
            nextArray[i - 1] = array[i];
        }

        return nextArray;
    }
}
class Registry {
    String command;
    char[] value;

    Registry(char[] value) {
        command = "";
        this.value = value;
    }
    Registry(String command, char[] value) {
        this.command = command;
        this.value = value;
    }
}
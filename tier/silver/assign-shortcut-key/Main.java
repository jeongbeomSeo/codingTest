import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        boolean[] isUsed = new boolean['Z' - 'A' + 1];

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < N; i++) {
            String str = br.readLine();

            StringTokenizer st = new StringTokenizer(str);

            boolean isSuccess = false;
            int idx = 0;
            while (st.hasMoreTokens()) {
                String word = st.nextToken();

                if (checkUsing(isUsed, word.charAt(0))) {
                    isSuccess = true;
                    break;
                } else {
                    idx += word.length() + 1;
                }
            }

            if (!isSuccess) {
                for (idx = 0; idx < str.length(); idx++) {
                    char c = str.charAt(idx);
                    if (isAlphabet(c)) {
                        if (checkUsing(isUsed, c)) {
                            isSuccess = true;
                            break;
                        }
                    }
                }
            }
            if (!isSuccess) bw.write(str + '\n');
            else bw.write(str.substring(0, idx) + "[" + str.charAt(idx) + "]" + str.substring(idx + 1) + '\n');
        }
        bw.flush();
        bw.close();
    }

    private static boolean isAlphabet(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private static boolean checkUsing(boolean[] isUsed, char c) {
        if (c >= 'a' && c <= 'z') {
            if (isUsed[c - 'a']) return false;
            return isUsed[c - 'a'] = true;
        } else if (c >= 'A' && c <= 'Z'){
            if (isUsed[c - 'A']) return false;
            return isUsed[c - 'A'] = true;
        }

        return false;
    }
}

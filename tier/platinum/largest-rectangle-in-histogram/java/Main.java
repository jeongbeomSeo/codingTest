import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (true) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());

            if (n == 0) break;

            
        }
    }
}
class Node {
    int idx;
    Long height;

    Node(int idx, Long height) {
        this.idx = idx;
        this.height = height;
    }
}
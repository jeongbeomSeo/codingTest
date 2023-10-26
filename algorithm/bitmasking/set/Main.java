import java.io.*;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int M = Integer.parseInt(br.readLine());

    int set = 0;

    while (M-- > 0) {
      st = new StringTokenizer(br.readLine());
      String command = st.nextToken();
      int x;
      switch (command) {
        case "add":
          x = Integer.parseInt(st.nextToken());
          set |= (1 << x);
          break;
        case "remove":
          x = Integer.parseInt(st.nextToken());
          set &= ~(1 << x);
          break;
        case "toggle":
          x = Integer.parseInt(st.nextToken());
          set ^= (1 << x);
          break;
        case "check":
          x = Integer.parseInt(st.nextToken());
          if ((set & (1 << x)) == (1 << x)) bw.write(1 + "\n");
          else bw.write(0 + "\n");
          break;
        case "all":
          set = -1;
          break;
        case "empty":
          set = 0;
          break;
      }
    }
    bw.flush();
    bw.close();
  }
}
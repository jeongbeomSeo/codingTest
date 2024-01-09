import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    String txt = br.readLine();
    String pat = br.readLine();

    int txtLen = txt.length();
    int patLen = pat.length();
    int[] skip = initSkip(pat, patLen);

    int pt = 0;
    int pp = 0;

    Queue<Integer> buffer = new ArrayDeque<>();
    while (pt < txtLen) {
      if (txt.charAt(pt) == pat.charAt(pp)) {
        pt++;
        pp++;
      } else if (pp == 0) {
        pt++;
      } else {
        pp = skip[pp];
      }

      if (pp == patLen) {
        buffer.add(pt - pp + 1);
        // 이와 같이 pp = 0;으로 할 경우에 일치하고나서 순차 탐색과 같이 돌아가므로 KMP방식과는 맞지 않음. 해당 방식 시간 초과 나옴
        // pt = pt - pp + 1;
        // pp = 0;
        pp = skip[pp];
      }
    }

    bw.write(buffer.size() + "\n");
    while (!buffer.isEmpty()) {
      bw.write(buffer.poll() + " ");
    }

    bw.flush();
    bw.close();
  }
  private static int[] initSkip(String pat, int patLen) {
    int[] skip = new int[patLen + 1];

    int pp = 0;
    int pt = 1;

    while (pt < patLen) {
      if (pat.charAt(pt) == pat.charAt(pp)) {
        skip[++pt] = ++pp;
      } else if (pp == 0) {
        skip[++pt] = pp;
      } else {
        pp = skip[pp];
      }
    }

    return skip;
  }
}
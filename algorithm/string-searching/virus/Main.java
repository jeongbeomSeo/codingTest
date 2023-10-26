import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static boolean KMP(ArrayList<Integer> txt, int[] pat, int reverse) {
    int txtLen = txt.size();
    int patLen = pat.length;  // patLen == K

    int[] skip = new int[patLen + 1];
    int pt = 1; int pp = 0;
    while (pt < patLen) {
      if(pat[pt] == pat[pp])
        skip[++pt] = ++pp;
      else if(pp == 0)
        skip[++pt] = pp;
      else
        pp = skip[pp];
    }

    pt = pp = 0;
    while (pt < txtLen & pp < patLen) {
      if(txt.get(pt) == pat[pp]) {
        ++pt;
        ++pp;
      }
      else if(pp == 0)
        ++pt;
      else
        pp = skip[pp];
    }

    if (pp == patLen) return true;
    else if(reverse == 0){
      int[] reversePat = new int[patLen];
      for(int i = 0; i < patLen; i++) {
        reversePat[i] = pat[patLen - 1 - i];
      }
      return KMP(txt, reversePat, 1);
    }
    return false;
  }

  static int[] listToArray(ArrayList<Integer> program, int start, int K) {
    int[] pat = new int[K];
    for(int i = 0; i < K; i++) {
      pat[i] = program.get(i + start);
    }
    return pat;
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    ArrayList<Integer>[] programs = new ArrayList[N];

    for(int i = 0; i < N; i++) {
      programs[i] = new ArrayList<>();
      int size = Integer.parseInt(br.readLine());
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < size; j++) {
        programs[i].add(Integer.parseInt(st.nextToken()));
      }
    }

    boolean success = false;
    for(int pt = 0; pt <= programs[0].size() - K; pt++) {
      int[] pat = listToArray(programs[0], pt, K);

      int i;
      for(i = 1; i < N; i++) {
        if(!KMP(programs[i], pat, 0)) break;
      }

      if(i == N) {
        success = true;
        break;
      }
    }
    if (success) System.out.println("YES");
    else System.out.println("NO");
  }
}
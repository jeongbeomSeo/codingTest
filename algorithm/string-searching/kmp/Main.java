import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

  static int[] skip;
  static int matchCount;
  static ArrayList<Integer> matchIdx;

  static void kmp(String txt, String pat) {
    int pt = 1;
    int pp = 0;

    skip[pt] = 0;
    // pt와 pp는 커서를 의미하는 것을 기억하자
    while (pt != pat.length()) {
      if(pat.charAt(pt) == pat.charAt(pp))
        skip[++pt] = ++pp;
      else if(pp == 0)
        skip[++pt] = pp;
      else
        pp = skip[pp];
    }

    pt = pp = 0;
    while (pt != txt.length()) {
      // 숫자가 일치한 경우 커서를 옮겨주는 방식
      if(txt.charAt(pt) == pat.charAt(pp)) {
        pt++;
        pp++;
        // 전부 일치할 경우(해당 코드가 이 안에 위치해야만 다음 반복으로 넘어가기 전에 처리를 해줄 수 있다)
        if(pp == pat.length()) {
          // 마지막 숫자까지 일치한 경우 pt 커서 또한 움직여줘야 skip 배열에 맞게 작용하는 것에 주의
          matchCount++;
          matchIdx.add(pt - pp + 1);
          pp = skip[pp];
        }
      } else if(pp == 0)
        pt++;
      else
        pp = skip[pp];
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // 공백도 문자열에 포함되어 있기 때문에 StringTokenizer 사용 X
    String txt = br.readLine();
    String pat = br.readLine();

    skip = new int[pat.length() + 1];
    matchCount = 0;
    matchIdx = new ArrayList<>();

    kmp(txt, pat);

    System.out.println(matchCount);
    for(int i = 0; i < matchCount; i++) {
      if(i != matchCount - 1)
        System.out.print(matchIdx.get(i) + " ");
      else
        System.out.print(matchIdx.get(i));
    }
  }
}
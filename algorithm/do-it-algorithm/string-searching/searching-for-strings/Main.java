import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
  static int charToInt(char c) {
    return c - 'a';
  }
  static int[] keyword(String pat) {
    // 'a' ~ 'z' => 26개
    int[] keyword = new int[26];
    for(int i = 0; i < pat.length(); i++) {
      int idx = charToInt(pat.charAt(i));
      keyword[idx]++;
    }
    return keyword;
  }

  static boolean checkKeyword(String txt, int pt, int patLen, int[] keyword) {
    for(int i = 0; i < patLen; i++) {
      int idx = charToInt(txt.charAt(pt + i));
      if(keyword[idx] > 0) keyword[idx]--;
      else return false;
    }
    return true;
  }

  static int matchCount(String txt, String pat) {
    int txtHash, patHash;
    txtHash = patHash = 0;
    int pt = 0;

    int txtLen = txt.length();
    int patLen = pat.length();

    int[] keyword = keyword(pat);

    Set<String> set = new HashSet<>();

    while (pt <= txtLen - patLen) {
      if(pt == 0) {
        for(int i = 0; i < patLen; i++) {
          txtHash += txt.charAt(i);
          patHash += pat.charAt(i);
        }
      } else {
        txtHash = txtHash - txt.charAt(pt - 1) + txt.charAt(pt + patLen - 1);
      }
      if(txtHash == patHash) {
        int[] word = keyword.clone();
        if(checkKeyword(txt, pt, patLen, word)) {
          String str = txt.substring(pt, pt + patLen);
          set.add(str);
        }
      }
      pt++;
    }
    return set.size();
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String N = br.readLine();
    String H = br.readLine();

    System.out.println(matchCount(H, N));
  }
}
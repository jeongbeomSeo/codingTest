import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  static boolean rkMatch(String txt, int size) {
    int txtLen = txt.length();

    long[] hashTable = new long[txtLen - size + 1];

    for(int i = 0; i <= txtLen - size; i++) {
      long power = 2;
      long hash = 0;
      for(int j = i; j < size; j++) {
        hash += txt.charAt(j) * power;
        power *= 2;
      }
      hashTable[i] = hash;
    }

    for(int i = 0; i < hashTable.length - 1; i++) {
      for(int j = i + 1; j < hashTable.length; j++) {
        if(hashTable[i] == hashTable[j]) {
          int pt = i; int pp = j;
          while (pt < i + size && txt.charAt(pt) == txt.charAt(pp)) {
            pt++;
            pp++;
          }
          if(pt == i + size) return true;
        }
      }
    }
    return false;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int L = Integer.parseInt(br.readLine());
    String txt = br.readLine();

    int size = L - 1;
    while (size > 1) {
      if(rkMatch(txt, size)) {
        System.out.println(size);
        break;
      }
      size--;
    }
    if(size == 1) System.out.println(0);
  }
}

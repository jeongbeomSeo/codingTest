import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;


public class Main {
  static Comparator<String> str = new Comparator<String>() {
    @Override
    public int compare(String o1, String o2) {
      if(o1.length() - o2.length() != 0) return o1.length() - o2.length();
      else
        for(int i = 0; i < o1.length(); i++) {
          int alphabet = o1.charAt(i) - o2.charAt(i);
          if(alphabet != 0) return alphabet;
        }
        return 0;
    }
  };
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    String[] words = new String[N];
    for(int i = 0; i < N; i++) words[i] = br.readLine();

    Arrays.sort(words, str);

    for(int i = 0; i < N - 1; i++) {
      if(words[i].equals(words[i + 1])){
        int j;
        for(j = i + 1; j < N - 1; j++)
          words[j] = words[j + 1];
        words[j] = words[i];
        N--;
        i--;
      }

    }

    for(int i = 0; i < N; i++) {
      if(words[i].length() == 0) break;
      System.out.println(words[i] + " ");
    }

  }
}

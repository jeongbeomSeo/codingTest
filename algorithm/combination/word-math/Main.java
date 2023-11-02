import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    String[] words = new String[N];
    for (int i = 0; i < N; i++) {
      words[i] = br.readLine();
    }

    List<Word> list = initList(words, N);

    Collections.sort(list);

    char[] nums = fixNum(list);

    System.out.println(getResult(words, nums));
  }
  private static int getResult(String[] words, char[] nums) {

    int sum = 0;
    for (String word : words) {
        sum += convertWordToInteger(word, nums);
    }

    return sum;
  }
  private static int convertWordToInteger(String word, char[] nums) {

    int size = word.length();
    char[] charNumList = new char[size];

    for (int i = 0; i < size; i++) {
      char c = word.charAt(i);
      charNumList[i] = nums[c - 'A'];
    }

    return Integer.parseInt(String.valueOf(charNumList));
  }
  private static char[] fixNum(List<Word> list) {
    char[] nums = new char['Z' - 'A' + 1];

    char num = '9';
    for (Word word : list) {
      nums[word.c - 'A'] = num--;
    }

    return nums;
  }
  private static List<Word> initList(String[] words, int N) {

    List<Word> list = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      String str = words[i];

      int length = str.length();
      for (int j = 0; j < length; j++) {
        char c = str.charAt(j);

        int k;
        for (k = 0; k < list.size(); k++) {
          Word word = list.get(k);
          if (word.c == c) {
            word.value += calcValue(j, length);
            break;
          }
        }

        if (k == list.size()) {
          Word word = new Word(c, calcValue(j, length));
          list.add(word);
        }
      }
    }
    return list;
  }
  private static int calcValue(int idx, int length) {
    return (int)Math.pow(10, (length - 1) - idx);
  }
}
class Word implements Comparable<Word>{
  char c;
  int value;

  Word(char c, int value) {
    this.c = c;
    this.value = value;
  }

  @Override
  public int compareTo(Word o) {
    return o.value - this.value;
  }
}
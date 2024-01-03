import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  private static int[] buffer;
  private static long count;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    buffer = new int[N];
    int[] array = new int[N];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      array[i] = Integer.parseInt(st.nextToken());
    }

    System.out.println(query(array, N));
  }
  private static long query(int[] array, int N) {

    count = 0;
    mergeSort(array, 0, N - 1);

/*    for (int i : array) {
      System.out.print(i + " ");
    }*/
    return count;
  }
  private static void mergeSort(int[] array, int left, int right) {

    if (left < right) {
      int ptr0 = left;
      int mid = (left + right) / 2;
      int bufferSize = 0;

      mergeSort(array, left, mid);
      mergeSort(array, mid + 1, right);

      while (ptr0 <= mid) {
        buffer[bufferSize++] = array[ptr0++];
      }

      int ptr1 = 0;
      int ptr2 = left;
      while (ptr0 <= right && ptr1 < bufferSize) {
        if (buffer[ptr1] <= array[ptr0]) {
          array[ptr2] = buffer[ptr1];
          if (ptr2 - left > ptr1) {
            count += ((ptr2 - left) - ptr1);
          }
          ptr1++;
        } else {
          array[ptr2] = array[ptr0];
          if (ptr2 > ptr0) {
            count += (ptr2 - ptr0);
          }
          ptr0++;
        }
        ptr2++;
      }

      while (ptr1 < bufferSize) {
        array[ptr2] = buffer[ptr1];
        if (ptr2 - left > ptr1) {
          count += ((ptr2 - left) - ptr1);
        }
        ptr2++;
        ptr1++;
      }
    }
  }
}
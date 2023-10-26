import java.io.*;

public class Main {
  static int[] buff;
  static void mergeSort(int[] nums){
    int n = nums.length;
    buff = new int[n];

    __mergeSort(nums, 0, n - 1);

    buff = null;

  }
  static void __mergeSort(int[] nums, int left,int right) {
    if(left < right) {
      int i;
      int p = 0; int j = 0;
      int center = (left + right) / 2;
      int k = left;

      __mergeSort(nums, left, center);
      __mergeSort(nums, center + 1, right);

      for(i = left; i <= center; i++)
        buff[p++] = nums[i];

      while (i <= right && j < p)
        nums[k++] = (buff[j] > nums[i]) ? nums[i++] : buff[j++];

      while (j < p)
        nums[k++] = buff[j++];
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N = Integer.parseInt(br.readLine());

    int[] nums = new int[N];
    for(int i = 0; i < N; i++)
      nums[i] = Integer.parseInt(br.readLine());

    mergeSort(nums);

    for(int i = 0; i < N; i++)
      bw.write(nums[i] + "\n");
    bw.flush();
    bw.close();
  }
}

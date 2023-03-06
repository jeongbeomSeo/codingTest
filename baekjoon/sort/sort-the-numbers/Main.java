import java.io.*;

public class Main {
  static void swap(int[] nums, int idx1, int idx2) {
    int temp = nums[idx1]; nums[idx1] = nums[idx2]; nums[idx2] = temp;
  }

  static void bubbleSort(int[] nums) {
    int n = nums.length;

    for (int i = 0 ; i < n - 1; i++ ) {
      for(int j = n - 1; j > i; j--) {
        if(nums[j] < nums[j - 1])
          swap(nums, j, j - 1);
      }
    }
  }

  static void downHeap(int[] nums, int left, int right) {
    int temp = nums[left];
    int parent;
    int child;

    for(parent = left; parent < (right + 1) / 2; parent = child) {
      int cl = parent * 2 + 1;
      int cr = cl + 1;
      child = (cr <= right && nums[cr] > nums[cl]) ? cr : cl;
      if(nums[child] <= temp) break;
      nums[parent] = nums[child];
    }
    nums[parent] = temp;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n = Integer.parseInt(br.readLine());

    int[] nums = new int[n];
    for (int i = 0; i < n; i++) {
      nums[i] = Integer.parseInt(br.readLine());
    }

    bubbleSort(nums);

    for (int i = 0; i < nums.length; i++) {
      bw.write(nums[i] + "\n");
    }
    bw.flush();
    bw.close();
  }
}

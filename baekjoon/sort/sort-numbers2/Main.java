import java.io.*;

public class Main {
  static void swap(int[] nums, int idx1, int idx2) {
    int temp = nums[idx1]; nums[idx1] = nums[idx2]; nums[idx2] = temp;
  }
  static void heapSort(int[] nums) {
    int n = nums.length;

    for(int i = (n - 1) / 2; i >= 0; i--) {
      downHeap(nums, i, n - 1 );
    }

    for(int i = n - 1; i > 0; i--) {
      swap(nums, 0, i);
      downHeap(nums, 0, i - 1);
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
      if(temp >= nums[child]) break;
      nums[parent] = nums[child];
    }
    nums[parent] = temp;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N = Integer.parseInt(br.readLine());

    int[] nums = new int[N];

    for(int i = 0; i < N; i++)
      nums[i] = Integer.parseInt(br.readLine());

    heapSort(nums);

    for(int i = 0; i < N; i++)
      bw.write(nums[i] + "\n");
    bw.flush();
    bw.close();
  }
}

import java.util.ArrayList;

class Solution {
  public String[] solution(int n, int[] arr1, int[] arr2) {
    String[] answer = new String[arr1.length];


    for(int i = 0; i < n; i++) {
      int[] binary = new int[n];
      int map1Line = arr1[i];
      int map2Line = arr2[i];

      ArrayList<Integer> map1List = new ArrayList<>();
      ArrayList<Integer> map2List = new ArrayList<>();

      makeBinaryNumber(map1List, map2List, map1Line, map2Line);

      int z = 0;
      for(int j = binary.length - 1;  j >= binary.length - map1List.size() ; j--) {
        binary[j] = map1List.get(z++);
      }
      z = 0;
      for(int j = binary.length - 1; j >= binary.length - map2List.size(); j--) {
        binary[j] = binary[j] == 1 ? 1 : (map2List.get(z) == 1 ? 1 : 0);
        z++;
      }

      String line = "";
      for(int j = 0; j < n; j++) {
      line += binary[j] == 1 ? "#" : " ";
      }

      answer[i] = line;

    }

    return answer;
  }

  void makeBinaryNumber(ArrayList map1List, ArrayList map2List,int num1, int num2) {
    while (num1 != 0) {
      int remain = num1 % 2 == 1 ? 1 : 0;
      num1 /= 2;
      map1List.add(remain);
    }
    while (num2 != 0) {
      int remain = num2 % 2 == 1 ? 1 : 0;
      num2 /= 2;
      map2List.add(remain);
    }
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    int n = 5;
    int[] arr1 = {9, 20, 28, 18, 11};
    int[] arr2 = {30, 1, 21, 17, 28};
    System.out.println(solution.solution(n , arr1, arr2));
  }
}
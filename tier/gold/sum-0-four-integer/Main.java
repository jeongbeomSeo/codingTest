import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] sumAarray = new int[n * n];
        int[] sumBarray = new int[n * n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sumA = arr[i][0] + arr[j][1];
                int sumB = arr[i][2] + arr[j][3];

                sumAarray[n * i + j] = sumA;
                sumBarray[n * i + j] = sumB;
            }
        }

        Arrays.sort(sumAarray);
        Arrays.sort(sumBarray);

        long result = 0L;
        int aPtr = 0;
        int bPtr = n * n - 1;

        while (aPtr < n * n && bPtr >= 0) {
            int sumA = sumAarray[aPtr];
            int sumB = sumBarray[bPtr];

            int res = sumA + sumB;

            if (res < 0) {
                aPtr++;
            } else if (res > 0) {
                bPtr--;
            } else {
/*                int nxtAPtr = aPtr + 1;
                while (nxtAPtr < n * n && sumAarray[nxtAPtr] == sumA) nxtAPtr++;
                int nxtBPtr = bPtr - 1;
                while (nxtBPtr >= 0 && sumBarray[nxtBPtr] == sumB) nxtBPtr--;*/
                int nxtAPtr = upper_bound(sumAarray, aPtr + 1, n * n, sumA);
                int nxtBPtr = lower_bound(sumBarray, 0, bPtr, sumB) - 1;

                result += (long)(nxtAPtr - aPtr) * (long)(bPtr - nxtBPtr);
                aPtr = nxtAPtr;
                bPtr = nxtBPtr;
            }
        }

        System.out.println(result);

        /*
        List<Integer> sumAList = new ArrayList<>();
        List<Integer> sumBList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sumA = arr[i][0] + arr[j][1];
                int sumB = arr[i][2] + arr[j][3];

                sumAList.add(sumA);
                sumBList.add(sumB);
            }
        }

        Collections.sort(sumAList);
        Collections.sort(sumBList);*/

        /*
        long result = 0L;
        int aPtr = 0;
        int bPtr = sumBList.size() - 1;

        while (aPtr < sumAList.size() && bPtr >= 0) {
            int sumA = sumAList.get(aPtr);
            int sumB = sumBList.get(bPtr);

            int res = sumA + sumB;

            if (res < 0) {
                aPtr++;
            } else if (res > 0) {
                bPtr--;
            } else {
                TLE
                int nxtAPtr = aPtr + 1;
                while (nxtAPtr < sumAList.size() && sumAList.get(nxtAPtr) == sumA) nxtAPtr++;
                int nxtBPtr = bPtr - 1;
                while (nxtBPtr >= 0 && sumBList.get(nxtBPtr) == sumB) nxtBPtr--;

                TLE
                int nxtAPtr = upper_bound(sumAList, aPtr + 1, sumAList.size(), sumA);
                int nxtBPtr = lower_bound(sumBList, 0, bPtr, sumB);


                result += (nxtAPtr - aPtr) * (bPtr + 1 - nxtBPtr);

                aPtr = nxtAPtr;
                bPtr = nxtBPtr;
            }
        }
        System.out.println(result);
        */
        /*
        TLE
        for (int e : sumAList) {
            int target = -e;


            int lowerIdx = lower_bound(sumBList, 0, sumBList.size(), target);
            if (sumBList.get(lowerIdx) != target) continue;

            int upperIdx = upper_bound(sumBList, 0, sumBList.size(), target);

            result += (upperIdx - lowerIdx);

            System.out.println(result);
        }*/


        /*
        TLE
        Map<Integer, Integer> countMapA = new HashMap<>();
        Map<Integer, Integer> countMapB = new HashMap<>();

        for (int i = 0; i < n; i++) {
            Integer sumA = 0;
            Integer sumB = 0;
            for (int j = 0; j < n; j++) {
                sumA = arr[i][0] + arr[j][1];
                sumB = arr[i][2] + arr[j][3];
                countMapA.put(sumA, countMapA.getOrDefault(sumA, 0) + 1);
                countMapB.put(sumB, countMapB.getOrDefault(sumB, 0) + 1);
            }
        }

        List<Integer> countMapAKeys = new ArrayList<>(countMapA.keySet());

        Integer result = 0;
        for (Integer key : countMapAKeys) {
            Integer target = -key;

            if (countMapB.containsKey(target)) {
                result += countMapA.get(key) * countMapB.get(target);
            }
        }

        System.out.println(result);
         */
    }
    private static int lower_bound(int[] arr, int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
    private static int upper_bound(int[] arr, int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
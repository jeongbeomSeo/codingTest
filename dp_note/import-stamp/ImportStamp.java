import java.util.Scanner;

public class ImportStamp {
    static int table[][][] = new int[9][9][8];
    static int mx[][] = new int[8][8];
    static int MAXMONEY = 32;
    static int MAX = 80;

    static void dfs(int now, int num[], int n, int m) {
        int i, j;
        int d[] = new int[MAX];
        for (i = 0; i < MAX; i++)
            d[i] = -1;
        d[0] = 0;

        for (i = 0; i < now; i++) {
            for (j = 0; j < MAX; j++) {
                if (d[j] != -1 && j + num[i] < MAX && (d[j + num[i]] > d[j] + 1 || d[j + num[i]] == -1)
                        && d[j] + 1 <= m)
                    d[j + num[i]] = d[j] + 1;
            }
        }

        for (i = 0; i < MAX; i++) {
            if (d[i] == -1) {
                if (i - 1 < num[now - 1])
                    return;
                break;
            }
        }

        if (now == n) {
            if (i - 1 > mx[m][n]) {
                mx[m][now] = i - 1;
                System.arraycopy(num, 0, table[m][now], 0, 8);
            }
            return;
        }

        for (i = num[now - 1] + 1; i <= MAXMONEY; i++) {
            num[now] = i;
            dfs(now + 1, num, n, m);
            num[now] = 0;
        }
    }

    public static void main(String[] args) {
        int tmp[] = new int[8];
        int i, j;
        int h, k;
        Scanner sc = new Scanner(System.in);
        for (i = 0; i < 8; i++)
            tmp[i] = 0;
        tmp[0] = 1;
        for (i = 1; i <= 8; i++) {
            for (j = 1; j <= 9 - i; j++)
                dfs(1, tmp, i, j);
        }

        while (sc.hasNextInt()) {
            h = sc.nextInt();
            k = sc.nextInt();
            if (h == 0 && k == 0)
                break;
            for (i = 0; i < k; i++)
                System.out.printf("%3d", table[h][k][i]);
            System.out.printf("->%3d\n", mx[h][k]);
        }
    }
}

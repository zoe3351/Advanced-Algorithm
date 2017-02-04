import java.util.Scanner;

public class stableMatching {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int[][] boy = new int[n][n];
		int[][] girl = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				boy[i][j] = scan.nextInt() - 1;
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int a = scan.nextInt() - 1;
				girl[i][a] = j;
			}
		}
		scan.close();
		int[] ans = match(n, girl, boy);
		output(ans);
	}

	public static void output(int[] ans) {
		for (int i = 0; i < ans.length; i++) {
			int a = ans[i] + 1;
			System.out.print(a + " ");
		}
	}

	public static int[] match(int n, int[][] girl, int[][] boy) {
		int[] boymatchto = new int[n];
		int[] girlmatchto = new int[n];

		for (int j = 0; j < n; j++) {
			boymatchto[j] = -1;
			girlmatchto[j] = -1;
		}

		int matched = 0;

		while (matched < n) {
			int i = 0;
			for (int j = 0; j < n; j++) {
				if (boymatchto[j] == -1) {
					i = j;
					break;
				}
			}

			for (int order = 0; order < n; order++) {
				if (girlmatchto[boy[i][order]] == -1) {
					girlmatchto[boy[i][order]] = i;
					boymatchto[i] = boy[i][order];
					matched++;
					break;
				} else if (prefernew(girl, boy[i][order], girlmatchto[boy[i][order]], i)) {
					boymatchto[girlmatchto[boy[i][order]]] = -1;
					girlmatchto[boy[i][order]] = i;
					boymatchto[i] = boy[i][order];
					break;
				}
			}
		}
		return boymatchto;
	}

	public static boolean prefernew(int[][] girl, int girlindex, int boyold, int boynew) {
		int oldpre = girl[girlindex][boyold];
		int newpre = girl[girlindex][boynew];
		if(newpre < oldpre) return true;
		return false;
	}

}

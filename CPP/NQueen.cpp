#include <cstdio>
#include <cstdlib>

bool isPossible(int col, int* columnBoard) {
	bool isPossble = true;
	int k = 1;
	while (k < col && isPossble) {
		if (columnBoard[col] == columnBoard[k] || abs(columnBoard[col] - columnBoard[k]) == abs(col - k)) isPossble = false;
		k++;
	}
	return isPossble;
}

int getNQueenCases(int col, int n, int* columnBoard) {
	int ret = 0;
	if (n == 1) return 1;
	if (isPossible(col, columnBoard)) {
		if (col == n) return 1;
		else {
			for (int row = 1; row <= n; row++) {
				columnBoard[col + 1] = row;
				ret += getNQueenCases(col + 1, n, columnBoard);
			}
		}
	}
	return ret;
}
int main() {
	int cases; scanf("%d", &cases);

	while (cases--) {
		int n; scanf("%d", &n);
		int* columnBoard = new int[n + 1];
		int casesOfNQueen = getNQueenCases(0, n, columnBoard);
		printf("%d\n", casesOfNQueen);
	}
}

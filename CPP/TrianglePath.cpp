#include <cstdio>
#include <cstring>

const int MAX_BOARD_SIZE = 100;
int size;
int board[MAX_BOARD_SIZE][MAX_BOARD_SIZE];
int cache[MAX_BOARD_SIZE][MAX_BOARD_SIZE];

int max(int a, int b) {
	return a > b ? a : b;
}

int getBestPath(int x, int y) {
	if (board[x][y] == -1) return 0;
	int &ret = cache[x][y];
	if (ret != -1) return ret;
	if (x == size) return board[x][y];
	ret = board[x][y] + max(getBestPath(x + 1, y), getBestPath(x + 1, y + 1));
	return ret;
}


int main() {
	int cases; scanf("%d", &cases);

	while (cases--) {
		scanf("%d", &size);
		memset(board, -1, sizeof(board));
		memset(cache, -1, sizeof(cache));
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < i + 1; j++) scanf("%d", &board[i][j]);
		}
		printf("%d\n", getBestPath(0, 0));
	}
}



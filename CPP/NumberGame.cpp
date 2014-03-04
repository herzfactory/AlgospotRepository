#include <cstdio>

const int MAX_BOARD_SIZE = 50;
const int EMPTY_VALUE = -99999;
int cache[MAX_BOARD_SIZE][MAX_BOARD_SIZE];

int max(int a, int b) {return a > b ? a : b;}

int playGame(int* board, int left, int right) {
	if (left > right) return 0;
	int& ret = cache[left][right];
	if (ret != EMPTY_VALUE) return ret;
	ret = max(board[left] - playGame(board, left+1, right), board[right] - playGame(board, left, right - 1));
	if (right - left + 1 >= 2) {
		ret = max(ret, -playGame(board, left + 2, right));
		ret = max(ret, -playGame(board, left, right - 2));
	}
	return ret;
}


int main() {
	int cases; scanf("%d", &cases);

	while (cases--) {
		for (int i = 0; i < MAX_BOARD_SIZE; i++) {
			for (int j = 0; j < MAX_BOARD_SIZE; j++) cache[i][j] = EMPTY_VALUE;
		}
		int length; scanf("%d", &length);
		int* board = new int[length];
		for (int i = 0; i < length; i++) scanf("%d", &board[i]);
		int difference = playGame(board, 0, length - 1);
		printf("%d\n", difference);
	}
}

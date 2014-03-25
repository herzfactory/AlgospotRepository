#include <cstdio>
#include <cstring>

const int BOARD_SIZE = 100;
int cache[BOARD_SIZE][BOARD_SIZE];
int board[BOARD_SIZE][BOARD_SIZE];
int size;

int jump(int height, int width) {
	if (height >= size || width >= size) return 0;
	int& ret = cache[height][width];
	if (ret != -1) return ret;
	if (height == size -1 && width == size -1) return 1;
	ret = jump(height + board[height][width], width);
	ret += jump(height, width + board[height][width]);
	return ret;
}

int main() {
	int cases; scanf("%d", &cases);

	while (cases--) {
		memset(cache, -1, sizeof(cache));
		scanf("%d", &size);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) scanf("%d", &board[i][j]);
		}
		if (jump(0, 0) != 0) fputs("YES\n", stdout);
		else fputs("NO\n", stdout);
	}
}




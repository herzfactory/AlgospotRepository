#include <cstdio>
#include <cstring>

const int coverType[4][3][2] = {{{0, 0}, {1, 0}, {0, 1}} ,
		{{0, 0}, {0, 1}, {1, 1}},
		{{0, 0},{1, 0},{1, 1}},
		{{0, 0}, {1, 0}, {1, -1}}};

const int TYPE_SIZE = 4;
const int BLOCK_SIZE = 3;
const int MODE_SET = 1;
const int MODE_REMOVE = 0;

int height, width;

void __fpurge() {
	while (getchar() != '\n');
}

bool setBoard(int *board[], int curHeight, int curWidth, int type, int mode) {
	bool successChange = true;
	if (mode == 1) {
		for (int block = 0; block < BLOCK_SIZE; block++) {
			int nextHeight = curHeight + coverType[type][block][0];
			int nextWidth = curWidth + coverType[type][block][1];
			if (nextHeight < 0 || nextHeight >= height || nextWidth < 0 || nextWidth >= width || board[nextHeight][nextWidth] == 0) return !successChange;
		}
	}
	for (int block = 0; block < BLOCK_SIZE; block++) {
		int nextHeight = curHeight + coverType[type][block][0];
		int nextWidth = curWidth + coverType[type][block][1];
		if (mode == 1) board[nextHeight][nextWidth] = 0;
		else board[nextHeight][nextWidth] = 1;
	}


	return successChange;
}

int coverBoard(int** board, int curHeight) {
	int ret = 0, curWidth = 0;
	bool hasEmpty = false;
	for (; curHeight < height; curHeight++) {
		for (curWidth = 0; curWidth < width; curWidth++) {
			if (board[curHeight][curWidth] == 1) {hasEmpty = true; break;}
		}
		if (hasEmpty) break;
	}
	if (curHeight == height) return 1;
	for (int type = 0; type < TYPE_SIZE; type++) {
		if (setBoard(board, curHeight, curWidth, type, MODE_SET)) {
			ret += coverBoard(board, curHeight);
			setBoard(board, curHeight, curWidth, type, MODE_REMOVE);
		}
	}

	return ret;
}


int main() {
	int cases;
	scanf("%d", &cases);
	__fpurge();
	while (cases--) {
		int countWhiteBlock = 0, ** board;
		scanf("%d %d", &height, &width); __fpurge();
		board = new int*[height];
		for (int i = 0; i < height; i++) board[i] = new int[width];
		for (int i = 0; i < height; i++) {
			char buf[1024]; fgets(buf, 1024, stdin);
			for (int j = 0; j < width; j++) {
				if (buf[j] == '#') board[i][j] = 0;
				else { board[i][j] = 1; countWhiteBlock++;}
			}
		}
		if (countWhiteBlock % 3 == 0) {printf("%d\n", coverBoard(board, 0));}
		else printf("0\n");
	}
}



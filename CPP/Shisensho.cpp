#include <cstdio>
#include <cstring>

const int direction[4][2] = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
const int DIRECTION_SIZE = 4;
const int VALID_PATH_SEGMENTS = 3;

int height, width;

void __fpurge() {
	while (getchar() != '\n');
}

int searchingValidPath(char* board[], char tile, int curHeight, int curWidth, int segments, int dType) {
	int ret = 0;
	if (curHeight < 0 || curHeight == height || curWidth < 0 || curWidth == width) return 0;
	if (segments > VALID_PATH_SEGMENTS) return 0;
	if (board[curHeight][curWidth] == tile) {
		board[curHeight][curWidth] = '*';
		return 1;
	}
	else if (board[curHeight][curWidth] == '.') {
		board[curHeight][curWidth] = '*';
		for (int directionType = 0; directionType < DIRECTION_SIZE; directionType++) {
			int nextHeight = curHeight + direction[directionType][0];
			int nextWidth = curWidth + direction[directionType][1];
			int nextSegments = segments;
			if (dType != directionType) nextSegments++;
			ret += searchingValidPath(board, tile, nextHeight, nextWidth, nextSegments, directionType);
		}
		board[curHeight][curWidth] = '.';
	}
	return ret;
}

int get_countOfValidPath(char* board[]) {
	int ret = 0;
	for (int curHeight = 0; curHeight < height; curHeight++) {
		for (int curWidth = 0; curWidth < width; curWidth++) {
			if (board[curHeight][curWidth] != '.' && board[curHeight][curWidth] != '*') {
				char tile = board[curHeight][curWidth];
				board[curHeight][curWidth] = '*';
				char** tempBoard = new char*[height];
				for (int i = 0; i < height; i++) {
					tempBoard[i] = new char[width];
					strcpy(tempBoard[i], board[i]);
				}
				for (int directionType = 0; directionType < DIRECTION_SIZE; directionType++) {
					int nextHeight = curHeight + direction[directionType][0];
					int nextWidth = curWidth + direction[directionType][1];
					ret += searchingValidPath(tempBoard, tile, nextHeight, nextWidth, 1, directionType);
				}
			}
		}
	}
	return ret;
}

int main() {
	int cases;
	scanf("%d", &cases);

	while (cases--) {
		scanf("%d %d", &height, &width); __fpurge();
		char** board = new char*[height];
		for (int i = 0; i < height; i++) board[i] = new char[width];
		for (int i = 0; i < height; i++) {
			char buf[1024]; fgets(buf, 1024, stdin);
			strncpy(board[i], buf, width);
		}
		int countOfValidPath = get_countOfValidPath(board);
		printf("%d\n", countOfValidPath);
	}
}

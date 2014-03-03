#include <cstdio>
#include <cstring>

const int MAX_HEIGHT = 200;
const int MAX_WIDTH = 100;
int cache[MAX_HEIGHT][MAX_WIDTH];
int width, height;

int max(int a, int b) {
	return a > b ? a : b;
}

int getBestPath(int** diamond, int i_height, int i_width) {
	if (i_height == height || i_width < 0 || i_width == width) return 0;
	int& ret = cache[i_height][i_width];
	if (ret != -1) return ret; else ret = 0;
	if (i_height < height / 2) {
		ret = diamond[i_height][i_width] + max(getBestPath(diamond, i_height + 1, i_width), getBestPath(diamond, i_height + 1, i_width + 1));
	}
	else {
		ret = diamond[i_height][i_width] + max(getBestPath(diamond, i_height + 1, i_width - 1), getBestPath(diamond, i_height + 1, i_width));
	}
	return ret;
}


int main() {
	int cases;
	scanf("%d", &cases);

	while (cases--) {
		memset(cache, -1, sizeof(cache));
		scanf("%d", &width);
		height = width * 2 - 1;
		int** diamond = new int*[height], size = 0;
		for (int i = 0; i < height; i++) diamond[i] = new int[width];
		for (int i = 0; i < height; i++) {
			if (i < height / 2 + 1) size++;
			else size--;
			for (int j = 0; j < size; j++) scanf("%d", &diamond[i][j]);
		}
		printf("%d\n", getBestPath(diamond, 0, 0));
	}
}




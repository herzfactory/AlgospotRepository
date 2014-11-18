#include <cstdio>
#include <cstring>

int numOfBoard;
int* boardHeight;

int max(int a, int b) {
	if (a > b) return a;
	return b;
}

int min(int a, int b) {
	if (a < b) return a;
	return b;
}

int cropFence(int left, int right) {
	if (left == right) return boardHeight[left];
	int middle = (left + right) / 2;
	int leftCropFence = cropFence(left, middle);
	int rightCropFence = cropFence(middle + 1, right);
	int ret = max(leftCropFence, rightCropFence);
	int n_left = middle, n_right = middle + 1;
	int height = min(boardHeight[n_left], boardHeight[n_right]);
	ret = max(ret, height * 2);

	while (n_left > left || n_right < right) {
		if (n_right < right && (n_left == left || boardHeight[n_left - 1] < boardHeight[n_right + 1])) {
			n_right++;
			height = min(height, boardHeight[n_right]);
		}
		else {
			n_left--;
			height = min(height, boardHeight[n_left]);
		}
		ret = max(ret, (n_right - n_left + 1) * height);
	}
	return ret;
}

int main() {
	int cases; scanf("%d", &cases);

	while (cases--) {
		scanf("%d", &numOfBoard);
		boardHeight = new int[numOfBoard];
		for (int i = 0; i < numOfBoard; i++) scanf("%d", &boardHeight[i]);
		printf("%d\n", cropFence(0, numOfBoard - 1));
	}
}

#include <cstdio>

const int SIZE = 3;

int searchingPoint(int arr[SIZE]) {
	int ret;
	if (arr[0] == arr[1]) ret = arr[2];
	else if (arr[0] == arr[2]) ret = arr[1];
	else if (arr[1] == arr[2]) ret = arr[0];
	return ret;
}

int main() {
	int cases;
	scanf("%d", &cases);
	while (cases--) {
		int x[SIZE], y[SIZE];
		for (int i = 0; i < SIZE; i++) scanf("%d %d", &x[i], &y[i]);
		printf("%d %d\n", searchingPoint(x), searchingPoint(y));
	}
}

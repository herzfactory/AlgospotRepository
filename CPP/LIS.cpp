#include <cstdio>
#include <cstring>

const int MAX_ELEMENT_SIZE = 500;
int size;
int cache[MAX_ELEMENT_SIZE];

int max(int a, int b) {
	return a > b ? a : b;
}

int getLongestIncreasingSequence(int* sequence, int index) {
	int& ret = cache[index];
 	if (ret != -1) return ret; else ret = 1;
	for (int i = index + 1; i < size; i++) {
		if (sequence[index] < sequence[i]) ret = max(ret, getLongestIncreasingSequence(sequence, i) + 1);
	}
	return ret;
}

int main() {
	int cases; scanf("%d", &cases);
	while (cases--) {
		memset(cache, -1, sizeof(cache));
		scanf("%d", &size);
		int* sequence = new int[size];
		for (int i = 0; i < size; i++) scanf("%d", &sequence[i]);
		int longest = 0;
		for (int i = 0; i < size; i++) longest = max(longest, getLongestIncreasingSequence(sequence, i));
		printf("%d\n", longest);
	}
}




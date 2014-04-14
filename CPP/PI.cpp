#include <cstdio>
#include <cstring>
#include <string>
#include <cstdlib>
using namespace std;

const int MAX_SIZE = 100002;
int cache[MAX_SIZE];
string sequence;

void __fpurge() {
	while (getchar() != '\n');
}

int min(int a, int b) {
	return a < b ? a : b;
}

int levelOfDifficulty(int left, int right) {
	bool isSameNumber = true, isProgress = true, isAlterate = true;
	for (int i = left + 1; i < right; i++) {
		if (sequence[left] != sequence[i]) {isSameNumber = false; break;}
	}
	if (isSameNumber) return 1;
	int diff = sequence[left + 1] - sequence[left];
	for (int i = left + 1; i < right - 1; i++) {
		if (sequence[i + 1] - sequence[i] != diff) {isProgress = false; break;}
	}
	if (isProgress && abs(diff) == 1) return 2;
	for (int i = left + 2; i < right; i++) {
		if (sequence[i] != sequence[(i%2) + left]) {isAlterate = false; break;}
	}
	if (isAlterate) return 4;
	if (isProgress) return 5;
	return 10;
}

int memorize(int left) {
	if (left == sequence.size()) return 0;
	int& ret = cache[left];
	if (ret != -1) return ret;
	ret = 987654321;
	for (int i = 3; i <= 5; i++) {
		if (left + i <= sequence.size()) ret = min(ret, memorize(left + i) + levelOfDifficulty(left, left + i));
	}
	return ret;
}

int main() {
	int cases; scanf("%d", &cases); __fpurge();

	while (cases--) {
		memset(cache, -1, sizeof(cache));
		char input[MAX_SIZE]; fgets(input, MAX_SIZE, stdin);
		sequence = string(input, strlen(input) - 1);
		printf("%d\n", memorize(0));
	}
}

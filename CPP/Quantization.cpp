#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>
#include <cmath>
using namespace std;

int cache[100][10];
vector<int> sequence;
int size;

int minSquardError(int left, int right) {
	int ret = 0, sum = 0;
	for (int i = left; i < right; i++) sum += sequence[i];
	int average = (int) round((double) sum / (double)(right - left));
	for (int i = left; i < right; i++) ret += pow(sequence[i] - average, 2);
	return ret;
}

int quantization(int left, int remainPocket) {
	if (left == size) return 0;
	if (remainPocket == 0) return 987654321;
	int &ret = cache[left][remainPocket];
	if (ret != -1) return ret;
	ret = 987654321;
	for (int i = 1; i + left <= size; i++) {
		ret = min(ret, quantization(left + i, remainPocket - 1) + minSquardError(left, left + i));
	}
	return ret;
}

int main () {
	int cases; scanf("%d", &cases);

	while (cases--) {
		memset(cache, -1, sizeof(cache));
		int pocket; scanf("%d %d", &size, &pocket);
		for (int i = 0; i < size; i++){
			int buf; scanf("%d", &buf); sequence.push_back(buf);
		}
		sort(sequence.begin(), sequence.end());
		int ret = quantization(0, pocket);
		printf("%d\n", ret);
		sequence.clear();
	}
}



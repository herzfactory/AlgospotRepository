#include <cstdio>
#include <queue>
using namespace std;

class LCR {
	private :
		unsigned int seed;
	public :
		LCR() : seed(1983){}
		unsigned int next();
};

unsigned int LCR::next() {
	unsigned int ret = seed;
	seed = seed * 214013u + 2531011u;
	return ret % 10000 + 1;
}

int signalAnalysis(int k, int n) {
	int ret = 0, sum = 0;
	LCR rand;
	queue<int> signals;
	for (int i = 0; i < n; i++) {
		int nextSignal = rand.next();
		sum += nextSignal; signals.push(nextSignal);
		while (sum > k) {
			sum -= signals.front(); signals.pop();
		}
		if (sum == k) ret++;
	}
	return ret;
}

int main() {
	int cases; scanf("%d", &cases);

	while (cases--) {
		int k, n; scanf("%d %d", &k, &n);
		printf("%d\n", signalAnalysis(k, n));
	}
}



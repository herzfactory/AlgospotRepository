#include <cstdio>
#include <cstring>

const int MOD = 1000000007;
int cache[101];

int fibonacciSequence(int number) {
	int &ret = cache[number];
	if (ret != -1) return ret;
	ret = fibonacciSequence(number - 1) + fibonacciSequence(number - 2);
	if (ret > MOD) ret %= MOD;
	return ret;
}

int main () {
	int cases; scanf("%d", &cases);
	memset(cache, -1, sizeof(cache));
	cache[1] = 1; cache[2] = 2; cache[3] =3;
	while (cases--) {
		int number; scanf("%d", &number);
		printf("%d\n", fibonacciSequence(number));
	}
}

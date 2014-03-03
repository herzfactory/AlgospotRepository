#include <cstdio>
#include <cstring>

const int MAX_CASES = 1000000007;
int cache[5001][100];

int coinsChanges(int money, int coins[], int index, int coinSize) {
	if (money < 0) return 0; else if (money == 0) return 1;
	int ret = cache[money][index];
	if (ret != -1) return ret; else ret = 0;
	for (int i = index; i < coinSize; i++) {
		ret += coinsChanges(money - coins[i], coins, i, coinSize);
		if (ret > MAX_CASES) ret %= MAX_CASES;
	}
	cache[money][index] = ret;
	return ret;
}

int main() {
	int cases;
	scanf("%d", &cases);

	while (cases--) {
		int money, coinSize;
		scanf("%d %d", &money, &coinSize);
		int* coins = new int[coinSize];
		for (int i = 0; i < coinSize; i++) scanf("%d", &coins[i]);
		memset(cache, -1, sizeof(cache));
		int changes = coinsChanges(money, coins, 0, coinSize);
		printf("%d\n", changes);
	}
}

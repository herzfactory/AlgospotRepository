#include <cstdio>

const int MAX_PUSH = 9999, CLOCK_SIZE = 16, SWITCH_SIZE = 10, PUSH_SIZE = 4;
int linkedWithClock[][CLOCK_SIZE] = {
		{3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 0
		{0, 0, 0, 3, 0, 0, 0, 3, 0, 3, 0, 3, 0, 0, 0, 0}, // 1
		{0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 3, 0, 0, 0, 3, 3}, // 2
		{3, 0, 0, 0, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0}, // 3
		{0, 0, 0, 0, 0, 0, 3, 3, 3, 0, 3, 0, 3, 0, 0, 0}, // 4
		{3, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3}, // 5
		{0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3}, // 6
		{0, 0, 0, 0, 3, 3, 0, 3, 0, 0, 0, 0, 0, 0, 3, 3}, // 7
		{0, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 8
		{0, 0, 0, 3, 3, 3, 0, 0, 0, 3, 0, 0, 0, 3, 0, 0}, // 5
	};

bool isOnTime(int* clocks) {
	for (int i = 0; i < CLOCK_SIZE; i++) {
		if (clocks[i] != 12) return false;
	}
	return true;
}

void pushSwitch(int* clocks, int switchNumber) {
	for (int i = 0; i < CLOCK_SIZE; i++) {
		clocks[i] += linkedWithClock[switchNumber][i];
		if (clocks[i] == 15) clocks[i] = 3;
	}
}

int synchronizingClocks(int* clocks, int switchNumber) {
	if (switchNumber == SWITCH_SIZE) return isOnTime(clocks) ? 0 : MAX_PUSH;
	int ret = MAX_PUSH;
	for (int i = 0; i < PUSH_SIZE; i++) {
		int temp = i + synchronizingClocks(clocks, switchNumber + 1);
		if (temp < ret) ret = temp;
		pushSwitch(clocks, switchNumber);
	}
	return ret;
}

int main() {
	int cases; scanf("%d", &cases);

	while (cases--) {
		int clocks[CLOCK_SIZE];
		for (int i = 0; i < CLOCK_SIZE; i++) scanf("%d", &clocks[i]);
		int push = synchronizingClocks(clocks, 0);
		if (push >= MAX_PUSH) printf("-1\n");
		else printf("%d\n", push);
	}
}




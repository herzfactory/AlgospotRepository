#include <cstdio>
#include <cstring>

bool takenStudent[10];
bool areFriends[10][10];
int numOfStudent;
int numOfPair;

int countPairing() {
	int firstFree = -1, ret = 0;
	for (int i = 0; i < numOfStudent; i++) {
		if (!takenStudent[i]) {
			firstFree = i; break;
		}
	}
	if (firstFree == -1) return 1;

	for (int pairWith = firstFree + 1; pairWith < numOfStudent; pairWith++) {
		if (!takenStudent[pairWith] && areFriends[firstFree][pairWith]) {
			takenStudent[firstFree] = takenStudent[pairWith] = true;
			ret += countPairing();
			takenStudent[firstFree] = takenStudent[pairWith] = false;
		}
	}
	return ret;
}

int main() {
	int cases; scanf("%d", &cases);

	while (cases-- > 0) {
		memset(takenStudent, false, sizeof(takenStudent));
		memset(areFriends, false, sizeof(areFriends));
		scanf("%d %d", &numOfStudent, &numOfPair);
		for (int i = 0; i < numOfPair; i++) {
			int x, y; scanf("%d %d", &x, &y);
			areFriends[x][y] = areFriends[y][x] = true;
		}
		printf("%d\n", countPairing());
	}
}

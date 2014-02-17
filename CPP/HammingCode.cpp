#include <cstdio>
#include <cstdlib>

const int BIT_SIZE = 7;
const int SYNDROME_SIZE = 3;

void __fpurge() {
	while (getchar() != '\n');
}

int checkError(int* bits) {
	int *syndrome = new int[SYNDROME_SIZE];
	syndrome[0] = bits[3] ^ bits[4] ^ bits[5] ^ bits[6];
	syndrome[1] = bits[1] ^ bits[2] ^ bits[5] ^ bits[6];
	syndrome[2] = bits[0] ^ bits[2] ^ bits[4] ^ bits[6];
	return syndrome[0] * 4 + syndrome[1] * 2 + syndrome[2] * 1;
}
int main () {
	int cases;
	scanf("%d", &cases); __fpurge();

	while (cases--) {
		int *encodedMessage = new int[BIT_SIZE];
		char input[1024]; fgets(input, 1024, stdin);
		for (int i = 0; i < BIT_SIZE; i++) encodedMessage[i] = input[i] - 48;
		int syndrome = checkError(encodedMessage);
		if (syndrome != 0) encodedMessage[syndrome - 1] = (encodedMessage[syndrome - 1] + 1) % 2;
		printf("%d%d%d%d\n", encodedMessage[2], encodedMessage[4], encodedMessage[5], encodedMessage[6]);
	}

}





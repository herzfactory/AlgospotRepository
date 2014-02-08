#include <cstdio>

unsigned int convertEndians(unsigned int endian) {
	unsigned char buf[4];
	for (int i = 0; i < 4; i++) buf[i] = (endian >> i * 8) & 0xFF;
	unsigned int ret = 0;
	for (int i = 0; i < 4; i++) ret += ((unsigned int)(buf[3 - i] & 0xFF) << (8 * i));
	return ret;
}

int main() {
	int cases;
	scanf("%d", &cases);

	while (cases--) {
		unsigned int endian;
		scanf("%u", &endian);
		printf("%u\n", convertEndians(endian));
	}
}




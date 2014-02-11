#include <cstdio>
#include <cstring>
using namespace std;

void __fpurge() {
	while (getchar() != '\n');
}

int main() {
	int cases;
	scanf("%d", &cases);
	__fpurge();
	while (cases--) {
		char input[1024], words[1024];
		fgets(input, 1024, stdin);
		int size = strlen(input) - 1, index = 0;
		for (int i = 0; i < size; i+=2) words[index++] = input[i];
		for (int i = 1; i < size; i+=2) words[index++] = input[i];
		words[index] = '\0';
		fputs(words, stdout);
		fputs("\n", stdout);
	}
}

#include <cstdio>
#include <cstring>
using namespace std;

void __fpurge() {
	while (getchar() != '\n');
}

void linearSortOrderByAsce(char *words, int size) {
	for (int i = 2; i < size; i+=2) {
		int j = i;
		char key[] = {words[i], words[i+1]};
		while (j > 0 && (words[j - 2] > key[0] || (words[j - 2] == key[0] && words[j - 1] > key[1]))) {
			words[j] = words[j - 2];
			words[j+1] = words[j - 1];
			j -= 2;
		}
		words[j] = key[0]; words[j+1] = key[1];
	}
}

int main() {
	int cases;
	scanf("%d", &cases);
	__fpurge();
	while (cases--) {
		char words[1024];
		fgets(words, 1024, stdin);
		int size = strlen(words) - 1;
		linearSortOrderByAsce(words, size);
		fputs(words, stdout);
	}
}

#include <cstdio>
#include <cstring>

void __fpurge() {
	while (getchar() != '\n');
}

int main() {
	int cases, index = 0;
	scanf("%d", &cases);
	__fpurge();
	while (index++ < cases) {
		int position, size, o_Index = 0;
		char word[1024], output[1024];
		scanf("%d", &position);
		getchar(); fgets(word, 1024, stdin);
		size = strlen(word);
		for (int i = 0; i < position - 1; i++) output[o_Index++] = word[i];
		for (int i = position; i < size; i++) output[o_Index++] = word[i];
		output[o_Index] = '\0';
		printf("%d ", index); fputs(output, stdout); fputs("\n", stdout);
	}
}




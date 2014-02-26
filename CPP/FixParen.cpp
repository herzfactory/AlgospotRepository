#include <cstdio>
#include <cstring>

const int PRIORITY_SIZE = 4;

char PRIORITY[4][2];

void __fpurge() {
	while (getchar() != '\n');
}

void initPrioirt(char* priority) {
	int size = strlen(priority) - 1;
	for (int i = 0; i < size; i++) {
		if (priority[i] == '(') {PRIORITY[i][0] = priority[i]; PRIORITY[i][1] = ')';}
		else if (priority[i] == '{') {PRIORITY[i][0] = priority[i]; PRIORITY[i][1] = '}';}
		else if (priority[i] == '[') {PRIORITY[i][0] = priority[i]; PRIORITY[i][1] = ']';}
		else if (priority[i] == '<') {PRIORITY[i][0] = priority[i]; PRIORITY[i][1] = '>';}
	}
}
int getPriority(char parenthesis) {
	int value = 0;
	for (; value < PRIORITY_SIZE; value++) {
		if (PRIORITY[value][0] == parenthesis || PRIORITY[value][1] == parenthesis) break;
	}
	return value;
}

void matchingParenthesis(char *parenthesis) {
	int size = strlen(parenthesis), index = 0;
	int* sIndex = new int[size];
	for (int i = 0; i < size; i++) {
		if (parenthesis[i] == '(' || parenthesis[i] == '{' || parenthesis[i] == '[' || parenthesis[i] == '<') { sIndex[index] =  i; index++; }
		else {
			index--;
			int i_left = sIndex[index];
			char left = parenthesis[i_left];
			int v_left = getPriority(left), v_right = getPriority(parenthesis[i]);
			if (v_left < v_right) parenthesis[i] = PRIORITY[v_left][1];
			else parenthesis[i_left] = PRIORITY[v_right][0];
		}
	}
}

int main() {
	int cases;
	scanf("%d", &cases); __fpurge();

	while (cases--) {
		char input[1024], *parenthesis, *priority;
		fgets(input, 1024, stdin);
		parenthesis = strtok(input, " ");
		priority = strtok(NULL, " ");
		initPrioirt(priority);
		matchingParenthesis(parenthesis);
		fputs(parenthesis, stdout);
		fputs("\n", stdout);
	}
}




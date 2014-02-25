#include <cstdio>
#include <cstring>
#include <stack>
using namespace std;
void __fpurge() {
	while (getchar() != '\n');
}

bool isWellMatched(char *brackets) {
	bool isWellMatched = false;
	stack<char> bracketsBasket;
	int size = (int)strlen(brackets) - 1;
	for (int i = 0; i < size; i++) {
		if (brackets[i] == '(' || brackets[i] == '{' || brackets[i] == '[') bracketsBasket.push(brackets[i]);
		else {
			if (bracketsBasket.empty()) return isWellMatched;
			if (brackets[i] == ')' && bracketsBasket.top() == '(') bracketsBasket.pop();
			else if (brackets[i] == ']' && bracketsBasket.top() == '[') bracketsBasket.pop();
			else if (brackets[i] == '}' && bracketsBasket.top() == '{') bracketsBasket.pop();
		}
	}
	if (bracketsBasket.empty() && size > 0) isWellMatched = true;
	return isWellMatched;
}

int main() {
	int cases;
	scanf("%d", &cases); __fpurge();
	while (cases--) {
		char brackets[1024];
		fgets(brackets, 1024, stdin);
		if (isWellMatched(brackets)) fputs("YES\n", stdout);
		else fputs("NO\n", stdout);
	}
}

#include <cstdio>
#include <cstring>
#include <algorithm>
#include <string>
using namespace std;

const string NUMBER[] = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
const int SIZE = 11;
void __fpurge() {
	while (getchar() != '\n');
}

bool compareWrongNumber(string charNumber_1, string charNumber_2) {
	string temp_1 = charNumber_1;
	string temp_2 = charNumber_2;
	sort(temp_1.begin(), temp_1.end() - 1);
	sort(temp_2.begin(), temp_2.end());
	if (temp_1.compare(temp_2) == 1) return true;
	return false;
}


int wrongNumber(string charNumber) {
	for (int i = 0; i < SIZE; i++) {
		if (charNumber.length() - 1 == NUMBER[i].length() && compareWrongNumber(charNumber, NUMBER[i])) return i;
	}
	return -1;
}

int getNumber(string charNumber) {
	for (int i = 0; i < SIZE; i++) if (charNumber == NUMBER[i]) return i;
	return wrongNumber(charNumber);
}

int calculator(int a, int b, string operation) {
	int ret = 0;
	if (operation == "+") ret = a+b;
	else if (operation == "-") ret = a-b;
	else ret = a*b;
	return ret;
}

int main() {
	int cases;
	scanf("%d", &cases); __fpurge();
	while (cases--) {
		char input[1024], *buf; fgets(input, 1024, stdin);
		string value[5];

		int index = 0;
		buf = strtok(input, " ");
		while (buf != NULL) {
			value[index++] = buf;
			buf = strtok(NULL, " ");
		}

		int result = calculator(getNumber(value[0]), getNumber(value[2]), value[1]);
		if (result == getNumber(value[4])) fputs("Yes\n", stdout);
		else  fputs("No\n", stdout);
	}
}




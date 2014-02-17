#include <cstdio>
#include <cstring>
#include <string>
using namespace std;

void __fpurge() {
	while (getchar() != '\n');
}

int main() {
	int cases;
	scanf("%d", &cases); __fpurge();
	while (cases--) {
		char input[1024];
		string result = "";
		fgets(input, 1024, stdin);
		int size = strlen(input) - 1;
		for (int i = 0; i < size; i++) {
			if (input[i] == '%') {
				if (input[i+2] == '0') result+=" ";
				else if (input[i+2] == '1') result+="!";
				else if (input[i+2] == '4') result+="$";
				else if (input[i+2] == '5') result+="%";
				else if (input[i+2] == '8') result+="(";
				else if (input[i+2] == '9') result+=")";
				else if (input[i+2] == 'a') result+="*";
				i+=2;
			}
			else result += input[i];
		}
		fputs(result.c_str(), stdout);
		fputs("\n", stdout);
	}
}




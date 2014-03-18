#include <cstdio>
#include <string>
using namespace std;

void __fpurge() {
	while (getchar() != '\n');
}

string reverse(string::iterator& it) {
	char head = *it; it++;
	if (head == 'b' || head == 'w') return string(1, head);
	string upperLeft = reverse(it);
	string upperRight = reverse(it);
	string lowerLeft = reverse(it);
	string lowerRigft = reverse(it);
	return string("x") + lowerLeft + lowerRigft + upperLeft + upperRight;
}

int main() {
	int cases; scanf("%d", &cases); __fpurge();

	while (cases--) {
		char buffer[1024]; fgets(buffer, 1024, stdin);
		string image = string(buffer);
		string::iterator it = image.begin();
		fputs(reverse(it).c_str(), stdout);
		fputs("\n", stdout);
	}
}

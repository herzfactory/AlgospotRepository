#include <cstdio>
#include <queue>
using namespace std;

int main() {
	int cases; scanf("%d", &cases);
	while (cases--) {
		int n, k; scanf("%d %d", &n, &k);
		queue<int> man;
		for (int i = 1; i <= n; i++) man.push(i);
		int index = 0;
		while (man.size() > 2) {
			if (index % k != 0) man.push(man.front());
			man.pop(); index++;
		}
		int a = man.front(), b = man.back();
		if (a > b) printf("%d %d\n", b, a);
		else printf("%d %d\n", a, b);
	}
}

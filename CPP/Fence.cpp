#include <cstdio>
#include <stack>
#include <vector>
using namespace std;

vector<int> height;

int max(int a, int b) { return a > b ? a : b;}

int maxRectSize() {
	int ret = 0;
	stack<int> remaining;
	height.push_back(0);
	for (int i = 0; i < height.size(); i++) {
		while (!remaining.empty() && height[remaining.top()] > height[i]) {
			int j = remaining.top();
			int width = -1;
			remaining.pop();
			if (remaining.empty()) width = 1;
			else width = i - remaining.top() - 1;
			ret = max(ret, width * height[j]);
		}
		remaining.push(i);
	}
	return ret;
}

int main() {
	int cases; scanf("%d", &cases);

	while (cases--) {
		int numOfFence; scanf("%d", &numOfFence);
		for (int i = 0; i < numOfFence; i++) {
			int value; scanf("%d", &value);
			height.push_back(value);
		}
		printf("%d\n", maxRectSize());
		height.clear();
	}
}

#include <cstdio>
#include <vector>
using namespace std;

vector<int> postOrder;

int indexOf(int value, vector<int> in) {
	for (int i = 0; i < in.size(); i++) {
		if (value == in[i]) return i;
	}
	return -1;
}

template <typename T>
vector<T> subList(vector<T> arr, int begin, int end) {
	return vector<T>(arr.begin() + begin, arr.begin() + end);
}

void printPostOrder(vector<int> pre, vector<int> in) {
	if (pre.empty()) return;
	int rootValue = pre[0];
	int rootIndex = indexOf(rootValue, in);
	printPostOrder(subList(pre, 1, rootIndex + 1), subList(in, 0, rootIndex));
	printPostOrder(subList(pre, rootIndex + 1, pre.size()), subList(in, rootIndex + 1, in.size()));
	postOrder.push_back(rootValue);

}

int main() {
	int cases; scanf("%d", &cases);
	while (cases--) {
		int size; scanf("%d", &size);
		vector<int> pre, in;
		for (int i = 0; i < size; i++) {
			int temp; scanf("%d", &temp); pre.push_back(temp);
		}
		for (int i = 0; i < size; i++) {
			int temp; scanf("%d", &temp); in.push_back(temp);
		}
		printPostOrder(pre, in);
		for (int i = 0; i < postOrder.size(); i++) {
			if (i != 0) printf(" ");
			printf("%d", postOrder[i]);
		}
		printf("\n");
		postOrder.clear();
	}
}

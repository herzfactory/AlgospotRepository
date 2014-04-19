#include <vector>
#include <string>
#include <iostream>
using namespace std;

vector<int> getPartialMatch(string str) {
	int length = str.size(), begin = 1, matched = 0;
	vector<int> pi(length, 0);
	while (begin + matched < length) {
		if (str[begin + matched] == str[matched]) {
			matched++;
			pi[begin + matched -1] = matched;
		}
		else {
			if (matched == 0) begin++;
			else {
				begin += matched - pi[matched - 1];
				matched = pi[matched - 1];
			}
		}
	}
	return pi;
}

vector<int> getPrefixSuffix(string str) {
	vector<int> ret;
	vector<int> pi = getPartialMatch(str);
	int k = pi.size();
	while (k > 0) {
		ret.push_back(k);
		k = pi[k - 1];
	}
	return ret;
}


int main() {
	string father, mother; cin >> father >> mother;
	string baby = father + mother;
	vector<int> ret = getPrefixSuffix(baby);
	for (int i = ret.size() - 1; i >= 0; i--) {
		if (i != ret.size() - 1) printf(" ");
		printf("%d", ret[i]);
	}
	printf("\n");
}




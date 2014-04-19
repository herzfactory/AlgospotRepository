#include <iostream>
#include <vector>
#include <string>
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

int palindromize(string word, string reverse) {
	int w_length = word.length(), r_length = reverse.length(), begin = 0, matched = 0;
	vector<int> pi = getPartialMatch(reverse);
	while (begin + matched < w_length) {
		if (word[begin + matched] == reverse[matched]) {
			matched++;
			if (begin + matched == r_length) return matched;
		}
		else {
			if (matched == 0) begin++;
			else {
				begin += matched - pi[matched - 1];
				matched = pi[matched - 1];
			}
		}
	}
	return 0;
}

int main() {
	int cases; cin >> cases;
	while (cases--) {
		string word, reverse = ""; cin >> word;
		for (int i = word.size() - 1; i >= 0; i--) reverse += word[i];
		cout << word.length() * 2 - palindromize(word, reverse) << endl;
	}

}



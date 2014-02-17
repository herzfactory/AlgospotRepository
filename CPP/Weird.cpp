#include <cstdio>
#include <vector>
using namespace std;

void quickSortOrderByAsce(int left, int right, vector<int>& array) {
	int h_left = left, h_right = right, pivot = array.at(left);
	while (left < right) {
		while (pivot > array.at(right) && left < right) right--;
		if (left != right) array.at(left) = array.at(right);
		while (pivot < array.at(left) && left < right) left++;
		if (left != right) { array.at(right) = array.at(left); right--;}
	}
	array.at(left) = pivot;
	pivot = left;
	left = h_left; right = h_right;
	if (left < pivot) quickSortOrderByAsce(left, pivot - 1, array);
	if (pivot < right) quickSortOrderByAsce(pivot + 1, right, array);
}

bool checkPseudoPecfectNumber(vector<int> array, int number, int sum, int index) {
	for (int i = index; i < (int)array.size(); i++) {
		sum += array.at(i);
		if (sum == number) return true;
		else if (sum < number) return checkPseudoPecfectNumber(array, number, sum, i + 1);
		sum -= array.at(i);
	}
	return false;
}

int main() {
	int cases;
	scanf("%d", &cases);

	while (cases--) {
		int number, sumOfDivisor = 0; scanf("%d", &number);
		vector<int> setOfDivisor;
		for (int i = 1; i * i <= number; i++) {
			if (number % i == 0) {
				setOfDivisor.push_back(i);
				if (i != 1 && i != number / i) setOfDivisor.push_back(number/i);
			}
		}
		quickSortOrderByAsce(0, (int)setOfDivisor.size() - 1, setOfDivisor);
		for (int i = 0; i < (int)setOfDivisor.size(); i++) sumOfDivisor += setOfDivisor.at(i);
		if (sumOfDivisor < number || checkPseudoPecfectNumber(setOfDivisor, number, 0, 0)) fputs("not weird\n", stdout);
		else fputs("weird\n", stdout);
	}
}




#include <cstdio>
#include <cstring>
#include <limits>

int numOfCities;
double** distance;
bool* visited;

double min(double a, double b) {
	if (a < b) return a;
	return b;
}

double travelingCity(double totalDistance, int city, int countOfVisited) {
	double ret = std::numeric_limits<double>::max();
	if (countOfVisited == numOfCities) return totalDistance;

	for (int i = 0; i < numOfCities; i++) {
		if (visited[i]) continue;
		visited[i] = true;
		if (city != -1) totalDistance += distance[city][i];
		ret = min(ret, travelingCity(totalDistance, i, countOfVisited + 1));
		if (city != -1) totalDistance -= distance[city][i];
		visited[i] = false;
	}
	return ret;
}


int main() {
	int cases;
	scanf("%d", &cases);

	while (cases--) {
		scanf("%d", &numOfCities);
		distance = new double*[numOfCities];
		for (int i = 0; i < numOfCities; i++) distance[i] = new double[numOfCities];
		visited = new bool[numOfCities];
		for (int i = 0; i < numOfCities; i++) {
			for (int j = 0; j < numOfCities; j++) {
				scanf("%lf", &distance[i][j]);
			}
		}
		printf("%.10f\n", travelingCity(0, -1, 0));
	}
}

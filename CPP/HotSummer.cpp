#include <cstdio>

const int TIME = 9;

int main () {
	int cases;
	scanf("%d", &cases);
	while (cases--) {
		int targetedPowerUsage;
		scanf("%d", &targetedPowerUsage);
		for (int i = 0; i < TIME; i++) {
			int usage; scanf("%d", &usage);
			targetedPowerUsage -= usage;
		}
		if (targetedPowerUsage >= 0) fputs("YES\n", stdout);
		else fputs("NO\n", stdout);
	}
}

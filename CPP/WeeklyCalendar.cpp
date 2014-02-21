#include <cstdio>
#include <cstring>

const int DAY_OF_WEEK = 7;
int lastDayOfMonth[] = {28, 30, 31};
char dayOfTheWeek[][10] = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

void __fpurge() {
	while (getchar() != '\n');
}

int* createWeeklyCalendar(int sunday, int last, int current) {
	int* week = new int[DAY_OF_WEEK];
	int lastday = current;
	if (sunday < 1) {
		sunday += last;
		lastday = last;
	}
	for (int i = 0; i < DAY_OF_WEEK; i++) {
		week[i] = (sunday + i) % lastday;
		if (week[i] == 0) week[i] = lastday;
	}
	return week;
}

int main () {
	int cases;
	scanf("%d", &cases); __fpurge();
	while (cases--) {
		int month, day, sunday;
		char dayOfWeek[1024];
		scanf("%d %d", &month, &day);
		getchar(); fgets(dayOfWeek, 1024, stdin);
		for (int i = 0; i < DAY_OF_WEEK; i++) {
			if (strncmp(dayOfTheWeek[i], dayOfWeek, strlen(dayOfWeek) - 1) == 0) sunday = day - i;
		}
		int* week;
		switch (month) {
		case 1 : case 8 :
			week = createWeeklyCalendar(sunday, lastDayOfMonth[2], lastDayOfMonth[2]);
			break;
		case 2 :
			week = createWeeklyCalendar(sunday, lastDayOfMonth[2], lastDayOfMonth[0]);
			break;
		case 3 :
			week = createWeeklyCalendar(sunday, lastDayOfMonth[0], lastDayOfMonth[2]);
			break;
		case 4 : case 6 : case 9 : case 11 :
			week = createWeeklyCalendar(sunday, lastDayOfMonth[2], lastDayOfMonth[1]);
			break;
		case 5 : case 7 : case 10 : case 12 :
			week = createWeeklyCalendar(sunday, lastDayOfMonth[1], lastDayOfMonth[2]);
			break;
		}
		for (int i = 0; i < DAY_OF_WEEK; i++) {
			if (i != 0) printf(" ");
			printf("%d", week[i]);
		}
		printf("\n");
	}
}




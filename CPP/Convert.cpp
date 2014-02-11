#include <cstdio>
#include <cstring>
#include <cmath>

void __fpurge() {
    while (getchar() != '\n');
}

int main() {
    int cases, index = 0;
    scanf("%d", &cases);
    __fpurge();
    while (index++ < cases) {
        float value;
        char measurement[4] = "", convertMeasurement[4] = "";
        scanf("%f", &value); getchar(); fgets(measurement, 4, stdin);

        if (strcmp(measurement, "kg\n") == 0) {
            value *= 2.2046;
            strcpy(convertMeasurement, "lb\0");
        }
        else if (strcmp(measurement, "l\n") == 0) {
            value *= 0.2642;
            strcpy(convertMeasurement, "g\0");
        }
        else if (strcmp(measurement, "lb\n") == 0) {
            value *= 0.4536;
            strcpy(convertMeasurement, "kg\0");
        }
        else if (strcmp(measurement, "g\n") == 0) {
            value *= 3.7854;
            strcpy(convertMeasurement, "l\0");
        }
        value = round(value * 10000) / 10000;
        printf("%d %0.4f %s\n", index, value, convertMeasurement);
    }
}


package ru.vorobev.statistic;

public interface LineStatistic {
    static void calculatingStats(int value) {
    }

    static void calculatingStats(double value) {
    }

    static void calculatingStats(String value) {
    }

    double getIntAverage();

    double getFloatAverage();

    void printStatistic(boolean briefStats, boolean fullStats);
}

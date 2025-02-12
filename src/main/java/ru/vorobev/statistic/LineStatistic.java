package ru.vorobev.statistic;

public interface LineStatistic {
    void calculatingStats(int value);

    void calculatingStats(double value);

    void calculatingStats(String value);

    double getIntAverage();

    double getFloatAverage();

    void printStatistic(boolean briefStats, boolean fullStats);
}

package ru.vorobev.statistic;

import lombok.Getter;

//TODO Статистика при пустых файлах не корректная
@Getter
public class LineStatistic {
    private int intCount;
    private int floatCount;
    private int intSum;
    private int intMin = Integer.MAX_VALUE;
    private int intMax = Integer.MIN_VALUE;
    private int stringCount;
    private int stringMaxLength = Integer.MIN_VALUE;
    private int stringMinLength = Integer.MAX_VALUE;
    private double floatSum;
    private double floatMin = Double.MAX_VALUE;
    private double floatMax = Double.MIN_VALUE;

    public void calculatingStats(int value) {
        intCount++;
        intSum += value;
        intMin = Math.min(intMin, value);
        intMax = Math.max(intMax, value);
    }

    public void calculatingStats(double value) {
        floatCount++;
        floatSum += value;
        floatMin = Math.min(floatMin, value);
        floatMax = Math.max(floatMax, value);
    }

    public void calculatingStats(String value) {
        stringCount++;
        stringMinLength = Math.min(value.length(), stringMinLength);
        stringMaxLength = Math.max(stringMaxLength, value.length());
    }

    public double getIntAverage() {
        return intCount > 0 ? (double) intSum / intCount : 0;
    }

    public double getFloatAverage() {
        return floatCount > 0 ? floatSum / floatCount : 0;
    }

    public void printStatistic(boolean briefStats, boolean fullStats) {
        if (briefStats) {
            System.out.println("Statistics:");
            System.out.println("Integers: " + intCount);
            System.out.println("Floats: " + floatCount);
            System.out.println("Strings: " + stringCount);
        }
        if (fullStats) {
            System.out.println("Float statistics:");
            System.out.println("  Min: " + floatMin);
            System.out.println("  Max: " + floatMax);
            System.out.println("  Sum: " + floatSum);
            System.out.println("  Avg: " + getFloatAverage());
            System.out.println("Integer statistics:");
            System.out.println("  Min: " + intMin);
            System.out.println("  Max: " + intMax);
            System.out.println("  Sum: " + intSum);
            System.out.println("  Avg: " + getIntAverage());
            System.out.println("String statistics");
            System.out.println("  Min length " + stringMinLength);
            System.out.println("  Max length " + stringMaxLength);
        }
    }
}


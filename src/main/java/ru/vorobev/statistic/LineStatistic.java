package ru.vorobev.statistic;

import lombok.Getter;

/**
 * Class for calculate statistics lines from files
 *
 * @author maxim
 */

@Getter
public class LineStatistic {
    /**
     * Field count integers
     */
    private int intCount;
    /**
     * Field count real numbers
     */
    private int floatCount;
    /**
     * Field sum of all integers
     */
    private int intSum;
    /**
     * Field minimum value integers
     */
    private int intMin = Integer.MAX_VALUE;
    /**
     * Field maximum value integers
     */
    private int intMax;
    /**
     * Field count strings
     */
    private int stringCount;
    /**
     * Field maximum string length
     */
    private int stringMaxLength;
    /**
     * Field minimum string length
     */
    private int stringMinLength = Integer.MAX_VALUE;
    /**
     * Field sum of all real numbers
     */
    private double floatSum;
    /**
     * Field minimum real numbers
     */
    private double floatMin = Float.MAX_VALUE;
    /**
     * Field maximum real numbers
     */
    private double floatMax;

    /**
     * Calculates the quantity, sum, minimum and maximum of integers obtained from a file
     *
     * @param value line from file
     */
    public void calculatingStats(int value) {
        intCount++;
        intSum += value;
        intMin = Math.min(intMin, value);
        intMax = Math.max(intMax, value);
    }

    /**
     * Calculates the quantity, sum, minimum and maximum of real numbers obtained from a file
     *
     * @param value line from file
     */
    public void calculatingStats(double value) {
        floatCount++;
        floatSum += value;
        floatMin = Math.min(floatMin, value);
        floatMax = Math.max(floatMax, value);
    }

    /**
     * Calculates the quantity, minimum and maximum length of strings obtained from a file
     *
     * @param value line from file
     */
    public void calculatingStats(String value) {
        stringCount++;
        stringMinLength = Math.min(value.length(), stringMinLength);
        stringMaxLength = Math.max(stringMaxLength, value.length());
    }

    /**
     * Calculates the average of integers
     *
     * @return average of integers
     */
    public double getIntAverage() {
        return intCount > 0 ? (double) intSum / intCount : 0;
    }

    /**
     * Calculates the average of real numbers
     *
     * @return average of real numbers
     */
    public double getFloatAverage() {
        return floatCount > 0 ? floatSum / floatCount : 0;
    }

    /**
     * Print statistics in console
     *
     * @param briefStats print short statistic count of integers, real numbers and strings
     * @param fullStats  print full statistics count, minimum, maximum, count, average of integers and real numbers,
     *                   for strings count, minimum length and maximum length
     */

    public void printStatistic(boolean briefStats, boolean fullStats) {
        if (briefStats) {
            System.out.println("Statistics:");
            System.out.println("Integers: " + intCount);
            System.out.println("Floats: " + floatCount);
            System.out.println("Strings: " + stringCount);
        }
        if (fullStats) {
            System.out.println("Float statistics:");
            if (intMin == Integer.MAX_VALUE)
                intMin = 0;
            if (floatMin == Float.MAX_VALUE)
                floatMin = 0;
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
            if (stringMinLength == Integer.MAX_VALUE)
                stringMinLength = 0;
            System.out.println(" Count strings " + stringCount);
            System.out.println("  Min length " + stringMinLength);
            System.out.println("  Max length " + stringMaxLength);
        }
    }
}


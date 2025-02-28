package ru.vorobev.statistic;

import lombok.Getter;

/**
 * Class for calculate statistics lines from files
 *
 * @author maxim
 */

@Getter
public class LineStatisticImpl implements LineStatistic {
    /**
     * Field count integers
     */
    private static int intCount;
    /**
     * Field count real numbers
     */
    private static int floatCount;
    /**
     * Field sum of all integers
     */
    private static int intSum;
    /**
     * Field minimum value integers
     */
    private static int intMin = Integer.MAX_VALUE;
    /**
     * Field maximum value integers
     */
    private static int intMax;
    /**
     * Field count strings
     */
    private static int stringCount;
    /**
     * Field maximum string length
     */
    private static int stringMaxLength;
    /**
     * Field minimum string length
     */
    private static int stringMinLength = Integer.MAX_VALUE;
    /**
     * Field sum of all real numbers
     */
    private static double floatSum;
    /**
     * Field minimum real numbers
     */
    private static double floatMin = Float.MAX_VALUE;
    /**
     * Field maximum real numbers
     */
    private static double floatMax;

    /**
     * Calculates the quantity, sum, minimum and maximum of integers obtained from a file
     *
     * @param value line from file
     */
    public static void calculatingStats(int value) {
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
    public static void calculatingStats(double value) {
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
    public static void calculatingStats(String value) {
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
        String brief = """
                Statistics:
                Integers: %s
                Floats: %s
                Strings: %s
                """.formatted(intCount, floatCount, stringCount);
        if (briefStats) {
            System.out.println(brief);
        }
        if (fullStats) {
            if (intMin == Integer.MAX_VALUE) {
                intMin = 0;
            }
            if (floatMin == Float.MAX_VALUE) {
                floatMin = 0;
            }
            if (stringMinLength == Integer.MAX_VALUE)
                stringMinLength = 0;
            String full = """
                    Full statistics:
                    Float statistics: 
                    Min: %.2f
                    Max: %.2f
                    Sum: %.2f
                    Avg %.2f
                    Integer statistics:
                    Min: %s
                    Max: %s
                    Sum: %s
                    Avg %s
                    String statistics:
                    Count strings %s
                    Min length %s
                    Max length %s
                    """.formatted(floatMin, floatMax, floatSum, getFloatAverage(), intMin, intMax, intSum,
                    getIntAverage(), stringCount, stringMinLength, stringMaxLength);
            System.out.println(full);
        }
    }
}


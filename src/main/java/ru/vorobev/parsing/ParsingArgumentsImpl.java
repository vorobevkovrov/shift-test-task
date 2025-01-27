package ru.vorobev.parsing;

import lombok.Getter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ParsingArgumentsImpl implements ParsingArguments {
    private final static String REGEX_PATH_TO_WRITE_FILES = "^/[^/]+(/[^/]+)*/$";
    private final static String REGEX_READ_FILES = null;
    private final static String REGEX_ = null;
    private static String intFileName = "integers.txt";
    private static String floatFileName = "floats.txt";
    private static String stringFileName = "strings.txt";
    private static boolean appendMode = false;
    @Getter
    private static boolean briefStats = false;
    @Getter
    private static boolean fullStats = false;
    private static String outputPath = "";
    @Getter
    private static String prefix = "";
    private static final String DEFAULT_PATH = "C:\\Users\\maxim\\IdeaProjectsUltimate\\shift-test-task\\";
    @Getter
    private static String floatFilePath;
    @Getter
    private static String stringFilePath;
    @Getter
    private static String intFilePath;



    static public List<String> programArgs(String[] args) {
        List<String> inputFiles = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-a":
                    appendMode = true;
                    break;
                case "-s":
                    briefStats = true;
                    break;
                case "-f":
                    fullStats = true;
                    break;
                case "-o":
                    if (i + 1 < args.length) {
                        outputPath = args[++i];
                    } else {
                        System.err.println("Error: Missing path after -o");
                        break;
                    }
                    break;
                case "-p":
                    if (i + 1 < args.length) {
                        prefix = args[++i];
                        if (!prefix.isEmpty()) {
                            intFileName = prefix + "integers.txt";
                            floatFileName = prefix + "floats.txt";
                            stringFileName = prefix + "strings.txt";
                        }
                        if (!outputPath.isEmpty()) {
                            intFilePath =  DEFAULT_PATH + outputPath + intFileName;
                            floatFilePath =  DEFAULT_PATH + outputPath + floatFileName;
                            stringFilePath =  DEFAULT_PATH + outputPath + stringFileName;
                           // outputPath = DEFAULT_PATH + outputPath + intFileName;
                        }
                    } else {
                        System.err.println("Error: Missing prefix after -p");
                    }
                    break;
                default:
                    inputFiles.add(args[i]);
            }
        }

        // Apply prefix and output path

        return inputFiles;
    }

    public String getIntFileName() {
        return intFileName;
    }

    public String getFloatFileName() {
        return floatFileName;
    }

    public String getStringFileName() {
        return stringFileName;
    }

    public boolean isAppendMode() {
        return appendMode;
    }

    public boolean isFullStat() {
        return fullStats;
    }

    public boolean isBriefStat() {
        return briefStats;
    }

    public String getOutputPath() {
        return outputPath;
    }
}
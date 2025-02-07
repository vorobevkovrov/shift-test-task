package ru.vorobev.parsing;

import lombok.Getter;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
//TODO выдача сообщения о том что нет файлов для обработки
@Getter
public class ParsingArgumentsImpl implements ParsingArguments {
    private static String intFileName = "integers.txt";
    private static String floatFileName = "floats.txt";
    private static String stringFileName = "strings.txt";

    @Getter
    private static boolean appendMode = false;
    @Getter
    private static boolean briefStats = false;
    @Getter
    private static boolean fullStats = false;
    @Getter
    private static String outputPath;
    private static String prefix;
    private static final String DEFAULT_PATH = "C:\\Users\\maxim\\IdeaProjects\\shift-test-task";
    @Getter
    private static Path floatFullPathToFile;
    @Getter
    private static Path stringFullPathToFile;
    @Getter
    private static Path intFullPathToFile;
    @Getter
    private static Path path;


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
                            intFullPathToFile = Path.of(DEFAULT_PATH + outputPath + "\\" + intFileName);
                            floatFullPathToFile = Path.of(DEFAULT_PATH + outputPath + "\\" + floatFileName);
                            stringFullPathToFile = Path.of(DEFAULT_PATH + outputPath + "\\" + stringFileName);
                            path = Path.of(DEFAULT_PATH + outputPath);

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
}
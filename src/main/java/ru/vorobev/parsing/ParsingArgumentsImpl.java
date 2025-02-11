package ru.vorobev.parsing;

import lombok.Getter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**Class for parsing input parameters
 *
 */
@Getter
public class ParsingArgumentsImpl implements ParsingArguments {
    /**
     * Field file name for integers
     */
    private static String intFileName = "integers.txt";
    /**
     * Field file name for real numbers
     */
    private static String floatFileName = "floats.txt";
    /**
     * Field file name for strings
     */
    private static String stringFileName = "strings.txt";
    /**
     * Field for append to file mode
     */
    @Getter
    private static boolean appendMode = false;
    /**
     * Field for brief statistics
     */
    @Getter
    private static boolean briefStats;
    @Getter
    /**
     * Field for full statistics
     */
    private static boolean fullStats;
    /**
     * Field for path to write files
     */
    @Getter
    private static String outputPath;
    /**
     * Field for prefix name files
     */
    private static String prefix;
    /**
     * Field for path to write real numbers files
     */
    @Getter
    private static Path floatFullPathToFile;
    /**
     * Field for path to write strings files
     */
    @Getter
    private static Path stringFullPathToFile;
    /**
     * Field for path to write integers files
     */
    @Getter
    private static Path intFullPathToFile;
    //TODO rename
    @Getter
    private static Path path;

    /**
     *Returns list arguments incoming from command line and set append mode, statistics
     * and path to write files and name files
     * @param args array of arguments
     * @return list of files to process
     */

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
                        if (outputPath==null){
                            Path currentRelativePath = Paths.get("");
                            outputPath = currentRelativePath.toAbsolutePath().toString();
                        }

                        if (!outputPath.isEmpty()) {
                            intFullPathToFile = Path.of(outputPath + "\\" + intFileName);
                            floatFullPathToFile = Path.of(outputPath + "\\" + floatFileName);
                            stringFullPathToFile = Path.of(outputPath + "\\" + stringFileName);
                            path = Path.of(outputPath);

                        }

                    } else {
                        System.err.println("Error: Missing prefix after -p");
                    }
                    break;
                default:
                    inputFiles.add(args[i]);
            }
        }
        return inputFiles;
    }
}
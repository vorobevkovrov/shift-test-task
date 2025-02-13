package ru.vorobev.fileprocessing;

import ru.vorobev.parsing.ParsingArgumentsImpl;
import ru.vorobev.statistic.LineStatisticImpl;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

/**
 * Class for file processing
 *
 * @author maxim
 */
public class FileProcessingImpl implements FileProcessing {
    LineStatisticImpl stats = new LineStatisticImpl();
    WriteToFile writeToFile = new WriteToFile();

    /**
     * Checks for files and distributes writing to files depending on the -a flag
     *
     * @param list of inputFiles
     * @return statistics on recorded lines
     */
    public LineStatisticImpl writeToFiles(List<String> inputFiles) {
        if (inputFiles.isEmpty()) {
            throw new RuntimeException("There are no files to process, please add files in working directory");
        }
        //if isAppendMode (-a) false
        if (!ParsingArgumentsImpl.isAppendMode()) {
            try {
                Files.deleteIfExists(ParsingArgumentsImpl.getStringFullPathToFile());
                Files.deleteIfExists(ParsingArgumentsImpl.getFloatFullPathToFile());
                Files.deleteIfExists(ParsingArgumentsImpl.getIntFullPathToFile());
            } catch (IOException e) {
                System.err.println("Could not to delete files " + e);
            }
            writeToFile(inputFiles);
        }
        //if isAppendMode (-a) true write to existing files
        if (ParsingArgumentsImpl.isAppendMode()) {
            writeToFile(inputFiles);
        }
        return stats;
    }

    /**
     * Write lines to files writes to files depending
     * on the passed path or its absence
     *
     * @param inputFiles list files to read
     */
    public void writeToFile(List<String> inputFiles) {
        Path path = Paths.get("");
        List<String> lines;
        if (ParsingArgumentsImpl.getOutputPath().isEmpty()) {
            for (String inputFile : inputFiles) {
                try {
                    lines = Files.readAllLines(Path.of((path.toAbsolutePath() + FileSystems.getDefault()
                            .getSeparator() + inputFile)));
                    for (String line : lines) {
                        // Integer
                        if (line.matches("-?\\d+")) {
                            writeToFile.writeToFile(line, ParsingArgumentsImpl.getPath());
                            stats.calculatingStats(Integer.parseInt(line));
                            // Float
                        } else if (line.matches("-?\\d*\\.\\d+")) {
                            writeToFile.writeToFile(line, ParsingArgumentsImpl.getPath());
                            stats.calculatingStats(Double.parseDouble(line));
                            // String
                        } else {
                            writeToFile.writeToFile(line, ParsingArgumentsImpl.getPath());
                            stats.calculatingStats(line);
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Error reading file " + e);
                }
            }
        } else {
            try {
                Files.createDirectories(ParsingArgumentsImpl.getPath());
            } catch (IOException e) {
                System.err.println("Path already exist " + e);
            }
            for (String inputFile : inputFiles) {
                try {
                    lines = Files.readAllLines(Path.of((path.toAbsolutePath() + FileSystems.getDefault()
                            .getSeparator() + inputFile)));
                    for (String line : lines) {
                        // Integer
                        if (line.matches("-?\\d+")) {
                            writeToFile.writeToFile(line, ParsingArgumentsImpl.getIntFullPathToFile());
                            stats.calculatingStats(Integer.parseInt(line));
                            // Float
                        } else if (line.matches("-?\\d*\\.\\d+")) {
                            writeToFile.writeToFile(line, ParsingArgumentsImpl.getFloatFullPathToFile());
                            stats.calculatingStats(Double.parseDouble(line));
                            // String
                        } else {
                            writeToFile.writeToFile(line, ParsingArgumentsImpl.getStringFullPathToFile());
                            stats.calculatingStats(line);
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Error reading incoming file: " + inputFile + " " + e);
                }
            }
        }
    }
}

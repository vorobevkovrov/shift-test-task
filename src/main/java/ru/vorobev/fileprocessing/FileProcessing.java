package ru.vorobev.fileprocessing;

import ru.vorobev.parsing.ParsingArgumentsImpl;
import ru.vorobev.statistic.LineStatistic;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

/**
 * Class for file processing
 *
 * @author maxim
 */
public class FileProcessing {
    LineStatistic stats = new LineStatistic();
    WriteToFile writeToFile = new WriteToFile();

    /**
     * @param list of inputFiles
     * @return s
     */
    public LineStatistic writeToFiles(List<String> inputFiles) {
        if (inputFiles.isEmpty()) {
            System.err.println("There are no files to process, please add files in working directory");
            System.exit(130);
        }
        //если isAppendMode (-a) false то необходимо удалить существующие файлы
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
        //если isAppendMode (-a) true то пишем в существующие файлы
        if (ParsingArgumentsImpl.isAppendMode()) {
            writeToFile(inputFiles);
        }
        return stats;
    }

    /**
     * @param inputFiles
     */
    private void writeToFile(List<String> inputFiles) {
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

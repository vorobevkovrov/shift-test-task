package ru.vorobev.fileprocessing;

import ru.vorobev.parsing.ParsingArgumentsImpl;
import ru.vorobev.statistic.LineStatistic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

//TODO Переписать чтение файла на nio библиотеку
public class FileProcessing {
    private final String intFileName;
    private final String floatFileName;
    private final String stringFileName;
    private final boolean appendMode;

    public FileProcessing(String intFileName, String floatFileName, String stringFileName, boolean appendMode) {
        this.intFileName = intFileName;
        this.floatFileName = floatFileName;
        this.stringFileName = stringFileName;
        this.appendMode = appendMode;
    }

    LineStatistic stats = new LineStatistic();
    WriteToFile writeToFile = new WriteToFile();

    public LineStatistic writeToFiles(List<String> inputFiles) {
        //если isAppendMode false то необходимо удалить существующие файлы
        if (!ParsingArgumentsImpl.isAppendMode()) {
            try {
                Files.deleteIfExists(ParsingArgumentsImpl.getStringFullPathToFile());
                Files.deleteIfExists(ParsingArgumentsImpl.getFloatFullPathToFile());
                Files.deleteIfExists(ParsingArgumentsImpl.getIntFullPathToFile());
            } catch (IOException e) {
                System.err.println("Could not to delete files " + e);
            }
            writeToFileIfPathIsEmpty(inputFiles);
        }
        //если isAppendMode true то пишем в существующие файлы
        if (ParsingArgumentsImpl.isAppendMode()) {
            writeToFileIfPathIsEmpty(inputFiles);
        }
        return stats;
    }


    private void writeToFileIfPathIsEmpty(List<String> inputFiles) {
        if (ParsingArgumentsImpl.getOutputPath().isEmpty()) {
            List<String> lines;
            try {
                lines = List.of(String.valueOf(Files.readAllLines(Path.of(String.valueOf(inputFiles)))));
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
                System.out.println("Error reading file " + e);
            }
        } else {
            try {
                Files.createDirectories(ParsingArgumentsImpl.getPath());
            } catch (IOException e) {
                System.err.println("Path already exist " + e);
            }
            //TODO переписать на
            for (String inputFile : inputFiles) {
                try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                     WriteToFile writerManager = new WriteToFile()) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        // Integer
                        if (line.matches("-?\\d+")) {
                            writerManager.writeToFile(line, ParsingArgumentsImpl.getIntFullPathToFile());
                            stats.calculatingStats(Integer.parseInt(line));
                            // Float
                        } else if (line.matches("-?\\d*\\.\\d+")) {
                            writerManager.writeToFile(line, ParsingArgumentsImpl.getFloatFullPathToFile());
                            stats.calculatingStats(Double.parseDouble(line));
                            // String
                        } else {
                            writerManager.writeToFile(line, ParsingArgumentsImpl.getStringFullPathToFile());
                            stats.calculatingStats(line);
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Error processing file: " + inputFile + " " + e);
                }
            }
        }
    }
}

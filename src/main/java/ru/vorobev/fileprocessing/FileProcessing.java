package ru.vorobev.fileprocessing;

import ru.vorobev.parsing.ParsingArgumentsImpl;
import ru.vorobev.statistic.LineStatistic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

//TODO нужна проверка существования файла по пути
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

    //TODO переписать все на switch case согласно параметрам (-a)
    public LineStatistic writeToFiles(List<String> inputFiles) {
        //если isAppendMode false то необходимо удалить существующие файлы
        if (!ParsingArgumentsImpl.isAppendMode()) {
            try {
                Files.deleteIfExists(ParsingArgumentsImpl.getIntFullPathToFile());
                Files.deleteIfExists(ParsingArgumentsImpl.getFloatFullPathToFile());
                Files.deleteIfExists(ParsingArgumentsImpl.getStringFullPathToFile());
            } catch (IOException e) {
                System.err.println("Delete files error " + e);
            }
            if (ParsingArgumentsImpl.getOutputPath().isEmpty()) {
                for (String inputFile : inputFiles) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            if (line.matches("-?\\d+")) {// Integer
                                Files.write(ParsingArgumentsImpl.getIntPath(), (line + System.lineSeparator())
                                        .getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                                stats.incrementIntCount(Integer.parseInt(line));
                            } else if (line.matches("-?\\d*\\.\\d+")) { // Float
                                Files.write(ParsingArgumentsImpl.getFloatFullPathToFile(), (line + System.lineSeparator())
                                        .getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                                stats.incrementFloatCount(Double.parseDouble(line));
                            } else { // String
                                Files.write(ParsingArgumentsImpl.getStringFullPathToFile(), (line + System.lineSeparator())
                                        .getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                                stats.incrementStringCount(line);
                            }
                        }
                    } catch (IOException e) {
                        System.err.println("Error processing file: " + inputFile);
                    }
                }
            } else {
                try {
                    Files.createDirectories(ParsingArgumentsImpl.getIntPath());
                    Files.createFile(ParsingArgumentsImpl.getIntFullPathToFile());
                    Files.createDirectories(ParsingArgumentsImpl.getFloatPath());
                    Files.createFile(ParsingArgumentsImpl.getFloatFullPathToFile());
                    Files.createDirectories(ParsingArgumentsImpl.getStringPath());
                    Files.createFile(ParsingArgumentsImpl.getStringFullPathToFile());
                } catch (IOException e) {
                    System.err.println("File already exist " + e);
                }
                for (String inputFile : inputFiles) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                         WriteToFile writerManager = new WriteToFile()) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            if (line.matches("-?\\d+")) {// Integer
                                writerManager.writeToFile(line, ParsingArgumentsImpl.getIntFullPathToFile());
                                stats.incrementIntCount(Integer.parseInt(line));
                            } else if (line.matches("-?\\d*\\.\\d+")) { // Float
                                writerManager.writeToFile(line, ParsingArgumentsImpl.getFloatFullPathToFile());
                                stats.incrementFloatCount(Double.parseDouble(line));
                            } else { // String
                                writerManager.writeToFile(line, ParsingArgumentsImpl.getStringFullPathToFile());
                                stats.incrementStringCount(line);
                            }
                        }
                    } catch (IOException e) {
                        System.err.println("Error processing file: " + inputFile);
                    }
                }
            }
        }
        //если isAppendMode true то пишем в существующие файлы
        if (ParsingArgumentsImpl.isAppendMode()) {
            if (ParsingArgumentsImpl.getOutputPath().isEmpty()) {
                for (String inputFile : inputFiles) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            if (line.matches("-?\\d+")) {// Integer
                                Files.write(ParsingArgumentsImpl.getIntPath(), (line + System.lineSeparator())
                                        .getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                                stats.incrementIntCount(Integer.parseInt(line));
                            } else if (line.matches("-?\\d*\\.\\d+")) { // Float
                                Files.write(ParsingArgumentsImpl.getFloatFullPathToFile(), (line + System.lineSeparator())
                                        .getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                                stats.incrementFloatCount(Double.parseDouble(line));
                            } else { // String
                                Files.write(ParsingArgumentsImpl.getStringFullPathToFile(), (line + System.lineSeparator())
                                        .getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                                stats.incrementStringCount(line);
                            }
                        }
                    } catch (IOException e) {
                        System.err.println("Error processing file: " + inputFile);
                    }
                }
            } else {
                try {
                    Files.createDirectories(ParsingArgumentsImpl.getIntPath());
                    Files.createFile(ParsingArgumentsImpl.getIntFullPathToFile());
                    Files.createDirectories(ParsingArgumentsImpl.getFloatPath());
                    Files.createFile(ParsingArgumentsImpl.getFloatFullPathToFile());
                    Files.createDirectories(ParsingArgumentsImpl.getStringPath());
                    Files.createFile(ParsingArgumentsImpl.getStringFullPathToFile());
                } catch (IOException e) {
                    System.err.println("File already exist " + e);
                }
                for (String inputFile : inputFiles) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                         WriteToFile writerManager = new WriteToFile()) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            if (line.matches("-?\\d+")) {// Integer
                                writerManager.writeToFile(line, ParsingArgumentsImpl.getIntFullPathToFile());
                                stats.incrementIntCount(Integer.parseInt(line));
                            } else if (line.matches("-?\\d*\\.\\d+")) { // Float
                                writerManager.writeToFile(line, ParsingArgumentsImpl.getFloatFullPathToFile());
                                stats.incrementFloatCount(Double.parseDouble(line));
                            } else { // String
                                writerManager.writeToFile(line, ParsingArgumentsImpl.getStringFullPathToFile());
                                stats.incrementStringCount(line);
                            }
                        }
                    } catch (IOException e) {
                        System.err.println("Error processing file: " + inputFile);
                    }
                }
            }
        }
        return stats;
    }
}

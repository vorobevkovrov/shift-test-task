package ru.vorobev.fileprocessing;

import ru.vorobev.parsing.ParsingArgumentsImpl;
import ru.vorobev.statistic.LineStatistic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;


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
    ParsingArgumentsImpl arguments = new ParsingArgumentsImpl();

    public LineStatistic writeToFiles(List<String> inputFiles) {
     //   Path pathIntFile = Path.of(ParsingArgumentsImpl.getIntFilePath());
        Path path = Path.of("C:\\Users\\maxim\\IdeaProjectsUltimate\\shift-test-task\\3.txt");
        try {
            Files.createFile(path);
      //      Files.createFile(pathIntFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (arguments.getOutputPath().isEmpty()) {
            for (String inputFile : inputFiles) {
                try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                     WriteToFile writerManager = new WriteToFile(intFileName, floatFileName, stringFileName, appendMode)) {
                    String line;

                    while ((line = reader.readLine()) != null) {
                        if (line.matches("-?\\d+")) {// Integer
                            writerManager.writeInteger(line);
                            Files.write(path, (line + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                            stats.incrementIntCount(Integer.parseInt(line));
                        } else if (line.matches("-?\\d*\\.\\d+")) { // Float
                            writerManager.writeFloat(line);
                            Files.write(path, (line + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                            stats.incrementFloatCount(Double.parseDouble(line));
                        } else { // String
                            writerManager.writeString(line);
                            Files.write(path, (line + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                            stats.incrementStringCount(line);
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Error processing file: " + inputFile);
                    e.printStackTrace();
                }
            }
        } else {
            for (String inputFile : inputFiles) {
                try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                     WriteToFile writerManager = new WriteToFile(intFileName, floatFileName, stringFileName, appendMode)) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.matches("-?\\d+")) {// Integer
                            //    writerManager.writeToFileWithPath(line);
                            writerManager.writeInteger(line);
                            stats.incrementIntCount(Integer.parseInt(line));
                        } else if (line.matches("-?\\d*\\.\\d+")) { // Float
                            //    writerManager.writeToFileWithPath(line);
                            writerManager.writeFloat(line);
                            stats.incrementFloatCount(Double.parseDouble(line));
                        } else { // String
                            writerManager.writeToFileWithPath(line, path);
                            writerManager.writeString(line);
                            stats.incrementStringCount(line);
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Error processing file: " + inputFile);
                    e.printStackTrace();
                }
            }
        }
        return stats;
    }
}


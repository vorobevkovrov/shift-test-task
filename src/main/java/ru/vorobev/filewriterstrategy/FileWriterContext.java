package ru.vorobev.filewriterstrategy;

import lombok.Setter;

@Setter
public class FileWriterContext {
    private FileProcessingStrategy fileWriterStrategy;

    public void write(String line) {
        if (fileWriterStrategy == null) {
            throw new IllegalStateException("File writer strategy is not set ");
        }
        fileWriterStrategy.writeToFile(line);
    }
}

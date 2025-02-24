package ru.vorobev.fileprocessing;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Slf4j
@NoArgsConstructor
public class WriteToFile implements AutoCloseable {

    /**
     * Method for write values to file
     *
     * @param value necessary to write to file
     * @param path  to write to file
     */

    public void writeToFile(String value, Path path) {
        try {
            Files.write(path, (value + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            log.error("Failed to write to the file {}", e);
        }
    }

    @Override
    public void close() {
    }
}


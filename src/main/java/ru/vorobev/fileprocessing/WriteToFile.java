package ru.vorobev.fileprocessing;

import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@NoArgsConstructor
public class WriteToFile implements AutoCloseable {

    public void writeToFile(String value, Path path) {
        try {
            Files.write(path, (value + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Failed to write to the file " + e);
        }
    }
    @Override
    public void close(){
    }
}


package ru.vorobev.fileprocessing;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ReadLinesFromFilesImpl implements ReadLinesFromFiles {
    @Override
    public List<String> readLinesFromFiles(Path path, List<String> inputFile) {
        List<String> allLines = new ArrayList<>();
        for (String files : inputFile) {
            try {
                allLines.addAll(Files.readAllLines(Path.of((path.toAbsolutePath() + FileSystems.getDefault()
                        .getSeparator() + files))));
            } catch (IOException e) {
                log.error("Error reading file: {}", e);
            }
        }
        return allLines;
    }
}


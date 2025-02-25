package ru.vorobev.fileprocessing;

import java.nio.file.Path;
import java.util.List;

public interface ReadLinesFromFiles {
    List<String> readLinesFromFiles(Path path, List<String> inputFile);
}

package ru.vorobev.fileprocessing;

import ru.vorobev.statistic.LineStatisticImpl;

import java.util.List;

public interface FileProcessing {
    void writeToFile(List<String> inputFiles);

    LineStatisticImpl writeToFiles(List<String> inputFiles);
}

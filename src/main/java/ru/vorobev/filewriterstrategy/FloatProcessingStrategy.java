package ru.vorobev.filewriterstrategy;

import ru.vorobev.fileprocessing.WriteToFile;
import ru.vorobev.parsing.ParsingArgumentsImpl;
import ru.vorobev.statistic.LineStatisticImpl;


public class FloatProcessingStrategy implements FileProcessingStrategy {
    WriteToFile writeToFile = new WriteToFile();

    @Override
    public void writeToFile(String line) {
        if (line.matches("-?\\d*\\.\\d+")) {
            writeToFile.writeToFile(line, ParsingArgumentsImpl.getFloatFullPathToFile());
            LineStatisticImpl.calculatingStats(Float.parseFloat(line));
        }
    }
}


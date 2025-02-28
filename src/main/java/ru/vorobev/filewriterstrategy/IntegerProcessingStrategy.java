package ru.vorobev.filewriterstrategy;

import ru.vorobev.fileprocessing.WriteToFile;
import ru.vorobev.parsing.ParsingArgumentsImpl;
import ru.vorobev.statistic.LineStatisticImpl;


public class IntegerProcessingStrategy implements FileProcessingStrategy {
    WriteToFile writeToFile = new WriteToFile();
    @Override
    public void writeToFile(String line) {
        if (line.matches("-?\\d+")) {
            writeToFile.writeToFile(line, ParsingArgumentsImpl.getIntFullPathToFile());
            LineStatisticImpl.calculatingStats(Integer.parseInt(line));
        }
    }
}
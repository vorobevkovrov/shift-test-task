package ru.vorobev;

import ru.vorobev.fileprocessing.FileProcessing;
import ru.vorobev.parsing.ParsingArgumentsImpl;
import ru.vorobev.statistic.LineStatistic;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> progArg = ParsingArgumentsImpl.programArgs(args);
        FileProcessing processor = new FileProcessing();
        LineStatistic stats = processor.writeToFiles(progArg);
        stats.printStatistic(ParsingArgumentsImpl.isBriefStats(), ParsingArgumentsImpl.isFullStats());
    }
}

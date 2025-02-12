package ru.vorobev;

import ru.vorobev.fileprocessing.FileProcessingImpl;
import ru.vorobev.parsing.ParsingArgumentsImpl;
import ru.vorobev.statistic.LineStatisticImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> progArg = ParsingArgumentsImpl.programArgs(args);
        FileProcessingImpl processor = new FileProcessingImpl();
        LineStatisticImpl stats = processor.writeToFiles(progArg);
        stats.printStatistic(ParsingArgumentsImpl.isBriefStats(), ParsingArgumentsImpl.isFullStats());
    }
}

package ru.vorobev;

import ru.vorobev.fileprocessing.FileProcessing;
import ru.vorobev.parsing.ParsingArgumentsImpl;
import ru.vorobev.statistic.LineStatistic;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ParsingArgumentsImpl parsingArguments = new ParsingArgumentsImpl();
        List<String> progArg = ParsingArgumentsImpl.programArgs(args);
        FileProcessing processor = new FileProcessing(parsingArguments.getIntFileName(),
                parsingArguments.getFloatFileName(), parsingArguments.getStringFileName(),
                parsingArguments.isAppendMode());
        LineStatistic stats = processor.writeToFiles(progArg);
        stats.printStatistic(ParsingArgumentsImpl.isBriefStats(), ParsingArgumentsImpl.isFullStats());
    }
}

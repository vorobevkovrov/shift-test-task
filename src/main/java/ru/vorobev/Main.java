package ru.vorobev;

import ru.vorobev.fileprocessing.FileProcessing;
import ru.vorobev.parsing.ParsingArgumentsImpl;
import ru.vorobev.statistic.LineStatistic;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
//        List<String> listArgs = List.of(args);
//        for (String s : listArgs) {
//            System.out.print(s + " ");
//        }
        ParsingArgumentsImpl parsingArguments = new ParsingArgumentsImpl();
        List<String> progArg = ParsingArgumentsImpl.programArgs(args);
        FileProcessing processor = new FileProcessing(parsingArguments.getIntFileName(),
                parsingArguments.getFloatFileName(), parsingArguments.getStringFileName(),
                parsingArguments.isAppendMode());
        LineStatistic stats = processor.writeToFiles(progArg);
        stats.printStatistic(parsingArguments.isFullStat(), parsingArguments.isBriefStat());
    }
}


//        // Example string to test
//        String testString = "/some/path/";
//
//        // Regular expression to match the pattern "/some/path/"
//        String regex = "^/[^/]+(/[^/]+)*/$"; // Matches any string that starts and ends with '/' and has content in between
//
//        // Compile the regex pattern
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(testString);
//
//        // Check if the string matches the pattern
//        if (matcher.matches()) {
//            System.out.println("The string matches the pattern: " + testString);
//        } else {
//            System.out.println("The string does not match the pattern: " + testString);
//        }

// Для запуска выполните
//mvn package

//java -jar target\shift-test-task-1.0.jar

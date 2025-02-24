package ru.vorobev.fileprocessing;

import lombok.extern.slf4j.Slf4j;
import ru.vorobev.parsing.ParsingArgumentsImpl;
import ru.vorobev.statistic.LineStatisticImpl;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

/**
 * Class for file processing
 *
 * @author maxim
 */
@Slf4j
public class FileProcessingImpl implements FileProcessing {
    LineStatisticImpl stats = new LineStatisticImpl();
    WriteToFile writeToFile = new WriteToFile();

    /**
     * Checks for files and distributes writing to files depending on the -a flag
     *
     * @param List of inputFiles
     * @return statistics on recorded lines
     */
    public LineStatisticImpl writeToFiles(List<String> inputFiles) {
        if (inputFiles.isEmpty()) {
            throw new RuntimeException("There are no files to process, please add files in working directory");
        }
        //TODO тоже так себе выглядит, три одинаковых строчки по сути. тоже стратегию можно впихнуть
        //if isAppendMode (-a) false
        if (!ParsingArgumentsImpl.isAppendMode()) {
            try {
                Files.deleteIfExists(ParsingArgumentsImpl.getStringFullPathToFile());
                Files.deleteIfExists(ParsingArgumentsImpl.getFloatFullPathToFile());
                Files.deleteIfExists(ParsingArgumentsImpl.getIntFullPathToFile());
            } catch (IOException e) {
                log.error("Could not to delete files {} ", e);

            }
            writeToFile(inputFiles);
        }
        //if isAppendMode (-a) true write to existing files
        if (ParsingArgumentsImpl.isAppendMode()) {
            writeToFile(inputFiles);
        }
        return stats;
    }

    /**
     * Write lines to files writes to files depending
     * on the passed path or its absence
     *
     * @param inputFiles list files to read
     */
    public void writeToFile(List<String> inputFiles) {
        Path path = Paths.get("");
        //TODO ну тут чтоб по красоте было надо на новые строчки вынести вызовы
        List<String> lines;
        if (ParsingArgumentsImpl.getOutputPath().isEmpty()) {
            for (String inputFile : inputFiles) {
                try {
                    lines = Files.readAllLines(Path.of((path.toAbsolutePath() + FileSystems.getDefault()
                            .getSeparator() + inputFile)));
                    for (String line : lines) {
                        //TODO тут у тебя одно и тоже почти во всех ветках происхродит, тут как раз можно не проверять
                        // какой тип линии тебе пришел, сразу писать в файл и передавать линию в калькулейтСТатс,
                        // а там уже определять че пришло и вот там уже работать с конкретным типом линии
                        // (инт, флоат, строка), и вот там уже хорошо и стратегия и все такое.

                        // Integer
                        if (line.matches("-?\\d+")) {
                            writeToFile.writeToFile(line, ParsingArgumentsImpl.getPath());
                            stats.calculatingStats(Integer.parseInt(line));
                            // Float
                        } else if (line.matches("-?\\d*\\.\\d+")) {
                            writeToFile.writeToFile(line, ParsingArgumentsImpl.getPath());
                            stats.calculatingStats(Double.parseDouble(line));
                            // String
                        } else {
                            writeToFile.writeToFile(line, ParsingArgumentsImpl.getPath());
                            stats.calculatingStats(line);
                        }
                    }
                } catch (IOException e) {
                    log.error("Error reading file{}", e);
                }
            }
            //TODO ну тут как будто у тебя дублирование кода с 59 и 80 строк. Как минимум вынести в отдельный приват метод.
            // Второе, if...else if...else if это прям оч плохо. Завтра у тебя добавиться еще какой-нибудь тип и
            // будешь опять переписывать. Подумай как тут паттерн Стратегия можно применить
            // (будет намного интереснее смотреться)
        } else {
            try {
                Files.createDirectories(ParsingArgumentsImpl.getPath());
            } catch (IOException e) {
                log.error("Path already exist {}", e);
            }
            for (String inputFile : inputFiles) {
                try {
                    lines = Files.readAllLines(Path.of((path.toAbsolutePath() + FileSystems.getDefault()
                            .getSeparator() + inputFile)));
                    for (String line : lines) {
                        // Integer
                        if (line.matches("-?\\d+")) {
                            writeToFile.writeToFile(line, ParsingArgumentsImpl.getIntFullPathToFile());
                            stats.calculatingStats(Integer.parseInt(line));
                            // Float
                        } else if (line.matches("-?\\d*\\.\\d+")) {
                            writeToFile.writeToFile(line, ParsingArgumentsImpl.getFloatFullPathToFile());
                            stats.calculatingStats(Double.parseDouble(line));
                            // String
                        } else {
                            writeToFile.writeToFile(line, ParsingArgumentsImpl.getStringFullPathToFile());
                            stats.calculatingStats(line);
                        }
                    }
                } catch (IOException e) {
                    log.error("Error reading incoming file: {}", inputFile);
                }
            }
        }
    }
}

package ru.vorobev.filewriterstrategy;


public interface FileProcessingStrategy {
    void writeToFile(String line);
}

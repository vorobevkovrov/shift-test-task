package ru.vorobev.fileprocessing;

import ru.vorobev.parsing.ParsingArgumentsImpl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;

//TODO при передаче пути записываемого файла падает с ошибкой
// java.io.FileNotFoundException: \some\path\sample-integers.txt (The system cannot find the path specified)
public class WriteToFile implements AutoCloseable {
    private final BufferedWriter intWriter;
    private final BufferedWriter floatWriter;
    private final BufferedWriter stringWriter;
    ParsingArgumentsImpl arguments = new ParsingArgumentsImpl();

    public WriteToFile(String intFileName, String floatFileName, String stringFileName, boolean appendMode) throws IOException {
        this.intWriter = new BufferedWriter(new FileWriter(intFileName, appendMode));
        this.floatWriter = new BufferedWriter(new FileWriter(floatFileName, appendMode));
        this.stringWriter = new BufferedWriter(new FileWriter(stringFileName, appendMode));
    }

    public void writeToFileWithPath(String value, Path path) {
        try {
            Files.write(path, value.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //  FileWriter fw = new FileWriter(new File(dir, str));
    public void writeInteger(String value) throws IOException {
        intWriter.write(value);
        intWriter.newLine();
    }

    public void writeFloat(String value) throws IOException {
        floatWriter.write(value);
        floatWriter.newLine();
    }

    public void writeString(String value) throws IOException {
        stringWriter.write(value);
        stringWriter.newLine();
    }

    @Override
    public void close() throws IOException {
        intWriter.close();
        floatWriter.close();
        stringWriter.close();
    }
}


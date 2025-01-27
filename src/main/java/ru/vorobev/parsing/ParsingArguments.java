package ru.vorobev.parsing;

import java.util.ArrayList;
import java.util.List;

public interface ParsingArguments {
    static List<String> programArgs(String[] args) {
        return new ArrayList<>();
    }
}

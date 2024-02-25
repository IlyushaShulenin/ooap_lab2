package ru.shulenin;

import ru.shulenin.pattern.AbstractFilesHandler;
import ru.shulenin.pattern.ConverterToUppercase;
import ru.shulenin.pattern.SearcherRepeatedWords;

import java.io.IOException;

public class Main {
    public static final String LINE = "file1.txt file2.txt file3.txt out.txt";


    public static void main(String[] args) throws IOException {

        AbstractFilesHandler filesHandler = new ConverterToUppercase();
        filesHandler.handle(LINE);


        AbstractFilesHandler filesHandler1 = new SearcherRepeatedWords();
        filesHandler1.handle(LINE);

    }
}

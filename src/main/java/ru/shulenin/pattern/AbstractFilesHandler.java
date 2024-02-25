package ru.shulenin.pattern;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractFilesHandler {

    public abstract void handle(String filesInLine) throws IOException;

    protected List<String> parseFileLine(String filesInLine, String prefix) {
        return new java.util.ArrayList<>(Arrays.stream(filesInLine.split(" "))
                .map(prefix::concat)
                .toList());
    }

    protected String getOutputFile(List<String> files) {
        int last = files.size() - 1;
        String outFile = files.get(last);
        files.remove(last);

        return outFile;
    }

    protected void printIOException(String filename) {
        System.out.println("File " + filename + " does not exist");
    }

}

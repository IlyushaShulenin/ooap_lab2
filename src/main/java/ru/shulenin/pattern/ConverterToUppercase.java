package ru.shulenin.pattern;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ConverterToUppercase extends AbstractFilesHandler {

    private static final String UPPERCASE_PREFIX = "src/main/resources/to-upper-case/";

    @Override
    public void handle(String filesInLine) throws IOException {
        List<String> files = parseFileLine(filesInLine, UPPERCASE_PREFIX);
        String outputFile = getOutputFile(files);

        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(outputFile))) {
            for (var inputFile : files) {
                try (BufferedReader reader = Files.newBufferedReader(Path.of(inputFile))) {

                    String str = null;

                    while ((str = reader.readLine()) != null) {
                        writer.write(str.toUpperCase());
                        writer.write(System.getProperty("line.separator"));
                    }

                } catch (IOException e) {
                    printIOException(inputFile);
                }
            }
        }
        catch (IOException e) {
            printIOException(outputFile);
        }
    }
}

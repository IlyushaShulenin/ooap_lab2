package ru.shulenin.pattern;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearcherRepeatedWords extends AbstractFilesHandler {

    private static final String UPPERCASE_PREFIX = "src/main/resources/find-repetitions/";
    private static final int FIRST = 0;

    @Override
    public void handle(String filesInLine) throws IOException {
        List<String> files = parseFileLine(filesInLine, UPPERCASE_PREFIX);
        String firstFile = getFirstFile(files);
        String outputFile = getOutputFile(files);

        Set<String> wordsInFirstFile = wordsInFile(firstFile);

        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(outputFile))) {
            for (var inputFile : files) {
                try (BufferedReader reader = Files.newBufferedReader(Path.of(inputFile))) {

                    String str = null;

                    while ((str = reader.readLine()) != null) {
                        String[] words = str.split(" ");
                        boolean repeatedWordIsFound = false;
                        int i = 0;

                        while (i < words.length && !repeatedWordIsFound) {
                            if (wordsInFirstFile.contains(words[i])) {
                                writer.write(inputFile);
                                writer.write(System.getProperty("line.separator"));
                                repeatedWordIsFound = true;
                            }
                            i++;
                        }
                        if (repeatedWordIsFound == true)
                            break;
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

    private String getFirstFile(List<String> files) {
        String first = files.get(FIRST);
        files.remove(FIRST);

        return first;
    }

    private Set<String> wordsInFile(String filename) {
        try (var reader = Files.newBufferedReader(Path.of(filename))) {
            String line = null;

            Set<String> wordsInFile = new HashSet<>();

            while ((line = reader.readLine()) != null) {
                List<String> words = Arrays.stream(line.split(" ")).toList();
                wordsInFile.addAll(words);
            }

            return wordsInFile;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 }

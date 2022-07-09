package simplefileparser.statistics;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TxtFileStatisticsProcessor extends AbstractFileStatisticsProcessor {
    @Override
    public List<String> getSupportedTypes() {
        return Arrays.asList("txt", "text");
    }

    @Override
    protected void extractStatistics(File file) throws IOException {
        //TODO: improve performance by doing it in only one pass
        wordOccurrences.putAll(Files.lines(file.toPath())
                .flatMap(line -> Stream.of(line.split(" ")))
                .map(word -> word.replaceAll("\\.", ""))
                .filter(word -> !word.isBlank())
                .collect(Collectors.groupingBy(word -> word, Collectors.counting())));
        dotOcurrences = Files.lines(file.toPath())
                .flatMap(line -> Stream.of(line.split("")))
                .filter(chr -> chr.equals("."))
                .count();
    }
}

package simplefileparser.statistics;

import simplefileparser.item.Item;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractFileStatisticsProcessor implements StatisticsProcessor<File> {

    HashMap<String, Long> wordOccurrences = new HashMap<>();

    Long dotOcurrences = 0l;

    @Override
    public void clear() {
        //clean any resource
        wordOccurrences = new HashMap<>();
        dotOcurrences = 0l;
    }

    @Override
    public Map<String, String> process(Item<File> item) {
        HashMap<String, String> map = new HashMap<>();

        File file = item.get();

        try {
            extractStatistics(file);
        } catch (IOException e) {
            e.printStackTrace(); //TODO: log the exception properly
            throw new RuntimeException(e); //TODO: handle the exception properly
        }
        map.put("number_of_words", getNumberOfWords());
        map.put("number_of_dots", getNumberOfDots());
        map.put("most_used_word", getMostUsedWord());
        return map;
    }

    private String getMostUsedWord() {
        return Collections.max(wordOccurrences.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey();
    }

    private String getNumberOfDots() {
        return dotOcurrences.toString();
    }

    private String getNumberOfWords() {
        return String.valueOf(wordOccurrences.entrySet().stream().mapToLong(Map.Entry::getValue).sum());
    }

    protected abstract void extractStatistics(File file) throws IOException;

}

package simplefileparser.statistics;

import simplefileparser.item.Item;

import java.util.List;
import java.util.Map;

public interface StatisticsProcessor<T> {

    Map<String, String> process(Item<T> item);

    List<String> getSupportedTypes();

    void clear();
}

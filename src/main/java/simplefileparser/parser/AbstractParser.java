package simplefileparser.parser;

import simplefileparser.channel.MonitorableChannel;
import simplefileparser.item.Item;
import simplefileparser.statistics.StatisticsProcessor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractParser<T> {

    protected MonitorableChannel channel;

    private List<StatisticsProcessor> statisticsProcessors;


    public AbstractParser(MonitorableChannel channel, StatisticsProcessor... statisticsProcessors) {
        this.channel = channel;
        this.statisticsProcessors = Arrays.asList(statisticsProcessors);
    }

    public void start() throws InterruptedException {
        System.out.println("Starting...");
        while(true) {
            System.out.println("Polling...");
            List<Item> polledItems = channel.poll();
            System.out.println(String.format("Polled %s files", polledItems.size()));
            polledItems.forEach(this::parse);
            Thread.sleep(60000);
        }
    }

    public void parse(Item item) {
        Map<String, String> statistics = new HashMap<>();
        statisticsProcessors.stream()
                .filter(p -> p.getSupportedTypes().contains(item.getType()))
                .forEach(statisticsProcessor -> {
                    statisticsProcessor.clear();
                    statistics.putAll(statisticsProcessor.process(item));
                });
        outputStatistics(item, statistics);
        moveToProcessed(item);
    }

    public abstract void moveToProcessed(Item<T> item);

    public void outputStatistics(Item<T> item, Map<String, String> statistics) {
        System.out.println(String.format("STATISTICS OF PROCESSED ITEM: %s", item.getName()));
        statistics.forEach((key, value) -> System.out.println(String.format("%s : %s", key, value)));
    }
}

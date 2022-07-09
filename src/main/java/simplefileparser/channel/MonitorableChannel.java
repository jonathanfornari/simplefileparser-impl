package simplefileparser.channel;

import simplefileparser.item.Item;

import java.util.List;

public abstract class MonitorableChannel<T> {

    private String name;
    public abstract List<Item> poll();

    protected abstract boolean createProcessedStructure();

    public abstract T getProcessedStructure();
}

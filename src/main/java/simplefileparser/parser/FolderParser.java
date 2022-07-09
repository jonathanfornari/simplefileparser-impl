package simplefileparser.parser;

import simplefileparser.channel.MonitorableFolder;
import simplefileparser.item.Item;
import simplefileparser.statistics.StatisticsProcessor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FolderParser extends AbstractParser<File> {

    public FolderParser(MonitorableFolder folder, StatisticsProcessor... statisticsProcessors) {
        super(folder, statisticsProcessors);
    }

    @Override
    public void moveToProcessed(Item<File> item) {
        try {
            Files.move(item.get().toPath(),
                    Paths.get(((MonitorableFolder) channel).getProcessedStructure().toString(), item.getName()),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace(); //TODO: log the exception properly
            throw new RuntimeException(e); //TODO: handle the exception properly
        }
    }
}

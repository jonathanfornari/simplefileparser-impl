package simplefileparser.channel;

import simplefileparser.item.FileItem;
import simplefileparser.item.Item;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MonitorableFolder extends MonitorableChannel<File>  {

    private String path;

    private String processedSubPath = "processed";

    public MonitorableFolder(String path) {
        this.path = path;
    }

    @Override
    public List<Item> poll() {
        List<Item> items = new ArrayList<>();
        File folder = new File(path);
        File[] files = folder.listFiles();
        for(File file : files) {
            if(file.isFile()) {
                items.add(new FileItem(file));
            }
        }
        return items;
    }

    @Override
    protected boolean createProcessedStructure() {
        try {
            Files.createDirectories(Paths.get(path,processedSubPath));
            return true;
        } catch (IOException e) {
            e.printStackTrace(); //TODO: log the exception properly
            return false;
        }
    }

    /**
     *
     * gets the processed structure (folder in this case) where processed files should go.
     * Since the implementation of create processed structure doesn't have issues if the folder already exists, this is always called here before returning it.
     *
     * @return
     */
    @Override
    public File getProcessedStructure() {
        createProcessedStructure();
        return Paths.get(path,processedSubPath).toFile();
    }
}

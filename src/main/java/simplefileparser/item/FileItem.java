package simplefileparser.item;

import java.io.File;

public class FileItem  extends Item<File>{

    public FileItem(File item) {
        super(item);
    }

    @Override
    public String getName() {
        return item.getName();
    }
    @Override
    public String getType() {
        return getName().substring(getName().lastIndexOf(".") + 1);
    }
}

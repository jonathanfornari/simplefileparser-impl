package simplefileparser.item;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileItemTest {

    @Test
    public void testShouldGetTxtTypeTest() {
        FileItem item = new FileItem(new File("test.txt"));
        assertEquals("txt", item.getType());
    }

    @Test
    public void testShouldGetCsvType() {
        FileItem item = new FileItem(new File("test.csv"));
        assertEquals("csv", item.getType());
    }

    @Test
    public void testShouldGetTypeWithoutExtension() {
        FileItem item = new FileItem(new File("test"));
        assertEquals("test", item.getType());
    }
}

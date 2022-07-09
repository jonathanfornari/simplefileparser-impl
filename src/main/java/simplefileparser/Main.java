package simplefileparser;

import simplefileparser.channel.MonitorableFolder;
import simplefileparser.parser.FolderParser;
import simplefileparser.statistics.TxtFileStatisticsProcessor;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        String path = args.length > 0 ? args[0] : null;
        if(path == null) {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter the folder to poll/monitor (i.e /home/user/testapp): ");
            path = in.nextLine();
        }
        FolderParser parser = new FolderParser(new MonitorableFolder(path), new TxtFileStatisticsProcessor());
        parser.start();
    }

}

package arcs.storage;

import arcs.data.Route;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RouteFileManager {
    private static String DIR_PATH = "data";
    private static final String fileName = "route.txt";

    public RouteFileManager(){
        DIR_PATH = System.getProperty("user.dir") + File.separator + "data";
    }

    public ArrayList<Route> loadData() throws IOException {
        File dir = new File(DIR_PATH);
        if(!dir.exists()) {
            dir.mkdir();
        }
        File file = new File(DIR_PATH + File.separator + fileName);
        if(!file.exists()) {
            file.createNewFile();
        }
        Scanner s = new Scanner(file);
        ArrayList<String> records = new ArrayList<>();
        while(s.hasNextLine()) {
            records.add(s.nextLine());
        }

        return decodeData(records);
    }

    public void saveData(ArrayList<Route> routes) throws IOException{
        FileWriter fw = new FileWriter(DIR_PATH + File.separator + fileName);
        ArrayList<String> records = encodeData(routes);
        for(int i = 0; i < records.size(); i++){
            fw.write(records.get(i));
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    private ArrayList<Route> decodeData(ArrayList<String> records) {
        ArrayList<Route> routes = new ArrayList<>();

        for (String record: records) {
            String[] data = record.split("/");
            if (data.length != 6) {
                continue;
            }
            routes.add(new Route(data[0], data[1], data[2], data[3], data[4], Integer.parseInt(data[5])));
        }

        return routes;
    }


    private ArrayList<String> encodeData(ArrayList<Route> routes) {
        ArrayList<String> records = new ArrayList<>();
        for (Route route: routes) {
            records.add(route.toString());
        }

        return records;
    }


}

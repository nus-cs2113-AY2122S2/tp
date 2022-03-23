package tp;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String root = System.getProperty("user.dir");
    private static final Path filePath = Paths.get(root, "data", "duke.txt");
    private static final Path dirPath = Paths.get(root, "data");

    public Storage() throws IHospitalException {
        try {
            File fileDirectory = new File(dirPath.toString());
            if (!fileDirectory.exists()) {
                fileDirectory.mkdir();
            }

            File dataFile = new File(filePath.toString());
            dataFile.createNewFile();
        } catch (IOException e) {
            throw new IHospitalException("File ERROR");
        }
    }



    /**
     * writes the items in the list to the file
     * @param list
     */
    public void saveFile(ArrayList<ArrayList> list){
        String text = "";
        for (int i = 0; i < list.size(); i++){
            for (int j = 0; j < list.get(i).size(); j++){
                text = text + list.get(i).get(j).toString() + System.lineSeparator();
            }
            try{
                FileWriter fw = new FileWriter(String.valueOf(filePath));
                fw.write(text);
                fw.close();
            } catch (IOException e){
                System.out.println(e.getMessage());
            }

        }

    }

    public ArrayList<ArrayList> loadFile(){
        ArrayList<ArrayList> list = new ArrayList<>();
        File f = new File(String.valueOf(filePath));
        Scanner s = new Scanner(f);
    }
//    public File loadSavedFile(){
//        File f = new File(filePath);
//        return f;
//    }

    /*public ArrayList<ArrayList> convertFileToList(){
        ArrayList<ArrayList> list = new ArrayList<>();
        File f = new File(filePath);
        try{
            Scanner s = new Scanner(f);
            while (s.hasNext()){
                String currentLine = s.nextLine();
                String[] arrayElements = currentLine.split("\\|");
                String listType = arrayElements[0].trim();
                ArrayList<ArrayList> temp;
                switch (listType){
                case "doctor":
                    temp = convertDoctor(arrayElements);
                    list.add(temp);
                    break;
                case "patient":
                    temp = convertPatient(arrayElements);
                    list.add(temp);
                    break;
                case "appointment":
                    temp = convertAppointment(arrayElements);
                    list.add(temp);
                    break;
                default:
                    break;
                }
            }
            return list;
        } catch (FileNotFoundException e){
            return list;
        }
    }

    private Doctor convertDoctor(String[] arrayElements){
        return new Doctor(getDescription(arrayElements))
    }

*/

}
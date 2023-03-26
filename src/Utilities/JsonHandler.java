package Utilities;

import Model.DTO_Drinks;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonHandler {
    public DTO_Drinks loadJsonFile() {
        //We need load the Json to process data
        Gson gson = new Gson();//Calling  Library "Gson" to load Json file
        DTO_Drinks dto_drinks = new DTO_Drinks();
        try {
            String path = "json/C2H5OH.json"; //Path of Json file
            FileReader fileReader = new FileReader(path); //Load the file to fileReader Object
            JsonReader jsonReader = new JsonReader(fileReader); //JsonReader receives the file
            dto_drinks = gson.fromJson(jsonReader, DTO_Drinks.class);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return dto_drinks;
    }

}

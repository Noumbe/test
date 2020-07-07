import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;


public class JsonParserAssur {



    public static  Assure jsonFileToAssure(String filename) throws NoSuchMethodException {

        try {

            String jsonString = DiskFile.loadFileIntoString(filename);
            JSONObject objet = (JSONObject) JSONSerializer.toJSON(jsonString);



            String nom = (String) objet.get("nom");
            int genre = (int) objet.get("genre");
            int age = (int) objet.get("age");
            JSONObject fumeur = (JSONObject) objet.get("fumeur");
            boolean alcool = (boolean) objet.get("alcool");


            System.out.println("Nom :" + nom);
            System.out.println("Genre: " + genre);
            System.out.println("Age: " + age);
            System.out.println("Alcool: " + alcool);
            Assure assureItem = new Assure(nom, genre, age, fumeur, alcool);
            return assureItem;


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ArithmeticException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;


 public class JsonParserAssur {
    public static void jsonToAssur (ArrayList<Antecedents> antecedents, JSONArray data){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date dateDepuis = null;
        String StringDate = null;
        for (int i = 0 ; i < data.size() ; i++) {
            JSONObject antecedent = data.getJSONObject(i);
            StringDate = antecedent.getString("depuis");
            try {
            dateDepuis = formatter.parse(StringDate);
            } catch (ParseException e) {
                    e.printStackTrace();
                }

            antecedents.add((new Antecedents(
                    antecedent.getString("diagnostic"),
                    dateDepuis
            )));
        }
    }



    public static  Assure jsonFileToAssure(String filename) throws NoSuchMethodException {

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            String jsonString = DiskFile.loadFileIntoString(filename);
            JSONObject objet = (JSONObject) JSONSerializer.toJSON(jsonString);
            String nom = (String) objet.get("nom");
            int genre = (int) objet.get("genre");
            Date date_de_naissance = null;
            Date dateEmbauche = null;
            String date_de_naissanceStr =  (String) objet.get("date_de_naissance");
            String dateEmbaucheStr = (String)objet.get("date_embauche");

            try {
            date_de_naissance = formatter.parse(date_de_naissanceStr);
            } catch (ParseException e) {
                System.out.printf("Format de Date Invalide: %s %n", date_de_naissanceStr);
                e.printStackTrace();
            }
            try {
                dateEmbauche = formatter.parse(dateEmbaucheStr);
            } catch (ParseException e) {
                System.out.printf("Format de Date Invalide: %s %n", dateEmbaucheStr);
                e.printStackTrace();
            }


            JSONObject fumeur = (JSONObject) objet.get("fumeur");
            boolean alcool = (boolean) objet.get("alcool");
            JSONArray antecedent = (JSONArray)objet.get("antecedents");
            JSONArray sport = (JSONArray)objet.get("sports");

            String employeur = (String)objet.get("employeur");
            String numContrat = (String)objet.get("numero_contrat");
            int part_employeur = (int)objet.get("part_employeur");
            String lieuTravail = (String)objet.get("lieu_travail");


            Assure assureItem = new Assure(nom, genre, date_de_naissance, fumeur, alcool, antecedent, sport,
                    employeur, numContrat, dateEmbauche, part_employeur, lieuTravail);
            return assureItem;


        } catch (FileNotFoundException e) {
            System.out.println("Le nom du frichier est introuvable") ;
        } catch (NullPointerException e) {
            System.out.println("Vous essayez d'appeler une methode sur un objet null") ;
        } catch (ArithmeticException e) {
            System.out.println("Il y a une erreur de nombres") ;
        } catch (IOException e) {
            System.out.println("Vous avez soulevé une exception") ;
        } catch (Exception e) {
            System.out.println("Vous avez soulevé une exception") ;
        }
        return null;
    }
}
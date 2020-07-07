import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.IllegalFormatConversionException;

public class AssureStats {
    public static void main (String [] args){

        String filename_Path_In = null;
        String filename_Path_Out = null;
        if (args.length != 2) {
            System.out.printf ("Erreur. Nombre d'arguments invalide.");
            return ;
        }

        filename_Path_In = args[0];

        String Newligne = System.getProperty("line.separator");
        String bEligible = null;
        String jsonString =   "{" + Newligne;
        jsonString += "      \"eligible\":";

        try {
            Assure AssureItem = JsonParserAssur.jsonFileToAssure(filename_Path_In);
            if (AssureItem.IsEligible() == true) {
                bEligible = " true";
            } else
                bEligible = " false";
           // System.out.printf("Nom: %s%nGenre: %d%nAge: %b%nFumeur Tabac: %b%nFumeur Canabis: %b%nAlcool: %b%n", (String) Assure.getNom(), Assure.getGenre(),Assure.getAge(), Assure.getFumeur().get("tabac"), Assure.getFumeur().get("cannabis"), Assure.getAlcool());

            jsonString += bEligible;
            jsonString += Newligne;
            jsonString += "}";

            // Ecrire dans le fichier d'output

            filename_Path_Out = args[1];

            DiskFile.saveStringIntoFile(filename_Path_Out, jsonString);

            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalFormatConversionException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
        }
    }
}
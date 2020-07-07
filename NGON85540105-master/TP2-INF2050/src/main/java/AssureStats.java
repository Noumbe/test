import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.IllegalFormatConversionException;

/***
 * Cette classe continent la methode main. Elle va appeler les autres classes et
 * effectuer le travail demandé. C'est dans cette classe que nous allons verifier
 * l'eligibilite, le nombre d'arguments et utiliser les fichiers d'entree et de
 * sortie.
 */

public class AssureStats {
    public static void main (String [] args){
        String filename_Path_In = null;
        String filename_Path_Out = null;
        String Newligne = System.getProperty("line.separator");
        String bEligible = null;
        String jsonString =   "{" + Newligne;

        if (args.length != 2) {
            System.out.printf ("Erreur. Nombre d'arguments invalide.");
            return ;
        }
        filename_Path_In = args[0] ;
        jsonString += "      \"eligible\":";

        try {
            Assure AssureItem = JsonParserAssur.jsonFileToAssure(filename_Path_In);
            if (AssureItem == null)
                    return;
                if (AssureItem.IsEligible() == true) {
                    // calculer le montant de l'assurance
                    int Age = AssureItem.getAge(AssureItem.getDate_Naissance());
                    AssureItem.ObtenirMontantAssurance(Age);
                    bEligible = " true" + ",";
                    jsonString += bEligible;
                    jsonString += Newligne;
                    jsonString += "      \"montant_annuel\": ";
                    jsonString += AssureItem.getMontantAssAnnuel();
                    jsonString += Newligne;
                    jsonString += "}";
                } else {
                    bEligible = " false";
                    jsonString += bEligible;
                    jsonString += Newligne;
                    jsonString += "}";
                }

            // Ecrire dans le fichier d'output
            filename_Path_Out = args[1];
            DiskFile.saveStringIntoFile(filename_Path_Out, jsonString);

            } catch (NoSuchMethodException e) {
                System.out.println("La methode que vous essayez d'appeler est inexistante") ;
            } catch (IllegalFormatConversionException e) {
                System.out.println("Le fromat est incorrect") ;
            } catch (IllegalArgumentException e) {
                System.out.println("L'argument est inccorrect") ;
            } catch (FileNotFoundException e) {
                System.out.println("Le fichier est introuvable") ;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Vous êtes au delà des limites") ;
            } catch (NullPointerException e) {
                System.out.println("Vous essayez d'appeler une methode sur un objet null") ;
            } catch (IOException e) {
                System.out.println("Vous avez soulevé une exception") ;
            } catch (Exception e) {
                System.out.println("Vous avez soulevé une exception") ;
        }
    }
}

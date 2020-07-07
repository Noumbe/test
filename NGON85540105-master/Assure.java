import net.sf.json.JSONObject;

public class Assure {
    private static String nom ;
    private static int genre ;
    private static int age ;
    private static JSONObject fumeur;
    private static boolean alcool ;

    public static String getNom() {
        return nom;
    }

    public static int getGenre() {
        return genre;
    }

    public static int getAge() {
        return age;
    }

    public static JSONObject getFumeur() {
        return fumeur;
    }

    public static boolean getAlcool() {
        return alcool;
    }


    public Assure ( String nom, int genre, int age, JSONObject fumeur, boolean alcool) {
        this.nom = nom;
        this.genre = genre;
        this.age = age;
        this.fumeur = fumeur;
        this.alcool = alcool ;

    }

    public boolean IsEligible(){
        try {
            // Vérifier l'age
            if (getAge() < 18) {
                return false;
            }

            // Vérifier le genre
            if (getGenre() != 0 && getGenre() != 1 &&
                    getGenre() != 2 && getGenre() != 9) {
                System.out.println("Erreur. Veuillez verifier votre fichier json et " +
                        "entrer une valeur de genre valide. (norme ISO 5218)");
                return false;

            } else {
                // Verifier que les conditions sont vérifiées
                if (getAge() > 85 && getGenre() == 2) {
                    return false;
                } else if (getAge() > 80 && getGenre() == 1) {
                    return false;
                }
            }

            // verifier si fumeur
            if ((boolean)getFumeur().get("tabac") == true && (boolean)getFumeur().get("cannabis") == true) {
                System.out.println("Fumeur de Tabac et de Cannabis");
                return false;
            }


        } catch (ArithmeticException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}

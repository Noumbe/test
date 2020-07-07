import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Date;

public class Assure {
    private static String nom ;
    private static int genre ;
    private static Date  Date_Naissance ;
    private static JSONObject fumeur;
    private static boolean alcool ;
    private static JSONArray antecedents;
    private static JSONArray sports;
    public static int MontantAss = 0;
    public static String employeur ;
    public static String numContrat ;
    public static Date dateEmbauche ;
    public static int partEmployeur ;
    public static String lieuTravail ;




    public static int getGenre() {
        return genre;
    }

    public static Date getDate_Naissance() {
        return Date_Naissance;
    }

    public static JSONObject getFumeur() {
        return fumeur;
    }

    public static int getMontantAssAnnuel() {
        return MontantAss;
    }

    public static int getPartEmployeur() {
        return MontantAss * (partEmployeur/100) ;
    }

    public static int getPartEmploye() {
        return MontantAss - getPartEmployeur() ;
    }



  // Le constructeur de la classe Assure
    public Assure (String nom, int genre, Date Date_Naissance, JSONObject fumeur,
                   boolean alcool, JSONArray antecedents, JSONArray sports, String employeur,
                   String numContrat, Date dateEmbauche, int partEmployeur, String lieuTravail) {
        this.nom = nom;
        this.genre = genre;
        this.Date_Naissance = Date_Naissance;
        this.fumeur = fumeur;
        this.alcool = alcool ;
        this.antecedents = antecedents;
        this.sports = sports;
        this.employeur = employeur ;
        this.numContrat = numContrat ;
        this.dateEmbauche = dateEmbauche ;
        this.partEmployeur = partEmployeur;
        this.lieuTravail = lieuTravail ;



    }

    // Verifier si la personne est eligible à l'assurance
        // Obtenir l'age à partir de la date de naissance.
        public static int getAge(Date dateOfBirth) {

            Calendar today = Calendar.getInstance();
            Calendar birthDate = Calendar.getInstance();

            int age = 0;

            birthDate.setTime(dateOfBirth);
            if (birthDate.after(today)) {
                throw new IllegalArgumentException("Peut pas être né(e) dans le futur");
            }
            age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
            return age;
        }

    public boolean IsEligible() {

        // Validation du nom de l'employeur
        if (employeur.length() < 5 || employeur.length() > 50) {
            return false;
        }

        // Validation du pourcentage
        if (partEmployeur < 0 || partEmployeur > 100) {
            return false ;
        }

        // Validation du numero de contrat
            if (numContrat.length() != 5) {
                System.out.println("erreur de numero de contrat");
                return false;
            }
            if (numContrat.charAt(1) != 'a' || numContrat.charAt(1) != 'A'
                    || numContrat.charAt(1) != 'h' || numContrat.charAt(1) != 'H'
                    || numContrat.charAt(1) != 'r' || numContrat.charAt(1) != 'R') {
                return false;
            }
            if (numContrat.charAt(2) < 1 || numContrat.charAt(2) > 7){
                return false;
            }




            int age = getAge(Date_Naissance);
            // Vérifier l'age
            if (age < 18) {
                return false ;
            }


            // Vérifier le genre
              if (getGenre() != 0 && getGenre() != 1 &&
                        getGenre() != 2 && getGenre() != 9) {
                    System.out.println("Erreur. Veuillez verifier votre fichier json et " +
                            "entrer une valeur de genre valide. (norme ISO 5218)");
                    return false;

                } else {
                    // Verifier que les conditions sont vérifiées
                    if (age > 85 && getGenre() == 2) {
                        return false;
                    } else if (age > 80 && getGenre() == 1) {
                        return false;
                    }
                }

            // verifier si fumeur ou pas
            if ((boolean)getFumeur().get("tabac") == true && (boolean)getFumeur().get("cannabis") == true) {

                System.out.println("Fumeur de Tabac et de Cannabis");
                return false;
            }
            if (getGenre()== 0 || getGenre()==9){
                if ( 18 >= age || age <= 85) {
                    return true;
                }
                else {
                    return false;
                }
            }

            // Les conditions pour augmenter le prix de l'assurance
            // Parcourir le JSONArray sports et verifier s'il y a Escalade ou parachute ou Bungee dans la liste
                for (int value = 0; value < sports.size(); value++) {
                    String Onesport = sports.getString(value);
                    if (Onesport.contains("Bungee") || Onesport.contains("Saut en parachute") ||
                            Onesport.contains("Escalade")) {
                    return false;
                    }
                }

             // Verifier si la ville est bonne
        if (lieuTravail == "Havre-Saint-Pierre" || lieuTravail == "Chibougamau" || lieuTravail == "Carignan"
                || lieuTravail == "Entrelacs" || lieuTravail == "Fassett") {
            return false;
        }

        /***
         * Ici est ce que tu peux me faire une validation et retourner false si la personne
         * travaille avec son employeur depuis moins de 3 mois.
         */

        return true;
    }


    // Calculer le montant de l'assurance à payer
        public void calculerMontantAssuranceHomme(int age) {
            if (getGenre()==1) {
                if (18 >= age || age <= 29) {
                    MontantAss = 150;
                } else if (30 >= age || age <= 40) {
                    MontantAss = 165;
                } else if (41 >= age || age <= 59) {
                    MontantAss = 200;
                } else if (60 >= age || age <= 73) {
                    MontantAss = 350;
                } else if (74 >= age || age <= 85) {
                    MontantAss = 700;
                }
            }
        }

    public void calculerMontantAssuranceFemme(int age) {
        if (getGenre() == 0 || getGenre() == 2 || getGenre() == 9) {
            if (18 >= age || age <= 29) {
                MontantAss = 100;
            } else if (30 >= age || age <= 40) {
                MontantAss = 140;
            } else if (41 >= age || age <= 59) {
                MontantAss = 155;
            } else if (60 >= age || age <= 73) {
                MontantAss = 250;
            } else if (74 >= age || age <= 85) {
                MontantAss = 600;
            }
        }
    }
    public void estFumeur () {
        if ((boolean) getFumeur().get("tabac") == true || (boolean) getFumeur().get("cannabis") == true)
            MontantAss = MontantAss + 100;
    }
        public void estBuveur () {
            if (alcool == true)
                MontantAss = MontantAss + (5 * MontantAss) / 100;
        }
        public void aPlusDeDeuxAntecedents () {
            if (antecedents.size() > 2)
                MontantAss = MontantAss + ((15 * MontantAss) / 100);

        }
        public void pratiqueAucunSport () {
        if (sports.size() == 0)
            MontantAss = MontantAss + 25;
        }

        public void estCancerique () {
        if (antecedents != null) {
            for (int value = 0; value < antecedents.size(); value++) {

                JSONObject antecedent = antecedents.getJSONObject(value);
                String OnDiagnostic = antecedent.getString("diagnostic");
                if (OnDiagnostic.startsWith("Cancer")) {
                    MontantAss = MontantAss + MontantAss / 2;
                }
            }
        } else
            return;
    }
        public void ObtenirMontantAssurance (int age) {
        calculerMontantAssuranceHomme(age);
        calculerMontantAssuranceFemme(age);
        estFumeur();
        estBuveur();
        estCancerique();
        aPlusDeDeuxAntecedents();
        pratiqueAucunSport();
        }
}



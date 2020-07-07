import java.util.Date;

public class Antecedents {
    public static String diagnostic ;
    public static Date depuis ;

    public static String getDiagnostic() {
        return diagnostic;
    }

    public static Date getDepuis() {
        return depuis;
    }

    public Antecedents (String diagnostic, Date depuis){
        this.diagnostic = diagnostic;
        this.depuis = depuis;
    }
}

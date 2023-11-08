import java.util.ArrayList;

public class Controller {
   private Database database;

   public Controller(Database database) {
       this.database = database;
   }

    public ArrayList<Superhelt> hentAlleSuperhelte() {
        return database.getAllSuperhelte();
    }

    public boolean validerJaNejInput(String input) {
        return input.equalsIgnoreCase("ja") || input.equalsIgnoreCase("nej");
    }

    public boolean eksistererSuperhelt(String navn) {
        // Brug af Database-klassen til at kontrollere, om superhelten findes
        return database.harSuperhelt(navn);
    }

    public void editSuperhero(String nytNavn, String nySuperkraft, Boolean nyErMenneske, int nytOprettelsesår, int nyStyrke) {
        // Fortæller database klassen at den skal opdatere en superhelt.
        boolean opdateret = database.editSuperhero(nytNavn, nySuperkraft, nyErMenneske, nytOprettelsesår, nyStyrke);

        if (opdateret) {
            System.out.println("Superhelten er blevet redigeret.");
        } else {
            System.out.println("Ingen superhelt med det angivne navn blev fundet.");
        }
    }

}

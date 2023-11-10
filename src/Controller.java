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

    public boolean superheroExists(String navn) {
        // Brug af Database-klassen til at kontrollere, om superhelten findes
        return database.harSuperhelt(navn);
    }

    public void addSuperHeroToDatabase(Superhelt superhelt) {
       database.addSuperhelt(superhelt);
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

    public void saveSuperheroesIfNeeded() {
            database.saveSuperheroes();
    }

    public void initDatabase() {
        database.loadSuperheroes();
    }

    public ArrayList<Superhelt> søgSuperhelte(String søgeord) {
        // Logikken for at søge i databasen og returnere resultaterne
        return database.søgSuperhelte(søgeord);
    }

    public boolean deleteSuperhero(String navn) {
        // Kalder slet-metoden i Database-klassen og returnerer resultatet.
        return database.sletSuperhelt(navn);
    }

}

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

}

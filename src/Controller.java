import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Controller {
   private final Database database;

   public Controller(Database database) {
       this.database = database;
   }

    public ArrayList<Superhelt> getAllSuperheroes() {
        return database.getAllSuperhelte();
    }

    public boolean validerJaNejInput(String input) {
        return input.equalsIgnoreCase("ja") || input.equalsIgnoreCase("nej");
    }

    public boolean superheroExists(String navn) {
        // Brug af Database-klassen til at kontrollere, om superhelten findes
        return database.haveSuperhero(navn);
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
        try {
            database.saveSuperheroes();
        } catch (IOException e) {
            System.out.println("Fejl ved gemning af superhelte: " + e.getMessage());
        }
    }

    public void initDatabase() {
        try {
            database.loadSuperheroes();
        } catch (Exception e) {
            System.out.println("Fejl ved indlæsning af superhelte: " + e.getMessage());
        }
    }


    public ArrayList<Superhelt> searchSuperhero(String searchword) {
        // Logikken for at søge i databasen og returnere resultaterne
        return database.searchSuperhero(searchword);
    }

    public boolean deleteSuperhero(String navn) {
        // Kalder slet-metoden i Database-klassen og returnerer resultatet.
        return database.deleteSuperhero(navn);
    }

    public List<Superhelt> getSuperheroesSorted(String primaryAttribute, String secondaryAttribute) {
        Comparator<Superhelt> primaryComparator = SuperHeltComparator.getComparatorBasedOnAttribute(primaryAttribute);
        Comparator<Superhelt> secondaryComparator = SuperHeltComparator.getComparatorBasedOnAttribute(secondaryAttribute);

        SuperHeltComparator combinedComparator = new SuperHeltComparator(primaryComparator, secondaryComparator);

        ArrayList<Superhelt> sortedList = new ArrayList<>(database.getAllSuperhelte());
        sortedList.sort(combinedComparator);
        return sortedList;
    }

}

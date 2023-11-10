import java.io.*;
import java.util.ArrayList;

public class Database {
        private boolean isDirty = false;
        private ArrayList<Superhelt> superhelte;
        private final String FILENAME = "superheroes.ser";

    public Database() {
        superhelte = new ArrayList<>();
        loadSuperheroes();
    }

    public ArrayList<Superhelt> getAllSuperhelte() {
        return superhelte;
    }

    public ArrayList<Superhelt> søgSuperhelte(String søgeord) {
        ArrayList<Superhelt> matchendeSuperhelte = new ArrayList<>();

        for (Superhelt superhelt : superhelte) {
            if (superhelt.getNavn().toLowerCase().contains(søgeord.toLowerCase())) {
                matchendeSuperhelte.add(superhelt);
            }
        }

        return matchendeSuperhelte;
    }

    public boolean editSuperhero(String nytNavn, String nySuperkraft, Boolean nyErMenneske, int nytOprettelsesår, int nyStyrke) {
        for (Superhelt superhelt : superhelte) {
            if (superhelt.getNavn().equalsIgnoreCase(nytNavn)) {
                // Find superhelten med det matchende navn og rediger oplysningerne
                superhelt.setNavn(nytNavn);
                superhelt.setSuperkraft(nySuperkraft);
                superhelt.setErMenneske(nyErMenneske);
                superhelt.setOprettelsesår(nytOprettelsesår);
                superhelt.setStyrke(nyStyrke);
                // Du kan tilføje flere redigeringsoperationer efter behov

                return true;
            }
        }
        System.out.println("Ingen superhelt med det angivne navn blev fundet.");
        return false;
    }

    public boolean harSuperhelt(String navn) {
        for (Superhelt superhelt : superhelte) {
            if (superhelt.getNavn().equalsIgnoreCase(navn)) {
                return true;
            }
        }
        return false;
    }

    // tilføjer og tjekker efter Dirty flag
    public void addSuperhelt(Superhelt superhelt) {
        superhelte.add(superhelt);
        isDirty = true;
    }

    // En metode til at indlæse data
    public void loadSuperheroes() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            superhelte = (ArrayList<Superhelt>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Kunne ikke indlæse superhelte fra filen: " + e.getMessage());
            superhelte = new ArrayList<>(); // Opret en ny liste, hvis indlæsning mislykkes
        }
    }

    public void saveSuperheroes() {
        if (!isDirty) {
            return; // Ingen grund til at gemme, hvis der ikke er lavet ændringer
        }

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            outputStream.writeObject(superhelte);
            isDirty = false; // Nulstil dirty flag efter gemning
            System.out.println("Superhelte er gemt til filen 'superheroes.txt'.");
        } catch (IOException e) {
            System.out.println("Fejl ved gemning af superhelte til filen.");
        }
    }

    public boolean sletSuperhelt(String navn) {
        return superhelte.removeIf(superhelt -> superhelt.getNavn().equalsIgnoreCase(navn));
    }

}


import java.io.*;
import java.util.ArrayList;

public class Database {
        private boolean isDirty = false;
        private ArrayList<Superhelt> superhelte;
        private final String FILENAME = "superheroes.ser";

    public Database() {
        superhelte = new ArrayList<>();
        try {
            loadSuperheroes();
        } catch (Exception e) {
            System.out.println("Fejl under indlæsning af superhelte: " + e.getMessage());
            superhelte = new ArrayList<>(); // Opret en ny liste, hvis indlæsning mislykkes
        }
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
                // Finder superhelten med det matchende navn og redigerer oplysningerne
                superhelt.setNavn(nytNavn);
                superhelt.setSuperkraft(nySuperkraft);
                superhelt.setErMenneske(nyErMenneske);
                superhelt.setOprettelsesår(nytOprettelsesår);
                superhelt.setStyrke(nyStyrke);
                return true;
            }
        }
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
    public void loadSuperheroes() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            superhelte = (ArrayList<Superhelt>) ois.readObject();
        }
    }

    public void saveSuperheroes() throws IOException {
        if (!isDirty) {
            return; // Ingen grund til at gemme, hvis der ikke er lavet ændringer
        }

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            outputStream.writeObject(superhelte);
            isDirty = false; // Nulstil dirty flag efter gemning
        }
    }

    public boolean sletSuperhelt(String navn) {
        return superhelte.removeIf(superhelt -> superhelt.getNavn().equalsIgnoreCase(navn));
    }

}


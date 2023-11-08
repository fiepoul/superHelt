import java.util.ArrayList;

public class Database {
        private ArrayList<Superhelt> superhelte;

    public Database() {
        this.superhelte = new ArrayList<>();
    }

    public void addSuperhelt(Superhelt superhelt) {
        superhelte.add(superhelt);
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
                return true; // Superhelten blev fundet i databasen
            }
        }
        return false; // Superhelten blev ikke fundet i databasen


    }
}


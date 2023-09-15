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
    public void editSuperhero(String nytNavn, String nySuperkraft, Boolean nyErMenneske, int nytOprettelsesår, String nyStyrke) {
        for (Superhelt superhelt : superhelte) {
            if (superhelt.getNavn().equalsIgnoreCase(nytNavn)) {
                // Find superhelten med det matchende navn og rediger oplysningerne
                superhelt.setNavn(nytNavn);
                superhelt.setSuperkraft(nySuperkraft);
                superhelt.setErMenneske(nyErMenneske);
                superhelt.setOprettelsesår(nytOprettelsesår);
                superhelt.setStyrke(nyStyrke);
                // Du kan tilføje flere redigeringsoperationer efter behov

                System.out.println("Superhelten er blevet redigeret.");
                return; // Afslut metoden, da redigeringen er gennemført
            }
        }
        System.out.println("Ingen superhelt med det angivne navn blev fundet.");
    }
    }


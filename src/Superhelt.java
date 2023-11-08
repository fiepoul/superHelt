import java.io.Serializable;
public class Superhelt implements Serializable, Comparable<Superhelt> {
        private String navn;
        private boolean erMenneske;
        private String superkraft;
        private int oprettelsesår;
        private int styrke;

        public Superhelt(String navn, boolean erMenneske, String superkraft, int oprettelsesår, int styrke) {
            this.navn = navn;
            this.erMenneske = erMenneske;
            this.superkraft = superkraft;
            this.oprettelsesår = oprettelsesår;
            this.styrke = styrke;
        }

        public String toString() {
            return "Navn: " + navn +
                    ", Menneske: " + (erMenneske ? "Ja" : "Nej") +
                    ", Superkraft: " + superkraft +
                    ", Oprettelsesår: " + oprettelsesår +
                    ", Styrke: " + styrke;
        }
        public String getNavn() {
            return navn;
        }

        public boolean getErMenneske() {
            return erMenneske;
        }

        public String getSuperkraft() {
            return superkraft;
        }

        public int getOprettelsesår(){
            return oprettelsesår;
        }

        public int getStyrke() {
            return styrke;
        }

    public void setNavn(String nytNavn) {
            this.navn = nytNavn;
    }
    public void setSuperkraft(String nySuperkraft) {
        this.superkraft = nySuperkraft;
    }

    public void setErMenneske(boolean nyErMenneske) {
            this.erMenneske = nyErMenneske;
    }

    public void setOprettelsesår(int nytOprettelsesår) {
            this.oprettelsesår = nytOprettelsesår;
    }

    public void setStyrke(int nyStyrke) {
            this.styrke = nyStyrke;
    }

    @Override
    public int compareTo(Superhelt otherSuperhelt) {
        // Sammenlign superhelte baseret på navnet (alfabetisk)
        return this.navn.compareTo(otherSuperhelt.navn);
    }

    }


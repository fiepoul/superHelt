public class Superhelt {
        private String navn;
        private boolean erMenneske;
        private String superkraft;
        private int oprettelsesår;
        private String styrke;

        public Superhelt(String navn, boolean erMenneske, String superkraft, int oprettelsesår, String styrke) {
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

        public String getStyrke() {
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

    public void setStyrke(String nyStyrke) {
            this.styrke = nyStyrke;
    }

    }


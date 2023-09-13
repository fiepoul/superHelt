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
        public String getSuperheroName() {
            return navn;
        }

        public boolean getIsHuman() {
            return erMenneske;
        }

        public String getSuperpower() {
            return superkraft;
        }

        public int getCreationYear(){
            return oprettelsesår;
        }

        public String getStrength() {
            return styrke;
        }
    }


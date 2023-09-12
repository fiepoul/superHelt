public class superHelt {
        private String superheroName;
        private boolean isHuman;
        private String superpower;
        private int creationYear;
        private String strength;

        public superHelt (String superheroName, boolean isHuman, String superpower, int creationYear, String strength) {
            this.superheroName = superheroName;
            this.isHuman = isHuman;
            this.superpower = superpower;
            this.creationYear = creationYear;
            this.strength = strength;
        }

        public String getSuperheroName() {
            return superheroName;
        }

        public boolean getIsHuman() {
            return isHuman;
        }

        public String getSuperpower() {
            return superpower;
        }

        public int getCreationYear(){
            return creationYear;
        }

        public String getStrength() {
            return strength;
        }
    }


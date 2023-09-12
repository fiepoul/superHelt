public class dataBase {
        private superHelt[] superHelte;
        private int count;

        public dataBase(int capacity) {
            superHelte = new superHelt[capacity];
            count = 0;
        }

        public void addSuperhelt(superHelt superhelt) {
            if (count < superHelte.length) {
                superHelte[count] = superhelt;
                count++;
                System.out.println("Superhelt tilføjet til listen.");
            } else {
                System.out.println("Listen er fuld. Der er ikke plads til flere superhelt.");
            }
        }

        // metoder til at finde, opdatere og slette superhelte
    }


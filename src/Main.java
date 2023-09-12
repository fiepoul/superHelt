import java.util.Scanner;
public class Main {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            // ny database med plads til 5 superhelte
            dataBase database = new dataBase(5);

            System.out.print("Indtast superheltnavn: ");
            String superheroName = scanner.nextLine();

            System.out.print("Er superhelten et menneske? (ja/nej): ");
            boolean isHuman = scanner.nextLine().equalsIgnoreCase("ja");

            System.out.print("Hvilken superkraft har superhelten: ");
            String superpower = scanner.nextLine();
        // Løkke for at teste om input er firecifret
            int creationYear = 0;

            while (true) {
                System.out.print("Indtast oprettelsesår (fire cifre): ");
                String input = scanner.nextLine();

                if (input.length() == 4 && input.matches("\\d+")) {
                    creationYear = Integer.parseInt(input);
                    break;
                } else {
                    System.out.print("Ugyldigt input.");
                }
            }

            System.out.print("Hvilken styrke har din superhelt: ");
            String strength = scanner.nextLine();

            // Superhelt objekt med attributterne
            superHelt superhelt = new superHelt(superheroName, isHuman, superpower, creationYear, strength);

            // superhelten tilføjes til databasen
            database.addSuperhelt(superhelt);

            scanner.close();
        }
    }

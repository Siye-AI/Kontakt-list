import java.io.*;          // Importerar klasser för filhantering
import java.util.*;        // Importerar klasser för List, Scanner, Comparator och Collections

public class PersonList {
    private ArrayList<Person> persons; // Lista för att lagra personer
    private final String fileName = "persons.txt"; // Filnamn där personlistan sparas

    // Konstruktor: Initierar listan och laddar data från fil
    public PersonList() {
        persons = new ArrayList<>();
        loadFromFile(fileName); // Laddar befintliga personer från fil vid programstart
    }

    // Metod för att lägga till en person i listan och spara till fil
    public void addPerson(Person person) {
        persons.add(person);
        saveToFile(fileName); // Uppdaterar filen efter tillägg
    }

    // Metod för att skriva ut alla personer i listan
    public void printPersons() {
        if (persons.isEmpty()) {
            System.out.println("Listan är tom.");
        } else {
            // Skriver ut tabellrubrik
            System.out.printf("%-5s %-10s %-15s %-5s %-30s\n", "Nr", "Signatur", "Namn", "Längd", "Adress");
            System.out.println("-------------------------------------------------------------");

            // Loopar genom listan och skriver ut varje person
            int count = 1;
            for (Person p : persons) {
                System.out.printf("%-5d %s\n", count++, p);
            }
        }
    }

    // Metod för att söka efter en person baserat på signatur
    public void searchPerson(String signatur) {
        for (Person p : persons) {
            if (p.getSignatur().equalsIgnoreCase(signatur)) { // Jämför utan att ta hänsyn till stora/små bokstäver
                System.out.println("Person hittad:\n" + p);
                return; // Avslutar metoden om personen hittas
            }
        }
        System.out.println("Personen med signatur " + signatur + " finns inte.");
    }

    // Metod för att ta bort en person från listan baserat på signatur
    public void removePerson(String signatur) {
        persons.removeIf(p -> p.getSignatur().equalsIgnoreCase(signatur)); // Tar bort personer med angiven signatur
        saveToFile(fileName); // Uppdaterar filen efter borttagning
        System.out.println("Person med signatur " + signatur + " borttagen.");
    }

    // Metod för att sortera listan efter signatur
    public void sortByName() {
        persons.sort(Comparator.comparing(p -> (p.getSignatur()))); // Sorterar alfabetiskt efter signatur
        System.out.println("Listan är sorterad efter signatur.");
    }

    // Metod för att slumpa ordningen i listan
    public void shuffleList() {
        Collections.shuffle(persons); // Blandar om listan slumpmässigt
        System.out.println("Listan är slumpad.");
    }

    // Metod för att spara listan till fil
    public void saveToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Person p : persons) {
                writer.write(p.formatForSave()); // Skriver varje persons data i filen
                writer.newLine(); // Lägg till en ny rad efter varje person
            }
            System.out.println("Listan har sparats till filen.");
        } catch (IOException e) {
            System.out.println("Fel vid sparning: " + e.getMessage());
        }
    }

    // Metod för att ladda personer från fil vid programstart
    public void loadFromFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) return; // Om filen inte finns, gör ingenting

        persons.clear(); // Rensar listan innan laddning från fil
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split("\\|"); // Dela upp raden med "|" som separator
                if (data.length == 7) { // Kontrollera att vi har rätt antal element
                    Address address = new Address(data[4], data[5], data[6]); // Skapa en Address-objekt
                    persons.add(new Person(data[0], data[1], Integer.parseInt(data[3]), address)); // Skapa en Person
                }
            }
            System.out.println("Personer har laddats från fil." + fileName);
        } catch (IOException e) {
            System.out.println("Fel vid inläsning: " + e.getMessage());
        }
    }
}

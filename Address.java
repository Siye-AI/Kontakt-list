// Klassen Address representerar en adress med gata, postnummer och stad
public class Address {
    private String street; // Privat variabel för gatunamn
    private String zipCode; // Privat variabel för postnummer
    private String city; // Privat variabel för stad

    // Konstruktor för att skapa ett Address-objekt med gatuadress, postnummer och
    // stad
    public Address(String street, String zipCode, String city) {
        this.street = street; // Tilldelar instansvariabeln street med värdet från parametern
        this.zipCode = zipCode; // Tilldelar instansvariabeln zipCode
        this.city = city; // Tilldelar instansvariabeln city
    }

    // Metod som returnerar adressen i ett format som kan sparas i en fil eller
    // databas
    public String formatForSave() {
        return street + "|" + zipCode + "|" + city; // Returnerar adressen separerad med "|"
    }

    // Överskriven toString()-metod som returnerar en formaterad version av adressen
    @Override
    public String toString() {
        return street + ", " + zipCode + " " + city; // Returnerar adressen i en läsbar form
    }
}

public class Product {
    enum suplements{
        BIALKO, CYTRULINA, KREATYNA, MAGNEZ, OMEGA, WITAMINAD3;

        public String getSupplement(){
            String writing = this.name().toLowerCase();
            char c = Character.toUpperCase(writing.charAt(0));
            String text = writing.substring(1);
            String resultOfWriting = c + text;
            return resultOfWriting;
        }
    };
    private static int identifier  = 0; //// unikalny identyfiaktor
    private String name; //// nazwa produktu
    private String manufacturer; //// wynalazca
    private suplements category; //// rodzaj suplementu z ENUMA powyżej

    public Product(String name, String manufacturer, String category) {
        identifier+=1;
        this.name = name;
        this.manufacturer = manufacturer;
        this.category = suplements.valueOf(category);
    }

    public static int getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public suplements getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return category.getSupplement() + " " + name + " ID: " + identifier + " manufacturer: " + manufacturer;
    }
}

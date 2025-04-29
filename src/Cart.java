import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Cart {
    enum typeOfCart { //// typ wózka czy jest realistyczny czy ONLINE
        VIRTUAL, REALISTIC;

        public String getTypeOfCart() {
            return this.name().toLowerCase();
        }
    }
    private static int nextId = 101; // licznik dla wszystkich koszyków
    private final int cartId;        // unikalne ID danego koszyka
    private InventoryItem inventory;
    private List<Product> productsInCart = new ArrayList<>(); // lista produktów w koszyku
    private LocalDate currentDate; //// zmienna przechowująca date
    private typeOfCart type; //// czy wózek wirtualny czy zwykły

    public Cart(String type, InventoryItem inventory) {
        this.cartId = nextId++;
        this.currentDate = LocalDate.now();
        this.type = typeOfCart.valueOf(type.toUpperCase());
        this.inventory = inventory;
    }

 @Override
public String toString() {
    StringBuilder cartContents = new StringBuilder();
    cartContents.append("Cart ID: ").append(cartId).append("\n");
    cartContents.append("Date: ").append(currentDate).append("\n");
    cartContents.append("Type: ").append(type).append("\n");
    cartContents.append("Products in cart:\n");

    Map<Product, Integer> listOfProducts = new HashMap<>();

    for (Product product : productsInCart) {
        listOfProducts.merge(product, 1, Integer::sum);
    }

    for (Map.Entry<Product, Integer> entry : listOfProducts.entrySet()) {
        Product productInCart = entry.getKey();
        Integer quantity = entry.getValue();
        cartContents.append(productInCart.toString())
                    .append(", Quantity: ")
                    .append(quantity)
                    .append("\n");
    }

    return cartContents.toString();
}

    public void addItems(Product product, int quantity) { //// dodajemy X produktów do koszyka
        if (quantity == 1) {
            productsInCart.add(product);
        } else if (quantity == 0) {
            System.out.println("Invalid value of quantity!");
        } else {
            for (int i = 0; i < quantity; i++) {
                productsInCart.add(product);
            }
        }
        inventory.reserveItem(quantity);
    }

    public void removeItems(Product product, int quantity) {
        inventory.releaseItem(product, quantity);
    }
    public void printSalesSlip() {
        System.out.println("LIST OF PRODUCTS IN YOUR CART:");
        for (Product p : productsInCart) {
            System.out.println(p);
        }
    }

    public void addSingleItemToCart(Product product){
        productsInCart.add(product);
        inventory.reserveItem(1);
    }

public void addListOfItems(List<Product> lists) {
    for (Product product : lists) {
        addSingleItemToCart(product);
    }
}



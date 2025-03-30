import java.util.ArrayList;
import java.util.List;

public class InventoryItem { //// mozna to interpretować jako konkretną półkę
    private Product product; //// produkt
    List<Product> productsOnTheShelf = new ArrayList<>(); //// Lista produktów w półce
    List<Product> productsWithReservation = new ArrayList<>(); //// Lista produktów w koszyku

    private int qtyTotal; //// całkowita ilość na magazynie
    private int qtyReserved; //// produkty co ktoś ma w koszyku
    private int qtyReorder; //// pułap kiedy ma domówić dany produkt
    private boolean salesPrice = false; //// czy jest na promocji

    public int getQtyTotal() {
        return qtyTotal;
    }

    public int getQtyReserved() {
        return qtyReserved;
    }

    public InventoryItem(Product product, int qtyTotal, int qtyReorder) {
        this.product = product;
        this.qtyTotal = qtyTotal;
        this.qtyReorder = qtyReorder;
        for(int i=0; i<qtyTotal; i++){
            productsOnTheShelf.add(product);
        } ////  dodajemy tutaj elementy do półki

    }
    public List<Product> reserveItem(int quantity) { //// produkty, które znajdują się we wszystkich koszykach
        if (quantity > qtyTotal) {
            return null;
        } else {
            for (int i = 0; i < quantity; i++) {
                productsWithReservation.add(product);
                productsOnTheShelf.remove(product);
                qtyTotal--;
                qtyReserved++;
            }
            return productsWithReservation;
        }
    }
    public List<Product> releaseItem(Product product, int quantity){ //// produkty, które zwracamy do półki
        if(quantity==0||productsWithReservation.size()<quantity){
            return null;
        }else{
            for(int i=0; i<quantity; i++){
                productsOnTheShelf.add(product);
                productsWithReservation.remove(product);
                qtyTotal++;
                qtyReserved--;
            }
        }
        return productsOnTheShelf;
    }
    public void sellItem(int quantity){ //// produkty, które sprzedajemy ze sklepu
        if(quantity!=0){
            for(int i=0; i<quantity; i++){
                productsWithReservation.remove(product);
                qtyTotal--;
                qtyReserved--;
            }
        }else{
            System.out.println("INVALID VALUE OF PRODUCT 0!");
        }
        placeInventoryOrder();
    }
    public void placeInventoryOrder(){ //// funkcja, która kontroluje czy danego produktu nie jest w sklepie za mało
        if(qtyTotal<=qtyReorder){
            System.out.println("Your quantity of product is to low: "+ qtyTotal + "!!!\nI had to order new amount of this product\nResult now: ");
            for(int i=0; i<qtyReorder*2; i++){
                productsOnTheShelf.add(product);
                qtyTotal++;
            }
            System.out.println(qtyTotal);
        }
    }
    @Override
    public String toString() {
        return "Products on inventory: " + qtyTotal +"\nProducts in baskets: " + product + " quantity: " + qtyReserved;
    }
}
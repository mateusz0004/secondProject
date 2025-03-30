public class Main {
    public static void main(String[] args) {
        Product product = new Product("karmelowe", "WPC", "BIALKO");
        InventoryItem A1 = new InventoryItem(product, 80, 20);
        Product product2 = new Product("waniliowe", "SFD", "BIALKO");
        Product product3 = new Product("monohydrat", "KFD", "KREATYNA");
        Cart cart = new Cart("Realistic", A1);
        cart.addListOfItems(product, 15, A1);
        cart.addListOfItems(product2, 10, A1);
        cart.addListOfItems(product3, 20, A1);
        //System.out.println(A1.getQtyTotal());
        //System.out.println(A1.getQtyReserved());
        //System.out.println(A1);
        System.out.println(cart);
        System.out.println(A1.getQtyReserved());
    }
}
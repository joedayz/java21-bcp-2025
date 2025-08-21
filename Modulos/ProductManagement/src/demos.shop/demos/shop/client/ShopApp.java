package demos.shop.client;

import demos.pm.model.ProductManager;
import demos.pm.data.Product;

public class ShopApp {
    public static void main(String[] args) {
        ProductManager manager = new ProductManager();
        manager.manage();

        Product product = new Product("Laptop");
        System.out.println("Product: " + product.getName());
    }
}
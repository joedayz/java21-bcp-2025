package demos.genericos;

public class Product {
    private String name;
    private double price;
    
    public Product(String name) {
        this.name = name;
        this.price = 0.0;
    }
    
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    @Override
    public String toString() {
        return "Product{name='" + name + "', price=$" + price + "}";
    }
}

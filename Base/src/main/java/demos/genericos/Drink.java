package demos.genericos;

public class Drink extends Product {
    
    public Drink(String name) {
        super(name);
    }
    
    @Override
    public String toString() {
        return "Drink{name='" + getName() + "'}";
    }
}

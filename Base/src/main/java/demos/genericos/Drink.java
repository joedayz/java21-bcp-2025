package demos.genericos;

public class Drink extends Product {
    
    public Drink(String name, double v) {
        super(name);
    }

    public Drink(String name){
        super(name);
    }
    
    @Override
    public String toString() {
        return "Drink{name='" + getName() + "'}";
    }
}

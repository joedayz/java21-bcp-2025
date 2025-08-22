package demos.genericos;

public class Food extends Product {
    
    public Food(String name) {
        super(name);
    }
    
    @Override
    public String toString() {
        return "Food{name='" + getName() + "'}";
    }
}

package demos.genericos;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo que muestra los warnings del compilador cuando se usan raw types.
 * 
 * IMPORTANTE: Este archivo generará warnings del compilador.
 * Para ver los warnings, compila con: javac -Xlint:rawtypes RawTypesWarningsDemo.java
 */
public class RawTypesWarningsDemo {
    
    public static void main(String[] args) {
        System.out.println("=== DEMO: Raw Types Warnings ===\n");
        
        // Este código generará warnings del compilador
        List<Food> foods = new ArrayList<Food>();
        foods.add(new Food("Pizza"));
        
        // ⚠️ WARNING: Raw type assignment
        List values = foods; // Raw type - genera warning
        
        // ⚠️ WARNING: Raw type assignment  
        List<Product> products = values; // Raw type - genera warning
        
        // ⚠️ WARNING: Unchecked call to add()
        products.add(new Drink("Tea")); // Peligroso pero compila
        
        System.out.println("Código ejecutado con raw types");
        System.out.println("Revisa los warnings del compilador!");
    }
}

package demos.genericos;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo que muestra las diferencias entre arrays (covariantes), 
 * colecciones genéricas (invariantes) y raw types.
 * 
 * Este ejemplo genera warnings del compilador para demostrar
 * los problemas de seguridad de tipos.
 */
public class GenericsTypeHierarchyDemo {
    
    public static void main(String[] args) {
        System.out.println("=== DEMO: Generics y Jerarquía de Tipos ===\n");
        
        // 1. Arrays (Covariantes) - Error en tiempo de ejecución
        demoArrays();
        
        // 2. Colecciones Genéricas (Invariantes) - Error en tiempo de compilación
        demoGenerics();
        
        // 3. Raw Types - Warnings y errores en tiempo de ejecución
        demoRawTypes();
    }
    
    /**
     * Demuestra el problema de los arrays covariantes
     */
    private static void demoArrays() {
        System.out.println("1. ARRAYS (Covariantes):");
        System.out.println("   Los arrays son covariantes - esto puede causar errores en tiempo de ejecución\n");
        
        try {
            Product[] products = new Food[10]; // ✅ Válido - arrays son covariantes
            System.out.println("   ✅ Product[] products = new Food[10]; // Compila correctamente");
            
            products[0] = new Food("Pizza", 12.99);
            System.out.println("   ✅ products[0] = new Food(\"Pizza\"); // Funciona");
            
            products[1] = new Drink("Tea", 3.99); // ❌ Esto causará ArrayStoreException
            System.out.println("   ❌ products[1] = new Drink(\"Tea\"); // Causará ArrayStoreException");
            
        } catch (ArrayStoreException e) {
            System.out.println("   💥 ArrayStoreException: " + e.getMessage());
        }
        System.out.println();
    }
    
    /**
     * Demuestra la invariancia de las colecciones genéricas
     */
    private static void demoGenerics() {
        System.out.println("2. COLECCIONES GENÉRICAS (Invariantes):");
        System.out.println("   Las colecciones genéricas son invariantes - errores en tiempo de compilación\n");
        
        // Este código NO compilaría si no estuviera comentado:
        /*
        List<Product> products = new ArrayList<Food>(); // ❌ Error de compilación!
        System.out.println("   ❌ List<Product> products = new ArrayList<Food>(); // No compila");
        */
        
        System.out.println("   ❌ List<Product> products = new ArrayList<Food>(); // Error de compilación");
        System.out.println("   💡 El compilador previene errores de tipo en tiempo de ejecución");
        System.out.println();
    }
    
    /**
     * Demuestra los problemas de los raw types
     * Este método generará warnings del compilador
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void demoRawTypes() {
        System.out.println("3. RAW TYPES:");
        System.out.println("   Los raw types eluden las verificaciones de genéricos\n");
        
        List<Food> foods = new ArrayList<Food>();
        foods.add(new Food("Pizza", 12.99));
        System.out.println("   ✅ List<Food> foods = new ArrayList<Food>();");
        System.out.println("   ✅ foods.add(new Food(\"Pizza\"));");
        
        // ⚠️ WARNING: Raw type assignment
        List values = foods; // Raw type - genera warning
        System.out.println("   ⚠️ List values = foods; // Raw type - genera warning");
        
        // ⚠️ WARNING: Raw type assignment  
        List<Product> products = values; // Raw type - genera warning
        System.out.println("   ⚠️ List<Product> products = values; // Raw type - genera warning");
        
        // ⚠️ WARNING: Unchecked call to add()
        products.add(new Drink("Tea", 3.99)); // Peligroso pero compila
        System.out.println("   ⚠️ products.add(new Drink(\"Tea\")); // Peligroso pero compila");
        System.out.println("   💡 Ahora 'foods' (List<Food>) contiene un Drink!");
        
        try {
            // ✅ Funciona si el elemento es realmente un Drink
            Drink x1 = (Drink) values.get(1);
            System.out.println("   ✅ Drink x1 = (Drink) values.get(1); // Funciona: " + x1);
            
            // ❌ ClassCastException en tiempo de ejecución
            Food x2 = foods.get(1); // Intenta castear Drink a Food
            System.out.println("   ❌ Food x2 = foods.get(1); // ClassCastException");
            
        } catch (ClassCastException e) {
            System.out.println("   💥 ClassCastException: " + e.getMessage());
            System.out.println("   💡 class Drink cannot be cast to class Food");
        }
        
        System.out.println("\n=== RESUMEN ===");
        System.out.println("• Arrays: Covariantes, errores en runtime");
        System.out.println("• Genéricos: Invariantes, errores en compile time");
        System.out.println("• Raw Types: Compatibles, warnings + errores en runtime");
    }
}

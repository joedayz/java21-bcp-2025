package demos.wildcards;

import demos.genericos.Drink;
import demos.genericos.Food;
import demos.genericos.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo que compara Upper Bound vs Lower Bound wildcards.
 * 
 * Esta clase muestra las diferencias clave entre:
 * - List<? extends Product> (Upper Bound - covarianza)
 * - List<? super Food> (Lower Bound - contravarianza)
 */
public class WildcardComparisonDemo {
    
    public static void main(String[] args) {
        System.out.println("=== DEMO: Comparación de Wildcards ===\n");
        
        demoUpperBoundWildcard();
        demoLowerBoundWildcard();
        demoComparison();
        demoPracticalDifferences();
    }
    
    /**
     * Demuestra el comportamiento del Upper Bound Wildcard
     */
    private static void demoUpperBoundWildcard() {
        System.out.println("1. UPPER BOUND WILDCARD (<? extends Product>):");
        System.out.println("   Covariante - Solo lectura\n");
        
        // Crear listas específicas
        List<Food> foodList = new ArrayList<>();
        foodList.add(new Food("Pizza", 12.99));
        foodList.add(new Food("Burger", 8.99));
        
        List<Drink> drinkList = new ArrayList<>();
        drinkList.add(new Drink("Coffee", 3.99));
        drinkList.add(new Drink("Tea", 2.99));
        
        // ✅ Covarianza: asignar subtipos
        List<? extends Product> upperBound1 = foodList;
        List<? extends Product> upperBound2 = drinkList;
        System.out.println("   ✅ List<? extends Product> upperBound1 = foodList; // Covariante");
        System.out.println("   ✅ List<? extends Product> upperBound2 = drinkList; // Covariante");
        
        // ✅ Puedes leer elementos (como Product)
        Product food1 = upperBound1.get(0);
        Product drink1 = upperBound2.get(0);
        System.out.println("   ✅ Lectura como Product: " + food1 + ", " + drink1);
        
        // ❌ NO puedes añadir elementos (excepto null)
        try {
            // upperBound1.add(new Food("Salad", 6.99)); // Error de compilación
            // upperBound2.add(new Drink("Juice", 4.99)); // Error de compilación
            System.out.println("   ❌ upperBound1.add(new Food(\"Salad\")); // Error de compilación");
            System.out.println("   ❌ upperBound2.add(new Drink(\"Juice\")); // Error de compilación");
        } catch (Exception e) {
            System.out.println("   💥 Error: " + e.getMessage());
        }
        
        // ✅ Solo null es permitido
        upperBound1.add(null);
        upperBound2.add(null);
        System.out.println("   ✅ upperBound1.add(null); // Solo null es permitido");
        System.out.println("   ✅ upperBound2.add(null); // Solo null es permitido");
        
        System.out.println();
    }
    
    /**
     * Demuestra el comportamiento del Lower Bound Wildcard
     */
    private static void demoLowerBoundWildcard() {
        System.out.println("2. LOWER BOUND WILDCARD (<? super Food>):");
        System.out.println("   Contravariante - Escribible\n");
        
        // Crear listas específicas
        List<Product> productList = new ArrayList<>();
        List<Food> foodList = new ArrayList<>();
        List<Object> objectList = new ArrayList<>();
        
        // ✅ Contravarianza: asignar supertipos
        List<? super Food> lowerBound1 = productList;
        List<? super Food> lowerBound2 = foodList;
        List<? super Food> lowerBound3 = objectList;
        System.out.println("   ✅ List<? super Food> lowerBound1 = productList; // Contravariante");
        System.out.println("   ✅ List<? super Food> lowerBound2 = foodList; // Contravariante");
        System.out.println("   ✅ List<? super Food> lowerBound3 = objectList; // Contravariante");
        
        // ✅ Puedes añadir Food y subtipos
        Food newFood = new Food("Salad", 6.99);
        lowerBound1.add(newFood);
        lowerBound2.add(newFood);
        lowerBound3.add(newFood);
        System.out.println("   ✅ lowerBound1.add(newFood); // Seguro porque Product puede contener Food");
        System.out.println("   ✅ lowerBound2.add(newFood); // Seguro porque Food puede contener Food");
        System.out.println("   ✅ lowerBound3.add(newFood); // Seguro porque Object puede contener Food");
        
        // ❌ Solo puedes leer como Object
        Object obj1 = lowerBound1.get(0);
        Object obj2 = lowerBound2.get(0);
        Object obj3 = lowerBound3.get(0);
        System.out.println("   ⚠️ Solo puedes leer como Object: " + obj1 + ", " + obj2 + ", " + obj3);
        
        // ❌ No puedes leer como Food directamente
        try {
            // Food food = lowerBound1.get(0); // Error de compilación
            System.out.println("   ❌ Food food = lowerBound1.get(0); // Error de compilación");
        } catch (Exception e) {
            System.out.println("   💥 Error: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    /**
     * Compara directamente ambos tipos de wildcards
     */
    private static void demoComparison() {
        System.out.println("3. COMPARACIÓN DIRECTA:");
        System.out.println("   Upper Bound vs Lower Bound\n");
        
        // Crear datos de prueba
        List<Food> foodList = new ArrayList<>();
        foodList.add(new Food("Pizza", 12.99));
        
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Generic Product", 5.99));
        
        System.out.println("   List<Food> foodList = [Pizza]");
        System.out.println("   List<Product> productList = [Generic Product]");
        
        // Probar Upper Bound
        System.out.println("\n   --- Upper Bound Wildcard ---");
        System.out.println("   List<? extends Product> upperBound = foodList; // ✅ Covariante");
        List<? extends Product> upperBound = foodList;
        
        // ✅ Puedes leer
        Product food = upperBound.get(0);
        System.out.println("   ✅ Product food = upperBound.get(0); // " + food);
        
        // ❌ No puedes añadir
        System.out.println("   ❌ upperBound.add(new Food(\"Burger\")); // Error de compilación");
        
        // Probar Lower Bound
        System.out.println("\n   --- Lower Bound Wildcard ---");
        System.out.println("   List<? super Food> lowerBound = productList; // ✅ Contravariante");
        List<? super Food> lowerBound = productList;
        
        // ✅ Puedes añadir
        Food newFood = new Food("Burger", 8.99);
        lowerBound.add(newFood);
        System.out.println("   ✅ lowerBound.add(newFood); // " + newFood);
        
        // ❌ Solo puedes leer como Object
        Object obj = lowerBound.get(1);
        System.out.println("   ⚠️ Object obj = lowerBound.get(1); // " + obj);
        
        System.out.println();
    }
    
    /**
     * Demuestra las diferencias prácticas entre ambos wildcards
     */
    private static void demoPracticalDifferences() {
        System.out.println("4. DIFERENCIAS PRÁCTICAS:");
        System.out.println("   Cuándo usar cada uno\n");
        
        // Ejemplo con Upper Bound (lectura)
        System.out.println("   --- Upper Bound: Para métodos de LECTURA ---");
        List<Food> foods = List.of(new Food("Pizza", 12.99), new Food("Burger", 8.99));
        List<Drink> drinks = List.of(new Drink("Coffee", 3.99), new Drink("Tea", 2.99));
        
        double totalFoodPrice = calculateTotalPrice(foods);
        double totalDrinkPrice = calculateTotalPrice(drinks);
        System.out.println("   ✅ calculateTotalPrice(foods): $" + totalFoodPrice);
        System.out.println("   ✅ calculateTotalPrice(drinks): $" + totalDrinkPrice);
        
        // Ejemplo con Lower Bound (escritura)
        System.out.println("\n   --- Lower Bound: Para métodos de ESCRITURA ---");
        List<Product> allProducts = new ArrayList<>();
        List<Food> foodCollection = new ArrayList<>();
        
        addFoodToCollection(foods, allProducts);
        addFoodToCollection(foods, foodCollection);
        System.out.println("   ✅ addFoodToCollection(foods, allProducts): " + allProducts.size() + " productos");
        System.out.println("   ✅ addFoodToCollection(foods, foodCollection): " + foodCollection.size() + " comidas");
        
        System.out.println("\n=== RESUMEN DE DIFERENCIAS ===");
        System.out.println("📖 Upper Bound <? extends T>:");
        System.out.println("   • Covariante (acepta subtipos)");
        System.out.println("   • Solo lectura");
        System.out.println("   • Puedes leer como T");
        System.out.println("   • Usar para métodos que solo necesitan leer");
        
        System.out.println("\n📝 Lower Bound <? super T>:");
        System.out.println("   • Contravariante (acepta supertipos)");
        System.out.println("   • Escribible");
        System.out.println("   • Solo puedes leer como Object");
        System.out.println("   • Usar para métodos que necesitan escribir");
        
        System.out.println("\n🎯 REGLA MNEMOTÉCNICA:");
        System.out.println("   • PECS: Producer Extends, Consumer Super");
        System.out.println("   • Producer (lee): usa <? extends T>");
        System.out.println("   • Consumer (escribe): usa <? super T>");
    }
    
    /**
     * Método que usa Upper Bound wildcard (para lectura)
     */
    private static double calculateTotalPrice(List<? extends Product> products) {
        double total = 0.0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }
    
    /**
     * Método que usa Lower Bound wildcard (para escritura)
     */
    private static void addFoodToCollection(List<Food> foods, List<? super Food> collection) {
        for (Food food : foods) {
            collection.add(food);
        }
    }
}

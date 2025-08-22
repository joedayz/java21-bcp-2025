package demos.wildcards;

import demos.genericos.Drink;
import demos.genericos.Food;
import demos.genericos.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo que muestra el comportamiento del Lower Bound Wildcard:
 * - List<Food> (tipo específico)
 * - List<? super Food> (lower bound wildcard)
 */
public class LowerBoundWildcardDemo {
    
    public static void main(String[] args) {
        System.out.println("=== DEMO: Lower Bound Wildcard ===\n");
        
        demoSpecificType();
        demoLowerBoundWildcard();
        demoMethodComparison();
        demoPracticalExample();
    }
    
    /**
     * Demuestra el comportamiento de List<Food> (tipo específico)
     */
    private static void demoSpecificType() {
        System.out.println("1. LIST<FOOD> (Tipo Específico):");
        System.out.println("   Escribible pero invariante\n");
        
        List<Food> foods = new ArrayList<>();
        System.out.println("   List<Food> foods = new ArrayList<>();");
        
        // ✅ Puedes añadir Food
        foods.add(new Food("Pizza", 12.99));
        foods.add(new Food("Burger", 8.99));
        System.out.println("   ✅ foods.add(new Food(\"Pizza\", 12.99));");
        System.out.println("   ✅ foods.add(new Food(\"Burger\", 8.99));");
        
        // ✅ Puedes leer elementos como Food
        Food food1 = foods.get(0);
        Food food2 = foods.get(1);
        System.out.println("   ✅ Lectura como Food: " + food1 + ", " + food2);
        
        // ❌ No puedes asignar List<Product> a List<Food>
        try {
            // List<Food> foodList = new ArrayList<Product>(); // Error de compilación
            System.out.println("   ❌ List<Food> foodList = new ArrayList<Product>(); // Error de compilación");
        } catch (Exception e) {
            System.out.println("   💥 Error: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    /**
     * Demuestra el comportamiento de List<? super Food> (lower bound wildcard)
     */
    private static void demoLowerBoundWildcard() {
        System.out.println("2. LIST<? SUPER FOOD> (Lower Bound Wildcard):");
        System.out.println("   Escribible y contravariante\n");
        
        // ✅ Contravarianza: puedes asignar supertipos
        List<? super Food> foodParents;
        
        foodParents = new ArrayList<Food>();
        System.out.println("   ✅ List<? super Food> foodParents = new ArrayList<Food>();");
        
        foodParents = new ArrayList<Product>();
        System.out.println("   ✅ foodParents = new ArrayList<Product>(); // Contravariante");
        
        foodParents = new ArrayList<Object>();
        System.out.println("   ✅ foodParents = new ArrayList<Object>(); // Contravariante");
        
        // ❌ No puedes asignar subtipos
        try {
            // foodParents = new ArrayList<Drink>(); // Error de compilación
            System.out.println("   ❌ foodParents = new ArrayList<Drink>(); // Error: Drink no es supertipo de Food");
        } catch (Exception e) {
            System.out.println("   💥 Error: " + e.getMessage());
        }
        
        // Crear listas específicas para demostrar
        List<Product> products = new ArrayList<>();
        List<Food> foods = new ArrayList<>();
        List<Object> objects = new ArrayList<>();
        
        // ✅ Asignar a lower bound wildcard
        List<? super Food> productList = products;
        List<? super Food> foodList = foods;
        List<? super Food> objectList = objects;
        System.out.println("   ✅ List<? super Food> productList = products; // Contravariante");
        System.out.println("   ✅ List<? super Food> foodList = foods; // Contravariante");
        System.out.println("   ✅ List<? super Food> objectList = objects; // Contravariante");
        
        // ✅ Puedes añadir Food y subtipos
        Food newFood = new Food("Salad", 6.99);
        productList.add(newFood);
        foodList.add(newFood);
        objectList.add(newFood);
        System.out.println("   ✅ productList.add(newFood); // Seguro porque Product puede contener Food");
        System.out.println("   ✅ foodList.add(newFood); // Seguro porque Food puede contener Food");
        System.out.println("   ✅ objectList.add(newFood); // Seguro porque Object puede contener Food");
        
        // ❌ Solo puedes leer como Object
        Object obj1 = productList.get(0);
        Object obj2 = foodList.get(0);
        Object obj3 = objectList.get(0);
        System.out.println("   ⚠️ Solo puedes leer como Object: " + obj1 + ", " + obj2 + ", " + obj3);
        
        // ❌ No puedes leer como Food directamente
        try {
            // Food food = productList.get(0); // Error de compilación
            System.out.println("   ❌ Food food = productList.get(0); // Error de compilación");
        } catch (Exception e) {
            System.out.println("   💥 Error: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    /**
     * Compara métodos con tipo específico vs lower bound wildcard
     */
    private static void demoMethodComparison() {
        System.out.println("3. COMPARACIÓN DE MÉTODOS:");
        System.out.println("   Tipo específico vs Lower Bound Wildcard\n");
        
        // Crear datos de prueba
        List<Product> products = new ArrayList<>();
        List<Food> foods = new ArrayList<>();
        Food f = new Food("Cake", 2.99);
        
        System.out.println("   List<Product> products = new ArrayList<>(); // Puede contener Product, Food, Drink");
        System.out.println("   List<Food> foods = new ArrayList<>();       // Solo puede contener Food");
        System.out.println("   Food f = new Food(\"Cake\", 2.99);");
        
        // Probar método con tipo específico
        System.out.println("\n   --- Método con tipo específico ---");
        System.out.println("   public void addFoodToFoods(List<Food> order, Food food) { }");
        
        addFoodToFoods(foods, f); // ✅ Válido
        System.out.println("   ✅ addFoodToFoods(foods, f); // List<Food> → List<Food>");
        
        try {
            // addFoodToFoods(products, f); // ❌ Error de compilación
            System.out.println("   ❌ addFoodToFoods(products, f); // List<Product> → List<Food> (invarianza)");
        } catch (Exception e) {
            System.out.println("   💥 Error: " + e.getMessage());
        }
        
        // Probar método con lower bound wildcard
        System.out.println("\n   --- Método con lower bound wildcard ---");
        System.out.println("   public void addFoodToFoodParents(List<? super Food> order, Food food) { }");
        
        addFoodToFoodParents(foods, f); // ✅ Válido
        System.out.println("   ✅ addFoodToFoodParents(foods, f); // List<Food> → List<? super Food>");
        
        addFoodToFoodParents(products, f); // ✅ Válido
        System.out.println("   ✅ addFoodToFoodParents(products, f); // List<Product> → List<? super Food> (contravarianza)");
        
        System.out.println();
    }
    
    /**
     * Ejemplo práctico del slide
     */
    private static void demoPracticalExample() {
        System.out.println("4. EJEMPLO PRÁCTICO DEL SLIDE:");
        System.out.println("   Demostrando contravarianza vs invarianza\n");
        
        // Crear listas como en el slide
        List<Product> products = new ArrayList<>(); // Puede contener Product, Food, Drink
        List<Food> foods = new ArrayList<>();       // Solo puede contener Food
        Food f = new Food("Cake", 2.99);
        
        System.out.println("   List<Product> products = new ArrayList<>(); // Puede contener Product, Food, Drink");
        System.out.println("   List<Food> foods = new ArrayList<>();       // Solo puede contener Food");
        System.out.println("   Food f = new Food(\"Cake\", 2.99);");
        
        System.out.println("\n   --- Llamadas a addFoodToFoods ---");
        System.out.println("   addFoodToFoods(foods, f);    // ✅ Válido: List<Food> se pasa a List<Food>");
        System.out.println("   addFoodToFoods(products, f); // ❌ Error: List<Product> NO puede pasarse a List<Food> (invarianza)");
        
        System.out.println("\n   --- Llamadas a addFoodToFoodParents ---");
        System.out.println("   addFoodToFoodParents(foods, f);    // ✅ Válido: List<Food> se pasa a List<? super Food>");
        System.out.println("   addFoodToFoodParents(products, f); // ✅ Válido: List<Product> se pasa a List<? super Food> (contravarianza)");
        
        System.out.println("\n=== RESUMEN ===");
        System.out.println("• List<Food>: Escribible, invariante");
        System.out.println("• List<? super Food>: Escribible, contravariante");
        System.out.println("• Lower bound wildcard permite mayor flexibilidad para escritura");
        System.out.println("• Perfecto para métodos que necesitan añadir elementos");
        System.out.println("• Nota: List<Product> puede contener Food y Drink, pero los métodos solo añaden Food");
    }
    
    /**
     * Método con tipo específico - solo acepta List<Food>
     */
    private static void addFoodToFoods(List<Food> order, Food food) {
        order.add(food);
        System.out.println("   🔧 addFoodToFoods() ejecutado - añadido: " + food);
    }
    
    /**
     * Método con lower bound wildcard - acepta List<Food>, List<Product>, List<Object>
     */
    private static void addFoodToFoodParents(List<? super Food> order, Food food) {
        order.add(food);
        System.out.println("   🔧 addFoodToFoodParents() ejecutado - añadido: " + food);
        System.out.println("      📏 Tamaño de la lista después de añadir: " + order.size());
    }
}

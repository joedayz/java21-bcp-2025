package demos.wildcards;

import demos.genericos.Drink;
import demos.genericos.Food;
import demos.genericos.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo que muestra el comportamiento del Upper Bound Wildcard:
 * - List<Product> (tipo específico)
 * - List<? extends Product> (upper bound wildcard)
 */
public class UpperBoundWildcardDemo {
    
    public static void main(String[] args) {
        System.out.println("=== DEMO: Upper Bound Wildcard ===\n");
        
        demoSpecificType();
        demoUpperBoundWildcard();
        demoMethodComparison();
        demoPracticalExample();
    }
    
    /**
     * Demuestra el comportamiento de List<Product> (tipo específico)
     */
    private static void demoSpecificType() {
        System.out.println("1. LIST<PRODUCT> (Tipo Específico):");
        System.out.println("   Escribible pero invariante\n");
        
        List<Product> products = new ArrayList<>();
        System.out.println("   List<Product> products = new ArrayList<>();");
        
        // ✅ Puedes añadir Product y subtipos
        products.add(new Product("Generic Product"));
        products.add(new Food("Pizza", 12.99));
        products.add(new Drink("Coffee", 3.99));
        System.out.println("   ✅ products.add(new Product(\"Generic Product\"));");
        System.out.println("   ✅ products.add(new Food(\"Pizza\"));");
        System.out.println("   ✅ products.add(new Drink(\"Coffee\"));");
        
        // ✅ Puedes leer elementos
        Product product = products.get(0);
        Food food = (Food) products.get(1);
        Drink drink = (Drink) products.get(2);
        System.out.println("   ✅ Lectura: " + product + ", " + food + ", " + drink);
        
        // ❌ No puedes asignar List<Food> a List<Product>
        try {
            // List<Product> productList = new ArrayList<Food>(); // Error de compilación
            System.out.println("   ❌ List<Product> productList = new ArrayList<Food>(); // Error de compilación");
        } catch (Exception e) {
            System.out.println("   💥 Error: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    /**
     * Demuestra el comportamiento de List<? extends Product> (upper bound wildcard)
     */
    private static void demoUpperBoundWildcard() {
        System.out.println("2. LIST<? EXTENDS PRODUCT> (Upper Bound Wildcard):");
        System.out.println("   Solo lectura pero covariante\n");
        
        // ✅ Covarianza: puedes asignar diferentes subtipos
        List<? extends Product> productSubtypes;
        
        productSubtypes = new ArrayList<Product>();
        System.out.println("   ✅ List<? extends Product> productSubtypes = new ArrayList<Product>();");
        
        productSubtypes = new ArrayList<Food>();
        System.out.println("   ✅ productSubtypes = new ArrayList<Food>(); // Covariante");
        
        productSubtypes = new ArrayList<Drink>();
        System.out.println("   ✅ productSubtypes = new ArrayList<Drink>(); // Covariante");
        
        // Crear listas específicas para demostrar
        List<Food> foodList = new ArrayList<>();
        foodList.add(new Food("Burger", 12.99));
        foodList.add(new Food("Salad", 12.99));
        
        List<Drink> drinkList = new ArrayList<>();
        drinkList.add(new Drink("Tea", 3.99));
        drinkList.add(new Drink("Juice", 3.99));
        
        // ✅ Asignar a wildcard
        List<? extends Product> foods = foodList;
        List<? extends Product> drinks = drinkList;
        System.out.println("   ✅ List<? extends Product> foods = foodList; // Covariante");
        System.out.println("   ✅ List<? extends Product> drinks = drinkList; // Covariante");
        
        // ✅ Puedes leer elementos (como Product)
        Product food1 = foods.get(0);
        Product food2 = foods.get(1);
        Product drink1 = drinks.get(0);
        Product drink2 = drinks.get(1);
        System.out.println("   ✅ Lectura como Product: " + food1 + ", " + food2);
        System.out.println("   ✅ Lectura como Product: " + drink1 + ", " + drink2);
        
        // ❌ NO puedes añadir elementos (excepto null)
        try {
            // foods.add(new Food("Pasta")); // Error de compilación
            // drinks.add(new Drink("Water")); // Error de compilación
            System.out.println("   ❌ foods.add(new Food(\"Pasta\")); // Error de compilación");
            System.out.println("   ❌ drinks.add(new Drink(\"Water\")); // Error de compilación");
        } catch (Exception e) {
            System.out.println("   💥 Error: " + e.getMessage());
        }
        
        // ✅ Solo null es permitido
        foods.add(null);
        drinks.add(null);
        System.out.println("   ✅ foods.add(null); // Solo null es permitido");
        System.out.println("   ✅ drinks.add(null); // Solo null es permitido");
        
        System.out.println();
    }
    
    /**
     * Compara métodos con tipo específico vs upper bound wildcard
     */
    private static void demoMethodComparison() {
        System.out.println("3. COMPARACIÓN DE MÉTODOS:");
        System.out.println("   Tipo específico vs Upper Bound Wildcard\n");
        
        // Crear datos de prueba
        Product p1 = new Food("Cake", 2.99);
        Product p2 = new Drink("Tea", 1.99);
        Product p3 = new Food("Cookie", 2.99);
        
        List<Product> products = List.of(p1, p2, p3);
        List<Food> foods = List.of((Food) p1, (Food) p3);
        
        System.out.println("   Product p1 = new Food(\"Cake\", 2.99);");
        System.out.println("   Product p2 = new Drink(\"Tea\", 1.99);");
        System.out.println("   Product p3 = new Food(\"Cookie\", 2.99);");
        System.out.println("   List<Product> products = List.of(p1, p2, p3);");
        System.out.println("   List<Food> foods = List.of((Food) p1, (Food) p3);");
        
        // Probar método con tipo específico
        System.out.println("\n   --- Método con tipo específico ---");
        System.out.println("   public void setProducts(List<Product> products) { }");
        
        setProducts(products); // ✅ Válido
        System.out.println("   ✅ setProducts(products); // List<Product> → List<Product>");
        
        try {
            // setProducts(foods); // ❌ Error de compilación
            System.out.println("   ❌ setProducts(foods); // List<Food> → List<Product> (invarianza)");
        } catch (Exception e) {
            System.out.println("   💥 Error: " + e.getMessage());
        }
        
        // Probar método con upper bound wildcard
        System.out.println("\n   --- Método con upper bound wildcard ---");
        System.out.println("   public void setProductAndSubtypes(List<? extends Product> products) { }");
        
        setProductAndSubtypes(products); // ✅ Válido
        System.out.println("   ✅ setProductAndSubtypes(products); // List<Product> → List<? extends Product>");
        
        setProductAndSubtypes(foods); // ✅ Válido
        System.out.println("   ✅ setProductAndSubtypes(foods); // List<Food> → List<? extends Product> (covarianza)");
        
        System.out.println();
    }
    
    /**
     * Ejemplo práctico del slide
     */
    private static void demoPracticalExample() {
        System.out.println("4. EJEMPLO PRÁCTICO DEL SLIDE:");
        System.out.println("   Demostrando covarianza vs invarianza\n");
        
        // Crear productos como en el slide
        Product p1 = new Food("Cake", 2.99);
        Product p2 = new Drink("Tea", 1.99);
        Product p3 = new Food("Cookie", 2.99);
        
        List<Product> products = List.of(p1, p2, p3);
        List<Food> foods = List.of((Food) p1, (Food) p3);
        
        System.out.println("   Product p1 = new Food(\"Cake\", 2.99);");
        System.out.println("   Product p2 = new Drink(\"Tea\", 1.99);");
        System.out.println("   Product p3 = new Food(\"Cookie\", 2.99);");
        System.out.println("   List<Product> products = List.of(p1, p2, p3); // Lista de Products");
        System.out.println("   List<Food> foods = List.of((Food) p1, (Food) p3); // Lista de Foods");
        
        System.out.println("\n   --- Llamadas a setProducts ---");
        System.out.println("   setProducts(products); // ✅ Válido: List<Product> se pasa a List<Product>");
        System.out.println("   setProducts(foods);    // ❌ Error: List<Food> NO puede pasarse a List<Product> (invarianza)");
        
        System.out.println("\n   --- Llamadas a setProductAndSubtypes ---");
        System.out.println("   setProductAndSubtypes(products); // ✅ Válido: List<Product> se pasa a List<? extends Product>");
        System.out.println("   setProductAndSubtypes(foods);    // ✅ Válido: List<Food> se pasa a List<? extends Product> (covarianza)");
        
        System.out.println("\n=== RESUMEN ===");
        System.out.println("• List<Product>: Escribible, invariante");
        System.out.println("• List<? extends Product>: Solo lectura, covariante");
        System.out.println("• Upper bound wildcard permite mayor flexibilidad");
        System.out.println("• Perfecto para métodos que solo necesitan leer");
    }
    
    /**
     * Método con tipo específico - solo acepta List<Product>
     */
    private static void setProducts(List<Product> products) {
        System.out.println("   🔧 setProducts() ejecutado con " + products.size() + " productos");
        // Puedes añadir elementos
        // products.add(new Food("New Food")); // ✅ Permitido
    }
    
    /**
     * Método con upper bound wildcard - acepta List<Product>, List<Food>, List<Drink>
     */
    private static void setProductAndSubtypes(List<? extends Product> products) {
        System.out.println("   🔧 setProductAndSubtypes() ejecutado con " + products.size() + " productos");
        // NO puedes añadir elementos
        // products.add(new Food("New Food")); // ❌ Error de compilación
        // Solo puedes leer
        for (Product product : products) {
            System.out.println("      📖 Leyendo: " + product);
        }
    }
}

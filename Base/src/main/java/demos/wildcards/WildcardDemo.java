package demos.wildcards;

import demos.genericos.Drink;
import demos.genericos.Food;
import demos.genericos.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo que muestra los diferentes comportamientos de wildcards genéricos:
 * - Raw types
 * - Tipos específicos
 * - Wildcards <?>
 */
public class WildcardDemo {
    
    public static void main(String[] args) {
        System.out.println("=== DEMO: Wildcard Generics ===\n");
        
        demoRawTypes();
        demoSpecificTypes();
        demoWildcards();
        demoCovariance();
    }
    
    /**
     * Demuestra el comportamiento de raw types
     */
    private static void demoRawTypes() {
        System.out.println("1. RAW TYPES (Sin genéricos):");
        System.out.println("   Comportamiento por defecto - usa Object\n");
        
        List listOfAnyObjects = new ArrayList(); // Raw type
        System.out.println("   List listOfAnyObjects = new ArrayList(); // Raw type");
        
        // Puedes añadir cualquier cosa
        listOfAnyObjects.add("String");
        listOfAnyObjects.add(42);
        listOfAnyObjects.add(new Object());
        System.out.println("   ✅ Puedes añadir cualquier tipo: String, Integer, Object");
        
        // Pero necesitas casting para leer
        String str = (String) listOfAnyObjects.get(0);
        Integer num = (Integer) listOfAnyObjects.get(1);
        System.out.println("   ⚠️ Necesitas casting manual: " + str + ", " + num);
        
        // Peligro: ClassCastException en runtime
        try {
            Integer wrongCast = (Integer) listOfAnyObjects.get(0); // String a Integer
            System.out.println("   ❌ Esto causaría ClassCastException");
        } catch (ClassCastException e) {
            System.out.println("   💥 ClassCastException: " + e.getMessage());
        }
        System.out.println();
    }
    
    /**
     * Demuestra el comportamiento de tipos específicos
     */
    private static void demoSpecificTypes() {
        System.out.println("2. TIPOS ESPECÍFICOS:");
        System.out.println("   Máxima seguridad de tipos\n");
        
        // Lista de Object
        List<Object> listOfAnyObjects = new ArrayList<>();
        System.out.println("   List<Object> listOfAnyObjects = new ArrayList<>();");
        
        listOfAnyObjects.add("String");
        listOfAnyObjects.add(42);
        listOfAnyObjects.add(new Object());
        System.out.println("   ✅ Puedes añadir cualquier tipo a List<Object>");
        
        // Acceso directo sin casting
        String str = (String) listOfAnyObjects.get(0); // Necesitas casting porque es Object
        System.out.println("   ⚠️ Acceso con casting: " + str);
        
        // Lista de Product
        List<Product> listOfProducts = new ArrayList<>();
        System.out.println("   List<Product> listOfProducts = new ArrayList<>();");
        
        listOfProducts.add(new Food("Pizza", 12.99));
        listOfProducts.add(new Drink("Coffee", 3.99));
        System.out.println("   ✅ Puedes añadir Product y subtipos");
        
        // Acceso directo
        Product product = listOfProducts.get(0);
        System.out.println("   ✅ Acceso directo sin casting: " + product);
        System.out.println();
    }
    
    /**
     * Demuestra el comportamiento de wildcards
     */
    private static void demoWildcards() {
        System.out.println("3. WILDCARDS <?>:");
        System.out.println("   Tipo desconocido - solo lectura\n");
        
        List<String> stringList = new ArrayList<>();
        stringList.add("Hello");
        stringList.add("World");
        
        // Asignación covariante
        List<?> unknown = stringList;
        System.out.println("   List<String> stringList = new ArrayList<>();");
        System.out.println("   List<?> unknown = stringList; // Asignación covariante");
        
        // ✅ Puedes leer elementos
        Object first = unknown.get(0);
        Object second = unknown.get(1);
        System.out.println("   ✅ Puedes leer elementos: " + first + ", " + second);
        
        // ❌ NO puedes añadir elementos (excepto null)
        try {
            // unknown.add("New String"); // Esto NO compilaría
            System.out.println("   ❌ unknown.add(\"New String\"); // Error de compilación");
        } catch (Exception e) {
            System.out.println("   💥 Error: " + e.getMessage());
        }
        
        // ✅ Solo null es permitido
        unknown.add(null);
        System.out.println("   ✅ unknown.add(null); // Solo null es permitido");
        
        // Verificación de tamaño
        System.out.println("   📏 Tamaño de la lista: " + unknown.size());
        System.out.println();
    }
    
    /**
     * Demuestra la covarianza de los wildcards
     */
    private static void demoCovariance() {
        System.out.println("4. COVARIANZA DE WILDCARDS:");
        System.out.println("   Asignación de tipos relacionados\n");
        
        // Crear listas específicas
        List<Food> foodList = new ArrayList<>();
        foodList.add(new Food("Pizza", 12.99));
        foodList.add(new Food("Burger", 12.99));
        
        List<Drink> drinkList = new ArrayList<>();
        drinkList.add(new Drink("Coffee", 3.99));
        drinkList.add(new Drink("Tea", 3.99));
        
        // ✅ Covarianza: puedes asignar List<Food> a List<?>
        List<?> unknown1 = foodList;
        System.out.println("   ✅ List<?> unknown1 = foodList; // Covariante");
        
        // ✅ Covarianza: puedes asignar List<Drink> a List<?>
        List<?> unknown2 = drinkList;
        System.out.println("   ✅ List<?> unknown2 = drinkList; // Covariante");
        
        // ✅ Puedes leer de ambas
        Object foodItem = unknown1.get(0);
        Object drinkItem = unknown2.get(0);
        System.out.println("   ✅ Lectura: " + foodItem + ", " + drinkItem);
        
        // ❌ Pero no puedes añadir a ninguna
        try {
            // unknown1.add(new Food("Salad")); // Error de compilación
            // unknown2.add(new Drink("Juice")); // Error de compilación
            System.out.println("   ❌ No puedes añadir elementos a wildcards");
        } catch (Exception e) {
            System.out.println("   💥 Error: " + e.getMessage());
        }
        
        System.out.println("\n=== RESUMEN ===");
        System.out.println("• Raw Types: Peligrosos, solo para legacy");
        System.out.println("• Tipos Específicos: Máxima seguridad y flexibilidad");
        System.out.println("• Wildcards <?>: Seguros para lectura, restringidos para escritura");
        System.out.println("• Covarianza: Permite asignar tipos relacionados a wildcards");
    }
}

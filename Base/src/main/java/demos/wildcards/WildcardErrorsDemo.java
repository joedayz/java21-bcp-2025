package demos.wildcards;

import demos.genericos.Drink;
import demos.genericos.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo que muestra los errores de compilación específicos de wildcards.
 * 
 * IMPORTANTE: Este archivo contiene código que NO compilará.
 * Los errores están comentados para demostración.
 */
public class WildcardErrorsDemo {
    
    public static void main(String[] args) {
        System.out.println("=== DEMO: Errores de Wildcards ===\n");
        
        demoCompilationErrors();
        demoRuntimeBehavior();
    }
    
    /**
     * Demuestra errores de compilación con wildcards
     */
    private static void demoCompilationErrors() {
        System.out.println("1. ERRORES DE COMPILACIÓN:");
        System.out.println("   Código que NO compilará\n");
        
        List<String> stringList = new ArrayList<>();
        stringList.add("Hello");
        
        List<?> unknown = stringList;
        System.out.println("   List<String> stringList = new ArrayList<>();");
        System.out.println("   List<?> unknown = stringList;");
        
        // ❌ ERRORES DE COMPILACIÓN (comentados):
        
        // 1. No puedes añadir String a List<?>
        // unknown.add("New String"); // ❌ Error: cannot find symbol
        
        // 2. No puedes añadir Object a List<?>
        // unknown.add(new Object()); // ❌ Error: cannot find symbol
        
        // 3. No puedes añadir Integer a List<?>
        // unknown.add(42); // ❌ Error: cannot find symbol
        
        System.out.println("   ❌ unknown.add(\"New String\"); // Error de compilación");
        System.out.println("   ❌ unknown.add(new Object()); // Error de compilación");
        System.out.println("   ❌ unknown.add(42); // Error de compilación");
        
        // ✅ Solo null es permitido
        unknown.add(null);
        System.out.println("   ✅ unknown.add(null); // Solo esto es válido");
        System.out.println();
    }
    
    /**
     * Demuestra el comportamiento en tiempo de ejecución
     */
    private static void demoRuntimeBehavior() {
        System.out.println("2. COMPORTAMIENTO EN RUNTIME:");
        System.out.println("   Lo que SÍ funciona con wildcards\n");
        
        // Crear listas específicas
        List<Food> foodList = new ArrayList<>();
        foodList.add(new Food("Pizza"));
        foodList.add(new Food("Burger"));
        
        List<Drink> drinkList = new ArrayList<>();
        drinkList.add(new Drink("Coffee"));
        drinkList.add(new Drink("Tea"));
        
        // Asignar a wildcards
        List<?> unknown1 = foodList;
        List<?> unknown2 = drinkList;
        
        System.out.println("   List<Food> foodList = [Pizza, Burger]");
        System.out.println("   List<Drink> drinkList = [Coffee, Tea]");
        System.out.println("   List<?> unknown1 = foodList;");
        System.out.println("   List<?> unknown2 = drinkList;");
        
        // ✅ Puedes leer elementos
        Object food1 = unknown1.get(0);
        Object food2 = unknown1.get(1);
        Object drink1 = unknown2.get(0);
        Object drink2 = unknown2.get(1);
        
        System.out.println("   ✅ Lectura de unknown1: " + food1 + ", " + food2);
        System.out.println("   ✅ Lectura de unknown2: " + drink1 + ", " + drink2);
        
        // ✅ Puedes usar métodos que no modifican la lista
        int size1 = unknown1.size();
        int size2 = unknown2.size();
        boolean isEmpty1 = unknown1.isEmpty();
        boolean isEmpty2 = unknown2.isEmpty();
        
        System.out.println("   ✅ Tamaño unknown1: " + size1 + ", vacía: " + isEmpty1);
        System.out.println("   ✅ Tamaño unknown2: " + size2 + ", vacía: " + isEmpty2);
        
        // ✅ Puedes añadir null
        unknown1.add(null);
        unknown2.add(null);
        System.out.println("   ✅ Añadido null a ambas listas");
        System.out.println("   📏 Nuevo tamaño unknown1: " + unknown1.size());
        System.out.println("   📏 Nuevo tamaño unknown2: " + unknown2.size());
        
        System.out.println("\n=== RESUMEN DE RESTRICCIONES ===");
        System.out.println("❌ NO puedes añadir elementos (excepto null)");
        System.out.println("❌ NO puedes usar métodos que modifiquen la lista");
        System.out.println("✅ SÍ puedes leer elementos");
        System.out.println("✅ SÍ puedes usar métodos de consulta (size, isEmpty, etc.)");
        System.out.println("✅ SÍ puedes añadir null");
    }
}

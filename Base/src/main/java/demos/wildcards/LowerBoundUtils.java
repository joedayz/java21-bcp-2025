package demos.wildcards;

import demos.genericos.Drink;
import demos.genericos.Food;
import demos.genericos.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase utilitaria que demuestra casos de uso prácticos del Lower Bound Wildcard.
 * 
 * Los lower bound wildcards son perfectos para métodos que necesitan escribir
 * en colecciones de tipos relacionados (jerarquía de herencia).
 */
public class LowerBoundUtils {
    
    /**
     * Añade todos los elementos de una lista fuente a una lista destino.
     * Usa lower bound wildcard porque necesita escribir en la lista destino.
     */
    public static <T> void addAll(List<? super T> destination, List<T> source) {
        for (T item : source) {
            destination.add(item);
        }
        System.out.println("   📥 Añadidos " + source.size() + " elementos a la lista destino");
    }
    
    /**
     * Añade un elemento a múltiples listas.
     * Usa lower bound wildcard porque necesita escribir en las listas.
     */
    public static <T> void addToMultipleLists(T item, List<? super T>... lists) {
        for (List<? super T> list : lists) {
            list.add(item);
        }
        System.out.println("   📥 Añadido " + item + " a " + lists.length + " listas");
    }
    
    /**
     * Copia elementos de una lista a otra.
     * Usa lower bound wildcard porque necesita escribir en la lista destino.
     */
    public static <T> void copyElements(List<T> source, List<? super T> destination) {
        destination.clear();
        for (T item : source) {
            destination.add(item);
        }
        System.out.println("   📋 Copiados " + source.size() + " elementos");
    }
    
    /**
     * Filtra y añade elementos que cumplen una condición.
     * Usa lower bound wildcard porque necesita escribir en la lista resultado.
     */
    public static <T> void filterAndAdd(List<T> source, List<? super T> result, java.util.function.Predicate<T> predicate) {
        for (T item : source) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        System.out.println("   🔍 Filtrados y añadidos elementos que cumplen la condición");
    }
    
    /**
     * Añade elementos por defecto a una lista si está vacía.
     * Usa lower bound wildcard porque necesita escribir en la lista.
     */
    public static <T> void addDefaultsIfEmpty(List<? super T> list, T... defaults) {
        if (list.isEmpty()) {
            for (T item : defaults) {
                list.add(item);
            }
            System.out.println("   📝 Añadidos " + defaults.length + " elementos por defecto");
        } else {
            System.out.println("   ℹ️ Lista no vacía, no se añaden elementos por defecto");
        }
    }
    
    /**
     * Demo que muestra el uso práctico de los métodos con lower bound wildcard
     */
    public static void demoPracticalUsage() {
        System.out.println("=== CASOS DE USO PRÁCTICOS DE LOWER BOUND WILDCARD ===\n");
        
        // Crear diferentes tipos de listas
        List<Product> allProducts = new ArrayList<>();
        List<Food> foods = new ArrayList<>();
        List<Drink> drinks = new ArrayList<>();
        
        // Crear elementos de prueba
        Food pizza = new Food("Pizza", 12.99);
        Food burger = new Food("Burger", 8.99);
        Drink coffee = new Drink("Coffee", 3.99);
        Drink tea = new Drink("Tea", 2.99);
        
        System.out.println("1. Añadiendo elementos a múltiples listas:");
        List<Food> foodSource = List.of(pizza, burger);
        List<Drink> drinkSource = List.of(coffee, tea);
        
        addToMultipleLists(pizza, foods, allProducts);
        addToMultipleLists(coffee, drinks, allProducts);
        
        System.out.println("\n2. Copiando elementos entre listas:");
        copyElements(foodSource, allProducts);
        System.out.println("   📏 Tamaño de allProducts después de copiar: " + allProducts.size());
        
        System.out.println("\n3. Añadiendo todos los elementos de una lista a otra:");
        List<Product> moreProducts = new ArrayList<>();
        moreProducts.add(new Product("Generic Product", 5.99));
        
        addAll(allProducts, moreProducts);
        System.out.println("   📏 Tamaño final de allProducts: " + allProducts.size());
        
        System.out.println("\n4. Filtrando y añadiendo elementos:");
        List<Product> expensiveProducts = new ArrayList<>();
        filterAndAdd(allProducts, expensiveProducts, product -> product.getPrice() > 5.0);
        System.out.println("   📏 Productos caros encontrados: " + expensiveProducts.size());
        
        System.out.println("\n5. Añadiendo elementos por defecto si la lista está vacía:");
        List<Food> emptyFoodList = new ArrayList<>();
        addDefaultsIfEmpty(emptyFoodList, new Food("Default Food", 1.99));
        System.out.println("   📏 Tamaño después de añadir por defecto: " + emptyFoodList.size());
        
        addDefaultsIfEmpty(foods, new Food("Another Default", 2.99));
        System.out.println("   📏 Tamaño de foods (no vacía): " + foods.size());
        
        System.out.println("\n=== VENTAJAS DE LOWER BOUND WILDCARD ===");
        System.out.println("✅ Un solo método funciona con el tipo y todos sus supertipos");
        System.out.println("✅ Puedes escribir elementos de forma segura");
        System.out.println("✅ Flexibilidad para trabajar con jerarquías de herencia");
        System.out.println("✅ Seguridad de tipos en tiempo de compilación");
        System.out.println("✅ No necesitas sobrecargar métodos para cada supertipo");
        System.out.println("✅ Perfecto para métodos de escritura y modificación");
    }
}

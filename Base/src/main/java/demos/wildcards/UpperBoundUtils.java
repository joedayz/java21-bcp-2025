package demos.wildcards;

import demos.genericos.Drink;
import demos.genericos.Food;
import demos.genericos.Product;

import java.util.List;

/**
 * Clase utilitaria que demuestra casos de uso prácticos del Upper Bound Wildcard.
 * 
 * Los upper bound wildcards son perfectos para métodos que necesitan trabajar
 * con colecciones de tipos relacionados (jerarquía de herencia).
 */
public class UpperBoundUtils {
    
    /**
     * Calcula el precio total de una lista de productos.
     * Usa upper bound wildcard porque puede trabajar con Product y cualquier subtipo.
     */
    public static double calculateTotalPrice(List<? extends Product> products) {
        double total = 0.0;
        for (Product product : products) {
            // Aquí podemos usar métodos de Product
            total += product.getPrice();
        }
        return total;
    }
    
    /**
     * Encuentra el producto más caro de una lista.
     * Usa upper bound wildcard porque puede trabajar con Product y cualquier subtipo.
     */
    public static Product findMostExpensive(List<? extends Product> products) {
        if (products.isEmpty()) {
            return null;
        }
        
        Product mostExpensive = products.get(0);
        for (Product product : products) {
            if (product.getPrice() > mostExpensive.getPrice()) {
                mostExpensive = product;
            }
        }
        return mostExpensive;
    }
    
    /**
     * Imprime información de todos los productos.
     * Usa upper bound wildcard porque puede trabajar con Product y cualquier subtipo.
     */
    public static void printProductInfo(List<? extends Product> products) {
        System.out.println("   📋 Información de productos:");
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.println("      [" + i + "] " + product.getName() + " - $" + product.getPrice());
        }
    }
    
    /**
     * Cuenta cuántos productos hay de un tipo específico.
     * Usa upper bound wildcard porque puede trabajar con Product y cualquier subtipo.
     */
    public static int countByType(List<? extends Product> products, Class<?> type) {
        int count = 0;
        for (Product product : products) {
            if (type.isInstance(product)) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Verifica si todos los productos tienen un precio mayor a un valor mínimo.
     * Usa upper bound wildcard porque puede trabajar con Product y cualquier subtipo.
     */
    public static boolean allProductsAbovePrice(List<? extends Product> products, double minPrice) {
        for (Product product : products) {
            if (product.getPrice() <= minPrice) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Demo que muestra el uso práctico de los métodos con upper bound wildcard
     */
    public static void demoPracticalUsage() {
        System.out.println("=== CASOS DE USO PRÁCTICOS DE UPPER BOUND WILDCARD ===\n");
        
        // Crear diferentes tipos de listas
        List<Product> allProducts = List.of(
            new Product("Generic Product", 5.99),
            new Food("Pizza", 12.99),
            new Drink("Coffee", 3.99)
        );
        
        List<Food> foods = List.of(
            new Food("Burger", 8.99),
            new Food("Salad", 6.99),
            new Food("Pasta", 11.99)
        );
        
        List<Drink> drinks = List.of(
            new Drink("Tea", 2.99),
            new Drink("Juice", 4.99),
            new Drink("Soda", 1.99)
        );
        
        System.out.println("1. Calculando precios totales:");
        System.out.println("   Total productos: $" + calculateTotalPrice(allProducts));
        System.out.println("   Total comidas: $" + calculateTotalPrice(foods));
        System.out.println("   Total bebidas: $" + calculateTotalPrice(drinks));
        
        System.out.println("\n2. Encontrando productos más caros:");
        System.out.println("   Más caro (productos): " + findMostExpensive(allProducts));
        System.out.println("   Más caro (comidas): " + findMostExpensive(foods));
        System.out.println("   Más caro (bebidas): " + findMostExpensive(drinks));
        
        System.out.println("\n3. Imprimiendo información:");
        printProductInfo(allProducts);
        printProductInfo(foods);
        printProductInfo(drinks);
        
        System.out.println("\n4. Contando por tipo:");
        System.out.println("   Foods en productos: " + countByType(allProducts, Food.class));
        System.out.println("   Drinks en productos: " + countByType(allProducts, Drink.class));
        System.out.println("   Foods en foods: " + countByType(foods, Food.class));
        System.out.println("   Drinks en drinks: " + countByType(drinks, Drink.class));
        
        System.out.println("\n5. Verificando precios mínimos:");
        System.out.println("   Todos productos > $5: " + allProductsAbovePrice(allProducts, 5.0));
        System.out.println("   Todos comidas > $7: " + allProductsAbovePrice(foods, 7.0));
        System.out.println("   Todas bebidas > $2: " + allProductsAbovePrice(drinks, 2.0));
        
        System.out.println("\n=== VENTAJAS DE UPPER BOUND WILDCARD ===");
        System.out.println("✅ Un solo método funciona con Product y todos sus subtipos");
        System.out.println("✅ Puedes usar métodos de la clase base (Product)");
        System.out.println("✅ Flexibilidad para trabajar con jerarquías de herencia");
        System.out.println("✅ Seguridad de tipos en tiempo de compilación");
        System.out.println("✅ No necesitas sobrecargar métodos para cada subtipo");
    }
}

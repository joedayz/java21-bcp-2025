package demos.wildcards;

import java.util.List;

/**
 * Clase utilitaria que demuestra casos de uso prácticos de wildcards.
 * 
 * Los wildcards son especialmente útiles para métodos que solo necesitan
 * leer de colecciones sin importar el tipo específico.
 */
public class WildcardUtils {
    
    /**
     * Imprime todos los elementos de cualquier lista.
     * Usa wildcard porque solo necesita leer elementos.
     */
    public static void printList(List<?> list) {
        System.out.println("   📋 Contenido de la lista:");
        for (int i = 0; i < list.size(); i++) {
            Object item = list.get(i);
            System.out.println("      [" + i + "] " + item);
        }
    }
    
    /**
     * Cuenta cuántos elementos tiene una lista.
     * Usa wildcard porque no necesita conocer el tipo específico.
     */
    public static int countElements(List<?> list) {
        return list.size();
    }
    
    /**
     * Verifica si una lista está vacía.
     * Usa wildcard porque no necesita conocer el tipo específico.
     */
    public static boolean isEmpty(List<?> list) {
        return list.isEmpty();
    }
    
    /**
     * Obtiene el primer elemento de una lista.
     * Usa wildcard porque retorna Object (tipo más general).
     */
    public static Object getFirstElement(List<?> list) {
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
    
    /**
     * Verifica si una lista contiene null.
     * Usa wildcard porque puede trabajar con cualquier tipo.
     */
    public static boolean containsNull(List<?> list) {
        for (Object item : list) {
            if (item == null) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Demo que muestra el uso práctico de los métodos con wildcards
     */
    public static void demoPracticalUsage() {
        System.out.println("=== CASOS DE USO PRÁCTICOS DE WILDCARDS ===\n");
        
        // Crear diferentes tipos de listas
        List<Food> foodList = List.of(new Food("Pizza"), new Food("Burger"));
        List<Drink> drinkList = List.of(new Drink("Coffee"), new Drink("Tea"));
        List<String> stringList = List.of("Hello", "World");
        List<Integer> numberList = List.of(1, 2, 3, 4, 5);
        
        System.out.println("1. Imprimiendo listas de diferentes tipos:");
        printList(foodList);
        printList(drinkList);
        printList(stringList);
        printList(numberList);
        
        System.out.println("\n2. Contando elementos:");
        System.out.println("   Food list: " + countElements(foodList) + " elementos");
        System.out.println("   Drink list: " + countElements(drinkList) + " elementos");
        System.out.println("   String list: " + countElements(stringList) + " elementos");
        System.out.println("   Number list: " + countElements(numberList) + " elementos");
        
        System.out.println("\n3. Verificando si están vacías:");
        System.out.println("   Food list vacía: " + isEmpty(foodList));
        System.out.println("   Drink list vacía: " + isEmpty(drinkList));
        System.out.println("   String list vacía: " + isEmpty(stringList));
        System.out.println("   Number list vacía: " + isEmpty(numberList));
        
        System.out.println("\n4. Obteniendo primer elemento:");
        System.out.println("   Primer food: " + getFirstElement(foodList));
        System.out.println("   Primer drink: " + getFirstElement(drinkList));
        System.out.println("   Primer string: " + getFirstElement(stringList));
        System.out.println("   Primer number: " + getFirstElement(numberList));
        
        System.out.println("\n5. Verificando si contienen null:");
        System.out.println("   Food list contiene null: " + containsNull(foodList));
        System.out.println("   Drink list contiene null: " + containsNull(drinkList));
        System.out.println("   String list contiene null: " + containsNull(stringList));
        System.out.println("   Number list contiene null: " + containsNull(numberList));
        
        System.out.println("\n=== VENTAJAS DE WILDCARDS ===");
        System.out.println("✅ Un solo método funciona con cualquier tipo de lista");
        System.out.println("✅ No necesitas sobrecargar métodos para cada tipo");
        System.out.println("✅ Código más limpio y mantenible");
        System.out.println("✅ Seguridad de tipos en tiempo de compilación");
    }
}

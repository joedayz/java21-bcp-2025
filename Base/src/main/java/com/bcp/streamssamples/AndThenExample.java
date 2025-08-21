package com.bcp.streamssamples;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;

public class AndThenExample {

    public static void main(String[] args) {
        System.out.println("=== EJEMPLO DE ANDTHEN EN STREAMS ===");
        System.out.println("Calculando la suma de las longitudes de los nombres de productos (después de trim).");
        System.out.println("Basado en el código de la imagen.\n");

        // Crear una lista de productos
        List<Product> products = new ArrayList<>();
        products.add(new Product("  Laptop  ", 999.99));
        products.add(new Product("Mouse ", 25.50));
        products.add(new Product(" Keyboard", 89.99));
        products.add(new Product("Monitor", 299.99));
        products.add(new Product(" Headphones ", 15.99));

        System.out.println("Lista original de productos:");
        products.forEach(p -> System.out.println("  - '" + p.getName() + "'"));
        System.out.println();

        // 1. Function para obtener el nombre del producto
        Function<Product, String> nameMapper = p -> p.getName();
        System.out.println("1. nameMapper: Product -> String (p.getName())");

        // 2. UnaryOperator para quitar espacios en blanco (trim)
        UnaryOperator<String> trimMapper = n -> n.trim();
        System.out.println("2. trimMapper: String -> String (n.trim())");

        // 3. ToIntFunction para obtener la longitud de un String
        ToIntFunction<String> lengthMapper = n -> n.length();
        System.out.println("3. lengthMapper: String -> int (n.length())\n");

        // Pipeline de Stream usando andThen
        // list.stream().map(nameMapper.andThen(trimMapper)).mapToInt(lengthMapper).sum();
        System.out.println("Aplicando la pipeline: list.stream().map(nameMapper.andThen(trimMapper)).mapToInt(lengthMapper).sum();");

        int totalLength = products.stream()
                                  .map(nameMapper.andThen(trimMapper)) // Primero obtiene el nombre, luego lo trimea
                                  .peek(trimmedName -> System.out.println("  Nombre trimeado: '" + trimmedName + "' (longitud: " + trimmedName.length() + ")")) // Para depuración
                                  .mapToInt(lengthMapper)             // Convierte el stream de String a IntStream (longitudes)
                                  .sum();                             // Suma todas las longitudes

        System.out.println("\nLa suma total de las longitudes de los nombres trimeados es: " + totalLength);

        // Ejemplo adicional: Composición de múltiples funciones
        System.out.println("\n=== EJEMPLO ADICIONAL: COMPOSICIÓN MÚLTIPLE ===");
        
        // Crear funciones adicionales
        Function<String, String> upperCaseMapper = String::toUpperCase;
        Function<String, String> addPrefix = s -> "Producto: " + s;
        
        System.out.println("Pipeline con múltiples transformaciones:");
        System.out.println("Product -> String (nombre) -> String (trim) -> String (mayúsculas) -> String (con prefijo)");
        
        products.stream()
                .map(nameMapper.andThen(trimMapper).andThen(upperCaseMapper).andThen(addPrefix))
                .forEach(System.out::println);

        // Ejemplo con ToIntFunction y composición
        System.out.println("\n=== EJEMPLO CON TOINTFUNCTION ===");
        
        // Crear funciones que devuelven int
        Function<String, Integer> lengthAsInteger = String::length;
        ToIntFunction<String> lengthAsInt = String::length;
        
        System.out.println("Comparación entre Function<String, Integer> y ToIntFunction<String>:");
        
        // Usando Function (con boxing)
        int sumWithFunction = products.stream()
                .map(nameMapper.andThen(trimMapper))
                .map(lengthAsInteger)
                .mapToInt(Integer::intValue)
                .sum();
        
        // Usando ToIntFunction (sin boxing)
        int sumWithToIntFunction = products.stream()
                .map(nameMapper.andThen(trimMapper))
                .mapToInt(lengthAsInt)
                .sum();
        
        System.out.println("Suma usando Function: " + sumWithFunction);
        System.out.println("Suma usando ToIntFunction: " + sumWithToIntFunction);
        System.out.println("¿Son iguales? " + (sumWithFunction == sumWithToIntFunction));

        // Ejemplo práctico: Cálculo de estadísticas
        System.out.println("\n=== EJEMPLO PRÁCTICO: ESTADÍSTICAS DE NOMBRES ===");
        
        // Crear funciones para análisis de nombres
        Function<String, Integer> wordCount = s -> s.split("\\s+").length;
        Function<String, Boolean> hasSpaces = s -> s.contains(" ");
        
        System.out.println("Estadísticas de los nombres de productos:");
        
        // Contar palabras en nombres trimeados
        int totalWords = products.stream()
                .map(nameMapper.andThen(trimMapper))
                .mapToInt(wordCount::apply)
                .sum();
        
        // Contar productos con espacios en el nombre
        long productsWithSpaces = products.stream()
                .map(nameMapper.andThen(trimMapper))
                .filter(hasSpaces::apply)
                .count();
        
        System.out.println("Total de palabras en todos los nombres: " + totalWords);
        System.out.println("Productos con espacios en el nombre: " + productsWithSpaces);
    }
} 
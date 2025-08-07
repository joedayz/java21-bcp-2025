# Paquete CollectorsSamples - Ejemplos de Collectors Básicos

Este paquete contiene ejemplos que demuestran los tres tipos básicos de `Collectors` en Java Streams, tal como se muestra en la imagen de referencia.

## Archivos del Paquete

### 1. `Product.java`
Clase base que representa un producto:
- `name`: Nombre del producto
- `price`: Precio del producto (BigDecimal)
- `category`: Categoría del producto (String)
- Getters y setters

### 2. `Food.java`
Subclase de `Product` para productos alimenticios.

### 3. `Drink.java`
Subclase de `Product` para bebidas.

### 4. `BasicCollectorsExample.java`
Ejemplo principal que demuestra los tres tipos básicos de Collectors.

## Los Tres Tipos Básicos de Collectors

### 1. **Calculating Summary Values** 🟢
**Descripción:** "Calculating summary values such as average, min, max, count, sum."

**Ejemplo del código:**
```java
DoubleSummaryStatistics stats = list.stream()
    .collect(Collectors.summarizingDouble(p -> p.getPrice().doubleValue()));
```

**Características:**
- Calcula estadísticas completas de valores numéricos
- Proporciona: count, sum, min, max, average
- Útil para análisis de datos y reportes

**Métodos relacionados:**
- `summarizingDouble()` - Estadísticas completas
- `counting()` - Conteo de elementos
- `summingDouble()` - Suma de valores
- `averagingDouble()` - Promedio de valores
- `maxBy()` / `minBy()` - Valores extremos

### 2. **Mapping and Joining Stream Elements** 🟠
**Descripción:** "Mapping and joining stream elements."

**Ejemplo del código:**
```java
String s1 = list.stream()
    .collect(Collectors.mapping(p -> p.getName(), Collectors.joining(",")));
```

**Características:**
- Transforma elementos y los une en una cadena
- Permite personalizar separadores
- Útil para generación de reportes y texto

**Métodos relacionados:**
- `mapping()` + `joining()` - Transformar y unir
- `joining()` - Unir elementos en cadena
- `joining(delimiter)` - Con separador personalizado
- `joining(delimiter, prefix, suffix)` - Con prefijo y sufijo

### 3. **Gathering Stream Elements into a Collection** 🔴
**Descripción:** "Gathering stream elements into a collection such as list, set, or map."

**Ejemplo del código:**
```java
List<Product> drinks = list.stream()
    .filter(p -> p instanceof Drink)
    .collect(Collectors.toList());
```

**Características:**
- Recolecta elementos en diferentes tipos de colecciones
- Permite filtrado antes de recolectar
- Útil para organización y agrupación de datos

**Métodos relacionados:**
- `toList()` - Recolectar en List
- `toSet()` - Recolectar en Set
- `toMap()` - Recolectar en Map
- `toCollection()` - Recolectar en colección específica

## Ejemplos Avanzados Incluidos

### Agrupación y Análisis
```java
// Agrupar por categoría
var groupedByCategory = list.stream()
    .collect(Collectors.groupingBy(Product::getCategory));

// Contar por categoría
var countByCategory = list.stream()
    .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));

// Suma de precios por categoría
var sumByCategory = list.stream()
    .collect(Collectors.groupingBy(Product::getCategory, 
        Collectors.summingDouble(p -> p.getPrice().doubleValue())));
```

### Transformaciones Avanzadas
```java
// Nombres con precios
String namesWithPrices = list.stream()
    .collect(Collectors.mapping(p -> p.getName() + "($" + p.getPrice() + ")", 
        Collectors.joining(", ")));

// Nombres por categoría
String namesByCategory = list.stream()
    .collect(Collectors.mapping(p -> p.getCategory() + ": " + p.getName(), 
        Collectors.joining("; ")));
```

## Casos de Uso Comunes

### Análisis de Datos
```java
// Estadísticas de ventas
DoubleSummaryStatistics salesStats = orders.stream()
    .collect(Collectors.summarizingDouble(Order::getTotal));
```

### Generación de Reportes
```java
// Lista de productos por categoría
String report = products.stream()
    .collect(Collectors.mapping(Product::getName, Collectors.joining("\n")));
```

### Filtrado y Agrupación
```java
// Productos agrupados por precio
Map<String, List<Product>> byPriceRange = products.stream()
    .collect(Collectors.groupingBy(p -> 
        p.getPrice().compareTo(BigDecimal.valueOf(50)) > 0 ? "Caro" : "Barato"));
```

### Agregación
```java
// Totales por categoría
Map<String, Double> totalsByCategory = products.stream()
    .collect(Collectors.groupingBy(Product::getCategory,
        Collectors.summingDouble(p -> p.getPrice().doubleValue())));
```

## Ventajas de los Collectors

- **Flexibilidad**: Múltiples formas de recolectar y procesar datos
- **Eficiencia**: Operaciones optimizadas y especializadas
- **Legibilidad**: Código más expresivo y declarativo
- **Reutilización**: Collectors se pueden combinar y reutilizar
- **Funcional**: Enfoque funcional vs imperativo

## Cómo Ejecutar

```bash
# Compilar
javac com/bcp/collectorsamples/*.java

# Ejecutar ejemplo principal
java com.bcp.collectorsamples.BasicCollectorsExample
```

## Salida Esperada

El ejemplo mostrará:
1. **Summary Values**: Estadísticas completas de precios
2. **Mapping and Joining**: Nombres concatenados de diferentes formas
3. **Gathering**: Listas filtradas de productos
4. **Ejemplos Avanzados**: Agrupación, conteo y agregación por categorías

## Mejores Prácticas

1. **Usa el Collector apropiado** para cada tipo de operación
2. **Combina Collectors** para operaciones complejas
3. **Aprovecha method references** cuando sea posible
4. **Considera el rendimiento** para streams grandes
5. **Usa tipos primitivos** para operaciones numéricas
6. **Mantén la legibilidad** del código
7. **Reutiliza Collectors** en diferentes contextos
8. **Documenta operaciones complejas** con comentarios

## Referencias

- **Java Stream API**: Documentación oficial de Java
- **Collectors Class**: Métodos y ejemplos disponibles
- **Functional Programming**: Conceptos de programación funcional
- **Data Processing**: Patrones de procesamiento de datos 
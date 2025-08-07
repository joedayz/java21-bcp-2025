# Paquete ConcurrentStreams - Ejemplos de Streams Paralelos y Concurrencia

Este paquete contiene ejemplos que demuestran el manejo correcto e incorrecto de streams paralelos en Java, específicamente enfocado en la modificación de recursos compartidos y el uso de `Collectors` thread-safe.

## Archivos del Paquete

### 1. `Product.java`
Clase base que representa un producto:
- `name`: Nombre del producto
- `price`: Precio del producto (BigDecimal)
- `category`: Categoría del producto (String)
- Getters y setters

### 2. `ConcurrentStreamsExample.java`
Ejemplo principal que demuestra los problemas de concurrencia y las soluciones correctas.

## Los Tres Ejemplos Principales

### 1. **PROBLEMÁTICO: Modificar recursos compartidos con forEach** ⚠️
**Descripción:** Ejemplo que muestra el uso incorrecto de `forEach` en streams paralelos.

**Código problemático:**
```java
List<BigDecimal> prices = new ArrayList<>();
list.stream()
    .parallel()
    .peek(p->System.out.println(p))
    .map(p->p.getPrice())
    .forEach(p->prices.add(p));
```

**Problemas:**
- ❌ **Condiciones de carrera**: Múltiples hilos modifican la misma `ArrayList`
- ❌ **Resultados impredecibles**: El número de elementos puede variar entre ejecuciones
- ❌ **Data corruption**: Posible pérdida o duplicación de datos
- ❌ **Uso incorrecto de peek**: Para efectos secundarios en streams paralelos

### 2. **CORRECTO: Usar Collectors.toList()** ✅
**Descripción:** Ejemplo que muestra el uso correcto de `collect()` con `toList()`.

**Código correcto:**
```java
List<BigDecimal> correctPrices = list.stream()
    .parallel()
    .map(p->p.getPrice())
    .collect(Collectors.toList());
```

**Ventajas:**
- ✅ **Thread-safe**: `Collectors.toList()` maneja la concurrencia internamente
- ✅ **Resultados consistentes**: Siempre produce el mismo número de elementos
- ✅ **Sin condiciones de carrera**: Cada hilo trabaja con su propia colección temporal
- ✅ **Eficiente**: Combina resultados de múltiples hilos de forma optimizada

### 3. **CORRECTO: Usar Collectors.toConcurrentMap()** ✅
**Descripción:** Ejemplo que muestra el uso correcto de `toConcurrentMap()` para mapas en streams paralelos.

**Código correcto:**
```java
Map<String, BigDecimal> namesAndPrices = list.stream()
    .parallel()
    .collect(Collectors.toConcurrentMap(p->p.getName(), p->p.getPrice()));
```

**Ventajas:**
- ✅ **Thread-safe para mapas**: `toConcurrentMap()` usa `ConcurrentHashMap` internamente
- ✅ **Eficiente**: Optimizado para operaciones concurrentes
- ✅ **Sin bloqueos**: Permite acceso concurrente sin sincronización explícita
- ✅ **Resultados consistentes**: Garantiza integridad de datos

## Ejemplos Adicionales de Collectors Thread-Safe

### toConcurrentMap con Merge Function
```java
Map<String, BigDecimal> categoryPrices = list.stream()
    .parallel()
    .collect(Collectors.toConcurrentMap(
        p -> p.getCategory(),
        p -> p.getPrice(),
        (existing, replacement) -> existing.add(replacement)
    ));
```

### groupingByConcurrent
```java
Map<String, List<Product>> productsByCategory = list.stream()
    .parallel()
    .collect(Collectors.groupingByConcurrent(p -> p.getCategory()));
```

### Counting y Summing con Parallel
```java
long totalProducts = list.stream()
    .parallel()
    .collect(Collectors.counting());

double totalPrice = list.stream()
    .parallel()
    .collect(Collectors.summingDouble(p -> p.getPrice().doubleValue()));
```

## Comparación de Rendimiento

### Secuencial vs Paralelo
```java
// Secuencial
List<BigDecimal> sequentialPrices = list.stream()
    .map(p -> p.getPrice())
    .collect(Collectors.toList());

// Paralelo
List<BigDecimal> parallelPrices = list.stream()
    .parallel()
    .map(p -> p.getPrice())
    .collect(Collectors.toList());
```

**Consideraciones:**
- **Overhead**: Los streams paralelos tienen overhead de inicialización
- **Dataset size**: Para datasets pequeños, el overhead puede superar los beneficios
- **CPU cores**: El rendimiento mejora con más núcleos disponibles
- **Task complexity**: Operaciones complejas se benefician más de la paralelización

## Problemas Comunes y Soluciones

### ❌ Problemas Comunes
1. **Usar forEach para modificar colecciones compartidas**
2. **Usar peek para efectos secundarios en streams paralelos**
3. **Modificar variables externas desde lambdas en streams paralelos**
4. **Usar colecciones no thread-safe en streams paralelos**

### ✅ Soluciones Recomendadas
1. **Usar collect() con Collectors thread-safe**
2. **Usar toConcurrentMap() para mapas en streams paralelos**
3. **Usar groupingByConcurrent() para agrupación paralela**
4. **Evitar efectos secundarios en streams paralelos**
5. **Usar reduce() para operaciones de reducción thread-safe**

## Ejemplos Prácticos

### Análisis Complejo con Streams Paralelos
```java
Map<String, Map<String, Object>> analysis = list.stream()
    .parallel()
    .collect(Collectors.groupingByConcurrent(
        p -> p.getCategory(),
        Collectors.collectingAndThen(
            Collectors.toList(),
            products -> {
                Map<String, Object> stats = new ConcurrentHashMap<>();
                stats.put("count", products.size());
                stats.put("totalPrice", products.stream()
                    .mapToDouble(p -> p.getPrice().doubleValue())
                    .sum());
                stats.put("avgPrice", products.stream()
                    .mapToDouble(p -> p.getPrice().doubleValue())
                    .average()
                    .orElse(0.0));
                return stats;
            }
        )
    ));
```

## Casos de Uso Comunes

### Análisis de Datos
- Procesamiento de grandes volúmenes de datos
- Cálculos estadísticos paralelos
- Agregación de datos por categorías

### Procesamiento de Archivos
- Lectura paralela de archivos grandes
- Procesamiento de logs en paralelo
- Análisis de datos de entrada

### Operaciones de Base de Datos
- Procesamiento paralelo de resultados de consultas
- Agregación de datos de múltiples fuentes
- Transformación de datos en paralelo

## Mejores Prácticas

### Cuándo Usar Streams Paralelos
1. **Datasets grandes**: Más de 10,000 elementos
2. **Operaciones costosas**: Cálculos complejos o I/O
3. **Múltiples núcleos**: Sistemas con 4+ núcleos disponibles
4. **Operaciones independientes**: Sin dependencias entre elementos

### Cuándo NO Usar Streams Paralelos
1. **Datasets pequeños**: Menos de 1,000 elementos
2. **Operaciones simples**: Operaciones básicas de mapeo
3. **Orden importante**: Cuando el orden de procesamiento es crítico
4. **Efectos secundarios**: Cuando se requieren efectos secundarios

### Patrones Recomendados
1. **Siempre usar collect()**: En lugar de forEach para recolectar resultados
2. **Usar Collectors thread-safe**: toConcurrentMap, groupingByConcurrent
3. **Evitar estado mutable**: No modificar variables externas
4. **Medir rendimiento**: Comparar secuencial vs paralelo en tu caso específico

## Cómo Ejecutar

```bash
# Compilar
javac com/bcp/concurrentstreams/*.java

# Ejecutar ejemplo principal
java com.bcp.concurrentstreams.ConcurrentStreamsExample
```

## Salida Esperada

El ejemplo mostrará:
1. **Problemas de concurrencia**: Resultados inconsistentes con forEach
2. **Soluciones correctas**: Resultados consistentes con collect()
3. **Ejemplos adicionales**: Diferentes Collectors thread-safe
4. **Comparación de rendimiento**: Secuencial vs paralelo
5. **Análisis práctico**: Ejemplo complejo de análisis de datos

## Referencias

- **Java Stream API**: Documentación oficial de Java
- **Collectors Class**: Métodos thread-safe disponibles
- **Concurrent Programming**: Conceptos de programación concurrente
- **Performance Tuning**: Optimización de rendimiento con streams 
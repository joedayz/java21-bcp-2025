# Paquete Interfaces - Ejemplos de Ordenamiento Avanzado con Comparator

Este paquete contiene ejemplos de ordenamiento avanzado usando `Comparator` con lambda expressions, incluyendo ordenamiento encadenado, reversión y manejo de valores nulos.

## Archivos del Paquete

### 1. `Product.java`
Clase base que implementa `Comparable<Product>`:
- `name`: Nombre del producto
- `price`: Precio del producto (BigDecimal)
- Método `compareTo()` para ordenamiento natural por nombre
- Getters y setters

### 2. `Food.java`
Subclase de `Product` para productos alimenticios:
- Hereda de `Product`
- Constructor que acepta `BigDecimal` o `double` para el precio
- Método `toString()` personalizado

### 3. `Drink.java`
Subclase de `Product` para bebidas:
- Hereda de `Product`
- Constructor que acepta `BigDecimal` o `double` para el precio
- Método `toString()` personalizado

### 4. `AdvancedSortingExample.java`
Ejemplo principal que demuestra ordenamiento avanzado:

## Ejemplos de Ordenamiento

### Definición de Comparadores con Lambda
```java
Comparator<Product> sortNames = (p1, p2) -> p1.getName().compareTo(p2.getName());
Comparator<Product> sortPrices = (p1, p2) -> p1.getPrice().compareTo(p2.getPrice());
```

### Ordenamiento Encadenado con Reversión
```java
Collections.sort(menu, sortNames.thenComparing(sortPrices).reversed());
```
**Explicación:**
1. `sortNames`: ordena por nombre (ascendente)
2. `thenComparing(sortPrices)`: si los nombres son iguales, ordena por precio (ascendente)
3. `reversed()`: invierte todo el orden (descendente)

### Manejo de Valores Nulos
```java
// Nulos al principio
Collections.sort(menu, Comparator.nullsFirst(sortNames));

// Nulos al final
Collections.sort(menu, Comparator.nullsLast(sortNames));
```

### Ordenamiento Múltiple Complejo
```java
// Ordenamiento: primero por nombre (ascendente), luego por precio (descendente)
Collections.sort(menu, sortNames.thenComparing(sortPrices.reversed()));
```

### Comparadores con Method References
```java
Comparator<Product> sortNamesMR = Comparator.comparing(Product::getName);
Comparator<Product> sortPricesMR = Comparator.comparing(Product::getPrice);
Collections.sort(menu, sortNamesMR.thenComparing(sortPricesMR));
```

## Características del Ejemplo

### 1. **Ordenamiento Encadenado**
- Permite ordenar por múltiples criterios
- Si el primer criterio es igual, usa el segundo criterio
- Se puede encadenar tantos criterios como sea necesario

### 2. **Reversión de Orden**
- `.reversed()` invierte el orden del comparador
- Se puede aplicar a comparadores individuales o encadenados

### 3. **Manejo de Valores Nulos**
- `nullsFirst()`: coloca valores nulos al principio
- `nullsLast()`: coloca valores nulos al final
- Evita `NullPointerException` durante el ordenamiento

### 4. **Method References**
- Forma más concisa de crear comparadores
- `Comparator.comparing(Product::getName)` es equivalente a `(p1, p2) -> p1.getName().compareTo(p2.getName())`

## Cómo Ejecutar

```bash
# Compilar
javac com/bcp/interfaces/*.java

# Ejecutar el ejemplo
java com.bcp.interfaces.AdvancedSortingExample
```

## Ventajas del Ordenamiento con Comparator

1. **Flexibilidad**: Permite múltiples criterios de ordenamiento
2. **Reutilización**: Los comparadores se pueden reutilizar
3. **Composición**: Se pueden combinar comparadores
4. **Manejo de nulos**: Incluye estrategias para manejar valores nulos
5. **Legibilidad**: El código es más claro y expresivo

## Casos de Uso Comunes

- **E-commerce**: Ordenar productos por categoría, precio, nombre
- **Reportes**: Ordenar datos por múltiples columnas
- **Interfaces de usuario**: Ordenar listas por diferentes criterios
- **Análisis de datos**: Ordenar resultados por múltiples métricas 
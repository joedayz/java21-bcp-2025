# Functional Interfaces Demo

Este paquete demuestra el concepto de interfaces funcionales en Java y cómo usarlas con clases anónimas, mostrando ejemplos prácticos de ordenamiento y otras operaciones funcionales.

## Clases

### Product.java
Clase de producto que se usa en los ejemplos de ordenamiento:

```java
public class Product {
    private String name;
    private BigDecimal price;
    // getters, setters, constructor
}
```

### SortingDemo.java
Demo principal que muestra ordenamiento con clases anónimas:

- **Ordenamiento por nombre** usando `Comparator<Product>` con clase anónima
- **Ordenamiento por precio** usando `Comparator<Product>` con clase anónima
- **Comparación con lambdas** para mostrar la evolución
- **Ejemplos adicionales** de ordenamiento

### FunctionalInterfacesDemo.java
Demo que muestra interfaces funcionales del JDK:

- **Consumer<T>**: Consume un valor, no retorna nada
- **Supplier<T>**: Proporciona un valor
- **Predicate<T>**: Evalúa una condición
- **Function<T,R>**: Transforma un valor
- **Uso práctico** con listas de productos

### CustomFunctionalInterfaces.java
Ejemplo de interfaces funcionales personalizadas:

- **DiscountCalculator**: Calcula descuentos para productos
- **ProductValidator**: Valida productos según criterios
- **ProductFormatter**: Formatea productos para display
- **ProductProcessor**: Procesa productos
- **Comparación con lambdas**

## Conceptos Clave

### Functional Interfaces
- **Tienen exactamente un método abstracto**
- **Pueden tener métodos default y estáticos**
- **Se pueden usar con lambdas** (Java 8+)
- **Se pueden usar con clases anónimas** (Java 1.1+)

### @FunctionalInterface
- **Anotación opcional** que documenta la intención
- **Garantiza** que la interfaz tenga exactamente un método abstracto
- **Error de compilación** si se agregan más métodos abstractos

### Interfaces Funcionales del JDK
| Interfaz | Método | Propósito |
|----------|--------|-----------|
| `Consumer<T>` | `void accept(T t)` | Consume un valor |
| `Supplier<T>` | `T get()` | Proporciona un valor |
| `Predicate<T>` | `boolean test(T t)` | Evalúa una condición |
| `Function<T,R>` | `R apply(T t)` | Transforma un valor |
| `Comparator<T>` | `int compare(T o1, T o2)` | Compara dos valores |

## Ejecutar los Demos

### Demo de Ordenamiento:
```bash
javac src/main/java/com/bcp/functionalinterface/*.java
java -cp src/main/java com.bcp.functionalinterface.SortingDemo
```

### Interfaces Funcionales del JDK:
```bash
javac src/main/java/com/bcp/functionalinterface/*.java
java -cp src/main/java com.bcp.functionalinterface.FunctionalInterfacesDemo
```

### Interfaces Funcionales Personalizadas:
```bash
javac src/main/java/com/bcp/functionalinterface/*.java
java -cp src/main/java com.bcp.functionalinterface.CustomFunctionalInterfaces
```

## Ejemplo del Código (como en la imagen)

### Ordenamiento por Nombre:
```java
Collections.sort(products, new Comparator<Product>() {
    public int compare(Product p1, Product p2) {
        return p1.getName().compareTo(p2.getName());
    }
});
```

### Ordenamiento por Precio:
```java
Collections.sort(products, new Comparator<Product>() {
    public int compare(Product p1, Product p2) {
        return p1.getPrice().compareTo(p2.getPrice());
    }
});
```

### Con Lambda (Java 8+):
```java
// Por nombre
Collections.sort(products, (p1, p2) -> p1.getName().compareTo(p2.getName()));

// Por precio
Collections.sort(products, (p1, p2) -> p1.getPrice().compareTo(p2.getPrice()));
```

## Salida Esperada

Los demos mostrarán:
1. Ordenamiento de productos por nombre usando clases anónimas
2. Ordenamiento de productos por precio usando clases anónimas
3. Comparación con lambdas para mostrar la evolución
4. Interfaces funcionales del JDK con ejemplos prácticos
5. Interfaces funcionales personalizadas
6. Características y ventajas de las interfaces funcionales
7. Casos de uso y mejores prácticas

## Casos de Uso Comunes

### Interfaces Funcionales del JDK
- **Consumer**: Procesamiento de elementos en streams
- **Supplier**: Generación de valores o instancias
- **Predicate**: Filtrado de elementos
- **Function**: Transformación de datos
- **Comparator**: Ordenamiento de colecciones

### Interfaces Funcionales Personalizadas
- **Cálculos específicos**: Descuentos, impuestos, etc.
- **Validaciones**: Reglas de negocio específicas
- **Formateo**: Presentación de datos
- **Procesamiento**: Lógica de negocio

## Ventajas

### Interfaces Funcionales
- **Código más expresivo**: Nombres descriptivos para operaciones
- **Reutilización**: Lógica que se puede reutilizar
- **Flexibilidad**: Diferentes implementaciones para el mismo contrato
- **Compatibilidad**: Funcionan con clases anónimas y lambdas
- **Testing**: Fácil de testear de forma aislada

## Evolución: Clases Anónimas → Lambdas

### Antes de Java 8 (Clases Anónimas):
```java
Comparator<Product> comparator = new Comparator<Product>() {
    @Override
    public int compare(Product p1, Product p2) {
        return p1.getName().compareTo(p2.getName());
    }
};
```

### Java 8+ (Lambdas):
```java
Comparator<Product> comparator = (p1, p2) -> p1.getName().compareTo(p2.getName());
```

### Java 8+ (Referencias de Método):
```java
Comparator<Product> comparator = Comparator.comparing(Product::getName);
```

## Mejores Prácticas

### Diseño de Interfaces Funcionales
- **Un solo método abstracto**: Garantiza que sea funcional
- **Nombres descriptivos**: Indica claramente el propósito
- **@FunctionalInterface**: Documenta la intención
- **Métodos default**: Para funcionalidad común
- **Métodos estáticos**: Para utilidades

### Uso de Interfaces Funcionales
- **Clases anónimas**: Para lógica compleja o múltiples métodos
- **Lambdas**: Para lógica simple y expresiva
- **Referencias de método**: Para delegación directa
- **Composición**: Combinar múltiples funciones

## Diferencias con Otros Tipos de Interfaces

| Característica | Interfaz Normal | Interfaz Funcional |
|----------------|-----------------|-------------------|
| Métodos abstractos | Múltiples | Exactamente uno |
| Lambdas | No compatible | Compatible |
| Clases anónimas | Compatible | Compatible |
| Propósito | Contrato general | Operación específica |
| Uso | Herencia múltiple | Comportamiento funcional | 
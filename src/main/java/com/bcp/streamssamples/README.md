# Paquete StreamsSamples - Ejemplos Avanzados de Java Streams

Este paquete contiene ejemplos completos que demuestran las capacidades avanzadas de Java Streams, incluyendo streams infinitos, operaciones de short-circuiting, interfaces funcionales primitivas, composición de funciones, alternativas a flatMap y diferentes formas de crear y procesar streams.

## Archivos del Paquete

### 1. `Product.java`
Clase base que representa un producto:
- `name`: Nombre del producto
- `price`: Precio del producto (BigDecimal)
- `discount`: Descuento aplicado (double)
- Getters y setters

### 2. `Food.java`
Subclase de `Product` para productos alimenticios.

### 3. `Drink.java`
Subclase de `Product` para bebidas.

### 4. `StreamVsLoopExample.java`
Comparación entre bucles tradicionales y Java Streams.

### 5. `StreamOperationsExample.java`
Operaciones básicas y avanzadas de streams.

### 6. `AdvancedStreamExamples.java`
Ejemplos avanzados basados en el código de la imagen.

### 7. `SingleArgumentStreamExample.java`
Ejemplos de interfaces funcionales de un solo argumento.

### 8. `AndThenExample.java`
Ejemplos de composición de funciones usando `andThen()`.

### 9. `AlternativeToFlatMapExample.java`
Alternativas a `flatMap` para procesar estructuras anidadas.

### 10. `TerminalOperationsExample.java`
**NUEVO** - Operaciones terminales de matching y finding (`allMatch`, `anyMatch`, `noneMatch`, `findAny`, `findFirst`).

## Operaciones Terminales de Matching y Finding

### Operaciones de Matching
Estas operaciones verifican condiciones sobre los elementos del stream y devuelven un `boolean`.

#### 1. **allMatch(Predicate<T>)**
Verifica si **TODOS** los elementos cumplen la condición.
```java
String[] values = {"RED", "GREEN", "BLUE"};
boolean allGreen = Arrays.stream(values).allMatch(s -> s.equals("GREEN"));
// Resultado: false (no todos son "GREEN")
```

#### 2. **anyMatch(Predicate<T>)**
Verifica si **AL MENOS UNO** cumple la condición.
```java
boolean anyGreen = Arrays.stream(values).anyMatch(s -> s.equals("GREEN"));
// Resultado: true (hay al menos un "GREEN")
```

#### 3. **noneMatch(Predicate<T>)**
Verifica si **NINGUNO** cumple la condición.
```java
boolean noneGreen = Arrays.stream(values).noneMatch(s -> s.equals("GREEN"));
// Resultado: false (hay al menos un "GREEN")
```

### Operaciones de Finding
Estas operaciones encuentran elementos específicos y devuelven un `Optional<T>`.

#### 4. **findAny()**
Encuentra **CUALQUIER** elemento del stream (no garantiza cuál).
```java
Optional<String> anyColour = Arrays.stream(values).findAny();
// Resultado: Optional["RED"] (o cualquier otro color)
```

#### 5. **findFirst()**
Encuentra el **PRIMER** elemento en el orden del stream.
```java
Optional<String> firstColour = Arrays.stream(values).findFirst();
// Resultado: Optional["RED"] (el primer elemento)
```

### Características Importantes

- **Short-circuiting**: Estas operaciones pueden terminar antes de procesar todo el stream
- **Optional**: Las operaciones de finding devuelven `Optional` para manejar streams vacíos
- **Predicate**: Las operaciones de matching usan `Predicate<T>` para las condiciones
- **Orden**: `findFirst()` respeta el orden, `findAny()` no garantiza orden específico

### Casos de Uso Comunes

#### Validación de Datos
```java
// Verificar que todos los productos tienen precio positivo
boolean allValid = products.stream()
    .allMatch(p -> p.getPrice().compareTo(BigDecimal.ZERO) > 0);
```

#### Búsqueda de Elementos
```java
// Encontrar el primer producto con descuento
Optional<Product> discountedProduct = products.stream()
    .filter(p -> p.getDiscount() > 0)
    .findFirst();
```

#### Verificación de Condiciones
```java
// Verificar si hay productos caros
boolean hasExpensiveProducts = products.stream()
    .anyMatch(p -> p.getPrice().compareTo(BigDecimal.valueOf(100)) > 0);
```

## Alternativas a FlatMap

### ¿Qué es FlatMap?
`flatMap` es una operación que "aplana" múltiples streams en un solo stream. Es útil para procesar estructuras de datos anidadas.

### Ejemplo con FlatMap
```java
// Con flatMap (forma elegante)
double total = orders.stream()
    .flatMap(order -> order.getItems().stream())
    .filter(item -> "Tea".equals(item.getName()))
    .mapToDouble(item -> item.getPrice().doubleValue())
    .sum();
```

### Alternativas a FlatMap

#### 1. **Bucle Tradicional**
```java
double total = 0.0;
for (Order order : orders) {
    for (Product item : order.getItems()) {
        if ("Tea".equals(item.getName())) {
            total += item.getPrice().doubleValue();
        }
    }
}
```

#### 2. **Streams con Map y Collect**
```java
List<Double> teaPrices = new ArrayList<>();
orders.stream()
    .map(Order::getItems)
    .forEach(productList -> 
        productList.stream()
            .filter(item -> "Tea".equals(item.getName()))
            .mapToDouble(item -> item.getPrice().doubleValue())
            .forEach(teaPrices::add)
    );
double total = teaPrices.stream().mapToDouble(Double::doubleValue).sum();
```

#### 3. **Streams con Reduce**
```java
double total = orders.stream()
    .map(Order::getItems)
    .reduce(0.0, (total, productList) -> {
        double orderTotal = productList.stream()
            .filter(item -> "Tea".equals(item.getName()))
            .mapToDouble(item -> item.getPrice().doubleValue())
            .sum();
        return total + orderTotal;
    }, Double::sum);
```

#### 4. **Streams con ForEach**
```java
final double[] total = {0.0};
orders.stream()
    .map(Order::getItems)
    .forEach(productList -> 
        productList.stream()
            .filter(item -> "Tea".equals(item.getName()))
            .forEach(item -> total[0] += item.getPrice().doubleValue())
    );
```

### Ventajas y Desventajas

| Método | Ventajas | Desventajas |
|--------|----------|-------------|
| **FlatMap** | Elegante, funcional, legible | Puede ser menos intuitivo |
| **Bucle Tradicional** | Simple, directo, fácil de entender | Imperativo, más código |
| **Map + Collect** | Funcional, reutilizable | Más verboso, múltiples pasos |
| **Reduce** | Funcional, eficiente | Complejo para casos simples |
| **ForEach** | Funcional, directo | Usa array mutable |

## Composición de Funciones con andThen()

### Concepto Básico
El método `andThen()` permite encadenar múltiples funciones en una sola operación, creando un pipeline de transformaciones.

### Ejemplo del Código de la Imagen
```java
Function<Product, String> nameMapper = p -> p.getName();
UnaryOperator<String> trimMapper = n -> n.trim();
ToIntFunction<String> lengthMapper = n -> n.length();

int totalLength = list.stream()
    .map(nameMapper.andThen(trimMapper))
    .mapToInt(lengthMapper)
    .sum();
```

**Explicación:**
1. `nameMapper` extrae el nombre del producto
2. `trimMapper` quita espacios en blanco del nombre
3. `lengthMapper` obtiene la longitud del nombre trimeado
4. Se suman todas las longitudes

### Ventajas de andThen()
- **Composición funcional**: Encadena transformaciones de forma elegante
- **Reutilización**: Las funciones se pueden reutilizar en diferentes contextos
- **Legibilidad**: El código es más claro y expresivo
- **Mantenibilidad**: Fácil de modificar y extender

## Interfaces Funcionales de Un Solo Argumento

### Consumer<T>
**Propósito:** Realizar una acción en cada elemento (no devuelve nada)
```java
Consumer<Product> printName = p -> System.out.println(p.getName());
products.forEach(printName);
```

### Predicate<T>
**Propósito:** Filtrar elementos (devuelve boolean)
```java
Predicate<Product> isExpensive = p -> p.getPrice().compareTo(BigDecimal.valueOf(100)) > 0;
products.stream().filter(isExpensive).forEach(System.out::println);
```

### Function<T,R>
**Propósito:** Transformar elementos (devuelve resultado de tipo R)
```java
Function<Product, String> getUpperCaseName = p -> p.getName().toUpperCase();
products.stream().map(getUpperCaseName).forEach(System.out::println);
```

### UnaryOperator<T>
**Propósito:** Transformar elementos del mismo tipo (T -> T)
```java
UnaryOperator<String> trim = s -> s.trim();
UnaryOperator<String> upperCase = String::toUpperCase;
```

## Interfaces Funcionales Primitivas

### Para evitar auto-boxing/unboxing:

#### Salida Primitiva
- `ToIntFunction<T>` - T -> int
- `ToLongFunction<T>` - T -> long  
- `ToDoubleFunction<T>` - T -> double

#### Entrada Primitiva
- `IntPredicate` - int -> boolean
- `IntConsumer` - int -> void
- `IntFunction<R>` - int -> R

#### Entrada y Salida Primitivas
- `IntUnaryOperator` - int -> int
- `IntToDoubleFunction` - int -> double

#### Supplier Primitivo
- `IntSupplier` - () -> int
- `DoubleSupplier` - () -> double

## Ejemplos del Código de la Imagen

### 1. Stream Infinito con takeWhile
```java
IntStream.generate(() -> (int) (Math.random() * 10))
    .takeWhile(n -> n != 3)
    .sum();
```

### 2. Stream de Objetos con forEach
```java
Stream.of(new Food(), new Drink())
    .forEach(p -> p.setPrice(1));
```

### 3. Stream Paralelo con mapToDouble
```java
list.stream().parallel()
    .mapToDouble(p -> p.getPrice())
    .sum();
```

### 4. Arrays.stream con Filter
```java
Arrays.stream(array)
    .filter(p -> p.getPrice() > 2)
    .forEach(p -> p.setDiscount(0.1));
```

## Operaciones Avanzadas Demostradas

### Streams Infinitos
- `IntStream.generate()` - Generación infinita
- `IntStream.iterate()` - Iteración infinita
- `Stream.generate()` - Stream genérico infinito

### Operaciones de Short-Circuiting
- `takeWhile()` - Toma elementos mientras se cumple condición
- `dropWhile()` - Descarta elementos mientras se cumple condición
- `findFirst()` - Encuentra el primer elemento
- `findAny()` - Encuentra cualquier elemento

### Streams Especializados
- `IntStream` - Para operaciones con enteros
- `LongStream` - Para operaciones con longs
- `DoubleStream` - Para operaciones con doubles

### Operaciones de Conversión
- `mapToInt()` - Convierte a IntStream
- `mapToLong()` - Convierte a LongStream
- `mapToDouble()` - Convierte a DoubleStream

### Composición de Funciones
- `andThen()` - Encadena funciones
- `compose()` - Composición inversa
- Múltiples transformaciones en un solo pipeline

### Procesamiento de Estructuras Anidadas
- `flatMap()` - Aplana streams anidados
- Alternativas a `flatMap()` - Diferentes enfoques
- Comparación de métodos

## Diferentes Formas de Crear Streams

### 1. Desde Colecciones
```java
list.stream()
```

### 2. Desde Arrays
```java
Arrays.stream(array)
```

### 3. Desde Elementos Específicos
```java
Stream.of("a", "b", "c")
```

### 4. Streams Infinitos
```java
IntStream.iterate(1, n -> n + 1)
Stream.generate(() -> "element")
```

### 5. Streams de Rangos
```java
IntStream.range(1, 10)
IntStream.rangeClosed(1, 10)
```

## Ventajas de los Streams Avanzados

### 1. **Procesamiento Infinito**
- Permite trabajar con flujos de datos infinitos
- Operaciones de short-circuiting para control

### 2. **Optimización Automática**
- Lazy evaluation
- Procesamiento paralelo cuando es beneficioso

### 3. **Flexibilidad**
- Múltiples formas de crear streams
- Operaciones especializadas por tipo

### 4. **Rendimiento**
- Procesamiento paralelo automático
- Operaciones optimizadas para tipos primitivos

### 5. **Interfaces Funcionales Eficientes**
- Evita auto-boxing/unboxing
- Mejor rendimiento con tipos primitivos

### 6. **Composición de Funciones**
- Encadenamiento elegante de transformaciones
- Código más modular y reutilizable

### 7. **Procesamiento de Estructuras Anidadas**
- Múltiples enfoques para el mismo problema
- Flexibilidad en el estilo de programación

## Casos de Uso Comunes

### Procesamiento de Datos en Tiempo Real
```java
IntStream.generate(() -> sensor.read())
    .takeWhile(value -> value < threshold)
    .forEach(System.out::println);
```

### Análisis de Datos
```java
data.stream()
    .parallel()
    .mapToDouble(DataPoint::getValue)
    .summaryStatistics();
```

### Generación de Secuencias
```java
IntStream.iterate(0, n -> n + 2)
    .limit(10)
    .forEach(System.out::println);
```

### Filtrado y Transformación
```java
products.stream()
    .filter(p -> p.getPrice() > 100)
    .map(p -> p.getName().toUpperCase())
    .forEach(System.out::println);
```

### Composición de Transformaciones
```java
products.stream()
    .map(nameMapper.andThen(trimMapper).andThen(upperCaseMapper))
    .forEach(System.out::println);
```

### Procesamiento de Estructuras Anidadas
```java
// Con flatMap
orders.stream()
    .flatMap(order -> order.getItems().stream())
    .filter(item -> "Tea".equals(item.getName()))
    .mapToDouble(item -> item.getPrice().doubleValue())
    .sum();

// Sin flatMap (bucle tradicional)
double total = 0.0;
for (Order order : orders) {
    for (Product item : order.getItems()) {
        if ("Tea".equals(item.getName())) {
            total += item.getPrice().doubleValue();
        }
    }
}
```

## Cómo Ejecutar

```bash
# Compilar
javac com/bcp/streamssamples/*.java

# Ejecutar comparación streams vs bucles
java com.bcp.streamssamples.StreamVsLoopExample

# Ejecutar operaciones básicas
java com.bcp.streamssamples.StreamOperationsExample

# Ejecutar ejemplos avanzados
java com.bcp.streamssamples.AdvancedStreamExamples

# Ejecutar ejemplos de interfaces funcionales de un argumento
java com.bcp.streamssamples.SingleArgumentStreamExample

# Ejecutar ejemplos de composición con andThen
java com.bcp.streamssamples.AndThenExample

# Ejecutar alternativas a flatMap
java com.bcp.streamssamples.AlternativeToFlatMapExample

# Ejecutar operaciones terminales de matching y finding
java com.bcp.streamssamples.TerminalOperationsExample
```

## Mejores Prácticas

1. **Usa streams especializados** para tipos primitivos
2. **Aprovecha el procesamiento paralelo** para operaciones intensivas
3. **Utiliza operaciones de short-circuiting** para streams infinitos
4. **Considera el rendimiento** al elegir entre secuencial y paralelo
5. **Usa lazy evaluation** para optimizar el procesamiento
6. **Prefiere interfaces funcionales primitivas** para evitar boxing/unboxing
7. **Combina interfaces funcionales** para pipelines complejos
8. **Usa method references** cuando sea posible para mayor legibilidad
9. **Aprovecha andThen()** para composición de funciones
10. **Mantén las funciones pequeñas y reutilizables**
11. **Elige el método apropiado** para procesar estructuras anidadas
12. **Considera la legibilidad** al elegir entre flatMap y alternativas 
# Paquete Lambda - Ejemplos de Expresiones Lambda en Java

Este paquete contiene ejemplos prácticos de cómo usar expresiones lambda en Java, específicamente para ordenamiento de objetos.

## Archivos del Paquete

### 1. `Product.java`
Clase modelo que representa un producto con:
- `name`: Nombre del producto
- `price`: Precio del producto
- Getters y setters
- Constructor
- Método `toString()` para mostrar la información

### 2. `SimpleLambdaExample.java`
Ejemplo básico que muestra la evolución del código:

**Contexto de Invocación:**
```java
List<Product> products = ...;
Collections.sort(products, <Comparator>);
```

**Clase Anónima (Java tradicional):**
```java
Collections.sort(products, new Comparator<Product>() {
    public int compare(Product p1, Product p2) {
        return p1.getPrice().compareTo(p2.getPrice());
    }
});
```

**Lambda Expression (Java 8+):**
```java
Collections.sort(products, (p1, p2) -> p1.getPrice().compareTo(p2.getPrice()));
```

### 3. `LambdaSortingExample.java`
Ejemplo más completo con múltiples variaciones de ordenamiento usando lambda expressions.

## Ventajas de las Lambda Expressions

1. **Código más conciso**: Reduce la verbosidad del código
2. **Mejor legibilidad**: La intención del código es más clara
3. **Menos boilerplate**: No necesitas escribir clases anónimas completas
4. **Funcional**: Permite programación funcional en Java

## Cómo Ejecutar

```bash
# Compilar
javac com/bcp/lambda/*.java

# Ejecutar ejemplo simple
java com.bcp.lambda.SimpleLambdaExample

# Ejecutar ejemplo completo
java com.bcp.lambda.LambdaSortingExample
```

## Sintaxis de Lambda

```java
// Forma básica
(parámetros) -> expresión

// Para Comparator
(p1, p2) -> p1.getPrice().compareTo(p2.getPrice())

// Con múltiples líneas
(p1, p2) -> {
    // lógica compleja aquí
    return resultado;
}
``` 
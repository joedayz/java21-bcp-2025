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

### 4. `LambdaVariationsExample.java`
Ejemplo que demuestra diferentes sintaxis de lambda expressions:

**Comparator con lambda:**
```java
Comparator<String> sortText = (s1, s2) -> s1.compareTo(s2);
```

**removeIf con diferentes sintaxis:**
```java
list.removeIf((final String s) -> s.equals("remove me"));
list.removeIf((final var s) -> s.equals("remove me"));
list.removeIf(s -> s.equals("remove me"));
```

**sort con lambda y bloque:**
```java
list.sort((s1, s2) -> { 
    return s1.compareTo(s2); 
});
```

**Collections.sort con comparator predefinido:**
```java
Collections.sort(list, sortText);
```

### 5. `TextFilter.java`
Clase utilitaria con métodos para filtrado y ordenamiento:

```java
public class TextFilter {
    public static boolean removeA(String s) {
        return s.equals("remove A");
    }

    public int sortText(String s1, String s2) {
        return s1.compareTo(s2);
    }
}
```

### 6. `TextFilterExample.java`
Ejemplo que demuestra **Method References** (referencias a métodos):

**removeIf con lambda vs method reference:**
```java
// Con lambda expression
list.removeIf(s -> TextFilter.removeA(s));

// Con method reference (más conciso)
list.removeIf(TextFilter::removeA); // mismo que la línea anterior
```

**Collections.sort con lambda vs method reference:**
```java
// Con lambda expression
Collections.sort(list, (s1, s2) -> filter.sortText(s1, s2));

// Con method reference (más conciso)
Collections.sort(list, filter::sortText); // mismo que la línea anterior

// Con lambda para String.compareToIgnoreCase
Collections.sort(list, (s1, s2) -> s1.compareToIgnoreCase(s2));

// Con method reference para String.compareToIgnoreCase
Collections.sort(list, String::compareToIgnoreCase); // mismo que la línea anterior
```

## Ventajas de las Lambda Expressions

1. **Código más conciso**: Reduce la verbosidad del código
2. **Mejor legibilidad**: La intención del código es más clara
3. **Menos boilerplate**: No necesitas escribir clases anónimas completas
4. **Funcional**: Permite programación funcional en Java

## Method References

Los **Method References** son una forma aún más concisa de escribir lambda expressions cuando la lambda simplemente llama a un método existente.

### Tipos de Method References:

1. **Método estático**: `Clase::metodoEstatico`
2. **Método de instancia**: `objeto::metodoInstancia`
3. **Método de instancia de clase**: `Clase::metodoInstancia`
4. **Constructor**: `Clase::new`

## Cómo Ejecutar

```bash
# Compilar
javac com/bcp/lambda/*.java

# Ejecutar ejemplo simple
java com.bcp.lambda.SimpleLambdaExample

# Ejecutar ejemplo completo
java com.bcp.lambda.LambdaSortingExample

# Ejecutar ejemplo con variaciones de sintaxis
java com.bcp.lambda.LambdaVariationsExample

# Ejecutar ejemplo con method references
java com.bcp.lambda.TextFilterExample
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

// Con parámetros tipados
(String s1, String s2) -> s1.compareTo(s2)

// Con var (Java 11+)
(var s1, var s2) -> s1.compareTo(s2)

// Con final
(final String s) -> s.equals("remove me")
```

## Sintaxis de Method References

```java
// Método estático
Clase::metodoEstatico

// Método de instancia
objeto::metodoInstancia

// Método de instancia de clase
Clase::metodoInstancia

// Constructor
Clase::new
``` 
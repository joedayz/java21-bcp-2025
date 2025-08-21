# Anotaciones Repetibles en Java

Este ejemplo demuestra cómo crear y usar anotaciones repetibles en Java, una característica introducida en Java 8.

## 📁 Archivos del Ejemplo

- **`BusinessPolicy.java`** - Anotación repetible principal
- **`BusinessPolicies.java`** - Anotación contenedora
- **`SomeBusinessClass.java`** - Clase que usa anotaciones repetibles
- **`AnnotationReader.java`** - Clase para leer anotaciones en tiempo de ejecución

## 🎯 Conceptos Clave

### Anotación Repetible
Una anotación que puede aplicarse múltiples veces a la misma declaración.

### Anotación Contenedora
Una anotación que contiene múltiples instancias de la anotación repetible.

## 📋 Estructura del Ejemplo

### 1. BusinessPolicy (Anotación Repetible)
```java
@Repeatable(BusinessPolicies.class)
public @interface BusinessPolicy {
    String name();
    String[] countries() default {};
    String value();
}
```

### 2. BusinessPolicies (Anotación Contenedora)
```java
public @interface BusinessPolicies {
    BusinessPolicy[] value();
}
```

### 3. Uso de Anotaciones

#### Forma 1: Anotaciones Repetibles Directas
```java
@BusinessPolicy(name = "Policy A", countries = {"US", "CA"}, value = "Rule1")
@BusinessPolicy(name = "Policy B", countries = {"EU"}, value = "Rule2")
public class SomeBusinessClass {
    // código de la clase
}
```

#### Forma 2: Usando el Contenedor (Equivalente)
```java
@BusinessPolicies({
    @BusinessPolicy(name = "Policy C", countries = {"JP"}, value = "Rule3"),
    @BusinessPolicy(name = "Policy D", countries = {"AU", "NZ"}, value = "Rule4")
})
class AnotherBusinessClass {
    // código de la clase
}
```

## 🚀 Cómo Ejecutar

```bash
# Compilar
mvn compile

# Ejecutar el ejemplo
mvn exec:java -Dexec.mainClass="com.bcp.anotaciones.AnnotationReader"
```

## 🔍 Salida Esperada

El programa mostrará:
1. Las anotaciones encontradas en `SomeBusinessClass`
2. Las anotaciones encontradas en `AnotherBusinessClass`
3. Cómo acceder a las anotaciones usando diferentes métodos de reflexión

## 💡 Puntos Importantes

1. **@Repeatable**: Indica que la anotación puede repetirse
2. **@Retention(RUNTIME)**: Permite acceso en tiempo de ejecución
3. **getAnnotationsByType()**: Método útil para obtener anotaciones repetibles
4. **Internamente**: Java convierte múltiples anotaciones en una contenedora

## 🎉 Beneficios

- **Legibilidad**: Código más limpio y legible
- **Flexibilidad**: Múltiples formas de aplicar anotaciones
- **Compatibilidad**: Funciona con código existente
- **Reflexión**: Fácil acceso en tiempo de ejecución

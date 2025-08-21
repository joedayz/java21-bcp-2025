# Anonymous Inner Classes Demo

Este paquete demuestra el concepto de clases anónimas internas (anonymous inner classes) en Java, que permiten crear implementaciones de clases o interfaces de forma inline sin necesidad de definir una clase separada.

## Clases

### Order.java
Clase base que define el método `getDiscount()`:

```java
public class Order {
    public BigDecimal getDiscount() {
        return BigDecimal.ZERO;
    }
}
```

### OnlineOrder.java
Implementación de clase separada que extiende `Order`:

```java
public class OnlineOrder extends Order {
    @Override
    public BigDecimal getDiscount() {
        return BigDecimal.valueOf(0.1);
    }
}
```

### AnonymousInnerClassDemo.java
Demo principal que muestra la comparación entre implementaciones:

- **Separate Class Implementation**: Usando `OnlineOrder` que extiende `Order`
- **Anonymous Inner Class Implementation**: Usando clase anónima inline
- Comparación de características y casos de uso
- Ejemplos prácticos adicionales

### InterfaceExample.java
Ejemplo que demuestra clases anónimas con interfaces:

- Implementación de `PaymentProcessor` con clase anónima
- Implementación de `OrderValidator` con clase anónima
- Múltiples implementaciones anónimas
- Uso en métodos

### LambdaComparison.java
Comparación entre clases anónimas y expresiones lambda:

- Interfaces funcionales vs no funcionales
- Sintaxis de clases anónimas vs lambdas
- Ventajas y desventajas de cada enfoque
- Casos de uso recomendados

## Conceptos Clave

### Anonymous Inner Classes
- **Definidas inline** durante la instanciación
- **No tienen nombre** explícito
- **Pueden extender clases** o implementar interfaces
- **Solo pueden sobrescribir métodos** existentes
- **No pueden ser reutilizadas** fuera del contexto donde se crean

### Sintaxis
```java
// Extendiendo una clase
Order order = new Order() {
    @Override
    public BigDecimal getDiscount() {
        return BigDecimal.valueOf(0.1);
    }
};

// Implementando una interfaz
PaymentProcessor processor = new PaymentProcessor() {
    @Override
    public void processPayment(double amount) {
        // implementación
    }
};
```

### Diferencias con Clases Separadas
| Característica | Separate Class | Anonymous Inner Class |
|----------------|----------------|---------------------|
| Nombre | Tiene nombre explícito | Sin nombre |
| Reutilización | Puede ser reutilizada | No reutilizable |
| Métodos adicionales | Puede tener métodos nuevos | Solo sobrescribir |
| Extensibilidad | Puede ser extendida | No puede ser extendida |
| Archivo | Archivo separado | Definida inline |
| Testing | Fácil de testear | Difícil de testear |

## Ejecutar los Demos

### Demo Principal:
```bash
javac src/main/java/com/bcp/anonymousinnerclass/*.java
java -cp src/main/java com.bcp.anonymousinnerclass.AnonymousInnerClassDemo
```

### Ejemplo con Interfaces:
```bash
javac src/main/java/com/bcp/anonymousinnerclass/*.java
java -cp src/main/java com.bcp.anonymousinnerclass.InterfaceExample
```

### Comparación con Lambdas:
```bash
javac src/main/java/com/bcp/anonymousinnerclass/*.java
java -cp src/main/java com.bcp.anonymousinnerclass.LambdaComparison
```

## Ejemplo del Código (como en la imagen)

### Separate Class Implementation:
```java
public class OnlineOrder extends Order {
    @Override
    public BigDecimal getDiscount() {
        return BigDecimal.valueOf(0.1);
    }
}
```

### Anonymous Inner Class Implementation:
```java
Order order = new Order() {
    @Override
    public BigDecimal getDiscount() {
        return BigDecimal.valueOf(0.1);
    }
};
```

## Salida Esperada

Los demos mostrarán:
1. Clase base `Order` con descuento cero
2. Implementación de clase separada `OnlineOrder`
3. Implementación de clase anónima inline
4. Comparación de características entre ambos enfoques
5. Casos de uso para cada tipo de implementación
6. Ejemplos con interfaces y lambdas
7. Ventajas y desventajas de cada enfoque

## Casos de Uso Comunes

### Clases Anónimas
- **Callbacks y listeners**: Implementar interfaces de callback de forma rápida
- **Implementaciones únicas**: Cuando solo necesitas la implementación una vez
- **Sobrescribir métodos específicos**: Para personalizar comportamiento
- **Testing**: Crear mocks o stubs rápidamente
- **Event handlers**: Manejar eventos de UI o sistema

### Cuándo Usar Cada Enfoque

**Usar Clase Separada cuando:**
- Necesitas reutilizar la implementación
- Quieres agregar métodos adicionales
- Necesitas crear una jerarquía de clases
- Quieres hacer testing unitario
- La implementación es compleja

**Usar Clase Anónima cuando:**
- Solo necesitas la implementación una vez
- Quieres código más conciso
- Necesitas sobrescribir métodos específicos
- Es una implementación simple
- No necesitas métodos adicionales

## Ventajas

### Clases Anónimas
- **Código más conciso**: No necesitas archivos separados
- **Implementación rápida**: Para casos únicos
- **Acceso a variables finales**: Pueden acceder a variables del contexto
- **Flexibilidad**: Pueden extender clases o implementar interfaces

## Desventajas

### Clases Anónimas
- **No reutilizables**: Solo existen en el contexto donde se crean
- **Difíciles de testear**: No pueden ser instanciadas independientemente
- **Código menos legible**: Para implementaciones complejas
- **No pueden ser extendidas**: Limitación de reutilización
- **Sintaxis verbosa**: Comparado con lambdas para interfaces funcionales

## Evolución a Lambdas

Las clases anónimas fueron el precursor de las expresiones lambda en Java 8. Para interfaces funcionales (con un solo método abstracto), las lambdas proporcionan una sintaxis más concisa:

```java
// Clase anónima
DiscountCalculator calculator = new DiscountCalculator() {
    @Override
    public double calculateDiscount(double amount) {
        return amount * 0.1;
    }
};

// Lambda equivalente
DiscountCalculator calculator = amount -> amount * 0.1;
``` 
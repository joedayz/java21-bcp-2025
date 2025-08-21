# Local Inner Classes Demo

Este paquete demuestra el concepto de clases anidadas locales (local inner classes) en Java, que están definidas dentro de métodos y pueden acceder a variables locales finales o efectivamente finales.

## Clases

### Order.java
Ejemplo principal de clase anidada local:

1. **Clase Externa** (`Order`)
   - Contiene un `Map<Integer, Item>` para almacenar items
   - Método `addItem(Product product, int quantity)` para agregar items
   - Método `manageTax(final String saleLocation)` que contiene la clase anidada local

2. **Clase Anidada Local** (`OrderTaxManager`)
   - Definida dentro del método `manageTax`
   - Puede acceder a `saleLocation` porque es final
   - Puede acceder a los miembros de la instancia de `Order`
   - Solo existe dentro del método `manageTax`

**Incluye método main** con ejemplos prácticos de uso.

### Product.java, Item.java
Clases de soporte para el ejemplo:

- **Product**: Clase para productos con nombre, precio y categoría
- **Item**: Clase que representa un item en la orden

### LocalInnerClassDemo.java
Demo que muestra el patrón conceptual de la imagen:

- Simula el diagrama conceptual mostrado en la imagen
- Demuestra la relación entre contextos de clase externa y clases anidadas locales
- Explica las características de las clases anidadas locales
- Muestra el patrón de diseño en acción

### FinalVariableDemo.java
Demo que demuestra la regla de variables finales:

- Muestra variables finales explícitas
- Muestra variables efectivamente finales
- Explica qué pasa con variables no finales
- Demuestra el uso de parámetros de método

## Conceptos Clave

### Local Inner Classes
- **Definidas dentro de métodos** de la clase externa
- **Pueden acceder** a variables locales finales o efectivamente finales
- **Pueden acceder** a todos los miembros de la instancia de la clase externa
- **Solo existen** dentro del método donde están definidas
- **Se crean y destruyen** con cada llamada al método

### Regla de Variables Finales o Efectivamente Finales
**🔴 Outer method local variables and parameters can only be accessed if they are final or effectively final.**

- **Variables finales**: Declaradas con `final`
- **Variables efectivamente finales**: No se modifican después de la inicialización
- **Parámetros de método**: Son efectivamente finales por defecto
- **Error de compilación**: Si se intenta acceder a variables no finales

### Diferencias con Otros Tipos de Clases Anidadas
| Característica | Local Inner Class | Member Inner Class | Static Nested Class |
|----------------|------------------|-------------------|-------------------|
| Ubicación | Dentro de método | Dentro de clase | Dentro de clase |
| Acceso a variables locales | Solo finales/efectivamente finales | No aplica | No aplica |
| Acceso a miembros de instancia | Sí | Sí | Solo estáticos |
| Existencia | Solo en el método | Con la instancia | Independiente |
| Reutilización | No | Sí | Sí |

## Ejecutar los Demos

### Ejemplo Order con OrderTaxManager:
```bash
javac src/main/java/com/bcp/localinnerclass/*.java
java -cp src/main/java com.bcp.localinnerclass.Order
```

### Patrón Conceptual:
```bash
javac src/main/java/com/bcp/localinnerclass/*.java
java -cp src/main/java com.bcp.localinnerclass.LocalInnerClassDemo
```

### Variables Finales:
```bash
javac src/main/java/com/bcp/localinnerclass/*.java
java -cp src/main/java com.bcp.localinnerclass.FinalVariableDemo
```

## Ejemplo del Código (como en la imagen)

```java
public void manageTax(final String saleLocation) {
    class OrderTaxManager {
        private BigDecimal findRate(Product product) {
            // use saleLocation and product to find the tax rate
        }
        
        public BigDecimal calculateTax() {
            // find tax rate in a given sale location for each product
            // calculate tax value
        }
    }
    
    OrderTaxManager taxManager = new OrderTaxManager();
    BigDecimal taxTotal = taxManager.calculateTax();
}
```

## Salida Esperada

Los demos mostrarán:
1. Creación de órdenes con productos
2. Gestión de impuestos usando clases anidadas locales
3. Acceso a variables finales desde la clase anidada local
4. Características de las clases anidadas locales
5. Regla de variables finales o efectivamente finales
6. Alcance y ciclo de vida de las clases anidadas locales
7. Diferencias con otros tipos de clases anidadas

## Casos de Uso Comunes

- **Lógica específica de método**: Cuando necesitas una clase solo para un método específico
- **Cálculos complejos**: Como el cálculo de impuestos basado en ubicación
- **Validaciones locales**: Clases que validan datos específicos del método
- **Temporalidad**: Cuando la clase solo se necesita durante la ejecución del método
- **Encapsulación de lógica**: Ocultar implementación compleja dentro del método

## Ventajas

- **Encapsulación**: La lógica está encapsulada dentro del método
- **Acceso a contexto local**: Puede usar variables del método
- **Temporalidad**: Se crea y destruye con el método
- **Simplicidad**: No contamina el espacio de nombres de la clase

## Desventajas

- **No reutilizable**: Solo existe dentro del método
- **Limitaciones de acceso**: Solo puede acceder a variables finales
- **Complejidad**: Puede hacer el método más complejo
- **Testing**: Más difícil de probar de forma aislada 
# Member Inner Classes Demo

Este paquete demuestra el concepto de clases anidadas de miembro (member inner classes) en Java, que están asociadas a instancias específicas de la clase externa.

## Clases

### Order.java
Ejemplo principal de clase anidada de miembro:

1. **Clase Externa** (`Order`)
   - Contiene un `Set<Item>` para almacenar items
   - Método `addItem(Product product, int quantity)` para agregar items
   - Métodos para calcular totales y mostrar la orden

2. **Clase Anidada de Miembro** (`Item`)
   - Está asociada a una instancia específica de `Order`
   - Constructor privado, solo accesible desde dentro de `Order`
   - Puede acceder a todos los miembros de la instancia de `Order`
   - Representa un item individual en la orden

**Incluye método main** con ejemplos prácticos de uso.

### Product.java, Drink.java, Food.java
Clases de soporte para el ejemplo:

- **Product**: Clase abstracta base para productos
- **Drink**: Extiende Product, representa bebidas
- **Food**: Extiende Product, representa comidas

### MemberInnerClassDemo.java
Demo que muestra el patrón conceptual de la imagen:

- Simula el diagrama conceptual mostrado en la imagen
- Demuestra la relación entre instancias de Order e Item
- Explica las características de las clases anidadas de miembro
- Muestra el patrón de diseño en acción

### ComparisonDemo.java
Demo comparativo entre tipos de clases anidadas:

- Compara Static Nested Classes vs Member Inner Classes
- Muestra diferencias en acceso a miembros
- Explica casos de uso para cada tipo
- Demuestra restricciones y capacidades

## Conceptos Clave

### Member Inner Classes
- **Están asociadas** a una instancia específica de la clase externa
- **No pueden existir** sin una instancia de la clase externa
- **Pueden acceder** a todos los miembros de la instancia (campos, métodos)
- **No pueden tener** métodos estáticos
- **Constructor privado** por defecto, solo accesible desde la clase externa

### Diferencias con Static Nested Classes
| Característica | Member Inner Class | Static Nested Class |
|----------------|-------------------|-------------------|
| Asociación | A instancia específica | Independiente |
| Existencia | Requiere instancia externa | Independiente |
| Acceso a miembros | Todos los miembros | Solo estáticos |
| Métodos estáticos | No permitidos | Permitidos |
| Instanciación | Desde instancia externa | Directa |

## Ejecutar los Demos

### Ejemplo Order con Item:
```bash
javac src/main/java/com/bcp/memberinnerclass/*.java
java -cp src/main/java com.bcp.memberinnerclass.Order
```

### Patrón Conceptual:
```bash
javac src/main/java/com/bcp/memberinnerclass/*.java
java -cp src/main/java com.bcp.memberinnerclass.MemberInnerClassDemo
```

### Comparación:
```bash
javac src/main/java/com/bcp/memberinnerclass/*.java
java -cp src/main/java com.bcp.memberinnerclass.ComparisonDemo
```

## Ejemplo del Código (como en la imagen)

```java
// Crear instancias de Order
Order order1 = new Order();
Order order2 = new Order();

// Agregar items (crea instancias de Item internamente)
order1.addItem(new Drink("Tea"), 2);
order1.addItem(new Food("Cake"), 1);
order2.addItem(new Drink("Tea"), 1);
```

## Salida Esperada

Los demos mostrarán:
1. Creación de instancias de Order
2. Agregado de items usando la clase anidada Item
3. Relación entre Order e Item (cada Item pertenece a su Order)
4. Características de las clases anidadas de miembro
5. Diferencias con static nested classes
6. Casos de uso prácticos
7. Patrón de encapsulación y asociación

## Casos de Uso Comunes

- **Modelado de relaciones**: Como Order-Item, donde Item es parte de Order
- **Callbacks y listeners**: Clases anidadas que necesitan acceder al estado de la instancia
- **Implementación de iteradores**: Clases anidadas que recorren elementos de la instancia
- **Encapsulación**: Ocultar detalles de implementación dentro de la clase externa 
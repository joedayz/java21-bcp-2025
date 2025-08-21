# Mejores Prácticas de Seguridad en Java

Este paquete contiene ejemplos prácticos de las 5 mejores prácticas para proteger tu código Java, basadas en el slide "Best Practices for Protecting Your Code".

## 📋 Índice

1. [Encapsulación Estricta](#1-encapsulación-estricta)
2. [Inmutabilidad](#2-inmutabilidad)
3. [Problemas de Herencia](#3-problemas-de-herencia)
4. [Diseño para Herencia](#4-diseño-para-herencia)
5. [Protección de Bytecode](#5-protección-de-bytecode)

## 1. Encapsulación Estricta

**Archivo:** `EncapsulationExample.java`

### ¿Qué demuestra?
- Uso de campos privados y métodos públicos controlados
- Factory methods con validaciones
- Protección contra reflexión
- Vistas inmutables de colecciones

### Conceptos clave:
```java
// Constructor privado + Factory method
private SecureBankAccount(String accountNumber, double initialBalance) { ... }
public static SecureBankAccount createAccount(String accountNumber, double initialBalance) { ... }

// Vista inmutable
public List<String> getTransactionHistory() {
    return Collections.unmodifiableList(transactionHistory);
}
```

## 2. Inmutabilidad

**Archivo:** `ImmutabilityExample.java`

### ¿Qué demuestra?
- Clases inmutables con campos `final`
- Copias defensivas para objetos mutables
- Métodos que crean nuevas instancias
- Problemas de referencias mutables

### Conceptos clave:
```java
// Clase inmutable
public static final class ImmutablePerson {
    private final String name;
    private final List<String> hobbies;
    
    // Copia defensiva en constructor
    this.hobbies = new ArrayList<>(hobbies);
    
    // Método que crea nueva instancia
    public ImmutablePerson withHobby(String hobby) {
        List<String> newHobbies = new ArrayList<>(hobbies);
        newHobbies.add(hobby);
        return new ImmutablePerson(name, age, newHobbies, birthDate);
    }
}
```

## 3. Problemas de Herencia

**Archivo:** `InheritanceExample.java`

### ¿Qué demuestra?
- Problemas de llamar métodos sobreescribibles en constructores
- Subclases maliciosas que explotan debilidades
- Cómo usar `final` para prevenir herencia no deseada

### Conceptos clave:
```java
// ❌ PROBLEMÁTICO
public BadSuperclass() {
    initializeItems(); // Método sobreescribible en constructor
}

// ✅ SEGURO
public final class SecureClass {
    private void initializeItemsInternal() { ... } // Método privado
    public final void addItem(String item) { ... } // Método final
}
```

## 4. Diseño para Herencia

**Archivo:** `InheritanceExample.java` (segunda parte)

### ¿Qué demuestra?
- Hooks protegidos para extensibilidad
- Métodos finales para validaciones críticas
- Patrón Template Method

### Conceptos clave:
```java
// Clase diseñada para herencia
public abstract class WellDesignedSuperclass {
    public final void addItem(String item) {
        validateItem(item); // Validación en método final
        items.add(item);
        onItemAdded(item); // Hook para subclases
    }
    
    protected void onItemAdded(String item) {
        // Las subclases pueden sobreescribir
    }
}
```

## 5. Protección de Bytecode

**Archivo:** `BytecodeProtectionExample.java`

### ¿Qué demuestra?
- Verificación de integridad con checksums
- Serialización/deserialización segura
- Protección contra ataques DoS
- Validación de tipos en deserialización

### Conceptos clave:
```java
// Verificación de integridad
public String getData() {
    if (!verifyIntegrity()) {
        throw new SecurityException("Integridad comprometida");
    }
    return data;
}

// Deserialización segura
public static IntegrityProtectedClass deserializeSecurely(byte[] data) {
    if (data.length > 1024 * 1024) { // Límite de 1MB
        throw new SecurityException("Datos demasiado grandes");
    }
    // ... validaciones adicionales
}
```

## 🚀 Cómo ejecutar los ejemplos

### Opción 1: Script automático
```bash
.\run-best-practices-demo.bat
```

### Opción 2: Ejecutar individualmente
```bash
# Compilar
mvn compile

# Ejecutar cada ejemplo
java -cp "target/classes" com.bcp.bestpractices.EncapsulationExample
java -cp "target/classes" com.bcp.bestpractices.ImmutabilityExample
java -cp "target/classes" com.bcp.bestpractices.InheritanceExample
java -cp "target/classes" com.bcp.bestpractices.BytecodeProtectionExample
```

## 🎯 Beneficios de estas prácticas

### Seguridad
- ✅ Previene acceso no autorizado a datos
- ✅ Protege contra manipulación de objetos
- ✅ Evita ataques de inyección de código

### Mantenibilidad
- ✅ Código más fácil de entender y testear
- ✅ Menos bugs relacionados con estado compartido
- ✅ Mejor encapsulación de responsabilidades

### Rendimiento
- ✅ Objetos inmutables son thread-safe por defecto
- ✅ Mejor optimización por el compilador
- ✅ Caché-friendly

## ⚠️ Consideraciones importantes

1. **No usar `-Xverify:none`** - Siempre mantener la verificación de bytecode habilitada
2. **Validar siempre** - Nunca confiar en datos de entrada
3. **Usar `final` estratégicamente** - Para clases y métodos que no deben ser extendidos
4. **Copias defensivas** - Siempre cuando devuelvas objetos mutables
5. **Hooks protegidos** - Para permitir extensibilidad de forma segura

## 📚 Referencias

- [Java Security Documentation](https://docs.oracle.com/en/java/javase/21/security/)
- [Effective Java - Joshua Bloch](https://www.amazon.com/Effective-Java-Joshua-Bloch/dp/0134685997)
- [Java Concurrency in Practice](https://www.amazon.com/Java-Concurrency-Practice-Brian-Goetz/dp/0321349601)

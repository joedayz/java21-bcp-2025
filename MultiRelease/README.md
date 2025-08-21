# Multi-Release JAR Demo

Este proyecto demuestra cómo crear un **Multi-Release JAR** que contiene diferentes versiones de código para diferentes versiones de Java.

## 📁 Estructura del Proyecto

```
MultiRelease/
├── src/
│   ├── main/
│   │   ├── java/                    # Versión por defecto (Java 8)
│   │   │   ├── module-info.java
│   │   │   └── demos/data/
│   │   │       ├── Product.java
│   │   │       └── ProductManager.java
│   │   ├── java-9/                  # Versión específica para Java 9
│   │   │   └── demos/data/
│   │   │       └── Product.java
│   │   ├── java-10/                 # Versión específica para Java 10
│   │   │   └── demos/data/
│   │   │       └── Product.java
│   │   └── java-11/                 # Versión específica para Java 11
│   │       └── demos/data/
│   │           └── Product.java
├── META-INF/
│   └── MANIFEST.MF                  # Configuración Multi-Release
├── build-multirelease.bat           # Script de construcción
└── README.md
```

## 🎯 Características por Versión

### Java 8 (Versión por defecto)
- Implementación básica compatible con Java 8
- Usa `ArrayList` y métodos tradicionales

### Java 9
- Usa `List.of()` (característica de Java 9)
- Sistema de módulos
- JShell

### Java 10
- Usa `var` (Local Variable Type Inference)
- Garbage Collection mejorado

### Java 11
- Métodos de String (`isBlank()`, `lines()`)
- HTTP Client
- Local Variable Syntax for Lambda Parameters

## 🚀 Cómo Construir

1. **Ejecutar el script de construcción:**
   ```bash
   build-multirelease.bat
   ```

2. **El script hará lo siguiente:**
   - Compilará cada versión con su target específico
   - Creará el JAR con la configuración Multi-Release
   - Agregará cada versión en `META-INF/versions/<version>/`

## 🧪 Cómo Probar

### Ejecutar el JAR:
```bash
java -jar multirelease-demo.jar
```

### Probar con diferentes versiones de Java:
```bash
# Con Java 8
java8 -jar multirelease-demo.jar

# Con Java 9
java9 -jar multirelease-demo.jar

# Con Java 10
java10 -jar multirelease-demo.jar

# Con Java 11
java11 -jar multirelease-demo.jar
```

## 📦 Estructura del JAR Resultante

```
multirelease-demo.jar
├── META-INF/
│   ├── MANIFEST.MF
│   └── versions/
│       ├── 9/
│       │   └── demos/data/Product.class
│       ├── 10/
│       │   └── demos/data/Product.class
│       └── 11/
│           └── demos/data/Product.class
├── module-info.class
└── demos/data/
    ├── Product.class (versión por defecto)
    └── ProductManager.class
```

## 🔍 Verificar el JAR

Para ver el contenido del JAR:
```bash
jar --list --file multirelease-demo.jar
```

Para ver el MANIFEST:
```bash
jar --extract --file multirelease-demo.jar META-INF/MANIFEST.MF
```

## 💡 Conceptos Clave

1. **Multi-Release: true** en MANIFEST.MF habilita la funcionalidad
2. **META-INF/versions/<version>/** contiene versiones específicas
3. **La versión por defecto** funciona en Java 8 y anteriores
4. **Cada versión específica** se usa automáticamente según la JVM

## 🎉 Resultado Esperado

Al ejecutar con diferentes versiones de Java, verás diferentes mensajes que indican qué características específicas de cada versión se están utilizando.

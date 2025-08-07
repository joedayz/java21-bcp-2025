# Paquete TestLogging - API de Logging de Java

Este paquete contiene ejemplos que demuestran el uso de la **API de Logging de Java**, tal como se muestra en la imagen de referencia.

## Archivos del Paquete

### 1. `Test.java`
Clase principal que demuestra el uso completo de la API de Logging de Java.

### 2. `module-info.java` (en el directorio raíz)
Archivo de configuración de módulos que declara la dependencia con `java.logging`.

## ¿Qué es la API de Logging de Java?

La **API de Logging de Java** proporciona un sistema robusto para registrar eventos y mensajes en aplicaciones Java. Es parte del paquete `java.util.logging`.

### Características Principales:
- **Siete niveles de logging**: Desde SEVERE hasta FINEST
- **Configuración flexible**: Niveles configurables por logger
- **Handlers personalizables**: Diferentes destinos de salida
- **Formatters**: Personalización del formato de salida
- **Integración con módulos**: Soporte para el sistema de módulos de Java

## Los Siete Niveles de Logging

### Orden de Severidad (de más crítico a menos crítico):

1. **SEVERE** 🔴 (más crítico)
   - Errores críticos que impiden que la aplicación funcione
   - Ejemplo: "La aplicación no puede continuar"

2. **WARNING** 🟠
   - Advertencias sobre problemas potenciales
   - Ejemplo: "Recursos del sistema bajos"

3. **INFO** 🔵
   - Información general sobre el funcionamiento
   - Ejemplo: "Usuario autenticado correctamente"

4. **CONFIG** ⚙️
   - Información de configuración
   - Ejemplo: "Configuración de base de datos cargada"

5. **FINE** 🔍
   - Detalles de depuración
   - Ejemplo: "Método X ejecutado"

6. **FINER** 🔬
   - Detalles más específicos de depuración
   - Ejemplo: "Variable Y = 42"

7. **FINEST** 🔬 (menos crítico)
   - Detalles más granulares de depuración
   - Ejemplo: "Iteración 15 completada"

## Ejemplo del Código de la Imagen

### Código Original:
```java
package demos;

import java.util.logging.*;

public class Test {
    private static Logger logger = 
        Logger.getLogger(demos.Test.class.getName());

    public static void main(String[] args) {
        try {
            /* actions that can throw exceptions */
        } catch (Exception e) {
            logger.log(Level.ERROR, "Your error message", e);
        }
        logger.log(Level.INFO, "Your message");
        logger.info("Your message");
    }
}
```

### Explicación del Código:

1. **Importación**: `import java.util.logging.*;`
   - Importa todas las clases necesarias para logging

2. **Creación del Logger**: `Logger.getLogger(demos.Test.class.getName())`
   - Crea un logger identificado por el nombre de la clase
   - Es práctica común usar el nombre de la clase como nombre del logger

3. **Logging con Excepción**: `logger.log(Level.SEVERE, "Your error message", e)`
   - Registra un error crítico con la excepción asociada
   - Útil para debugging y monitoreo

4. **Logging de Información**: `logger.log(Level.INFO, "Your message")`
   - Registra información general sobre el funcionamiento

5. **Método Conveniencia**: `logger.info("Your message")`
   - Forma abreviada para logging de nivel INFO

## Configuración de Módulos

### module-info.java:
```java
module com.bcp.testlogging {
    requires java.logging;
}
```

**Explicación:**
- **module**: Declara un módulo Java
- **com.bcp.testlogging**: Nombre del módulo
- **requires java.logging**: Declara dependencia con el módulo de logging

## Ejemplos Implementados

### 1. Configuración Básica del Logger
```java
private static Logger logger = 
    Logger.getLogger(com.bcp.testlogging.Test.class.getName());
```

### 2. Logging con Excepciones
```java
try {
    int result = 10 / 0; // Operación que falla
} catch (Exception e) {
    logger.log(Level.SEVERE, "Error en operación matemática", e);
}
```

### 3. Logging de Información
```java
logger.log(Level.INFO, "Aplicación iniciada correctamente");
logger.info("Procesamiento completado exitosamente");
```

### 4. Demostración de Todos los Niveles
```java
logger.severe("🚨 ERROR CRÍTICO: La aplicación no puede continuar");
logger.warning("⚠️ ADVERTENCIA: Recursos del sistema bajos");
logger.info("ℹ️ INFORMACIÓN: Usuario autenticado correctamente");
logger.config("⚙️ CONFIGURACIÓN: Configuración de base de datos cargada");
logger.fine("🔍 FINE: Detalle de depuración - Método X ejecutado");
logger.finer("🔬 FINER: Detalle más específico - Variable Y = 42");
logger.finest("🔬 FINEST: Detalle más granular - Iteración 15 completada");
```

## Ejemplos Prácticos

### Logging de Operaciones de Base de Datos
```java
logger.info("Iniciando conexión a base de datos...");
// ... operación de BD ...
logger.info("Conexión a base de datos establecida");
logger.fine("Ejecutando consulta SELECT * FROM users");
// ... consulta ...
logger.info("Consulta ejecutada exitosamente - 150 registros encontrados");
```

### Logging de Autenticación
```java
String username = "usuario123";
logger.info("Intento de autenticación para usuario: " + username);

if (authSuccess) {
    logger.info("Autenticación exitosa para usuario: " + username);
} else {
    logger.warning("Autenticación fallida para usuario: " + username);
}
```

### Logging de Rendimiento
```java
long startTime = System.currentTimeMillis();
// ... operación costosa ...
long duration = System.currentTimeMillis() - startTime;

if (duration > 100) {
    logger.warning("Operación lenta detectada: " + duration + "ms");
} else {
    logger.fine("Operación completada en tiempo normal: " + duration + "ms");
}
```

## Configuración de Niveles

### Cambiar Nivel del Logger
```java
// Mostrar nivel actual
System.out.println("Nivel actual: " + logger.getLevel());

// Cambiar a nivel FINE para ver más detalles
logger.setLevel(Level.FINE);

// Ahora los mensajes FINE serán visibles
logger.fine("Este mensaje ahora será visible");
```

## Ventajas de la API de Logging

### Flexibilidad
- **Niveles configurables**: Diferentes niveles para diferentes entornos
- **Handlers personalizables**: Archivo, consola, red, etc.
- **Formatters**: Personalización del formato de salida

### Rendimiento
- **Lazy evaluation**: Los mensajes se formatean solo si se van a mostrar
- **Filtrado eficiente**: Solo se procesan los mensajes del nivel configurado
- **Configuración en tiempo de ejecución**: Cambios sin reiniciar la aplicación

### Integración
- **Sistema de módulos**: Soporte nativo para módulos Java
- **Configuración externa**: Archivos de propiedades para configuración
- **Handlers estándar**: ConsoleHandler, FileHandler, etc.

## Casos de Uso Comunes

### Desarrollo y Debugging
- **FINE/FINER/FINEST**: Para debugging detallado
- **INFO**: Para seguimiento del flujo de la aplicación
- **WARNING**: Para problemas potenciales

### Producción
- **SEVERE**: Para errores críticos que requieren atención inmediata
- **WARNING**: Para problemas que no impiden el funcionamiento
- **INFO**: Para auditoría y monitoreo

### Configuración
- **CONFIG**: Para información de configuración
- **INFO**: Para eventos importantes del sistema

## Mejores Prácticas

### Nombres de Logger
```java
// ✅ Correcto: Usar nombre de clase
Logger logger = Logger.getLogger(MyClass.class.getName());

// ❌ Incorrecto: Usar string literal
Logger logger = Logger.getLogger("MyClass");
```

### Niveles Apropiados
```java
// ✅ Correcto: Usar nivel apropiado
logger.severe("Error crítico en base de datos");
logger.warning("Recursos del sistema bajos");
logger.info("Usuario autenticado");
logger.fine("Método ejecutado");

// ❌ Incorrecto: Usar SEVERE para todo
logger.severe("Usuario autenticado"); // Demasiado crítico
```

### Logging de Excepciones
```java
// ✅ Correcto: Incluir la excepción
try {
    // operación
} catch (Exception e) {
    logger.log(Level.SEVERE, "Error en operación", e);
}

// ❌ Incorrecto: Solo mensaje
try {
    // operación
} catch (Exception e) {
    logger.severe("Error en operación"); // Sin stack trace
}
```

## Cómo Ejecutar

### Con Módulos (Recomendado):
```bash
# Compilar
javac -d out src/main/java/module-info.java src/main/java/com/bcp/testlogging/*.java

# Ejecutar
java --module-path out --module com.bcp.testlogging/com.bcp.testlogging.Test
```

### Sin Módulos (Clásico):
```bash
# Compilar
javac -cp src/main/java src/main/java/com/bcp/testlogging/*.java

# Ejecutar
java -cp src/main/java com.bcp.testlogging.Test
```

## Salida Esperada

El ejemplo mostrará:
1. **Configuración del logger**: Nombre y nivel actual
2. **Logging con excepciones**: Captura y registro de errores
3. **Logging de información**: Mensajes de nivel INFO
4. **Método conveniencia**: Uso de logger.info()
5. **Todos los niveles**: Demostración de los siete niveles
6. **Ejemplos prácticos**: Casos de uso reales
7. **Configuración de niveles**: Cambio dinámico de niveles

## Referencias

- **Java Logging API**: Documentación oficial de Java
- **Logger Class**: Documentación de la clase Logger
- **Level Class**: Documentación de los niveles de logging
- **Module System**: Documentación del sistema de módulos 
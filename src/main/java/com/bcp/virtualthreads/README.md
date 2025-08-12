# Ejemplos de Hilos Virtuales (Virtual Threads) en Java 21

Este paquete contiene ejemplos prácticos de hilos virtuales (Virtual Threads), una característica introducida en Java 21 que permite crear millones de hilos ligeros para mejorar el rendimiento en aplicaciones con mucho I/O.

## Archivos incluidos:

### 1. VirtualThreadExample.java
**Descripción:** Ejemplo completo que demuestra todas las operaciones de hilos virtuales mencionadas en la documentación oficial.

**Métodos y conceptos demostrados:**
- `Thread.Builder` - Para crear objetos `Thread` y `ThreadFactory`
- `ThreadFactory` - Para crear múltiples hilos con propiedades idénticas
- `Thread.ofVirtual()` - Crear un hilo virtual
- `Thread.ofPlatform()` - Crear un hilo de plataforma
- `Thread.startVirtualThread(Runnable)` - Crear e iniciar un hilo virtual
- `Thread.isVirtual()` - Verificar si un hilo es virtual
- `Thread.getAllStackTraces()` - Obtener stack traces (solo hilos de plataforma)

**Para ejecutar:**
```bash
java com.bcp.virtualthreads.VirtualThreadExample
```

### 2. VirtualThreadExecutorExample.java
**Descripción:** Ejemplo que demuestra el uso de hilos virtuales con ExecutorService y las ventajas de rendimiento.

**Conceptos demostrados:**
- Comparación de rendimiento entre hilos de plataforma y virtuales
- Uso de `Executors.newVirtualThreadPerTaskExecutor()`
- Manejo de excepciones en hilos virtuales
- Ventajas de escalabilidad

**Para ejecutar:**
```bash
java com.bcp.virtualthreads.VirtualThreadExecutorExample
```

## Conceptos Clave de Hilos Virtuales

### ¿Qué son los Hilos Virtuales?
Los hilos virtuales son hilos ligeros que son programados por la JVM en lugar del sistema operativo. Son ideales para aplicaciones con mucho I/O porque:
- Pueden crear millones de hilos virtuales
- Tienen un overhead muy bajo
- Se bloquean eficientemente durante operaciones I/O
- No consumen recursos del sistema operativo

### Características de los Hilos Virtuales
- **Siempre son daemon threads** - Se terminan automáticamente cuando todos los hilos de usuario terminan
- **Prioridad normal** - No se puede cambiar la prioridad
- **Grupo "VirtualThreads"** - Usan un placeholder como nombre de grupo
- **No aparecen en getAllStackTraces()** - Solo los hilos de plataforma aparecen

### Métodos Principales

#### Creación de Hilos Virtuales
```java
// Método 1: Crear e iniciar directamente
Thread t1 = Thread.startVirtualThread(() -> { /* tarea */ });

// Método 2: Crear con builder
Thread t2 = Thread.ofVirtual().name("mi-hilo").unstarted(() -> { /* tarea */ });
t2.start();

// Método 3: Con ThreadFactory
ThreadFactory factory = Thread.ofVirtual().name("worker-", 0).factory();
Thread t3 = factory.newThread(() -> { /* tarea */ });
```

#### Verificación de Tipo
```java
Thread virtualThread = Thread.startVirtualThread(() -> {});
boolean isVirtual = virtualThread.isVirtual(); // true
```

#### Comparación con Hilos de Plataforma
```java
// Hilo virtual
Thread virtual = Thread.ofVirtual().name("virtual").unstarted(() -> {});
System.out.println(virtual.isVirtual()); // true
System.out.println(virtual.isDaemon()); // true

// Hilo de plataforma
Thread platform = Thread.ofPlatform().name("platform").unstarted(() -> {});
System.out.println(platform.isVirtual()); // false
System.out.println(platform.isDaemon()); // false (por defecto)
```

### Uso con ExecutorService
```java
// Crear executor con hilos virtuales
try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
    // Enviar tareas
    for (int i = 0; i < 10000; i++) {
        executor.submit(() -> {
            // Esta tarea se ejecutará en un hilo virtual
            Thread.sleep(1000);
        });
    }
    // El executor se cierra automáticamente
}
```

## Ventajas de los Hilos Virtuales

### 1. Escalabilidad
- Pueden crear millones de hilos virtuales
- Ideal para aplicaciones con mucho I/O (HTTP, base de datos, archivos)

### 2. Rendimiento
- Overhead muy bajo
- Mejor utilización de recursos del sistema
- Reducción del contexto switching del sistema operativo

### 3. Simplicidad
- API familiar (misma que los hilos tradicionales)
- No requiere cambios en el código existente
- Manejo automático de la programación

### 4. Compatibilidad
- Funcionan con todas las APIs existentes
- Compatibles con ExecutorService, CompletableFuture, etc.
- No requieren cambios en bibliotecas de terceros

## Casos de Uso Ideales

### 1. Servidores Web
- Manejo de múltiples conexiones HTTP simultáneas
- Procesamiento de requests en paralelo

### 2. Aplicaciones de Base de Datos
- Múltiples consultas concurrentes
- Operaciones de lectura/escritura paralelas

### 3. APIs REST
- Procesamiento de múltiples requests
- Llamadas a servicios externos

### 4. Procesamiento de Archivos
- Lectura/escritura de múltiples archivos
- Operaciones de red concurrentes

## Limitaciones y Consideraciones

### 1. No para CPU Intensivo
- Los hilos virtuales no mejoran el rendimiento para tareas CPU-intensivas
- Para esas tareas, use hilos de plataforma con un pool limitado

### 2. Memoria
- Aunque son ligeros, cada hilo virtual consume algo de memoria
- Monitoree el uso de memoria en aplicaciones con muchos hilos virtuales

### 3. Debugging
- Los hilos virtuales no aparecen en `getAllStackTraces()`
- Use herramientas específicas para debugging de hilos virtuales

## Ejecución de los ejemplos

Todos los ejemplos pueden ejecutarse desde la raíz del proyecto:

```bash
# Compilar
mvn compile

# Ejecutar ejemplos específicos
java -cp target/classes com.bcp.virtualthreads.VirtualThreadExample
java -cp target/classes com.bcp.virtualthreads.VirtualThreadExecutorExample
```

## Notas importantes

1. **Java 21+:** Los hilos virtuales requieren Java 21 o superior
2. **Rendimiento:** Los beneficios se ven principalmente en aplicaciones con mucho I/O
3. **Compatibilidad:** Funcionan con todas las APIs de concurrencia existentes
4. **Debugging:** Use herramientas específicas para debugging de hilos virtuales
5. **Monitoreo:** Monitoree el uso de memoria y rendimiento en producción

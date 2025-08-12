# Ejemplos de Threads y Sincronización en Java

Este paquete contiene ejemplos prácticos de programación concurrente en Java, demostrando diferentes aspectos de la sincronización, comunicación entre hilos y gestión de hilos daemon.

## Archivos incluidos:

### 1. SynchronizationExample.java
**Descripción:** Demuestra los diferentes tipos de sincronización en Java y los monitores utilizados en cada caso.

**Conceptos demostrados:**
- Métodos sincronizados de instancia (`synchronized void a()`)
- Métodos sincronizados estáticos (`static synchronized void b()`)
- Bloques sincronizados (`synchronized (object) { ... }`)
- Diferentes monitores: `this`, `Class.class`, y objetos específicos

**Para ejecutar:**
```bash
java com.bcp.threadsamples.SynchronizationExample
```

### 2. SharedCounterExample.java
**Descripción:** Ejemplo práctico que demuestra la necesidad de sincronización cuando múltiples hilos acceden a recursos compartidos.

**Conceptos demostrados:**
- Problema de condición de carrera (race condition)
- Contador seguro con sincronización
- Contador no seguro sin sincronización
- Comparación de resultados

**Para ejecutar:**
```bash
java com.bcp.threadsamples.SharedCounterExample
```

### 3. ProducerConsumerExample.java
**Descripción:** Implementación del patrón clásico Productor-Consumidor usando `wait()` y `notify()`.

**Conceptos demostrados:**
- Comunicación entre hilos con `wait()` y `notify()`
- Buffer compartido con sincronización
- Patrón Productor-Consumidor
- Manejo de buffer lleno/vacío

**Para ejecutar:**
```bash
java com.bcp.threadsamples.ProducerConsumerExample
```

### 4. DaemonThreadExample.java
**Descripción:** Ejemplo sencillo de creación y gestión de un hilo daemon.

**Conceptos demostrados:**
- Creación de hilos con `Runnable` y lambda expressions
- Configuración de hilos daemon con `setDaemon(true)`
- Obtención de ID y verificación de estado daemon
- Configuración de prioridad con `setPriority()`
- Espera de terminación con `join()`

**Para ejecutar:**
```bash
java com.bcp.threadsamples.DaemonThreadExample
```

### 5. DaemonVsUserThreadExample.java
**Descripción:** Comparación entre hilos daemon y hilos de usuario, mostrando cómo los daemon se terminan automáticamente.

**Conceptos demostrados:**
- Diferencia entre hilos daemon y hilos de usuario
- Terminación automática de hilos daemon
- Verificación de estado de hilos con `isAlive()`
- Manejo de interrupciones

**Para ejecutar:**
```bash
java com.bcp.threadsamples.DaemonVsUserThreadExample
```

## Conceptos Clave de Threads y Sincronización

### Hilos Daemon
- **Definición:** Hilos de baja prioridad que se ejecutan en segundo plano
- **Comportamiento:** Se auto-terminan cuando todos los hilos de usuario terminan
- **Configuración:** `setDaemon(true)` antes de `start()`
- **Verificación:** `isDaemon()` para verificar si es daemon

### Monitores en Java
- **Método sincronizado de instancia:** El monitor es el objeto actual (`this`)
- **Método sincronizado estático:** El monitor es la clase (`Class.class`)
- **Bloque sincronizado:** El monitor es el objeto especificado en el bloque

### Palabras clave importantes
- `synchronized`: Garantiza acceso exclusivo a un bloque de código
- `wait()`: Libera el monitor y pone el hilo en espera
- `notify()`: Despierta un hilo que está esperando en el mismo monitor
- `notifyAll()`: Despierta todos los hilos que están esperando
- `join()`: Espera a que un hilo termine su ejecución

### Estados de un hilo
- `NEW`: Hilo creado pero no iniciado
- `RUNNABLE`: Hilo ejecutándose o listo para ejecutar
- `BLOCKED`: Hilo bloqueado esperando un monitor
- `WAITING`: Hilo esperando indefinidamente
- `TIMED_WAITING`: Hilo esperando por un tiempo específico
- `TERMINATED`: Hilo terminado

### Propiedades de hilos
- **ID:** Identificador único obtenido con `getId()`
- **Nombre:** Nombre del hilo para identificación
- **Prioridad:** Valor de 1-10 (MIN_PRIORITY=1, NORM_PRIORITY=5, MAX_PRIORITY=10)
- **Daemon:** Boolean que indica si es hilo daemon

## Ejecución de los ejemplos

Todos los ejemplos pueden ejecutarse desde la raíz del proyecto:

```bash
# Compilar
mvn compile

# Ejecutar ejemplos específicos
java -cp target/classes com.bcp.threadsamples.SynchronizationExample
java -cp target/classes com.bcp.threadsamples.SharedCounterExample
java -cp target/classes com.bcp.threadsamples.ProducerConsumerExample
java -cp target/classes com.bcp.threadsamples.DaemonThreadExample
java -cp target/classes com.bcp.threadsamples.DaemonVsUserThreadExample
```

## Notas importantes

1. **Interrupciones:** Todos los ejemplos manejan correctamente las interrupciones de hilos
2. **Recursos compartidos:** Se demuestra la importancia de sincronizar el acceso a recursos compartidos
3. **Comunicación entre hilos:** Se muestra cómo los hilos pueden comunicarse de forma segura
4. **Patrones de diseño:** Se implementan patrones comunes de programación concurrente
5. **Hilos daemon:** Útiles para tareas de fondo que no deben impedir la terminación de la aplicación

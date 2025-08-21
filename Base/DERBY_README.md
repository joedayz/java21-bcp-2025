# Apache Derby en el Proyecto Java BCP

## ¿Qué es Apache Derby?

Apache Derby es una base de datos relacional escrita completamente en Java. Es ideal para:
- Aplicaciones embebidas
- Desarrollo y pruebas
- Aplicaciones que requieren una base de datos ligera
- Aprendizaje de SQL y JDBC

## Configuración del Proyecto

### 1. Dependencias Maven

El proyecto ya incluye las dependencias necesarias en `pom.xml`:

```xml
<dependency>
    <groupId>org.apache.derby</groupId>
    <artifactId>derby</artifactId>
    <version>10.17.1.0</version>
</dependency>

<dependency>
    <groupId>org.apache.derby</groupId>
    <artifactId>derbytools</artifactId>
    <version>10.17.1.0</version>
</dependency>
```

### 2. Descargar Dependencias

Ejecuta el siguiente comando para descargar las dependencias:

```bash
mvn clean compile
```

## Ejemplo de Uso

### Ejecutar el Ejemplo

```bash
# Desde la carpeta Base
mvn exec:java -Dexec.mainClass="com.bcp.DerbyExample"
```

O compilar y ejecutar manualmente:

```bash
mvn compile
java -cp "target/classes;target/dependency/*" com.bcp.DerbyExample
```

### Características del Ejemplo

El archivo `DerbyExample.java` demuestra:

1. **Conexión a la base de datos**: Usa JDBC para conectar a Derby embebido
2. **Creación de tablas**: Crea una tabla de empleados
3. **Inserción de datos**: Inserta registros de ejemplo
4. **Consultas**: Ejecuta consultas SQL y muestra resultados
5. **Cierre de conexión**: Cierra la base de datos correctamente

## URLs de Conexión Derby

### Derby Embebido (Recomendado para desarrollo)
```java
// Crear base de datos si no existe
String url = "jdbc:derby:miBaseDatos;create=true";

// Conectar a base de datos existente
String url = "jdbc:derby:miBaseDatos";
```

### Derby en Red (Cliente-Servidor)
```java
// Conectar a Derby servidor
String url = "jdbc:derby://localhost:1527/miBaseDatos";
```

## Comandos Útiles

### Iniciar Derby en Modo Servidor
```bash
# Desde la carpeta donde están los JARs de Derby
java -jar derbyrun.jar server start
```

### Conectar con ij (Herramienta de Derby)
```bash
# Iniciar ij
java -jar derbyrun.jar ij

# En ij, conectar a la base de datos
CONNECT 'jdbc:derby:bcpDB';
```

### Comandos SQL en ij
```sql
-- Ver tablas
SHOW TABLES;

-- Ver estructura de tabla
DESCRIBE empleados;

-- Consultar datos
SELECT * FROM empleados;

-- Salir de ij
EXIT;
```

## Estructura de Archivos

Después de ejecutar el ejemplo, se creará:
- `bcpDB/` - Carpeta de la base de datos Derby
- `bcpDB/log/` - Archivos de log
- `bcpDB/seg0/` - Archivos de datos

## Ventajas de Derby

1. **Ligero**: Solo ~2MB de JARs
2. **Sin instalación**: Se ejecuta embebido en la aplicación
3. **Estándar SQL**: Soporta SQL estándar
4. **Transaccional**: ACID compliance
5. **Multiplataforma**: Funciona en cualquier JVM

## Consideraciones

- **Rendimiento**: Ideal para aplicaciones pequeñas y medianas
- **Concurrencia**: Limitada comparada con bases de datos empresariales
- **Persistencia**: Los datos se guardan en archivos locales
- **Backup**: Hacer copia de la carpeta de la base de datos

## Próximos Pasos

1. Ejecutar el ejemplo básico
2. Modificar las consultas SQL
3. Crear nuevas tablas
4. Implementar operaciones CRUD completas
5. Usar transacciones
6. Configurar conexiones con pool de conexiones

# java21-bcp-2025
Repo para Java 17 y Java 21

## Demo de Módulos Java (JPMS)

Este repositorio incluye una demo completa del sistema de módulos de Java (Java Platform Module System - JPMS) que demuestra:

### Conceptos Cubiertos

- **Módulos Básicos**: Creación y estructura de módulos
- **Dependencias entre Módulos**: `requires`, `exports`, `opens`
- **Módulos de Servicios**: `provides` y `uses`
- **Módulos de Plataforma**: Uso de módulos del JDK
- **Reflection y Módulos**: Configuración de acceso a reflection

### Estructura de Módulos

```
src/main/java/com/bcp/modules/
├── core/           # Módulo core con funcionalidad básica
├── service/        # Módulo de servicios (implementaciones)
├── client/         # Módulo cliente que usa los servicios
└── utils/          # Módulo de utilidades
```

### Cómo Ejecutar la Demo

#### Opción 1: Scripts Automáticos

**Linux/Mac:**
```bash
chmod +x run-modules-demo.sh
./run-modules-demo.sh
```

**Windows:**
```cmd
run-modules-demo.bat
```

#### Opción 2: Compilación Manual

1. Compilar todos los módulos:
   ```bash
   mvn compile
   ```

2. Ejecutar el demo principal:
   ```bash
   java --module-path target/classes --module com.bcp.modules.client/com.bcp.modules.client.ModuleDemo
   ```

3. Ejecutar demo de reflection:
   ```bash
   java --module-path target/classes --module com.bcp.modules.client/com.bcp.modules.client.ReflectionDemo
   ```

### Archivos Principales

- `src/main/java/com/bcp/modules/*/module-info.java` - Declaraciones de módulos
- `src/main/java/com/bcp/modules/client/ModuleDemo.java` - Demo principal
- `src/main/java/com/bcp/modules/client/ReflectionDemo.java` - Demo de reflection
- `run-modules-demo.sh` / `run-modules-demo.bat` - Scripts de ejecución

### Conceptos Clave del Sistema de Módulos

- **module-info.java**: Archivo de declaración del módulo
- **requires**: Declara dependencias de otros módulos
- **exports**: Hace públicos los paquetes del módulo
- **opens**: Permite reflection en paquetes específicos
- **provides/uses**: Para el patrón de servicios
- **transitive**: Para dependencias transitivas

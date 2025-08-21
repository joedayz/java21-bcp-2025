# Java Modules Demo

Esta demo muestra los conceptos fundamentales del sistema de módulos de Java (JPMS - Java Platform Module System).

## Conceptos Demostrados

1. **Módulos Básicos**: Creación y estructura de módulos
2. **Dependencias entre Módulos**: `requires`, `exports`, `opens`
3. **Módulos de Servicios**: `provides` y `uses`
4. **Módulos de Plataforma**: Uso de módulos del JDK
5. **Reflection y Módulos**: Configuración de acceso a reflection

## Estructura del Proyecto

```
modules/
├── core/           # Módulo core con funcionalidad básica
├── service/        # Módulo de servicios
├── client/         # Módulo cliente que usa los servicios
└── utils/          # Módulo de utilidades
```

## Cómo Ejecutar

1. Compilar todos los módulos:
   ```bash
   mvn compile
   ```

2. Ejecutar el módulo principal:
   ```bash
   java --module-path target/classes --module com.bcp.modules.client
   ```

3. Ejecutar con módulos específicos:
   ```bash
   java --module-path target/classes --module com.bcp.modules.core
   ```

## Conceptos Clave

- **module-info.java**: Archivo de declaración del módulo
- **requires**: Declara dependencias de otros módulos
- **exports**: Hace públicos los paquetes del módulo
- **opens**: Permite reflection en paquetes específicos
- **provides/uses**: Para el patrón de servicios
- **transitive**: Para dependencias transitivas

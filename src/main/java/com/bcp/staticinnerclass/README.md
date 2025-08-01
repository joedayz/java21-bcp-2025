# Static Nested Classes Demo

Este paquete demuestra el concepto de clases anidadas estáticas (static nested classes) en Java con diferentes modificadores de acceso.

## Clases

### Outer.java
Contiene tres tipos de clases anidadas estáticas:

1. **Clase Anidada Estática Pública** (`PublicStaticNested`)
   - Puede ser instanciada directamente desde fuera de la clase externa
   - Ejemplo: `Outer.PublicStaticNested instance = new Outer.PublicStaticNested();`

2. **Clase Anidada Estática Package-Private** (`PackagePrivateStaticNested`)
   - Puede ser instanciada desde dentro del mismo paquete
   - No puede ser accedida desde fuera del paquete
   - Ejemplo: `Outer.PackagePrivateStaticNested instance = new Outer.PackagePrivateStaticNested();`

3. **Clase Anidada Estática Privada** (`PrivateStaticNested`)
   - Solo puede ser instanciada desde dentro de la clase externa
   - No puede ser accedida desde fuera de la clase externa
   - Ejemplo: `new PrivateStaticNested();` (solo desde dentro de Outer)

**Incluye método main** para demostrar todas las funcionalidades.

### SimpleExample.java
Ejemplo simple basado en el código mostrado en la imagen:

1. **Clase Anidada Estática Pública** (`StaticNested`)
   - Demuestra instanciación directa: `SimpleExample.StaticNested x = new SimpleExample.StaticNested();`

2. **Clase Anidada Estática Privada** (`PrivateStaticNested`)
   - Demuestra acceso a través de método público: `SimpleExample.createInstance();`

**Incluye método main** con ejemplos básicos.

### StaticNestedClassDemo.java
Demuestra cómo trabajar con cada tipo de clase anidada estática:

- Muestra instanciación directa de clases anidadas públicas
- Muestra cómo las clases anidadas privadas no pueden ser accedidas directamente
- Muestra acceso a clases anidadas package-private a través de métodos públicos
- Muestra instanciación directa de clases anidadas package-private dentro del mismo paquete

## Conceptos Clave

- **Las clases anidadas estáticas** son independientes de la instancia de la clase externa
- **Los modificadores de acceso** controlan la visibilidad y capacidades de instanciación
- **Públicas** pueden ser instanciadas desde cualquier lugar
- **Package-private** pueden ser instanciadas dentro del mismo paquete
- **Privadas** solo pueden ser instanciadas dentro de la clase externa

## Ejecutar los Demos

### Demo Completo (Outer.java):
```bash
javac src/main/java/com/bcp/staticinnerclass/Outer.java
java -cp src/main/java com.bcp.staticinnerclass.Outer
```

### Ejemplo Simple (SimpleExample.java):
```bash
javac src/main/java/com/bcp/staticinnerclass/SimpleExample.java
java -cp src/main/java com.bcp.staticinnerclass.SimpleExample
```

### Demo Detallado (StaticNestedClassDemo.java):
```bash
javac src/main/java/com/bcp/staticinnerclass/StaticNestedClassDemo.java
java -cp src/main/java com.bcp.staticinnerclass.StaticNestedClassDemo
```

## Salida Esperada

Los demos mostrarán:
1. Instanciación exitosa de clase anidada estática pública
2. Explicación de por qué la clase anidada estática privada no puede ser instanciada directamente
3. Acceso a clase anidada package-private a través de método público
4. Instanciación directa de clase anidada package-private dentro del mismo paquete
5. Demostración del uso de clase anidada privada dentro de la clase externa 
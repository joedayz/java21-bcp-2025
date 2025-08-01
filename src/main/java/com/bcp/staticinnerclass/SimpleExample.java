package com.bcp.staticinnerclass;

/**
 * Ejemplo simple de clases anidadas estáticas
 * Basado en el código mostrado en la imagen
 */
public class SimpleExample {
    
    /**
     * Clase anidada estática pública
     */
    public static class StaticNested {
        // código de la clase anidada
        private String data = "Datos de StaticNested";
        
        public String getData() {
            return data;
        }
    }
    
    /**
     * Método para crear instancia de la clase privada anidada
     */
    public static void createInstance() {
        new PrivateStaticNested();
        System.out.println("Instancia de PrivateStaticNested creada desde SimpleExample");
    }
    
    /**
     * Clase anidada estática privada
     */
    private static class PrivateStaticNested {
        // código de la clase anidada
        private String data = "Datos de PrivateStaticNested";
        
        public PrivateStaticNested() {
            System.out.println("Constructor de PrivateStaticNested ejecutado");
        }
        
        public String getData() {
            return data;
        }
    }
    
    /**
     * Método main para demostrar el ejemplo simple
     */
    public static void main(String[] args) {
        System.out.println("=== Ejemplo Simple de Static Nested Classes ===\n");
        
        // Ejemplo 1: Clase pública anidada estática
        System.out.println("1. Clase Pública Anidada Estática:");
        System.out.println("   SimpleExample.StaticNested x = new SimpleExample.StaticNested();");
        SimpleExample.StaticNested x = new SimpleExample.StaticNested();
        System.out.println("   Resultado: " + x.getData());
        System.out.println();
        
        // Ejemplo 2: Clase privada anidada estática
        System.out.println("2. Clase Privada Anidada Estática:");
        System.out.println("   SimpleExample.createInstance();");
        SimpleExample.createInstance();
        System.out.println();
        
        // Ejemplo 3: Instanciación directa desde dentro de la clase
        System.out.println("3. Instanciación Directa desde SimpleExample:");
        System.out.println("   new PrivateStaticNested(); (desde dentro de SimpleExample)");
        new PrivateStaticNested();
        System.out.println();
        
        System.out.println("=== Ejemplo Simple Completado ===");
    }
} 
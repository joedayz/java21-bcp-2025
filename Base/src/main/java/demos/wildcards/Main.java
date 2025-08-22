package demos.wildcards;

/**
 * Clase principal que ejecuta todos los demos de wildcards.
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("🚀 INICIANDO DEMOS DE WILDCARD GENERICS\n");
        
        // Ejecutar demo principal
        System.out.println("=" * 60);
        WildcardDemo.main(args);
        
        System.out.println("\n" + "=" * 60);
        
        // Ejecutar demo de errores
        WildcardErrorsDemo.main(args);
        
        System.out.println("\n" + "=" * 60);
        
        // Ejecutar demo de casos prácticos
        WildcardUtils.demoPracticalUsage();
        
        System.out.println("\n" + "=" * 60);
        System.out.println("🎉 TODOS LOS DEMOS COMPLETADOS");
        System.out.println("=" * 60);
    }
}

package com.bcp.staticinnerclass;

/**
 * Demonstrates static nested classes with different access modifiers.
 * Shows how public and private static nested classes can be instantiated.
 */
public class Outer {
    
    /**
     * Public static nested class - can be instantiated directly from outside
     */
    public static class PublicStaticNested {
        private String message;
        
        public PublicStaticNested() {
            this.message = "Hello from PublicStaticNested";
        }
        
        public String getMessage() {
            return message;
        }
        
        public void setMessage(String message) {
            this.message = message;
        }
    }
    
    /**
     * Package-private static nested class - can be instantiated from within the same package
     */
    static class PackagePrivateStaticNested {
        private String message;
        
        public PackagePrivateStaticNested() {
            this.message = "Hello from PackagePrivateStaticNested";
        }
        
        public String getMessage() {
            return message;
        }
        
        public void setMessage(String message) {
            this.message = message;
        }
    }
    
    /**
     * Private static nested class - can only be instantiated from within Outer
     */
    private static class PrivateStaticNested {
        private String message;
        
        public PrivateStaticNested() {
            this.message = "Hello from PrivateStaticNested";
        }
        
        public String getMessage() {
            return message;
        }
        
        public void setMessage(String message) {
            this.message = message;
        }
    }
    
    /**
     * Public static method to create an instance of the package-private static nested class
     */
    public static PackagePrivateStaticNested createPackagePrivateInstance() {
        return new PackagePrivateStaticNested();
    }
    
    /**
     * Public static method to demonstrate working with the private static nested class
     */
    public static void demonstratePrivateNested() {
        PrivateStaticNested instance = new PrivateStaticNested();
        System.out.println("Created private static nested class: " + instance.getMessage());
    }
    
    /**
     * Main method to demonstrate static nested classes
     */
    public static void main(String[] args) {
        System.out.println("=== Outer Class Static Nested Classes Demo ===\n");
        
        // Demo 1: Public static nested class
        System.out.println("1. Public Static Nested Class:");
        PublicStaticNested publicInstance = new PublicStaticNested();
        System.out.println("   Message: " + publicInstance.getMessage());
        publicInstance.setMessage("Mensaje modificado para clase pública anidada");
        System.out.println("   Mensaje modificado: " + publicInstance.getMessage());
        System.out.println();
        
        // Demo 2: Package-private static nested class (direct instantiation)
        System.out.println("2. Package-Private Static Nested Class (instanciación directa):");
        PackagePrivateStaticNested packagePrivateInstance = new PackagePrivateStaticNested();
        System.out.println("   Message: " + packagePrivateInstance.getMessage());
        packagePrivateInstance.setMessage("Mensaje modificado para clase package-private");
        System.out.println("   Mensaje modificado: " + packagePrivateInstance.getMessage());
        System.out.println();
        
        // Demo 3: Package-private static nested class (through public method)
        System.out.println("3. Package-Private Static Nested Class (a través de método público):");
        PackagePrivateStaticNested methodInstance = createPackagePrivateInstance();
        System.out.println("   Message: " + methodInstance.getMessage());
        System.out.println();
        
        // Demo 4: Private static nested class (direct instantiation from within Outer)
        System.out.println("4. Private Static Nested Class (instanciación directa desde Outer):");
        PrivateStaticNested privateInstance = new PrivateStaticNested();
        System.out.println("   Message: " + privateInstance.getMessage());
        privateInstance.setMessage("Mensaje modificado para clase privada anidada");
        System.out.println("   Mensaje modificado: " + privateInstance.getMessage());
        System.out.println();
        
        // Demo 5: Private static nested class (through demonstration method)
        System.out.println("5. Private Static Nested Class (a través de método de demostración):");
        demonstratePrivateNested();
        System.out.println();
        
        System.out.println("=== Demo desde Outer Class Completado ===");
    }
} 
package com.bcp.staticinnerclass;

/**
 * Demo class showing how to work with static nested classes
 */
public class StaticNestedClassDemo {
    
    public static void main(String[] args) {
        System.out.println("=== Static Nested Class Demo ===\n");
        
        // Demo 1: Public static nested class - can be instantiated directly
        System.out.println("1. Public Static Nested Class:");
        Outer.PublicStaticNested publicInstance = new Outer.PublicStaticNested();
        System.out.println("   Message: " + publicInstance.getMessage());
        publicInstance.setMessage("Modified message for public nested class");
        System.out.println("   Modified message: " + publicInstance.getMessage());
        System.out.println();
        
        // Demo 2: Private static nested class - cannot be instantiated directly
        System.out.println("2. Private Static Nested Class:");
        System.out.println("   Cannot instantiate directly: Outer.PrivateStaticNested x = new Outer.PrivateStaticNested();");
        System.out.println("   This would cause compilation error!");
        System.out.println();
        
        // Demo 3: Accessing package-private static nested class through public method
        System.out.println("3. Accessing Package-Private Static Nested Class through public method:");
        Outer.PackagePrivateStaticNested packagePrivateInstance = Outer.createPackagePrivateInstance();
        System.out.println("   Message: " + packagePrivateInstance.getMessage());
        packagePrivateInstance.setMessage("Modified message for package-private nested class");
        System.out.println("   Modified message: " + packagePrivateInstance.getMessage());
        System.out.println();
        
        // Demo 4: Direct instantiation of package-private nested class (same package)
        System.out.println("4. Direct instantiation of Package-Private Static Nested Class (same package):");
        Outer.PackagePrivateStaticNested directInstance = new Outer.PackagePrivateStaticNested();
        System.out.println("   Direct message: " + directInstance.getMessage());
        System.out.println();
        
        // Demo 5: Using the demonstration method for private nested class
        System.out.println("5. Using demonstration method for private nested class:");
        Outer.demonstratePrivateNested();
        System.out.println();
        
        System.out.println("=== Demo Complete ===");
    }
} 
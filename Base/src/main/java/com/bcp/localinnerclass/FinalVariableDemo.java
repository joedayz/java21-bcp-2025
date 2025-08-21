package com.bcp.localinnerclass;

/**
 * Demo que demuestra la regla de variables finales o efectivamente finales
 * para clases anidadas locales
 */
public class FinalVariableDemo {
    
    public static void main(String[] args) {
        System.out.println("=== Demo: Variables Finales y Efectivamente Finales ===\n");
        
        // Ejemplo 1: Variable final (explícitamente final)
        System.out.println("1. Variable Final (Explícitamente Final):");
        demonstrateFinalVariable();
        System.out.println();
        
        // Ejemplo 2: Variable efectivamente final
        System.out.println("2. Variable Efectivamente Final:");
        demonstrateEffectivelyFinalVariable();
        System.out.println();
        
        // Ejemplo 3: Variable que no es final (causaría error)
        System.out.println("3. Variable que NO es Final (Error de Compilación):");
        demonstrateNonFinalVariable();
        System.out.println();
        
        // Ejemplo 4: Parámetros de método
        System.out.println("4. Parámetros de Método:");
        demonstrateMethodParameters("California", 0.0825);
        System.out.println();
        
        System.out.println("=== Demo Completado ===");
    }
    
    /**
     * Demuestra el uso de una variable final explícita
     */
    public static void demonstrateFinalVariable() {
        final String location = "New York";
        final double taxRate = 0.0875;
        
        System.out.println("   Variables finales declaradas:");
        System.out.println("   final String location = \"" + location + "\";");
        System.out.println("   final double taxRate = " + taxRate + ";");
        
        // Clase anidada local que accede a variables finales
        class TaxCalculator {
            public void calculate() {
                System.out.println("   ✅ TaxCalculator puede acceder a location: " + location);
                System.out.println("   ✅ TaxCalculator puede acceder a taxRate: " + taxRate);
                System.out.println("   ✅ Calculando impuesto para " + location + " con tasa " + (taxRate * 100) + "%");
            }
        }
        
        TaxCalculator calculator = new TaxCalculator();
        calculator.calculate();
    }
    
    /**
     * Demuestra el uso de una variable efectivamente final
     */
    public static void demonstrateEffectivelyFinalVariable() {
        String location = "Texas";
        double taxRate = 0.0625;
        
        System.out.println("   Variables efectivamente finales:");
        System.out.println("   String location = \"" + location + "\";");
        System.out.println("   double taxRate = " + taxRate + ";");
        System.out.println("   (No se modifican después de la inicialización)");
        
        // Clase anidada local que accede a variables efectivamente finales
        class TaxCalculator {
            public void calculate() {
                System.out.println("   ✅ TaxCalculator puede acceder a location: " + location);
                System.out.println("   ✅ TaxCalculator puede acceder a taxRate: " + taxRate);
                System.out.println("   ✅ Calculando impuesto para " + location + " con tasa " + (taxRate * 100) + "%");
            }
        }
        
        TaxCalculator calculator = new TaxCalculator();
        calculator.calculate();
    }
    
    /**
     * Demuestra qué pasa con variables que no son finales
     */
    public static void demonstrateNonFinalVariable() {
        String location = "California";
        double taxRate = 0.0825;
        
        System.out.println("   Variables que NO son finales:");
        System.out.println("   String location = \"" + location + "\";");
        System.out.println("   double taxRate = " + taxRate + ";");
        
        // Si intentáramos crear una clase anidada local aquí que acceda a estas variables
        // después de modificarlas, causaría un error de compilación
        
        System.out.println("   ❌ Si modificáramos location o taxRate después de esto,");
        System.out.println("      una clase anidada local no podría acceder a ellas");
        System.out.println("   ❌ Error: Variable 'location' is accessed from within inner class,");
        System.out.println("      needs to be final or effectively final");
        
        // Comentado para evitar error de compilación:
        /*
        location = "Modified"; // Esto haría que location no sea efectivamente final
        
        class TaxCalculator {
            public void calculate() {
                System.out.println(location); // ❌ Error de compilación
            }
        }
        */
    }
    
    /**
     * Demuestra el uso de parámetros de método (que son efectivamente finales)
     */
    public static void demonstrateMethodParameters(String location, double taxRate) {
        System.out.println("   Parámetros de método (efectivamente finales):");
        System.out.println("   public static void demonstrateMethodParameters(String location, double taxRate)");
        System.out.println("   location = \"" + location + "\"");
        System.out.println("   taxRate = " + taxRate);
        
        // Clase anidada local que accede a parámetros de método
        class TaxCalculator {
            public void calculate() {
                System.out.println("   ✅ TaxCalculator puede acceder a location: " + location);
                System.out.println("   ✅ TaxCalculator puede acceder a taxRate: " + taxRate);
                System.out.println("   ✅ Los parámetros de método son efectivamente finales");
            }
        }
        
        TaxCalculator calculator = new TaxCalculator();
        calculator.calculate();
    }
} 